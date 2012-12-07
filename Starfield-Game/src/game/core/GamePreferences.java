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
 * Preferences die für das gesamte Spiel gelten.
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
	}

	/**
	 * Beschreibt die Zustände in denen sich die Applikation befinden kann. <br>
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

	public enum Resolution {
		/** Stellt eine Auflösung von 800x600 ein */
		R800X600(800, 600),
		/** Stellt eine Auflösung von 1024x768 ein */
		R1024X768(1024, 768),
		/** Stellt eine Auflösung von 1280x800 ein */
		R1280X800(1280, 800),
		/** Stellt eine Auflösung von 1280x1024 ein */
		R1280X1024(1280, 1024),
		/** Stellt eine Auflösung von 1366x768 ein */
		R1366X768(1366, 768),
		/** Stellt eine Auflösung von 1400x900 ein */
		R1400X900(1400, 900),
		/** Stellt eine Auflösung von 1680x1050 ein */
		R1680X1050(1680, 1050),
		/** Stellt eine Auflösung von 1900x1080 ein */
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

	public enum LinealMode {
		/** Kein Lineal anzeigen */
		NO,
		/** Horizontales und vertikales Hilfsraster anzeigen */
		CROSS,
		/** Horizontales, vertikales und diagonales Hilfsraster anzeigen */
		STAR;
	}

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
	/** aktuelle Auflösung */
	private Resolution _resolution;
	/** LinealMode */
	private LinealMode _lineal;
	/** Hilfsmodus */
	private HelpMode _helpMode;

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
	 * Setzt die neue Auflösung
	 * 
	 * @param pResolution
	 *            die neue Auflösung
	 */
	public void setResolution(Resolution pResolution) {
		// neue Auflösung setzen
		_resolution = pResolution;
		// Überprüfen ob Auflösung MaximalAuflösung ist
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
			// neuAufbau der GUI Elemente anstoßen
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
		// Wurde noch kein Starfield gesetzt wird ein neues 5,5 zurückgegeben.
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

	public File getStarfieldFile() {
		return _starfieldFile;
	}

	public void removeStarfieldFile() {
		_starfieldFile = null;
	}

	public void setLinealMode(LinealMode newMode) {
		_lineal = newMode;
	}

	public LinealMode getLinealMode() {
		return _lineal;
	}

	public void setHelpMode(HelpMode newMode) {
		_helpMode = newMode;
	}

	public HelpMode getHelpMode() {
		return _helpMode;
	}
}
