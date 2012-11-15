/**
 * 
 */
package game.ui;

import game.commands.CommandStack;
import game.core.GamePreferences;
import game.core.GamePreferences.AppMode;
import game.core.GamePreferences.Resolution;
import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.menubar.CloseApplicationAction;
import game.menubar.MainMenuBar;
import game.model.Starfield;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

/**
 * Das Hauptfenster der Anwendung. <br>
 * Wird als Singleton behandelt, um Zugriff auf die einzelnen Elemente zu
 * gew�hren.
 * 
 * @author Jan
 * 
 */
public class MainWindow extends JFrame {

	// Singleton:
	private static MainWindow _mainWindow;
	// GameUI Elemente
	/** Die Men�Leiste */
	private JMenuBar _menuBar;
	/** Toolbar */
	private JToolBar _toolbar;
	/** StarfieldView */
	private StarfieldView _starfieldView;

	// Unsichtbare Hilfmittel
	/** GamePreferences */
	private GamePreferences _gamePrefs;
	/** ConentPane */
	private JPanel _contentPane;
	/** Der CommandStack */
	private CommandStack _commandStack;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainWindow() {
		super("Starfield - The Game");
		// **************************************************
		// Diese Schritte werden nur einmal durchlaufen beim
		// ersten Start der Applikation
		initWindow();
		// GamePreferences laden
		initPreferences();
		// MenuBar setzen
		initMenuBar();
		// austauschbare GUI-Elemente erstellen
		initGame();
		setVisible(true);
	}

	/**
	 * Folgende Schritte m�ssen vor jedem neuen Spiel erneut aufgerufen werden
	 */
	public void initGame() {
		// alte Elemente entfernen
		_contentPane.removeAll();
		// Toolbar anzeigen
		initToolbar();
		// Starfield und CommandStack erzeugen
		initStarfieldView();
		// Optionen f�r die Platzierung auf dem Bildschirm
		renderElements();

	}

	/**
	 * Rendert das Starfield anhand der eingestellten Optionen
	 */
	public void renderElements() {

		// eingestellte Aufl�sung auslesen
		Resolution res = _gamePrefs.getResolution();

		// Aufl�sung des Window �ndern
		setSize(res.getWidth(), res.getHeight());

		if (_gamePrefs.getAppMode() == AppMode.FIRST_START) {
			_contentPane.setBackground(new Color(000066));
			setLocationRelativeTo(null);
			return;
		}
		// Alte Farbe wiederherstellen
		if (_contentPane.getBackground() != new Color(238, 238, 238))
			_contentPane.setBackground(new Color(238, 238, 238));

		// CalcWidth wird anhand der Breite des Fenster berechnet
		int allowedWidth = (res.getWidth() - 6)
				- _toolbar.getPreferredSize().width;
		int calcWidth = (allowedWidth / getCurrentStarfield().getSize().width) - 4;

		// CalcHeight wird anhand der H�he des Fensters berechnet
		int allowedHeight = res.getHeight() - _menuBar.getHeight();
		int calcHeight = (allowedHeight)
				/ getCurrentStarfield().getSize().height - 14;

		// Im Game oder Load_Game Mode muss die Breite der angezeigten Zahlen
		// noch abgezogen werden.
		switch (_gamePrefs.getAppMode()) {
		case GAME_MODE:
		case LOAD_GAME_MODE:
			calcWidth -= 15;
			calcHeight -= 15;
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

		// errechnete Bildgr��e festlegen
		ImageResources.setScalingSize(calcSize);
		// repaint der Elemente veranlassen
		_mainWindow.validate();
		// das Window wieder mittig platzieren
		setLocationRelativeTo(null);
	}

	/**
	 * Setzt einmalig die Eigenschaften des Hauptfensters der Anwendung
	 */
	private void initWindow() {
		// Schlie�en-Button ruft die CloseAppliactionAction auf
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent pE) {
				// Nur der Schlie�en-Button soll �berschrieben werden
				if (pE.getID() == WindowEvent.WINDOW_CLOSING)
					// Soll nur die CloseAction aufrufen, ben�tigt keine GUI
					// Anzeige
					new CloseApplicationAction(null, null)
							.actionPerformed(null);
			}
		});
		setResizable(false);
		// ContentPane erstellen und Layout festlegen
		_contentPane = new JPanel();
		_contentPane.setLayout(new BorderLayout(0, 5));
		setContentPane(_contentPane);

	}

	/**
	 * Erstellt die MenuBar der Anwendung und f�gt diese dem UI hinzu.
	 */
	private void initMenuBar() {
		// Setzen der Men�leiste
		_menuBar = new MainMenuBar();
		setJMenuBar(_menuBar);
	}

	/**
	 * L�dt die GamePreferences
	 */
	private void initPreferences() {
		// ggf. Preferences aus altem Stand laden
		_gamePrefs = new GamePreferences();
	}

	/**
	 * Liest aus den aktuellen {@link GamePreferences} aus, welche Toolbar
	 * gesetzt werden soll und f�gt diese dem UI hinzu.
	 */
	private void initToolbar() {
		// Anhand des AppMode entscheiden welche Toolbar gesetzt wird
		switch (getGamePrefs().getAppMode()) {
		case GAME_MODE:
		case LOAD_GAME_MODE:
			_toolbar = new PlayToolbar();
			break;
		case EDIT_MODE:
		case LOAD_EDIT_MODE:
			_toolbar = new EditToolbar();
			break;
		}

		if (getGamePrefs().getAppMode() != AppMode.FIRST_START) {
			_contentPane.add(_toolbar, BorderLayout.LINE_START);
		}
	}

	/**
	 * Initialisiert die Anzeige des Starfield. Wurde in den GamePreferences ein
	 * CommandStack geladen, so wird das zugeh�rige Starfield geladen und der
	 * alte Spielstand wiederhergestellt.
	 */
	private void initStarfieldView() {
		// Anhand des AppMode entscheiden, ob ein neues leeres Starfield geladen
		// werden soll oder ein gespeichertes Spiel wiederhergestellt werden
		// soll.

		Starfield starfield = null;
		switch (getGamePrefs().getAppMode()) {
		case FIRST_START:
			_contentPane.add(
					new JLabel(ImageResources.getIcon(Images.SPLASHSCREEN)),
					BorderLayout.CENTER);
			return;
		case GAME_MODE:
			// Im GameMode wird nur der Startbildschirm angezeigt, darum
			// brauchen wir kein Starfield und k�nnen einen neuen leeren
			// Commandstack setzen
			starfield = getGamePrefs().getLoadedStarfield();
			getGamePrefs().removeLoadedStarfield();
			setCommandStack(new CommandStack(MainWindow.getInstance()
					.getGamePrefs().getStarfieldFile()));
			MainWindow.getInstance().getGamePrefs().removeStarfieldFile();
			break;
		case LOAD_GAME_MODE:
			// Im LoadGameMode sind durch die Actions im Vorhinein die
			// anzuzeigenden Elemente gesetzt worden
			starfield = getGamePrefs().getLoadedStarfield();
			setCommandStack(getGamePrefs().getLoadedCommandStack());
		case LOAD_EDIT_MODE:
			starfield = getGamePrefs().getLoadedStarfield();
			getGamePrefs().removeLoadedStarfield();
			setCommandStack(new CommandStack());
			break;
		case EDIT_MODE:
			starfield = new Starfield(5, 5);
			setCommandStack(new CommandStack());
			break;
		}
		_starfieldView = new StarfieldView(starfield);
		_contentPane.add(_starfieldView, BorderLayout.CENTER);

	}

	public GamePreferences getGamePrefs() {
		return _gamePrefs;
	}

	public CommandStack getCommandStack() {
		return _commandStack;
	}

	private void setCommandStack(CommandStack pCommandStack) {
		_commandStack = pCommandStack;
	}

	/**
	 * Liefert die aktuell angezeigte StarfieldView
	 * 
	 * @return - die StarfieldView
	 */
	public Starfield getCurrentStarfield() {
		return _starfieldView.getCurrentStarfield();
	}

	/**
	 * Liefert die aktive Toolbar
	 * 
	 * @return - die Toolbar
	 */
	public JToolBar getActiveToolBar() {
		return _toolbar;
	}

	/**
	 * Liefert die Singleton-Instanz des MainWindow
	 * 
	 * @return
	 */
	public static synchronized MainWindow getInstance() {
		if (_mainWindow == null)
			_mainWindow = new MainWindow();
		return _mainWindow;
	}
}
