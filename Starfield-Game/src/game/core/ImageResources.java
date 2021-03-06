/**
 * 
 */
package game.core;

import game.model.Field.AllowedContent;

import java.awt.Image;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * Globale Klasse zur Verwaltung von BildDateien. <br>
 * Neue Bilder m�ssen in der Enum bekanntgemacht werden, danach k�nnen Instanzen
 * �ber die Methoden angelegt werden.
 * 
 * @author Jan
 * 
 */
public class ImageResources {

	// Konstanten
	/** Endung der Bilddateien */
	private final static String FILE_TYPE = ".png";
	/** Skalierungsfaktor */
	private static int _scalingSize = 32;
	/** Maximale Gr��e */
	private final static int _maxSize = 128;
	/** Minimale Gr��e */
	private final static int _minSize = 24;

	// Klassenvariablen
	/** SimpleImageCache */
	private static Map<Images, ImageIcon> _imageCache = new HashMap<ImageResources.Images, ImageIcon>();

	private static Map<AllowedContent, Images> _contentMapping;

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
		ICON_LINEAL("icons/icon_lineal"),
		ICON_HELP("icons/icon_help"),
		ICON_HELP_MODE("icons/icon_help_mode"),
		ICON_MARKER_ON("icons/icon_marker_on"),
		ICON_MARKER_OFF("icons/icon_marker_off"),
		ICON_OPTIONS("icons/icon_options"),
		ICON_PLAYABLE_TRUE("icons/icon_playable_true"),
		ICON_PLAYABLE_FALSE("icons/icon_playable_false"),
		ICON_PLAYABLE_UNSURE("icons/icon_playable_unsure"),
		ICON_SLOW("icons/icon_slow"),
		ICON_FAST("icons/icon_fast"),
		ICON_NO_PREVIEW("icons/icon_no_preview"),
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
	 * Durchsucht den SimpleCache nach dem angefragten Bild. Gibt dieses zur�ck
	 * falls vorhanden, oder erstellt es, schiebt es in den SimpleCache und gibt
	 * das Bild zur�ck
	 * 
	 * @param Name
	 *            des Bildes in der {@link Images}
	 * @return ImageIcon des Bildes
	 */
	public static ImageIcon getIcon(Images image) {

		if (image == null)
			return null;
		// Sonderregel f�r den SplashScreen
		if (image == Images.SPLASHSCREEN) {
			URL pfad = getImagePath(image, 99);
			if (pfad != null)
				return new ImageIcon(pfad);
		}
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
	 *            - H�he des skalierten Icons
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
			URL pfad = getImagePath(image, size);
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

	public static ImageIcon getScaledIcon(int size, AllowedContent image,
			int scaleHint) {
		return getScaledIcon(size, getRelatedImage(image), scaleHint);
	}

	/**
	 * Liefert in Abh�ngigkeit der eingestellten Optionen den richtigen Bildpfad
	 * 
	 * @param image
	 *            nach dem gesucht werden soll
	 * @return Pfad zur Bilddatei in richtiger Gr��e
	 */
	private static URL getImagePath(Images image, int pSize) {

		String size = "128";

		if (pSize < 33) {
			size = "32";
		}
		if (pSize < 65)
			size = "64";
		// Ausnahme f�r Icons einf�gen
		if (image.toString().startsWith("ICON_"))
			size = "32";
		// Ausnahme f�r den Splashscreen einf�gen
		if (image == Images.SPLASHSCREEN)
			size = "";

		URL url = ClassLoader.class.getResource("/" + image.name + size
				+ FILE_TYPE);
		return url;
	}

	/**
	 * Leert den ImageCache <br>
	 * Kann n�tig sein, wenn sich zB die ImageSize ge�ndert hat <br>
	 * Hierbei werden die Icons nicht gel�scht, da diese sowieso nicht skaliert
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
	 * Diese Methode initialisiert das Mapping von AllowedContent zu Images
	 */
	private static void initContentMap() {
		_contentMapping = new HashMap<AllowedContent, Images>();

		_contentMapping.put(AllowedContent.CONTENT_EMPTY, Images.CONTENT_EMPTY);
		_contentMapping.put(AllowedContent.CONTENT_GRAYED,
				Images.CONTENT_GRAYED);
		_contentMapping.put(AllowedContent.CONTENT_STAR, Images.CONTENT_STAR);
		_contentMapping.put(AllowedContent.CONTENT_ARROW_U,
				Images.CONTENT_ARROW_U);
		_contentMapping.put(AllowedContent.CONTENT_ARROW_D,
				Images.CONTENT_ARROW_D);
		_contentMapping.put(AllowedContent.CONTENT_ARROW_L,
				Images.CONTENT_ARROW_L);
		_contentMapping.put(AllowedContent.CONTENT_ARROW_R,
				Images.CONTENT_ARROW_R);
		_contentMapping.put(AllowedContent.CONTENT_ARROW_UR,
				Images.CONTENT_ARROW_UR);
		_contentMapping.put(AllowedContent.CONTENT_ARROW_UL,
				Images.CONTENT_ARROW_UL);
		_contentMapping.put(AllowedContent.CONTENT_ARROW_DR,
				Images.CONTENT_ARROW_DR);
		_contentMapping.put(AllowedContent.CONTENT_ARROW_DL,
				Images.CONTENT_ARROW_DL);
	}

	private static Images getRelatedImage(AllowedContent content) {
		if (_contentMapping == null)
			initContentMap();
		return _contentMapping.get(content);
	}

	/**
	 * 
	 * @return die maximale SkalierGr��e
	 */
	public static int getMaxSize() {
		return _maxSize;
	}

	/**
	 * 
	 * @return die minimale SkalierGr��e
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
