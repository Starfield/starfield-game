/**
 * 
 */
package game.core;

import game.ui.MainWindow;

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
	private final static String	FOLDER		= "images/";
	/** Endung der Bilddateien */
	private final static String	FILE_TYPE	= ".png";
	/** Schalter für kleine Bilder */
	public final static int		SIZE_32		= 0;
	/** Schalter für große Bilder */
	public final static int		SIZE_64		= 1;

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

		private final String	name;

		Images(String imageName) {
			this.name = imageName;
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
		if (image == null)
			return null;
		String pfad = getImagePath(image);
		if (pfad != null)
			return new ImageIcon(pfad);
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
		if (MainWindow.getGamePrefs().getImageSize() == SIZE_32)
			return FOLDER + image.name + "32" + FILE_TYPE;
		if (MainWindow.getGamePrefs().getImageSize() == SIZE_64)
			return FOLDER + image.name + "64" + FILE_TYPE;
		return null;
	}
}
