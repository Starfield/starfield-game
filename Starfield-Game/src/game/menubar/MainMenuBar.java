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
		// Menüpunkt Beenden
		_spiel.addSeparator();
		_spiel.add(new JMenuItem(new CloseApplicationAction("Beenden",
				ImageResources.getIcon(Images.ICON_EXIT))));
		return _spiel;
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
		return _hilfe;
	}
}
