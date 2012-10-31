/**
 * 
 */
package game.ui;

import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.model.Starfield;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

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
		for (ArrayList<game.model.Field> row : _starfield.getListcontainer())
			for (game.model.Field cell : row)
				if (cell.getUserContent() == null) {
					GridBagConstraints c = new GridBagConstraints();
					c.gridx = cell.getxPos();
					c.gridy = cell.getyPos();
					c.fill = GridBagConstraints.BOTH;
					add(new JLabel(ImageResources.getIcon(Images.CONTENT_EMPTY)),
							c);
				}
	}
}
