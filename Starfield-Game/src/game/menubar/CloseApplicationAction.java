/**
 * 
 */
package game.menubar;

import game.core.GamePreferences.AppMode;
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
		// Wird der Splashscreen angezeigt oder befindet man sich im Replay
		// Modus kann das Spiel direkt geschlossen werden
		if (MainWindow.getInstance().getGamePrefs().getAppMode() == AppMode.FIRST_START
				| MainWindow.getInstance().getGamePrefs().getAppMode() == AppMode.REPLAY_MODE) {
			exitApplication();
		}

		// Gibt es keine ungespeicherten Änderungen kann das Spiel direkt
		// geschlossen werden
		if (!MainWindow.getInstance().getCommandStack().isStackChange())
			exitApplication();

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
		switch (MainWindow.getInstance().getGamePrefs().getAppMode()) {
		case LOAD_EDIT_MODE:
		case EDIT_MODE:
			saveAction = new SavePuzzleAction(null, null);
			break;
		case LOAD_GAME_MODE:
		case GAME_MODE:
			saveAction = new SaveGameAction(null, null);
			break;
		default:
			break;
		}
		if (saveAction != null)
			saveAction.actionPerformed(null);
	}
}
