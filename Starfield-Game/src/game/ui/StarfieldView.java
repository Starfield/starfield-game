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
import javax.swing.JScrollPane;

/**
 * Das eigentliche Spielfeld <br>
 * 
 * @author schroeder_jan
 * 
 */
public class StarfieldView extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Das Modell */
	private final Starfield _starfield;
	/** Der Handler für Aktionen */
	private final StarfieldViewHandler _handler = new StarfieldViewHandler();
	/** ContentPanel */
	private static JPanel _content = new JPanel();

	public StarfieldView(Starfield pStarfield) {
		super(_content);
		_content.removeAll();
		_starfield = pStarfield;
		if (_starfield != null) {
			initWindowsPrefs();
			fillView();
		}
	}

	/**
	 * Setzt die allgemeingültigen Einstellungen des JPanels wie <br>
	 * <li>PrefferedSize</li> <li>Layout</li> <li>...</li>
	 */
	private void initWindowsPrefs() {
		GridBagLayout gridLayout = new GridBagLayout();
		_content.setLayout(gridLayout);

	}

	/**
	 * Füllt das anzeigbare Starfield mit den Daten aus dem Modell
	 */
	private void fillView() {
		if (_starfield == null)
			return;

		Dimension size = _starfield.getSize();
		// Fields des Modell abbilden
		for (int y = 0; y < size.getHeight(); y++) {
			for (int x = 0; x < size.getWidth(); x++) {
				GridBagConstraints c = new GridBagConstraints();
				c.gridx = x + 1;
				c.gridy = y + 1;
				game.model.Field field = _starfield.getField(x, y);
				field.addMouseListener(_handler);
				_content.add(field, c);
			}
		}

		// Rand nur setzen, wenn im Game oder Load_Game Mode
		switch (MainWindow.getInstance().getGamePrefs().getAppMode()) {
		case LOAD_GAME_MODE:
		case GAME_MODE:
			createHintBorder(size);
			break;
		}
	}

	/**
	 * @param size
	 */
	private void createHintBorder(Dimension size) {
		// Anzahl der Sterne auf der UI anzeigen
		// Oberer Rand
		for (int x = 0; x < size.getWidth(); x++) {
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = x + 1;
			c.gridy = 0;
			c.ipady = 5;
			JLabel label = new JLabel("<html><font size='5'>" + _starfield.getStarCountX(x)+ "</font></html>");
			_content.add(label, c);
		}
		// Linker Rand
		for (int y = 0; y < size.getHeight(); y++) {
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.ipadx = 5;
			c.gridy = y + 1;
			JLabel label = new JLabel("<html><font size='5'>" + _starfield.getStarCountY(y)+ "</font></html>");
			_content.add(label, c);
		}
	}

	/**
	 * Liefert das aktuell angezeigte {@link Starfield}
	 * 
	 * @return das Starfield
	 */
	public Starfield getCurrentStarfield() {
		return _starfield;
	}

	@Override
	public void repaint() {
		if (_starfield != null) {
			// Komplettes Feld durchlaufen und Icons neu setzen
			for (int x = 0; x < _starfield.getSize().getWidth(); x++) {
				for (int y = 0; y < _starfield.getSize().getHeight(); y++) {
					_starfield.getField(x, y).setUserContent(
							_starfield.getField(x, y).getUserContent());
				}
			}
		}
		super.revalidate();
		super.repaint();
	}
}
