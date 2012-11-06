/**
 * 
 */
package game.menubar;

import game.core.ImageResources;
import game.core.ImageResources.Images;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Die MenuBar des MainWindows.
 * 
 * @author Jan
 * 
 */
public class MainMenuBar extends JMenuBar {

	/* Menüelemente */
	/** Menüpunkt Spiel */
	private JMenu _spiel;
	/** Menüpunkt Editor */
	private JMenu _editor;
	/** Menüpunkt Hilfe */
	private JMenu _hilfe;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor für das Menü
	 */
	public MainMenuBar() {
		super();
		initMenu();

	}

	/**
	 * Initialisiert die MenuBar
	 * 
	 */
	private void initMenu() {
		/* Menüpunkt Spiel erstellen */
		this.add(initSpiel());

		/* Menüpunkt Editor erstellen */
		this.add(initEditor());

		/* Menüpunkt Hilfe erstellen */
		this.add(initHilfe());
	}

	/**
	 * Erzeugt das Untermenü "Spiel"
	 * 
	 * @return
	 */
	private JMenu initSpiel() {
		_spiel = new JMenu("Spiel");
		// Menüpunkt Neues Spiel
		_spiel.add(new JMenuItem(new NewGameAction("Neues Spiel ...",
				ImageResources.getIcon(Images.ICON_NEW_GAME))));
		// Menüpunkt Spiel Laden
		_spiel.add(new JMenuItem(new LoadGameAction("Spiel Laden ...",
				ImageResources.getIcon(Images.ICON_LOAD_GAME))));
		// Menüpunkt Spiel Speichern
		_spiel.add(new JMenuItem(new SaveGameAction("Spiel Speichern ...",
				ImageResources.getIcon(Images.ICON_SAVE_GAME))));
		// Menüpunkt Beenden
		_spiel.addSeparator();
		_spiel.add(new JMenuItem(new CloseApplicationAction("Beenden",
				ImageResources.getIcon(Images.ICON_CLOSE_GAME))));
		return _spiel;
	}

	private JMenu initEditor() {
		_editor = new JMenu("Editor");
		// Menüpunkt Puzzle erstellen
		_editor.add(new JMenuItem(new CreatePuzzleAction(
				"Neues Puzzle erstellen ...", ImageResources
						.getIcon(Images.ICON_NEW_GAME))));
		// Menüpunkt Spiel Laden
		_editor.add(new JMenuItem(new LoadPuzzleAction("Puzzle Bearbeiten ...",
				ImageResources.getIcon(Images.ICON_LOAD_GAME))));
		// Menüpunkt Puzzle Speichern
		_editor.add(new JMenuItem(new SavePuzzleAction("Puzzle Speichern ...",
				ImageResources.getIcon(Images.ICON_SAVE_GAME))));
		return _editor;

	}

	/**
	 * Erzeugt das Untermenü "Hilfe"
	 * 
	 * @return
	 */
	private JMenu initHilfe() {
		_hilfe = new JMenu("Hilfe");
		// Hilfe hinzufügen
		_hilfe.add(new JMenuItem(new OpenHelpAction("Hilfe öffnen ...",
				ImageResources.getIcon(Images.ICON_HELP))));
		// About
		_hilfe.addSeparator();
		_hilfe.add(new JMenuItem(new OpenAboutAction("Über", ImageResources
				.getIcon(Images.ICON_HELP))));
		return _hilfe;
	}
}
