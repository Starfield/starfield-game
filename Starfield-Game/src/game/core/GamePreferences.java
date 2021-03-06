/**
 * 
 */
package game.core;

import game.commands.CommandStack;
import game.model.Starfield;
import game.ui.MainWindow;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;

/**
 * Preferences die f�r das gesamte Spiel gelten.
 * 
 * @author Jan
 * 
 */
public class GamePreferences {

	/**
	 * Standardkonstruktor. <br>
	 * Belegt die Optionswerte mit ihren Defaults.
	 * 
	 */
	public GamePreferences() {
		_appMode = AppMode.FIRST_START;
		_loadedCommandStack = null;
		_resolution = Resolution.R800X600;
		_lineal = LinealMode.NO;
		_helpMode = HelpMode.OFF;
		_floatableToolbars = false;
	}

	/**
	 * Beschreibt die Zust�nde in denen sich die Applikation befinden kann. <br>
	 * Hieraus kann bestimmt werden, welche GUI-Elemente momentan angezeigt
	 * werden.
	 */
	public enum AppMode {
		/** Der Modus um ein neues Spiel zu spielen */
		GAME_MODE,
		/** Der Modus zum Laden eines gespeicherten Spiels */
		LOAD_GAME_MODE,
		/** Der Modus zum Erstellen eines neuen Puzzles */
		EDIT_MODE,
		/** Der Modus zum Bearbeiten eines gespeicherten Puzzles */
		LOAD_EDIT_MODE,
		/** Der Modus zum Anzeigen des Splashscreens */
		FIRST_START,
		/** Der Modus zum starten des Replays */
		REPLAY_MODE;
	}

	/**
	 * Stellt eine Auswahl von Aufl�sungen dar, f�r die das Spiel optimiert
	 * dargestellt werden kann. <br>
	 * Eine weitere Aufl�sung kann durch hinzuf�gen in diese Liste hinzugef�gt
	 * werden, es sind keine weiteren Codever�nderungen n�tig.
	 * 
	 * @author Jan
	 * 
	 */
	public enum Resolution {
		/** Stellt eine Aufl�sung von 800x600 ein */
		R800X600(800, 600),
		/** Stellt eine Aufl�sung von 1024x768 ein */
		R1024X768(1024, 768),
		/** Stellt eine Aufl�sung von 1280x800 ein */
		R1280X800(1280, 800),
		/** Stellt eine Aufl�sung von 1280x1024 ein */
		R1280X1024(1280, 1024),
		/** Stellt eine Aufl�sung von 1366x768 ein */
		R1366X768(1366, 768),
		/** Stellt eine Aufl�sung von 1400x900 ein */
		R1400X900(1400, 900),
		/** Stellt eine Aufl�sung von 1680x1050 ein */
		R1680X1050(1680, 1050),
		/** Stellt eine Aufl�sung von 1900x1080 ein */
		R1900X1080(1900, 1080);

		private final int width;
		private final int height;

		Resolution(int pWidth, int pHeight) {
			width = pWidth;
			height = pHeight;
		}

		public int getHeight() {
			return height;
		}

		public int getWidth() {
			return width;
		}

	}

	/**
	 * Beschreibt den Modus f�r die Linealanzeige auf dem Spielfeld. <br>
	 * Bei eingeschalteter LinealAnzeige gibt es zwei Modi:
	 * <ul>
	 * <li>Der Kreuz-Modus hebt waagerechte und senkrechte Linien von der
	 * aktuell mit der Maus markierten Zelle hervor</li>
	 * <li>Der Stern-Modus hebt zus�tzlich auch die diagonalen hervor</li>
	 * </ul>
	 * 
	 * @author Jan
	 * 
	 */
	public enum LinealMode {
		/** Kein Lineal anzeigen */
		NO,
		/** Horizontales und vertikales Hilfsraster anzeigen */
		CROSS,
		/** Horizontales, vertikales und diagonales Hilfsraster anzeigen */
		STAR;
	}

	/**
	 * Beschreibt den Modus f�r die Hilfsanzeige des Spiels. <br>
	 * Bei eingeschalteter Hilfsanzeige werden folgende Hilfen angezeigt:
	 * <ul>
	 * <li>H�lt man die Maus einen Pfeil, so werden alle Zellen in Richtung des
	 * Pfeils herborgehoben</li>
	 * <li>H�lt man die Maus einen Stern so werden alle Pfeile die auf diesen
	 * Stern zeigen hervorgehoben</li>
	 * </ul>
	 * 
	 * @author Jan
	 * 
	 */
	public enum HelpMode {
		/** Aus */
		OFF,
		/** An */
		ON;
	}

	// Optionsvariablen
	/** AppModus */
	private AppMode _appMode;
	/** Geladener CommandStack */
	private CommandStack _loadedCommandStack;
	/** Geladenes Starfield */
	private Starfield _loadedStarfield;
	/** Datei auf dem Filesystem */
	private File _starfieldFile;
	/** aktuelle Aufl�sung */
	private Resolution _resolution;
	/** LinealMode */
	private LinealMode _lineal;
	/** Hilfsmodus */
	private HelpMode _helpMode;
	/** flotable toolbars */
	private final boolean _floatableToolbars;

	/**
	 * @return the {@link AppMode}
	 */
	public AppMode getAppMode() {
		return _appMode;
	}

	/**
	 * @param pAppMode
	 *            the {@link AppMode} to set
	 */
	public void setAppMode(AppMode pAppMode) {
		_appMode = pAppMode;
	}

	/**
	 * @return the resolution
	 */
	public Resolution getResolution() {
		return _resolution;
	}

	/**
	 * Setzt die neue Aufl�sung
	 * 
	 * @param pResolution
	 *            die neue Aufl�sung
	 */
	public void setResolution(Resolution pResolution) {
		// neue Aufl�sung setzen
		_resolution = pResolution;
		// �berpr�fen ob Aufl�sung MaximalAufl�sung ist
		Dimension curRes = Toolkit.getDefaultToolkit().getScreenSize();
		boolean maximumReached = false;
		if (_resolution.getWidth() >= curRes.width)
			maximumReached = true;
		if (_resolution.getHeight() >= curRes.height)
			maximumReached = true;
		// Wenn Maximum Size dann maximieren, sonst Res setzen
		if (maximumReached)
			MainWindow.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
		else {
			MainWindow.getInstance().setSize(_resolution.getWidth(),
					_resolution.getHeight());
			// neuAufbau der GUI Elemente ansto�en
			MainWindow.getInstance().validate();
			MainWindow.getInstance().setLocationRelativeTo(null);
		}
	}

	/**
	 * Holt einen vorher gesetzten {@link CommandStack} aus den
	 * {@link GamePreferences}
	 * 
	 * @return the loadedCommandStack
	 */
	public CommandStack getLoadedCommandStack() {
		return _loadedCommandStack;
	}

	/**
	 * Entfernt den geladenen {@link CommandStack} aus den
	 * {@link GamePreferences}. Sollte nach erfolgreichem Ladevorgang aufgerufen
	 * werden.
	 */
	public void removeLoadedCommandStack() {
		_loadedCommandStack = null;
	}

	/**
	 * Setzt einen geladenen {@link CommandStack} zur Abholung in die
	 * {@link GamePreferences}
	 * 
	 * @param loadedCommandStack
	 *            the loadedCommandStack to set
	 */
	public void setLoadedCommandStack(CommandStack loadedCommandStack) {
		_loadedCommandStack = loadedCommandStack;
		setAppMode(AppMode.LOAD_GAME_MODE);
	}

	/**
	 * Holt das vorher gesetzte {@link Starfield} aus den
	 * {@link GamePreferences}
	 * 
	 * @return the loadedStarfield
	 */
	public Starfield getLoadedStarfield() {
		// Wurde noch kein Starfield gesetzt wird ein neues 5,5 zur�ckgegeben.
		if (_loadedStarfield == null)
			return new Starfield(5, 5);
		return _loadedStarfield;
	}

	/**
	 * Setzt ein geladenenes {@link Starfield} zur Abholung in die
	 * {@link GamePreferences}
	 * 
	 * @param loadedStarfield
	 *            the loadedStarfield to set
	 */
	public void setLoadedStarfield(Starfield loadedStarfield) {
		_loadedStarfield = loadedStarfield;
	}

	/**
	 * Entfernt das {@link Starfield} aus den {@link GamePreferences}. Sollte
	 * nach erfolgreichem Ladevorgang aufgerufen werden.
	 */
	public void removeLoadedStarfield() {
		_loadedStarfield = null;
	}

	/**
	 * Setzt die Datei auf der das Starfield auf dem PC gespeichert ist.
	 * 
	 * @param pFile
	 */
	public void setStarfieldFile(File pFile) {
		_starfieldFile = pFile;
	}

	/**
	 * Liefert das File des gespeicherten Starfield, das im Zwischenspeicher
	 * liegt
	 * 
	 * @return
	 */
	public File getStarfieldFile() {
		return _starfieldFile;
	}

	/**
	 * L�scht das zuvor gespeicherte Starfield aus dem Zwischenspeicher
	 */
	public void removeStarfieldFile() {
		_starfieldFile = null;
	}

	/**
	 * Setzt den Modus des Bildschirmlineals
	 * 
	 * @param newMode
	 *            aus {@link LinealMode}
	 */
	public void setLinealMode(LinealMode newMode) {
		_lineal = newMode;
	}

	/**
	 * Liefert den eingestellten {@link LinealMode}
	 * 
	 * @return den LinealMode
	 */
	public LinealMode getLinealMode() {
		return _lineal;
	}

	/**
	 * Setzt den Modus f�r die Hilfsanzeige
	 * 
	 * @param newMode
	 *            aus {@link HelpMode}
	 */
	public void setHelpMode(HelpMode newMode) {
		_helpMode = newMode;
	}

	/**
	 * Liefert den eingestellten {@link HelpMode}
	 * 
	 * @return den HelpMode
	 */
	public HelpMode getHelpMode() {
		return _helpMode;
	}

	/**
	 * Sind die Toolbars floatable?
	 * 
	 * @return
	 */
	public boolean isToolbarFloatable() {
		return _floatableToolbars;
	}
}
