/**
 * 
 */
package game.menubar;

import game.core.ImageResources;
import game.core.ImageResources.Images;

import java.awt.Image;

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
				ImageResources.getScaledIcon(20, 20, Images.ICON_NEW_GAME,
						Image.SCALE_SMOOTH))));
		// Menüpunkt Spiel Laden
		_spiel.add(new JMenuItem(new LoadGameAction("Spiel Laden ...",
				ImageResources.getScaledIcon(20, 20, Images.ICON_LOAD_GAME,
						Image.SCALE_SMOOTH))));
		// Menüpunkt Spiel Speichern
		_spiel.add(new JMenuItem(new SaveGameAction("Spiel Speichern ...",
				ImageResources.getScaledIcon(20, 20, Images.ICON_SAVE_GAME,
						Image.SCALE_SMOOTH))));
		// Menüpunkt Beenden
		_spiel.addSeparator();
		_spiel.add(new JMenuItem(new CloseApplicationAction("Beenden",
				ImageResources.getScaledIcon(20, 20, Images.ICON_CLOSE_GAME,
						Image.SCALE_SMOOTH))));
		return _spiel;
	}

	private JMenu initEditor() {
		_editor = new JMenu("Editor");
		// Menüpunkt Puzzle erstellen
		_editor.add(new JMenuItem(new CreatePuzzleAction(
				"Neues Puzzle erstellen ...", ImageResources.getScaledIcon(20,
						20, Images.ICON_NEW_GAME, Image.SCALE_SMOOTH))));
		// Menüpunkt Spiel Laden
		_editor.add(new JMenuItem(new LoadPuzzleAction("Puzzle Bearbeiten ...",
				ImageResources.getScaledIcon(20, 20, Images.ICON_LOAD_GAME,
						Image.SCALE_SMOOTH))));
		// Menüpunkt Puzzle Speichern
		_editor.add(new JMenuItem(new SavePuzzleAction("Puzzle Speichern ...",
				ImageResources.getScaledIcon(20, 20, Images.ICON_SAVE_GAME,
						Image.SCALE_SMOOTH))));
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
