/**
 * 
 */
package game.ui.handler;

import game.core.GamePreferences.AppMode;
import game.model.Field.AllowedContent;
import game.ui.EditToolbar;
import game.ui.MainWindow;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * @author Jan
 * 
 */
public class ToolbarEditHandler implements ActionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pEvent) {

		Object o = MainWindow.getInstance().getActiveToolBar();
		if (!(o instanceof EditToolbar))
			return;
		EditToolbar toolbar = (EditToolbar) o;

		/*
		 * Behandlung der Buttons aus der Gruppe "rechte Maustaste"
		 */
		if (pEvent.getActionCommand() == "ARROW_U")
			toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_U);
		if (pEvent.getActionCommand() == "ARROW_UR")
			toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_UR);
		if (pEvent.getActionCommand() == "ARROW_UL")
			toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_UL);
		if (pEvent.getActionCommand() == "ARROW_L")
			toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_L);
		if (pEvent.getActionCommand() == "ARROW_R")
			toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_R);
		if (pEvent.getActionCommand() == "ARROW_D")
			toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_D);
		if (pEvent.getActionCommand() == "ARROW_DR")
			toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_DR);
		if (pEvent.getActionCommand() == "ARROW_DL")
			toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_DL);
		if (pEvent.getActionCommand() == "EMPTY")
			toolbar.setSelectedArrow(AllowedContent.CONTENT_EMPTY);

		/*
		 * Behandlung der Buttons aus der Gruppe "Einstellungen"
		 */

		if (pEvent.getActionCommand() == "APPLY")
			handleApply(toolbar);
		if (pEvent.getActionCommand() == "CHECK")
			handleCheck(toolbar);

	}

	private void handleApply(EditToolbar pToolbar) {
		Object o = MainWindow.getInstance().getActiveToolBar();
		if (o instanceof EditToolbar) {
			int newXSize = ((EditToolbar) o).getInputSizeX();
			int newYSize = ((EditToolbar) o).getInputSizeY();
			if (newXSize < 3 | newYSize < 3) {
				// Minimalgröße ist 3x3
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,
						"Minimale Feldgröße beträgt 3x3 Felder",
						"Minimale Feldgröße unterschritten",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			// Um die Änderungen sichtbar zu machen, muss das Starfield neu
			// initialiert werden. Dazu wird das Starfield zwischengespeichert
			MainWindow
					.getInstance()
					.getGamePrefs()
					.setLoadedStarfield(
							MainWindow.getInstance().getCurrentStarfield()
									.changeSize(newXSize, newYSize));
			MainWindow.getInstance().getGamePrefs()
					.setAppMode(AppMode.LOAD_EDIT_MODE);
			MainWindow.getInstance().initGame();
		}

	}

	private void handleCheck(EditToolbar pToolbar) {
		pToolbar.setPlayable(MainWindow.getInstance().getCurrentStarfield()
				.checkPlayable(), false);
		pToolbar.changeDifficulty();
	}
}
