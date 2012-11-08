/**
 * 
 */
package game.menubar;

import game.core.ImageResources;
import game.core.ImageResources.Images;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author schroeder_jan
 * 
 */
public class OpenAboutAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpenAboutAction(String text, ImageIcon icon) {
		super(text, icon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pE) {
		JOptionPane.showMessageDialog(null, createAboutText(),
				"About Starfield", JOptionPane.OK_OPTION,
				ImageResources.getIcon(Images.ICON_ABOUT));

	}

	/**
	 * Erstellt den About-Text der auf dem InfoPanel ausgegeben werden soll. <br>
	 * Der Text kann mit HTML formatiert werden.
	 * 
	 * @return
	 */
	private String createAboutText() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		// 1. Absatz
		sb.append("<p>");
		sb.append("Starfield - The Game");
		sb.append("</p>");
		sb.append("<br>");
		// 2. Absatz
		sb.append("<p>");
		sb.append("Version: ");
		sb.append("<br>");
		sb.append("Datum  : ");
		sb.append("</p>");
		sb.append("<br>");
		// 3. Absatz
		sb.append("<p>");
		sb.append("Entwickelt von: <br>");
		sb.append("<ul>");
		sb.append("<li>Alexander Arians</li>");
		sb.append("<li>Nikolaj Gerads</li>");
		sb.append("<li>Moritz Hilgers</li>");
		sb.append("<li>Kevin Hückelhoven</li>");
		sb.append("<li>Jan Schroeder</li>");
		sb.append("</ul>");
		sb.append("</p>");
		sb.append("</html>");
		return sb.toString();
	}
}
