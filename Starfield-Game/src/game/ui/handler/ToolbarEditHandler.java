/**
 * 
 */
package game.ui.handler;

import game.model.Field.AllowedContent;
import game.ui.EditToolbar;
import game.ui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

			// Da Java 1.6 noch keine Strings switchen kann wird das hier
			// ein langer if-Block
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
	}
}
