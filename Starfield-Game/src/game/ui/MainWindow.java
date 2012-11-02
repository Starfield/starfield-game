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
import javax.swing.WindowConstants;

/**
 * Das Hauptfenster der Anwendung. <br>
 * 
 * @author Jan
 * 
 */
public class MainWindow extends JFrame {

	// GameUI Elemente
	/** Die MenüLeiste */
	private JMenuBar _menuBar;
	/** Toolbar */
	private AbstractToolbar _toolbar;
	/** StarfieldView */
	private StarfieldView _starfieldView;

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

		initGame();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Folgende Schritte müssen vor jedem neuen Spiel erneut aufgerufen werden
	 */
	public void initGame() {
		// Toolbar anzeigen
		initToolbar();
		// CommandStack erzeugen
		initCommandStack();
		// Starfield erzeugen
		initStarfieldView();
		// Optionen für die Platzierung auf dem Bildschirm
		pack();
		if (isAppSizeBiggerThanDisplay()) {
			setSize(new Dimension(800, 600));
			setExtendedState(MAXIMIZED_BOTH);
		}
	}

	/**
	 * Setzt einmalig die Eigenschaften des Hauptfensters der Anwendung
	 */
	private void initWindow() {
		// Schließen-Button ruft die CloseAppliactionAction auf
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent pE) {
				// Nur der Schließen-Button soll überschrieben werden
				if (pE.getID() == WindowEvent.WINDOW_CLOSING)
					// Soll nur die CloseAction aufrufen, benötigt keine GUI
					// Anzeige
					new CloseApplicationAction(null, null)
							.actionPerformed(null);
			}
		});
		// Size sollte nicht hart angepasst werden, sondern über PrefferedSizes
		// von Unterobjekten
		setSize(new Dimension(800, 600));
		// ContentPane erstellen und Layout festlegen
		_contentPane = new JPanel();
		_contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(_contentPane);

	}

	/**
	 * Erstellt die MenuBar der Anwendung und fügt diese dem UI hinzu.
	 */
	private void initMenuBar() {
		// Setzen der Menüleiste
		_menuBar = new MainMenuBar();
		setJMenuBar(_menuBar);
	}

	/**
	 * Lädt die GamePreferences
	 */
	private void initPreferences() {
		// ggf. Preferences aus altem Stand laden
		_gamePrefs = new GamePreferences(this);
	}

	/**
	 * Liest aus den aktuellen {@link GamePreferences} aus, welche Toolbar
	 * gesetzt werden soll und fügt diese dem UI hinzu.
	 */
	private void initToolbar() {
		// Reste entfernen
		if (_toolbar != null)
			_contentPane.remove(_toolbar);
		// Anhand des AppMode entscheiden welche Toolbar gesetzt wird
		switch (getGamePrefs().getAppMode()) {
		case LOAD_GAME_MODE:
		case GAME_MODE:
			_toolbar = new PlayToolbar();
			break;
		case EDIT_MODE:
			_toolbar = new EditToolbar();
			break;
		}
		_contentPane.add(_toolbar, BorderLayout.WEST);
	}

	/**
	 * Lädt aus den {@link GamePreferences} den CommandStack. Wurde kein
	 * CommandStack geladen wird ein neuer erstellt.
	 */
	private void initCommandStack() {
		setCommandStack(getGamePrefs().getLoadedCommandStack());
	}

	/**
	 * Initialisiert die Anzeige des Starfield. Wurde in den GamePreferences ein
	 * CommandStack geladen, so wird das zugehörige Starfield geladen und der
	 * alte Spielstand wiederhergestellt.
	 */
	private void initStarfieldView() {
		// Reste entfernen
		if (_starfieldView != null)
			_contentPane.remove(_starfieldView);
		// Anhand des AppMode entscheiden, ob ein neues leeres Starfield geladen
		// werden soll, der EditorDialog erscheinen soll, oder ein gespeichertes
		// Spiel wiederhergestellt werden soll.
		// Neues Starfield erzeugen und anzeigen
		_starfieldView = new StarfieldView(new Starfield(10, 10));
		_contentPane.add(_starfieldView, BorderLayout.CENTER);
	}

	/**
	 * Überprüft ob die Größe nicht zu groß für den Bildschirm ist.
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

}
