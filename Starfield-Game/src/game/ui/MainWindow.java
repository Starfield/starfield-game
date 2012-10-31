/**
 * 
 */
package game.ui;

import game.core.GamePreferences;
import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.menubar.CloseApplicationAction;
import game.menubar.MainMenuBar;
import game.model.Starfield;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
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
	/** Die MenüLeiste */
	private JMenuBar				_menuBar;
	/** Toolbar */
	private JToolBar				_toolbar;
	/** StarfieldView */
	private StarfieldView			_starfieldView;

	// Unsichtbare Hilfmittel
	/** GamePreferences */
	private static GamePreferences	_gamePrefs;
	/** ConentPane */
	private JPanel					_contentPane;

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;

	public MainWindow() {
		super("Starfield - The Game");
		initWindow();
		// GamePreferences laden
		initPreferences();
		// MenuBar setzen
		initMenuBar();
		// Toolbar anzeigen
		initToolbar();
		// Starfield erzeugen
		initStarfieldView();
		// Optionen für die Platzierung auf dem Bildschirm
		pack();
		if (isAppSizeBiggerThanDisplay()) {
			setSize(new Dimension(800, 600));
			setExtendedState(MAXIMIZED_BOTH);
		}
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Setzt die Eigenschaften des Hauptfensters der Anwendung
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
		_gamePrefs = new GamePreferences();
	}

	private void initToolbar() {
		// Reste entfernen
		if (_toolbar != null)
			_contentPane.remove(_toolbar);
		// Neue Toolbar erzeugen und anzeigen
		_toolbar = new JToolBar("TestToolBar");
		_toolbar.add(new JButton(ImageResources.getIcon(Images.STAR)));
		_contentPane.add(_toolbar, BorderLayout.WEST);
	}

	private void initStarfieldView() {
		// Reste entfernen
		if (_starfieldView != null)
			_contentPane.remove(_starfieldView);
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
}
