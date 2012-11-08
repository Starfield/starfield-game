/**
 * 
 */
package game.ui.handler;

import game.model.Field.AllowedContent;
import game.ui.EditToolbar;
import game.ui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

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

		Object o = pEvent.getSource();
		if (o instanceof JToggleButton) {
			JToggleButton button = (JToggleButton) o;
			o = MainWindow.getActiveToolBar();
			if (!(o instanceof EditToolbar))
				return;
			EditToolbar toolbar = (EditToolbar) o;

			/*
			 * Behandlung der Buttons aus der Gruppe "rechte Maustaste"
			 */
			if (button.getName() == "ARROW_U")
				toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_U);
			if (button.getName() == "ARROW_UR")
				toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_UR);
			if (button.getName() == "ARROW_UL")
				toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_UL);
			if (button.getName() == "ARROW_L")
				toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_L);
			if (button.getName() == "ARROW_R")
				toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_R);
			if (button.getName() == "ARROW_D")
				toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_D);
			if (button.getName() == "ARROW_DR")
				toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_DR);
			if (button.getName() == "ARROW_DL")
				toolbar.setSelectedArrow(AllowedContent.CONTENT_ARROW_DL);
		}
		if (o instanceof JButton) {
			JButton button = (JButton) o;
			/*
			 * Behandlung der Buttons aus der Gruppe "Einstellungen"
			 */

			if (button.getName() == "APPLY")
				handleApply(pEvent);
			if (button.getName() == "CHECK")
				handleCheck(pEvent);
		}
	}

	private void handleApply(ActionEvent pEvent) {
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

	private void handleCheck(ActionEvent pEvent) {
		/*
		 * TODO Check anstoßen ob das Starfield als spielbar gilt. Das Starfield
		 * kann über MainWindow.getStarfieldView().getCurrentStarfield()
		 * bekommen werden. Hier muss eine Methode erstellt werden, die
		 * überprüft ob das Modell so korrekt ist. Das Ergebnis der Überprüfung
		 * muss über MainWindow.getActiveToolbar().setPlayable(boolean) wieder
		 * zurück an die Toolbar geschickt werden.
		 */

	}
}
