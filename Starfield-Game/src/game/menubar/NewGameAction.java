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
 * Action für ein neues Spiel <br>
 * Durch die Action wird der Dialog zur Auswahl eines neues Puzzles ausgeführt.
 * Wird dieser mit OK bestätigt wird das alte Puzzle beendet und das neue Puzzle
 * geladen.
 * 
 * 
 * @author Jan
 * 
 */
public class NewGameAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Standardkonstruktur mit Text und Icon
	 * 
	 * @param text
	 *            - Name der Aktion
	 * @param icon
	 *            - AnzeigeIcon
	 */
	public NewGameAction(String text, ImageIcon icon) {
		super(text, icon);
		// Beispiel für weitere Properties zur Action
		putValue(SHORT_DESCRIPTION, "Ein neues Spiel starten");
		putValue(MNEMONIC_KEY, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pArg0) {
		// TODO
		// 1. NewGameDialog aufrufen
		// 2. Alten Inhalt des GameWindow leeren
		// 3. Neues Puzzle laden
		// Ändert zum Testen den AppMode und forced einen NeuAufbau des
		// Starfields.
		MainWindow.getGamePrefs().setAppMode(AppMode.GAME_MODE);
		MainWindow.getGamePrefs().getMainWindow().initGame();

	}
}
