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

		if (cmd.equals(Resolution.R800X600.toString()))
			return Resolution.R800X600;
		if (cmd.equals(Resolution.R1024X768.toString()))
			return Resolution.R1024X768;
		if (cmd.equals(Resolution.R1280X800.toString()))
			return Resolution.R1280X800;
		if (cmd.equals(Resolution.R1366X768.toString()))
			return Resolution.R1366X768;
		if (cmd.equals(Resolution.R1400X900.toString()))
			return Resolution.R1400X900;
		if (cmd.equals(Resolution.R1680X1050.toString()))
			return Resolution.R1680X1050;
		return null;
	}

}
