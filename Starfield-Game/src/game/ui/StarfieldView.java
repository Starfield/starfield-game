/**
 * 
 */
package game.ui;

import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.model.Starfield;

import java.awt.GridLayout;
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
		GridLayout gridLayout = new GridLayout(_starfield.getSize().height,
				_starfield.getSize().width);
		gridLayout.setHgap(5);
		gridLayout.setVgap(5);
		setLayout(gridLayout);
	}

	private void fillView() {
		for (ArrayList<game.model.Field> row : _starfield.getListcontainer())
			for (game.model.Field cell : row)
				if (cell.getUserContent() == null)
					add(new JLabel(ImageResources.getIcon(Images.CONTENT_EMPTY)));
	}
}
