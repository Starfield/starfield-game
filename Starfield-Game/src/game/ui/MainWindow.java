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

	/** Die Men�Leiste */
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
		// Schlie�enButton des Hauptwindows schlie�t die Komplette
		// Applikation!!!
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Size sollte nicht hart angepasst werden, sondern �ber PrefferedSizes
		// von Unterobjekten
		setSize(new Dimension(800, 600));
		// GamePreferences laden
		initPreferences();
		// Setzen der Men�leiste
		_menuBar = new MainMenuBar();
		setJMenuBar(_menuBar);
		// Optionen f�r die Platzierung auf dem Bildschirm
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * L�dt die GamePreferences
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
