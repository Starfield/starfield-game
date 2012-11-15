/**
 * 
 */
package game.menubar;

import game.core.GamePreferences.AppMode;
import game.ui.MainWindow;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * @author schroeder_jan
 * 
 */
public class CreatePuzzleAction extends AbstractAction {

	public CreatePuzzleAction(String text, ImageIcon icon) {
		super(text, icon);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pE) {
		// Erstellt ein Standardpuzzle das im Editor über die Toolbar
		// weiterbearbeitet werden kann.
		MainWindow.getInstance().getGamePrefs().setAppMode(AppMode.EDIT_MODE);
		MainWindow.getInstance().initGame();

	}

}
