/**
 * 
 */
package game.ui;

import game.core.GamePreferences;
import game.menubar.MainMenuBar;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * Das Hauptfenster der Anwendung. <br>
 * 
 * @author Jan
 * 
 */
public class MainWindow extends JFrame {

	/** Die MenüLeiste */
	private JMenuBar				_menuBar;
	/** GamePreferences */
	private static GamePreferences	_gamePrefs;

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;

	public MainWindow() {
		super("Starfield - The Game");
		initWindow();
	}

	/**
	 * Setzt die Eigenschaften des Hauptfensters der Anwendung
	 */
	private void initWindow() {
		// SchließenButton des Hauptwindows schließt die Komplette
		// Applikation!!!
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Size sollte nicht hart angepasst werden, sondern über PrefferedSizes
		// von Unterobjekten
		setSize(new Dimension(800, 600));
		// GamePreferences laden
		initPreferences();
		// Setzen der Menüleiste
		_menuBar = new MainMenuBar();
		setJMenuBar(_menuBar);
		// Optionen für die Platzierung auf dem Bildschirm
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Lädt die GamePreferences
	 */
	private void initPreferences() {
		// ggf. Preferences aus altem Stand laden
		_gamePrefs = new GamePreferences();
	}

	@Override
	public JMenuBar getJMenuBar() {
		return _menuBar;
	}

	public static GamePreferences getGamePrefs() {
		return _gamePrefs;
	}
}
