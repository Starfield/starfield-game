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

	/* Men�elemente */
	/** Men�punkt Spiel */
	private JMenu _spiel;
	/** Men�punkt Editor */
	private JMenu _editor;
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

		/* Men�punkt Editor erstellen */
		this.add(initEditor());

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
				ImageResources.getScaledIcon(20, 20, Images.ICON_NEW_GAME,
						Image.SCALE_SMOOTH))));
		// Men�punkt Spiel Laden
		_spiel.add(new JMenuItem(new LoadGameAction("Spiel Laden ...",
				ImageResources.getScaledIcon(20, 20, Images.ICON_LOAD_GAME,
						Image.SCALE_SMOOTH))));
		// Men�punkt Spiel Speichern
		_spiel.add(new JMenuItem(new SaveGameAction("Spiel Speichern ...",
				ImageResources.getScaledIcon(20, 20, Images.ICON_SAVE_GAME,
						Image.SCALE_SMOOTH))));
		// Men�punkt Beenden
		_spiel.addSeparator();
		_spiel.add(new JMenuItem(new CloseApplicationAction("Beenden",
				ImageResources.getScaledIcon(20, 20, Images.ICON_CLOSE_GAME,
						Image.SCALE_SMOOTH))));
		return _spiel;
	}

	private JMenu initEditor() {
		_editor = new JMenu("Editor");
		// Men�punkt Puzzle erstellen
		_editor.add(new JMenuItem(new CreatePuzzleAction(
				"Neues Puzzle erstellen ...", ImageResources.getScaledIcon(20,
						20, Images.ICON_NEW_GAME, Image.SCALE_SMOOTH))));
		// Men�punkt Spiel Laden
		_editor.add(new JMenuItem(new LoadPuzzleAction("Puzzle Bearbeiten ...",
				ImageResources.getScaledIcon(20, 20, Images.ICON_LOAD_GAME,
						Image.SCALE_SMOOTH))));
		// Men�punkt Puzzle Speichern
		_editor.add(new JMenuItem(new SavePuzzleAction("Puzzle Speichern ...",
				ImageResources.getScaledIcon(20, 20, Images.ICON_SAVE_GAME,
						Image.SCALE_SMOOTH))));
		return _editor;

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
		// About
		_hilfe.addSeparator();
		_hilfe.add(new JMenuItem(new OpenAboutAction("�ber", ImageResources
				.getIcon(Images.ICON_HELP))));
		return _hilfe;
	}
}
