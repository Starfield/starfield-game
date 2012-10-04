/**
 * 
 */
package game.window;

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
	JMenuBar _menuBar;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		// Setzen der Menüleiste
		_menuBar = new MainMenuBar();
		setJMenuBar(_menuBar);
		// Optionen für die Platzierung auf dem Bildschirm
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
