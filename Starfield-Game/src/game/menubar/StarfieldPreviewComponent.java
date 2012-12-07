/**
 * 
 */
package game.menubar;

import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.model.Starfield;
import game.ui.StarfieldView;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

/**
 * @author Jan
 * 
 */
public class StarfieldPreviewComponent extends JPanel implements
		PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JLabel _content;
	private File _file;
	private Icon _preview;

	public StarfieldPreviewComponent() {
		_content = new JLabel();
		_content.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		setLayout(new GridBagLayout());
		add(_content, new GridBagConstraints());
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		boolean update = false;
		String prop = event.getPropertyName();

		if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
			_file = (File) event.getNewValue();
			update = true;
		}

		// Update the preview accordingly.
		if (update) {
			_preview = null;
			if (isShowing()) {
				loadImage();
				repaint();
			}
		} else
			handleIncompatibleFileError();
	}

	private void loadImage() {
		if (_file != null) {
			ObjectInputStream ois = null;
			try {
				FileInputStream fis = new FileInputStream(_file);
				ois = new ObjectInputStream(fis);
				Object o = ois.readObject();
				if (o instanceof Starfield) {
					((Starfield) o).clearUserContent().prepareUserContent(true);
					StarfieldView view = new StarfieldView(((Starfield) o));
					view.repaint();
					Rectangle rect = new Rectangle(view.getContent()
							.getPreferredSize().width, view.getContent()
							.getPreferredSize().height);
					BufferedImage image = paintNotVisibleComponent(
							view.getContent(), new Container(), rect);
					if (rect.getHeight() > rect.getWidth())
						_preview = new ImageIcon(image.getScaledInstance(-1,
								150, Image.SCALE_SMOOTH));
					else
						_preview = new ImageIcon(image.getScaledInstance(150,
								-1, Image.SCALE_SMOOTH));
					if (_preview != null)
						_content.setIcon(_preview);

				} else
					handleIncompatibleFileError();
			} catch (FileNotFoundException e) {
				handleIncompatibleFileError();
			} catch (IOException e) {
				handleIncompatibleFileError();
			} catch (ClassNotFoundException e) {
				handleIncompatibleFileError();
			} finally {
				try {
					if (ois != null)
						ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	private BufferedImage paintNotVisibleComponent(Component c, Container con,
			Rectangle rect) {
		BufferedImage img = new BufferedImage(rect.width, rect.height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		SwingUtilities.paintComponent(g, c, con, rect);
		// c.paint(g);
		g.dispose();
		return img;
	}

	private void handleIncompatibleFileError() {
		_preview = ImageResources.getScaledIcon(150, Images.ICON_NO_PREVIEW,
				Image.SCALE_SMOOTH);
		_content.setIcon(_preview);
	}
}
