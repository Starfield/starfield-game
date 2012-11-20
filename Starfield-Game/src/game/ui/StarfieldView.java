/**
 * 
 */
package game.ui;

import game.core.ImageResources;
import game.model.Field;
import game.model.Starfield;
import game.ui.handler.StarfieldViewHandler;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;

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

	private GridBagLayout _layout;

	public StarfieldView(final Starfield pStarfield) {
		super(_content);
		setBorder(null);
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
		_layout = new GridBagLayout();
		_content.setLayout(_layout);

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
			c.anchor = GridBagConstraints.ABOVE_BASELINE;
			for (int x = 0; x < size.getWidth(); x++) {
				c.gridx = x + 1;
				c.gridy = y + 1;
				final game.model.Field field = _starfield.getField(x, y);
				// nach fünf spalten soll eine Linie kommen
				// if (((x + 1) % 5) == 0) {
				// c.ipadx = 4;
				// } else {
				// c.ipadx = 0;
				// }
				// // nach fünf reihen soll eine Linie kommen
				// if (((y + 1) % 5) == 0) {
				// c.ipady = 4;
				// } else {
				// c.ipady = 0;
				// }
				if (!checkIfListenersRegistered(field))
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
		default:
			break;
		}
	}

	/**
	 * @param size
	 */
	private void createHintBorder(final Dimension size) {
		// Anzahl der Sterne auf der UI anzeigen
		// Oberer Rand
		for (int x = 0; x < size.getWidth(); x++) {
			final GridBagConstraints c = new GridBagConstraints();
			c.gridx = x + 1;
			c.gridy = 0;
			c.ipady = 5;
			final JLabel label = new JLabel("<html><font size='5'>"
					+ _starfield.getStarCountX(x) + "</font></html>");
			_content.add(label, c);
		}
		// Linker Rand
		for (int y = 0; y < size.getHeight(); y++) {
			final GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.ipadx = 5;
			c.gridy = y + 1;
			final JLabel label = new JLabel("<html><font size='5'>"
					+ _starfield.getStarCountY(y) + "</font></html>");
			_content.add(label, c);
		}
	}

	/**
	 * Diese Methode überprüft, ob auf diesem Feld bereits ein MouseListener
	 * registriert ist.
	 * 
	 * @param pField
	 *            - Das Feld
	 * @return <code>true</code> wenn mindestens ein Listener gefunden,
	 *         ansonsten <code>false</code>
	 */
	private boolean checkIfListenersRegistered(final Field pField) {
		boolean registered = false;
		final MouseListener[] listeners = pField.getMouseListeners();
		if (listeners.length > 0)
			registered = true;

		return registered;
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
			// CalcWidth wird anhand der Breite des Fenster berechnet
			final int allowedWidth = MainWindow.getInstance().getSize().width
					- MainWindow.getInstance().getActiveToolBar()
							.getPreferredSize().width;
			int calcWidth = allowedWidth / _starfield.getSize().width - 8;

			// CalcHeight wird anhand der Höhe des Fensters berechnet
			final int allowedHeight = MainWindow.getInstance().getSize().height
					- MainWindow.getInstance().getJMenuBar().getHeight();
			int calcHeight = allowedHeight / _starfield.getSize().height - 4;

			// Im Game oder Load_Game Mode muss die Breite der angezeigten
			// Zahlen
			// noch abgezogen werden.
			switch (MainWindow.getInstance().getGamePrefs().getAppMode()) {
			case GAME_MODE:
			case LOAD_GAME_MODE:
				calcWidth -= 15;
				calcHeight -= 15;
				break;
			default:
				break;
			}
			// Mit dem kleineren der beiden Werte wird weitergerechnet
			int calcSize = calcWidth;
			if (calcHeight < calcWidth)
				calcSize = calcHeight;

			// Obergrenze festlegen
			if (calcSize > ImageResources.getMaxSize())
				calcSize = ImageResources.getMaxSize();
			// Untergrenze festlegen
			if (calcSize < ImageResources.getMinSize())
				calcSize = ImageResources.getMinSize();

			// errechnete Bildgröße festlegen
			ImageResources.setScalingSize(calcSize);

			// Komplettes Feld durchlaufen und Icons neu setzen
			for (int x = 0; x < _starfield.getSize().getWidth(); x++) {
				for (int y = 0; y < _starfield.getSize().getHeight(); y++) {
					_starfield.getField(x, y).setUserContent(
							_starfield.getField(x, y).getUserContent());
				}
			}

			// Nach Änderungen wieder validieren
			super.revalidate();
		}

		super.repaint();
	}
}
