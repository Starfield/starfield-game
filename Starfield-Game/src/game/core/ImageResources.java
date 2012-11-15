/**
 * 
 */
package game.core;

import java.awt.Image;
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
	/** Skalierungsfaktor */
	private static int _scalingSize = 32;
	/** Maximale Größe */
	private final static int _maxSize = 128;
	/** Minimale Größe */
	private final static int _minSize = 32;

	// Klassenvariablen
	/** SimpleImageCache */
	private static Map<Images, ImageIcon> _imageCache = new HashMap<ImageResources.Images, ImageIcon>();

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
		ICON_DISPLAY_SIZE("icons/icon_display_size"),
		ICON_EMPTY("icons/icon_empty"),
		ICON_ABOUT("icons/icon_about"),
		ICON_HELP("icons/icon_help"),
		ICON_MARKER_ON("icons/icon_marker_on"),
		ICON_MARKER_OFF("icons/icon_marker_off"),
		ICON_OPTIONS("icons/icon_options"),
		ICON_PLAYABLE_TRUE("icons/icon_playable_true"),
		ICON_PLAYABLE_FALSE("icons/icon_playable_false"),
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
		CONTENT_ARROW_DL("content/arrows/DownLeftArrowContent"),
		SPLASHSCREEN("splashscreen");

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
		// Sonderregel für den SplashScreen
		if (image == Images.SPLASHSCREEN)
			return new ImageIcon(FOLDER + image.name + FILE_TYPE);
		// Icons werden immer klein angezeigt
		if (image.toString().startsWith("ICON_"))
			return getScaledIcon(32, image, Image.SCALE_SMOOTH);
		return getScaledIcon(getScalingSize(), image, Image.SCALE_SMOOTH);
	}

	/**
	 * Liefert ein skaliertes Icon.
	 * 
	 * @param width
	 *            - Breite des skalierten Icons
	 * @param height
	 *            - Höhe des skalierten Icons
	 * @param image
	 *            - ein Icon aus {@link Images}
	 * @param scaleHint
	 *            - eine Scaling Methode aus {@link Image}
	 * @return
	 */
	public static ImageIcon getScaledIcon(int size, Images image, int ScaleHint) {

		ImageIcon icon = null;
		if (_imageCache.containsKey(image))
			icon = _imageCache.get(image);
		else {
			String pfad = getImagePath(image, size);
			if (pfad != null) {
				icon = new ImageIcon(pfad);
				icon.setImage(icon.getImage().getScaledInstance(size, size,
						ScaleHint));
				_imageCache.put(image, icon);
			}
		}
		if (icon != null)
			return icon;
		return null;
	}

	/**
	 * Liefert in Abhängigkeit der eingestellten Optionen den richtigen Bildpfad
	 * 
	 * @param image
	 *            nach dem gesucht werden soll
	 * @return Pfad zur Bilddatei in richtiger Größe
	 */
	private static String getImagePath(Images image, int pSize) {

		if (pSize < 33)
			return FOLDER + image.name + "32" + FILE_TYPE;
		return FOLDER + image.name + "64" + FILE_TYPE;
	}

	/**
	 * Leert den ImageCache <br>
	 * Kann nötig sein, wenn sich zB die ImageSize geändert hat <br>
	 * Hierbei werden die Icons nicht gelöscht, da diese sowieso nicht skaliert
	 * werden!
	 */
	private static void clearCache() {
		for (Images image : Images.values()) {
			if (!image.toString().startsWith("ICON_")) {
				if (_imageCache.containsKey(image))
					_imageCache.remove(image);
			}
		}
	}

	/**
	 * 
	 * @return die maximale SkalierGröße
	 */
	public static int getMaxSize() {
		return _maxSize;
	}

	/**
	 * 
	 * @return die minimale SkalierGröße
	 */
	public static int getMinSize() {
		return _minSize;
	}

	/**
	 * 
	 * @return the _scalingSize
	 */
	public static int getScalingSize() {
		return _scalingSize;
	}

	/**
	 * 
	 * @param pSize
	 */
	public static void setScalingSize(int pSize) {
		clearCache();
		_scalingSize = pSize;
	}
}
