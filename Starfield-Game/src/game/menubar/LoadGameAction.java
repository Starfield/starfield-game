/**
 * 
 */
package game.menubar;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Diese Action öffnet ein Fenster zur Auswahl eines gespeicherten Spiels. <br>
 * Im Anschluss wird das aktuelle Spiel beendet und das gewählte Spiel geladen
 * @author schroeder_jan
 *
 */
public class LoadGameAction extends AbstractAction {

	
	public LoadGameAction(String text, ImageIcon icon){
		super(text, icon);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pArg0) {
		// TODO FileChooser zum Laden eines gespeicherten Spiels

	}

}
