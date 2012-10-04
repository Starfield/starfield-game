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

	/* Men�elemente */
	/** Men�punkt Spiel */
	private JMenu _spiel;
	/** Men�punkt Hilfe */
	private JMenu _hilfe;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor f�r das Men�
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
		/* Men�punkt Spiel erstellen */
		this.add(initSpiel());

		/* Men�punkt Hilfe erstellen */
		this.add(initHilfe());
	}

	/**
	 * Erzeugt das Untermen� "Spiel"
	 * 
	 * @return
	 */
	private JMenu initSpiel() {
		_spiel = new JMenu("Spiel");
		// Men�punkt Neues Spiel
		_spiel.add(new JMenuItem(new NewGameAction("Neues Spiel ...",
				ImageResources.getIcon(Images.ICON_NEWGAME))));
		// Men�punkt Beenden
		_spiel.addSeparator();
		_spiel.add(new JMenuItem(new CloseApplicationAction("Beenden",
				ImageResources.getIcon(Images.ICON_EXIT))));
		return _spiel;
	}

	/**
	 * Erzeugt das Untermen� "Hilfe"
	 * 
	 * @return
	 */
	private JMenu initHilfe() {
		_hilfe = new JMenu("Hilfe");
		// Hilfe hinzuf�gen
		_hilfe.add(new JMenuItem(new OpenHelpAction("Hilfe �ffnen ...",
				ImageResources.getIcon(Images.ICON_HELP))));
		return _hilfe;
	}
}
