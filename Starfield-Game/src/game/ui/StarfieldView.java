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

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

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
	private final StarfieldViewHandler _handler;
	/** ContentPanel */
	private static JPanel _content;

	private GridBagLayout _layout;

	/** Size der HintBorder */
	private Dimension _hintBorderSize = new Dimension(0, 0);

	public StarfieldView(final Starfield pStarfield) {
		super(_content = new JPanel());
		setBorder(null);
		_content.removeAll();
		_starfield = pStarfield;
		_handler = new StarfieldViewHandler();
		if (_starfield != null) {
			initWindowsPrefs();
			fillView();
		}
	}

	/**
	 * Setzt die allgemeingültigen Einstellungen des JPanels wie <br>
	 * <li>Layout</li> <li>...</li>
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
			c.anchor = GridBagConstraints.NORTHWEST;
			for (int x = 0; x < size.getWidth(); x++) {
				c.gridx = x + 1;
				c.gridy = y + 1;
				final game.model.Field field = _starfield.getField(x, y);
				if (!listenersRegistered(field))
					// for (MouseListener ml : field.getMouseListeners())
					field.addMouseListener(_handler);
				_content.add(field, c);

			}

		}

		// Rand nur setzen, wenn im Game oder Load_Game Mode
		switch (MainWindow.getInstance().getGamePrefs().getAppMode()) {
		case LOAD_GAME_MODE:
		case GAME_MODE:
		case REPLAY_MODE:
			createHintBorder(size);
			break;
		default:
			break;
		}
	}

	/**
	 * Erstellt am Rand der Anzeige die Labels auf denen die Anzahl der Sterne
	 * dargestellt werden
	 * 
	 * @param size
	 *            - die Größe des Spielfelds
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
			_hintBorderSize = label.getPreferredSize();
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
	private boolean listenersRegistered(final Field pField) {
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
			int allowedWidth = MainWindow.getInstance().getContentPane()
					.getSize().width;
			int allowedHeight = MainWindow.getInstance().getContentPane()
					.getSize().height;

			// Größe der Toolbar abziehen
			JToolBar toolbar = MainWindow.getInstance().getActiveToolBar();
			if (toolbar != null) {
				allowedWidth -= toolbar.getWidth();
			}

			// Größe der JMenuBar abziehen
			JMenuBar menubar = MainWindow.getInstance().getJMenuBar();
			if (menubar != null) {
				allowedHeight -= menubar.getHeight();
			}

			// Größe der Statusbar abziehen
			StatusBar statusbar = MainWindow.getInstance().getStatusBar();
			if (statusbar != null) {
				allowedHeight -= statusbar.getHeight();
			}

			// Dicke der Border abziehen
			allowedHeight -= _starfield.getSize().height * 2;
			allowedWidth -= _starfield.getSize().width * 2;

			// HintBorder abziehen
			allowedHeight -= _hintBorderSize.height;
			allowedWidth -= _hintBorderSize.width;

			// Einzelne Kantenlänge berechnen
			int calcWidth = allowedWidth / _starfield.getSize().width;
			int calcHeight = allowedHeight / _starfield.getSize().height;

			// Im Game oder Load_Game Mode muss die Breite der angezeigten
			// Zahlen
			// noch abgezogen werden.
			switch (MainWindow.getInstance().getGamePrefs().getAppMode()) {
			case GAME_MODE:
			case LOAD_GAME_MODE:
			case REPLAY_MODE:
				calcWidth -= 4;
				calcHeight -= 4;
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

			// Nach Änderungen revalidieren
			super.revalidate();
		}

		super.repaint();
	}

	public JComponent getContent() {
		return _content;
	}
}
