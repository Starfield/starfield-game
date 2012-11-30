/**
 * 
 */
package game.menubar;

import game.core.GamePreferences.Resolution;
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
		final MainMenuBarHandler handler = new MainMenuBarHandler();
		_options = new JMenu("Optionen");
		final JMenu resolution = new JMenu("Aufl�sung");
		resolution.setIcon(ImageResources.getScaledIcon(24,
				Images.ICON_DISPLAY_SIZE, Image.SCALE_SMOOTH));

		boolean first = true;
		for (final Resolution res : Resolution.values()) {
			final JMenuItem item = new JMenuItem(res.getWidth() + " x "
					+ res.getHeight());
			item.setActionCommand(res.toString());
			item.addActionListener(handler);
			if (!first)
				enableItem(item, res.getWidth(), res.getHeight());
			resolution.add(item);
			first = false;
		}
		_options.add(resolution);

		// Auswahlbereich f�r Linealanzeige erstellen
		final JMenu lineal = new JMenu("Lineal");
		ButtonGroup bg = new ButtonGroup();
		// Kein Lineal
		JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem(
				"Kein Lineal");
		radioButton.setActionCommand("NO");
		radioButton.addActionListener(handler);
		bg.add(radioButton);
		radioButton.setSelected(true); // Standard
		lineal.add(radioButton);
		// Kreuz
		radioButton = new JRadioButtonMenuItem("Kreuz");
		radioButton.setActionCommand("CROSS");
		radioButton.addActionListener(handler);
		bg.add(radioButton);
		lineal.add(radioButton);
		// Stern
		radioButton = new JRadioButtonMenuItem("Stern");
		radioButton.setActionCommand("STAR");
		radioButton.addActionListener(handler);
		bg.add(radioButton);
		lineal.add(radioButton);

		_options.add(lineal);
		return _options;
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
	private void enableItem(final JMenuItem pItem, final int pWidth,
			final int pHeight) {

		final Dimension displayDimension = Toolkit.getDefaultToolkit()
				.getScreenSize();
		boolean enable = true;
		if (pWidth > displayDimension.getWidth())
			enable = false;
		if (pHeight > displayDimension.getHeight())
			enable = false;
		pItem.setEnabled(enable);

	}
}
