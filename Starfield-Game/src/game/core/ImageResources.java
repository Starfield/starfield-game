/**
 * 
 */
package game.core;

import javax.swing.ImageIcon;

/**
 * Globale Klasse zur Verwaltung von BildDateien. <br>
 * Neue Bilder müssen in der Enum bekanntgemacht werden, danach können Instanzen
 * über die Methoden angelegt werden.
 * 
 * @author Jan
 * 
 */
public class ImageResources {

	/**
	 * Auflistung aller bekannten Bilder
	 * 
	 * @author Jan
	 * 
	 */
	public enum Images {
		STAR("images/star.png"), ICON_NEWGAME("images/icon_newgame.png"), ICON_EXIT(
				"images/icon_exit.png"), ICON_HELP("images/icon_help.png");

		private final String imagePath;

		Images(String imageName) {
			this.imagePath = imageName;
		}
	}

	/**
	 * Liefert eine Instanz eines ImageIcon des Bildes
	 * 
	 * @param Name
	 *            des Bildes in der Liste
	 * @return ImageIcon des Bildes
	 */
	public static ImageIcon getIcon(Images image) {
		// return new ImageIcon(getUserDir() + "\\" + image.getImageName());
		return new ImageIcon(image.imagePath);
	}

}
