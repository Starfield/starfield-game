/**
 * 
 */
package game.menubar.handler;

import game.core.GamePreferences.HelpMode;
import game.core.GamePreferences.LinealMode;
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
		String cmd = pEvent.getActionCommand();
		// Änderungen am Linealmodus abfragen
		if (cmd.equalsIgnoreCase("NO")) {
			MainWindow.getInstance().getGamePrefs()
					.setLinealMode(LinealMode.NO);
			return;
		}
		if (cmd.equalsIgnoreCase("CROSS")) {
			MainWindow.getInstance().getGamePrefs()
					.setLinealMode(LinealMode.CROSS);
			return;
		}
		if (cmd.equalsIgnoreCase("STAR")) {
			MainWindow.getInstance().getGamePrefs()
					.setLinealMode(LinealMode.STAR);
			return;
		}
		// Änderungen am Hilfsmodus abfragen
		if (cmd.equalsIgnoreCase("HELP_OFF")) {
			MainWindow.getInstance().getGamePrefs().setHelpMode(HelpMode.OFF);
			return;
		}
		if (cmd.equalsIgnoreCase("HELP_ON")) {
			MainWindow.getInstance().getGamePrefs().setHelpMode(HelpMode.ON);
			return;
		}
		// Änderung des Users in die Optionen schreiben
		MainWindow.getInstance().getGamePrefs()
				.setResolution(getSelectedResolution(cmd));

	}

	private Resolution getSelectedResolution(String cmd) {

		for (Resolution res : Resolution.values()) {
			if (cmd.equals(res.toString()))
				return res;
		}
		return null;
	}

}
