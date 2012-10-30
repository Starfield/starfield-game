/**
 * 
 */
package game.core;

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
		imageSize = ImageResources.SIZE_64;

	}

	// Optionsvariablen
	/** ImageSize */
	private int	imageSize;

	public int getImageSize() {
		return imageSize;
	}

	public void setImageSize(int pImageSize) {
		imageSize = pImageSize;
	}

}
