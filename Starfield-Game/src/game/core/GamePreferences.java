/**
 * 
 */
package game.core;

import game.commands.CommandStack;
import game.model.Starfield;
import game.ui.MainWindow;

import java.io.File;

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
	}

	/**
	 * Beschreibt die Zustände in denen sich die Applikation befinden kann. <br>
	 * Hieraus kann bestimmt werden, welche GUI-Elemente momentan angezeigt
	 * werden.
	 */
	public enum AppMode {
		GAME_MODE,
		LOAD_GAME_MODE,
		EDIT_MODE,
		LOAD_EDIT_MODE,
		FIRST_START,
		REPLAY_MODE;
	}

	public enum Resolution {
		R800X600(800, 600),
		R1024X768(1024, 768),
		R1280X800(1280, 800),
		R1280X1024(1280, 1024),
		R1366X768(1366, 768),
		R1400X900(1400, 900),
		R1680X1050(1680, 1050),
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
		// Nichts unternehmen, wenn keine Änderung eingetreten ist
		if (_resolution == pResolution)
			return;
		// neue Auflösung setzen
		_resolution = pResolution;
		// neuAufbau der GUI Elemente anstoßen
		MainWindow.getInstance().setSize(_resolution.getWidth(),
				_resolution.getHeight());
		MainWindow.getInstance().validate();
		MainWindow.getInstance().setLocationRelativeTo(null);
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

}
