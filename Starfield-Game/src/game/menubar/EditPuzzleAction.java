/**
 * 
 */
package game.menubar;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * 
 * @author schroeder_jan
 *
 */
public class EditPuzzleAction extends AbstractAction {

	public EditPuzzleAction(String text, ImageIcon icon){
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
	public void actionPerformed(ActionEvent pE) {
		// TODO FileChooser zum Ausw�hlen eines gespeicherten Puzzles anzeigen

	}

}
