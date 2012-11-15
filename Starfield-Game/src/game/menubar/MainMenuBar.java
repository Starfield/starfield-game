/**
 * 
 */
package game.menubar;

import game.core.GamePreferences;
import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.menubar.handler.MainMenuBarHandler;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

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
	/** Men�punkt Optionen */
	private JMenu _options;
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

		/* Men�punkt Optionen erstellen */
		this.add(initOptions());

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
				ImageResources.getScaledIcon(24, Images.ICON_NEW_GAME,
						Image.SCALE_SMOOTH))));
		// Men�punkt Spiel Laden
		_spiel.add(new JMenuItem(new LoadGameAction("Spiel laden ...",
				ImageResources.getScaledIcon(24, Images.ICON_LOAD_GAME,
						Image.SCALE_SMOOTH))));
		// Men�punkt Spiel Speichern
		_spiel.add(new JMenuItem(new SaveGameAction("Spiel speichern ...",
				ImageResources.getScaledIcon(24, Images.ICON_SAVE_GAME,
						Image.SCALE_SMOOTH))));
		// Men�punkt Beenden
		_spiel.addSeparator();
		_spiel.add(new JMenuItem(new CloseApplicationAction("Beenden",
				ImageResources.getScaledIcon(24, Images.ICON_CLOSE_GAME,
						Image.SCALE_SMOOTH))));
		return _spiel;
	}

	private JMenu initEditor() {
		_editor = new JMenu("Editor");
		// Men�punkt Puzzle erstellen
		_editor.add(new JMenuItem(new CreatePuzzleAction(
				"Neues Puzzle erstellen ...", ImageResources.getScaledIcon(24,
						Images.ICON_NEW_GAME, Image.SCALE_SMOOTH))));
		// Men�punkt Spiel Laden
		_editor.add(new JMenuItem(new LoadPuzzleAction("Puzzle bearbeiten ...",
				ImageResources.getScaledIcon(24, Images.ICON_LOAD_GAME,
						Image.SCALE_SMOOTH))));
		// Men�punkt Puzzle Speichern
		_editor.add(new JMenuItem(new SavePuzzleAction("Puzzle speichern ...",
				ImageResources.getScaledIcon(24, Images.ICON_SAVE_GAME,
						Image.SCALE_SMOOTH))));
		return _editor;

	}

	private JMenu initOptions() {
		MainMenuBarHandler handler = new MainMenuBarHandler();
		_options = new JMenu("Optionen");
		JMenu resolution = new JMenu("Aufl�sung");
		resolution.setIcon(ImageResources.getScaledIcon(24,
				Images.ICON_DISPLAY_SIZE, Image.SCALE_SMOOTH));
		ButtonGroup bg = new ButtonGroup();

		// 800 x 600
		JRadioButtonMenuItem item = new JRadioButtonMenuItem("800 x 600");
		item.setActionCommand(GamePreferences.Resolution.R800X600.toString());
		item.addActionListener(handler);
		bg.add(item);
		item.setSelected(true);
		resolution.add(item);
		// MinimumResolution muss aktiv bleiben!

		// 1024 x 768
		item = new JRadioButtonMenuItem("1024 x 768");
		item.setActionCommand(GamePreferences.Resolution.R1024X768.toString());
		item.addActionListener(handler);
		bg.add(item);
		resolution.add(item);
		enableItem(item, 1024, 768);

		// 1280 x 800
		item = new JRadioButtonMenuItem("1280 x 800");
		item.setActionCommand(GamePreferences.Resolution.R1280X800.toString());
		item.addActionListener(handler);
		bg.add(item);
		resolution.add(item);
		enableItem(item, 1280, 800);

		// 1366 x 768
		item = new JRadioButtonMenuItem("1366 x 768");
		item.setActionCommand(GamePreferences.Resolution.R1366X768.toString());
		item.addActionListener(handler);
		bg.add(item);
		resolution.add(item);
		enableItem(item, 1366, 768);

		// 1400 x 900
		item = new JRadioButtonMenuItem("1400 x 900");
		item.setActionCommand(GamePreferences.Resolution.R1400X900.toString());
		item.addActionListener(handler);
		bg.add(item);
		resolution.add(item);
		enableItem(item, 1400, 900);

		// 1680 x 1050
		item = new JRadioButtonMenuItem("1680 x 1050");
		item.setActionCommand(GamePreferences.Resolution.R1680X1050.toString());
		item.addActionListener(handler);
		bg.add(item);
		resolution.add(item);
		enableItem(item, 1680, 1050);

		_options.add(resolution);
		return _options;
	}

	/**
	 * �berpr�ft die aktuelle Displaygr��e und setzt demnach das Item auf
	 * enabled, wenn der Client die Aufl�sung anzeigen kann.
	 * 
	 * @param pItem
	 *            der RadioButton
	 * @param pWidth
	 *            die zu �berpr�fende Breite
	 * @param pHeight
	 *            die zu �berpr�fende H�he
	 */
	private void enableItem(JRadioButtonMenuItem pItem, int pWidth, int pHeight) {

		Dimension displayDimension = Toolkit.getDefaultToolkit()
				.getScreenSize();
		boolean enable = true;
		if (pWidth > displayDimension.getWidth())
			enable = false;
		if (pHeight > displayDimension.getHeight())
			enable = false;
		pItem.setEnabled(enable);

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
				ImageResources.getScaledIcon(24, Images.ICON_HELP,
						Image.SCALE_SMOOTH))));
		// About
		_hilfe.addSeparator();
		_hilfe.add(new JMenuItem(new OpenAboutAction("�ber", ImageResources
				.getScaledIcon(24, Images.ICON_ABOUT, Image.SCALE_SMOOTH))));
		return _hilfe;
	}
}
