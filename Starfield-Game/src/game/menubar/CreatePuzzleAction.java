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
		// TODO Dialog für Erstellung eines neuen Puzzles
		// Ändert zum Testen den AppMode und forced einen NeuAufbau des
		// Starfields.
		MainWindow.getGamePrefs().setAppMode(AppMode.EDIT_MODE);
		MainWindow.getGamePrefs().getMainWindow().initGame();

	}

}
