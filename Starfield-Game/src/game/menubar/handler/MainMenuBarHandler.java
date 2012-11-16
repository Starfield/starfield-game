/**
 * 
 */
package game.menubar.handler;

import game.core.GamePreferences.Resolution;
import game.ui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jan
 * 
 */
public class MainMenuBarHandler implements ActionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pEvent) {
		// Änderung des Users in die Optionen schreiben
		MainWindow.getInstance().getGamePrefs()
				.setResolution(getSelectedResolution(pEvent));

	}

	private Resolution getSelectedResolution(ActionEvent pEvent) {
		String cmd = pEvent.getActionCommand();

		for (Resolution res : Resolution.values()) {
			if (cmd.equals(res.toString()))
				return res;
		}
		return null;
	}

}
