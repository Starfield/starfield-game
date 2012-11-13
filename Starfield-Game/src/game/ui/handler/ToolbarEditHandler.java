/**
 * 
 */
package game.ui.handler;

import game.model.Field.AllowedContent;
import game.ui.EditToolbar;
import game.ui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		Object o = MainWindow.getActiveToolBar();
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
		/*
		 * TODO Ändern der Größe des Starfields. Die Usereingaben können über
		 * MainWindow.getActiveToolbar().getInputSizeX() und
		 * MainWindow.getActiveToolbar().getInputSizeY() bekommen werden (Casten
		 * in EditToolbar nicht vergessen)
		 */
		System.out
				.println("SizeX: "
						+ ((EditToolbar) MainWindow.getActiveToolBar())
								.getInputSizeX());
		System.out
				.println("SizeY: "
						+ ((EditToolbar) MainWindow.getActiveToolBar())
								.getInputSizeY());

	}

	private void handleCheck(EditToolbar pToolbar) {
		pToolbar.setPlayable(MainWindow.getStarfieldView()
				.getCurrentStarfield().checkPlayable());
	}
}
