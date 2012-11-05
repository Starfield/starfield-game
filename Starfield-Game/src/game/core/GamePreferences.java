/**
 * 
 */
package game.core;

import game.commands.CommandStack;
import game.model.Starfield;
import game.ui.MainWindow;

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
	public GamePreferences(MainWindow parentWindow) {
		_mainWindow = parentWindow;
		_imageSize = ImageResources.SIZE_64;
		_appMode = AppMode.GAME_MODE;
		_loadedCommandStack = null;

	}

	/**
	 * Beschreibt die Zust�nde in denen sich die Applikation befinden kann. <br>
	 * Hieraus kann bestimmt werden, welche GUI-Elemente momentan angezeigt
	 * werden.
	 */
	public enum AppMode {
		GAME_MODE, LOAD_GAME_MODE, EDIT_MODE, LOAD_EDIT_MODE;
	}

	/** Das MainWindow */
	private final MainWindow _mainWindow;

	// Optionsvariablen
	/** ImageSize */
	private int _imageSize;
	/** AppModus */
	private AppMode _appMode;
	/** Geladener CommandStack */
	private CommandStack _loadedCommandStack;
	/** Geladenes Starfield */
	private Starfield _loadedStarfield;

	public int getImageSize() {
		return _imageSize;
	}

	public void setImageSize(int pImageSize) {
		_imageSize = pImageSize;
	}

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
	 * Holt einen vorher gesetzten {@link CommandStack} aus den
	 * {@link GamePreferences}
	 * 
	 * @return the loadedCommandStack
	 */
	public CommandStack getLoadedCommandStack() {
		// Wurde noch kein CommandStack gesetzt wird ein neuer "leerer"
		// Commandstack zur�ckgegeben
		if (_loadedCommandStack == null)
			return new CommandStack();
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
	 * @return the mainWindow
	 */
	public MainWindow getMainWindow() {
		return _mainWindow;
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
		setAppMode(AppMode.LOAD_EDIT_MODE);
	}

	/**
	 * Entfernt das {@link Starfield} aus den {@link GamePreferences}. Sollte
	 * nach erfolgreichem Ladevorgang aufgerufen werden.
	 */
	public void removeLoadedStarfield() {
		_loadedStarfield = null;
	}

}
