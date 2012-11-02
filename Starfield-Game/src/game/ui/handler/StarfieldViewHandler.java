/**
 * 
 */
package game.ui.handler;

import game.commands.AbstractCommand;
import game.commands.RemoveStarCommand;
import game.commands.SetStarCommand;
import game.model.Field.AllowedContent;
import game.ui.MainWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Der StarfieldViewHandler reagiert auf UserEvents auf dem Starfield. <br>
 * Bei einem Mausklick auf dem Spielfeld werden hier die entsprechenden Commands
 * ausgelöst.
 * 
 * @author Jan
 * 
 */
public class StarfieldViewHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent pE) {
		Object o = pE.getSource();
		// Zur Sicherheit wird überprüft ob das aufrufende Element ein Field von
		// unserem Modell ist
		if (o instanceof game.model.Field) {
			// Es wird ein leeres Command erstellt, das im folgenden mit dem
			// passenden Command gefüllt wird.
			AbstractCommand command = null;
			/*
			 * Wird die linke Maustaste geklickt, wird ein Stern im Feld
			 * gesetzt. Ist bereits ein Stern im Feld drin, wird dieser
			 * entfernt.
			 */
			if (pE.getButton() == MouseEvent.BUTTON1) {
				if (((game.model.Field) o).getUserContent() == AllowedContent.CONTENT_EMPTY)
					command = new SetStarCommand(MainWindow.getCommandStack(),
							pE);
				else if (((game.model.Field) o).getUserContent() == AllowedContent.CONTENT_STAR)
					command = new RemoveStarCommand(
							MainWindow.getCommandStack(), pE);
			}
			if (pE.getButton() == MouseEvent.BUTTON3) {
				if (((game.model.Field) o).getUserContent() == AllowedContent.CONTENT_EMPTY)
					((game.model.Field) o)
							.setUserContent(AllowedContent.CONTENT_GRAYED);
				else if (((game.model.Field) o).getUserContent() == AllowedContent.CONTENT_GRAYED)
					((game.model.Field) o)
							.setUserContent(AllowedContent.CONTENT_EMPTY);
			}
			if (command != null)
				command.execute();
		}

	}

	@Override
	public void mouseEntered(MouseEvent pE) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent pE) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent pE) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent pE) {
		// TODO Auto-generated method stub

	}

}
