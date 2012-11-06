/**
 * 
 */
package game.core;

import game.ui.MainWindow;

import java.util.HashMap;
import java.util.Map;

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

	// Konstanten
	/** Ordner in dem die Bilder liegen */
	private final static String FOLDER = "images/";
	/** Endung der Bilddateien */
	private final static String FILE_TYPE = ".png";
	/** Schalter für kleine Bilder */
	public final static int SIZE_32 = 0;
	/** Schalter für große Bilder */
	public final static int SIZE_64 = 1;

	// Klassenvariablen
	/** SimpleImageCache */
	private final static Map<Images, ImageIcon> _imageCache = new HashMap<ImageResources.Images, ImageIcon>();

	/**
	 * Auflistung aller bekannten Bilder
	 * 
	 * @author Jan
	 * 
	 */
	public enum Images {
		ICON_STAR("icons/icon_star"),
		ICON_NEW_GAME("icons/icon_new_game"),
		ICON_SAVE_GAME("icons/icon_save_game"),
		ICON_LOAD_GAME("icons/icon_load_game"),
		ICON_CLOSE_GAME("icons/icon_close_game"),
		ICON_EXIT("icons/icon_exit"),
		ICON_HELP("icons/icon_help"),
		ICON_ARROW_U("icons/arrows/UpArrowIcon"),
		ICON_ARROW_D("icons/arrows/DownArrowIcon"),
		ICON_ARROW_L("icons/arrows/LeftArrowIcon"),
		ICON_ARROW_R("icons/arrows/RightArrowIcon"),
		ICON_ARROW_UR("icons/arrows/UpRightArrowIcon"),
		ICON_ARROW_UL("icons/arrows/UpLeftArrowIcon"),
		ICON_ARROW_DR("icons/arrows/DownRightArrowIcon"),
		ICON_ARROW_DL("icons/arrows/DownLeftArrowIcon"),
		CONTENT_EMPTY("content/EmptyContent"),
		CONTENT_GRAYED("content/GrayedContent"),
		CONTENT_STAR("content/StarContent"),
		CONTENT_ARROW_U("content/arrows/UpArrowContent"),
		CONTENT_ARROW_D("content/arrows/DownArrowContent"),
		CONTENT_ARROW_L("content/arrows/LeftArrowContent"),
		CONTENT_ARROW_R("content/arrows/RightArrowContent"),
		CONTENT_ARROW_UR("content/arrows/UpRightArrowContent"),
		CONTENT_ARROW_UL("content/arrows/UpLeftArrowContent"),
		CONTENT_ARROW_DR("content/arrows/DownRightArrowContent"),
		CONTENT_ARROW_DL("content/arrows/DownLeftArrowContent");

		private final String name;

		Images(String imageName) {
			this.name = imageName;
		}
	}

	/**
	 * Durchsucht den SimpleCache nach dem angefragten Bild. Gibt dieses zurück
	 * falls vorhanden, oder erstellt es, schiebt es in den SimpleCache und gibt
	 * das Bild zurück
	 * 
	 * @param Name
	 *            des Bildes in der {@link Images}
	 * @return ImageIcon des Bildes
	 */
	public static ImageIcon getIcon(Images image) {
		if (image == null)
			return null;
		if (_imageCache.containsKey(image))
			return _imageCache.get(image);
		else {
			String pfad = getImagePath(image);
			if (pfad != null) {
				ImageIcon imageIcon = new ImageIcon(pfad);
				_imageCache.put(image, imageIcon);
				return imageIcon;
			}
		}
		return null;
	}

	/**
	 * Liefert in Abhängigkeit der eingestellten Optionen den richtigen Bildpfad
	 * 
	 * @param image
	 *            nach dem gesucht werden soll
	 * @return Pfad zur Bilddatei in richtiger Größe
	 */
	private static String getImagePath(Images image) {
		// Icons werden immer klein angezeigt
		if (image.toString().startsWith("ICON_"))
			return FOLDER + image.name + "32" + FILE_TYPE;
		// Ansonsten wird nach Optionseinstellung entschieden
		if (MainWindow.getGamePrefs().getImageSize() == SIZE_32)
			return FOLDER + image.name + "32" + FILE_TYPE;
		if (MainWindow.getGamePrefs().getImageSize() == SIZE_64)
			return FOLDER + image.name + "64" + FILE_TYPE;
		return null;
	}
}
