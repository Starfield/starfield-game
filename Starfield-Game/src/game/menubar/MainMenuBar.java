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
				ImageResources.getIcon(Images.ICON_NEWGAME))));
		// Menüpunkt Spiel Laden
		_spiel.add(new JMenuItem(new LoadGameAction("Spiel Laden ...", ImageResources.getIcon(Images.ICON_NEWGAME))));
		// Menüpunkt Spiel Speichern
		_spiel.add(new JMenuItem(new SaveGameAction("Spiel Speichern ...", ImageResources.getIcon(Images.ICON_NEWGAME))));
		// Menüpunkt Beenden
		_spiel.addSeparator();
		_spiel.add(new JMenuItem(new CloseApplicationAction("Beenden",
				ImageResources.getIcon(Images.ICON_EXIT))));
		return _spiel;
	}
	
	private JMenu initEditor(){
		_editor = new JMenu("Editor");
		// Menüpunkt Puzzle erstellen
		_editor.add(new JMenuItem(new CreatePuzzleAction("Neues Puzzle erstellen ...", ImageResources.getIcon(Images.ICON_NEWGAME))));
		// Menüpunkt Spiel Laden
		_editor.add(new JMenuItem(new EditPuzzleAction("Puzzle Bearbeiten ...", ImageResources.getIcon(Images.ICON_NEWGAME))));
		// Menüpunkt Puzzle Speichern
		_editor.add(new JMenuItem(new SaveGameAction("Puzzle Speichern ...", ImageResources.getIcon(Images.ICON_NEWGAME))));
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
		_hilfe.add(new JMenuItem(new OpenAboutAction("Über",
				ImageResources.getIcon(Images.ICON_HELP))));
		return _hilfe;
	}
}
