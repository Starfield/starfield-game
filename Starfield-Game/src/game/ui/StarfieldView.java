/**
 * 
 */
package game.ui;

import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.model.Starfield;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Das eigentliche Spielfeld <br>
 * 
 * @author schroeder_jan
 * 
 */
public class StarfieldView extends JPanel {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private final Starfield		_starfield;

	public StarfieldView(Starfield pStarfield) {
		_starfield = pStarfield;
		initWindowsPrefs();
		fillView();
	}

	/**
	 * Setzt die allgemeingültigen Einstellungen des JPanels wie <br>
	 * <li>PrefferedSize</li> <li>Layout</li> <li>...</li>
	 */
	private void initWindowsPrefs() {
		GridBagLayout gridLayout = new GridBagLayout();
		setLayout(gridLayout);

	}

	private void fillView() {
		Dimension size = _starfield.getSize();
		for (int y = 0; y < size.getHeight(); y++) {
			for (int x = 0; x < size.getWidth(); x++) {
				GridBagConstraints c = new GridBagConstraints();
				c.gridx = x;
				c.gridy = y;
				add(new JLabel(ImageResources.getIcon(Images.CONTENT_EMPTY)), c);
			}
		}
	}
}
