/**
 * 
 */
package game.menubar;

import game.ui.MainWindow;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Schließt die gesamte Applikation
 * 
 * @author Jan
 * 
 */
public class CloseApplicationAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CloseApplicationAction(String text, ImageIcon icon) {
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
		// Überprüfen ob nichtgespeicherte Änderungen vorliegen
		// TODO

		// Confirm Dialog ob Änderungen gespeichert werden sollen
		final int userChoice = JOptionPane.showConfirmDialog(null,
				"Wollen Sie das Spiel vor dem Beenden sichern?", "Speichern?",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null);

		switch (userChoice) {
		case JOptionPane.CANCEL_OPTION:
			break;
		case JOptionPane.OK_OPTION:
			saveGame();
		default:
			exitApplication();

		}

	}

	private void exitApplication() {
		System.exit(0);

	}

	private void saveGame() {
		AbstractAction saveAction = null;
		switch (MainWindow.getGamePrefs().getAppMode()) {
		case LOAD_EDIT_MODE:
		case EDIT_MODE:
			saveAction = new SavePuzzleAction(null, null);
			break;
		case LOAD_GAME_MODE:
		case GAME_MODE:
			saveAction = new SaveGameAction(null, null);
			break;
		}
		if (saveAction != null)
			saveAction.actionPerformed(null);
	}
}
