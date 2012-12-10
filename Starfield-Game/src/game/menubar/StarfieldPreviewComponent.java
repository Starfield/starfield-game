/**
 * 
 */
package game.menubar;

import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.model.Starfield;
import game.ui.SnapshotView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

		// Bei Selektion einer Datei wird das File Objekt ausgelesen
		if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
			_file = (File) event.getNewValue();
			update = true;
		}

		// Stößt ein Update des PreviewImages an
		if (update) {
			_preview = null;
			if (isShowing()) {
				loadImage();
				repaint();
			}
		} else
			handleIncompatibleFileError();
	}

	/**
	 * Diese Methode erstellt anhand des selektierten Files einen Snapshot des
	 * Puzzles und stellt diesen in der Vorschauansicht dar.
	 */
	private void loadImage() {
		if (_file != null) {
			ObjectInputStream ois = null;
			try {
				FileInputStream fis = new FileInputStream(_file);
				ois = new ObjectInputStream(fis);
				Object o = ois.readObject();
				if (o instanceof Starfield) {
					final SnapshotView snapshot = new SnapshotView(
							((Starfield) o).clearUserContent()
									.prepareUserContent(true));
					_preview = snapshot.getSnapshot();
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

	/**
	 * Diese Methode setzt ein StandardPreview Image, falls keine Datei oder
	 * eine ungültige Datei ausgewählt ist
	 */
	private void handleIncompatibleFileError() {
		_preview = ImageResources.getScaledIcon(150, Images.ICON_NO_PREVIEW,
				Image.SCALE_SMOOTH);
		_content.setIcon(_preview);
	}
}
