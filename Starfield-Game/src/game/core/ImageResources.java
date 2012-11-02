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
 * Neue Bilder m�ssen in der Enum bekanntgemacht werden, danach k�nnen Instanzen
 * �ber die Methoden angelegt werden.
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
	/** Schalter f�r kleine Bilder */
	public final static int SIZE_32 = 0;
	/** Schalter f�r gro�e Bilder */
	public final static int SIZE_64 = 1;
	/** SimpleImageCache */
	private final static Map<Images, ImageIcon> _imageCache = new HashMap<ImageResources.Images, ImageIcon>();

	/**
	 * Auflistung aller bekannten Bilder
	 * 
	 * @author Jan
	 * 
	 */
	public enum Images {
		STAR("star"),
		ICON_NEWGAME("icon_newgame"),
		ICON_EXIT("icon_exit"),
		ICON_HELP("icon_help"),
		CONTENT_EMPTY("EmptyContent"),
		CONTENT_GRAYED("GrayedContent"),
		CONTENT_STAR("StarContent"),
		CONTENT_ARROW_U("arrows/UpArrowContent"),
		CONTENT_ARROW_D("arrows/DownArrowContent"),
		CONTENT_ARROW_L("arrows/LeftArrowContent"),
		CONTENT_ARROW_R("arrows/RightArrowContent"),
		CONTENT_ARROW_UR("arrows/UpRightArrowContent"),
		CONTENT_ARROW_UL("arrows/UpLeftArrowContent"),
		CONTENT_ARROW_DR("arrows/DownRightArrowContent"),
		CONTENT_ARROW_DL("arrows/DownLeftArrowContent");

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
	 * Liefert in Abh�ngigkeit der eingestellten Optionen den richtigen Bildpfad
	 * 
	 * @param image
	 *            nach dem gesucht werden soll
	 * @return Pfad zur Bilddatei in richtiger Gr��e
	 */
	private static String getImagePath(Images image) {
		if (MainWindow.getGamePrefs().getImageSize() == SIZE_32)
			return FOLDER + image.name + "32" + FILE_TYPE;
		if (MainWindow.getGamePrefs().getImageSize() == SIZE_64)
			return FOLDER + image.name + "64" + FILE_TYPE;
		return null;
	}
}
