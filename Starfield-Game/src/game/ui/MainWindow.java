/**
 * 
 */
package game.ui;

import game.commands.CommandStack;
import game.core.GamePreferences;
import game.menubar.CloseApplicationAction;
import game.menubar.MainMenuBar;
import game.model.Starfield;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

/**
 * Das Hauptfenster der Anwendung. <br>
 * 
 * @author Jan
 * 
 */
public class MainWindow extends JFrame {

	// GameUI Elemente
	/** Die Men�Leiste */
	private JMenuBar _menuBar;
	/** Toolbar */
	private static JToolBar _toolbar;
	/** StarfieldView */
	private static StarfieldView _starfieldView;

	// Unsichtbare Hilfmittel
	/** GamePreferences */
	private static GamePreferences _gamePrefs;
	/** ConentPane */
	private JPanel _contentPane;
	/** Der CommandStack */
	private static CommandStack _commandStack;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainWindow() {
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
		// Toolbar anzeigen
		initToolbar();
		// Starfield und CommandStack erzeugen
		initStarfieldView();
		// Optionen f�r die Platzierung auf dem Bildschirm
		pack();
		if (isAppSizeBiggerThanDisplay()) {
			setSize(new Dimension(800, 600));
			setExtendedState(MAXIMIZED_BOTH);
		}
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
		// Size sollte nicht hart angepasst werden, sondern �ber PrefferedSizes
		// von Unterobjekten
		setSize(new Dimension(800, 600));
		// ContentPane erstellen und Layout festlegen
		_contentPane = new JPanel();
		_contentPane.setLayout(new BorderLayout(5, 5));
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
		_gamePrefs = new GamePreferences(this);
	}

	/**
	 * Liest aus den aktuellen {@link GamePreferences} aus, welche Toolbar
	 * gesetzt werden soll und f�gt diese dem UI hinzu.
	 */
	private void initToolbar() {
		// Reste entfernen
		if (_toolbar != null)
			_contentPane.remove(_toolbar);
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
		_contentPane.add(_toolbar, BorderLayout.LINE_START);
	}

	/**
	 * Initialisiert die Anzeige des Starfield. Wurde in den GamePreferences ein
	 * CommandStack geladen, so wird das zugeh�rige Starfield geladen und der
	 * alte Spielstand wiederhergestellt.
	 */
	private void initStarfieldView() {
		// Reste entfernen
		if (_starfieldView != null)
			_contentPane.remove(_starfieldView);
		// Anhand des AppMode entscheiden, ob ein neues leeres Starfield geladen
		// werden soll oder ein gespeichertes Spiel wiederhergestellt werden
		// soll.

		Starfield starfield = null;
		switch (getGamePrefs().getAppMode()) {
		case GAME_MODE:
			// Im GameMode wird nur der Startbildschirm angezeigt, darum
			// brauchen wir kein Starfield und k�nnen einen neuen leeren
			// Commandstack setzen
			starfield = getGamePrefs().getLoadedStarfield();
			getGamePrefs().removeLoadedStarfield();
			setCommandStack(new CommandStack(MainWindow.getGamePrefs().getStarfieldFile()));
			MainWindow.getGamePrefs().removeStarfieldFile();
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

	/**
	 * �berpr�ft ob die Gr��e nicht zu gro� f�r den Bildschirm ist.
	 */
	private boolean isAppSizeBiggerThanDisplay() {
		boolean answer = false;

		Dimension programSize = getSize();
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();

		if (programSize.getHeight() > displaySize.getHeight())
			answer = true;
		if (programSize.getWidth() > displaySize.getWidth())
			answer = true;
		return answer;
	}

	@Override
	public JMenuBar getJMenuBar() {
		return _menuBar;
	}

	public static GamePreferences getGamePrefs() {
		return _gamePrefs;
	}

	public static CommandStack getCommandStack() {
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
	public static StarfieldView getStarfieldView() {
		return _starfieldView;
	}

	/**
	 * Liefert die aktive Toolbar
	 * 
	 * @return - die Toolbar
	 */
	public static JToolBar getActiveToolBar() {
		return _toolbar;
	}
}
