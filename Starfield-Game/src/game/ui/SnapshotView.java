/**
 * 
 */
package game.ui;

import game.core.ImageResources;
import game.model.Field;
import game.model.Starfield;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * @author Jan
 * 
 *         Diese Klasse dient dazu, das Starfield offscreen darzustellen und
 *         ermöglicht das Generieren eines SnapShots für das Starfield
 */
public class SnapshotView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Das dargestellte Starfield */
	private final Starfield _starfield;

	/** Der öffentliche Constructor */
	public SnapshotView(Starfield starfield) {
		_starfield = starfield;
		setLayout(new GridBagLayout());
		fillView();
		// revalidate();
		repaint();
	}

	/**
	 * Liefert den Snapshot des dargestellten Starfields, ohne unnötige Ränder
	 * und komische Skalierungen
	 * 
	 * @return
	 */
	public Icon getSnapshot() {
		final JWindow window = new JWindow();
		window.getContentPane().add(this);
		window.pack();
		final BufferedImage image = paintNotVisibleComponent(window
				.getContentPane());
		window.dispose();
		Icon icon;
		if (image.getHeight() > image.getWidth())
			icon = new ImageIcon(image.getScaledInstance(-1, 150,
					Image.SCALE_SMOOTH));
		else
			icon = new ImageIcon(image.getScaledInstance(150, -1,
					Image.SCALE_SMOOTH));
		return icon;
	}

	/**
	 * Füllt das anzeigbare Starfield mit den Daten aus dem Modell
	 */
	private void fillView() {
		if (_starfield == null)
			return;
		final Dimension size = _starfield.getSize();
		// Fields des Modell abbilden
		for (int y = 0; y < size.getHeight(); y++) {
			final GridBagConstraints c = new GridBagConstraints();
			for (int x = 0; x < size.getWidth(); x++) {
				c.gridx = x + 1;
				c.gridy = y + 1;
				final Field field = _starfield.getField(x, y);
				field.setBorder(null);
				field.setIcon(ImageResources.getScaledIcon(32,
						field.getUserContent(), Image.SCALE_SMOOTH));
				add(field, c);
			}
		}
	}

	/**
	 * Diese Methode zeichnet ein Image mit den sichtbaren Elementen der
	 * übergebenen Komponente
	 * 
	 * @param c
	 *            die zu zeichnende Komponente
	 * @param con
	 *            ein Container - das zu zeichnende Rechteck
	 * @return ein Bild des Inhalts der Komponente
	 */
	private BufferedImage paintNotVisibleComponent(Component c) {
		BufferedImage img = new BufferedImage(c.getSize().width,
				c.getSize().height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		c.paint(g);
		g.dispose();
		return img;
	}
}
