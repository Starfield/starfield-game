/**
 * 
 */
package game.ui;

import game.model.Starfield;
import game.ui.handler.StarfieldViewHandler;

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
	private static final long serialVersionUID = 1L;

	/** Das Modell */
	private final Starfield _starfield;
	/** Der Handler für Aktionen */
	private final StarfieldViewHandler _handler = new StarfieldViewHandler();;

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
		// Fields des Modell abbilden
		for (int y = 0; y < size.getHeight(); y++) {
			for (int x = 0; x < size.getWidth(); x++) {
				GridBagConstraints c = new GridBagConstraints();
				c.gridx = x + 1;
				c.gridy = y + 1;
				game.model.Field field = _starfield.getField(x, y);
				field.addMouseListener(_handler);
				add(field, c);
			}
		}
		// Anzahl der Sterne auf der UI anzeigen
		// Oberer Rand
		for (int x = 0; x < size.getWidth(); x++) {
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = x + 1;
			c.gridy = 0;
			c.ipady = 5;
			add(new JLabel("" + _starfield.getStarCountX(x)), c);
		}
		// Linker Rand
		for (int y = 0; y < size.getHeight(); y++) {
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.ipadx = 5;
			c.gridy = y + 1;
			add(new JLabel("" + _starfield.getStarCountY(y)), c);
		}
	}
}
