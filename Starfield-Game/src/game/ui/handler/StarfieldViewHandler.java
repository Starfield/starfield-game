/**
 * 
 */
package game.ui.handler;

import game.commands.AbstractCommand;
import game.commands.RemoveArrowCommand;
import game.commands.RemoveGrayedCommand;
import game.commands.RemoveStarCommand;
import game.commands.SetArrowDownCommand;
import game.commands.SetArrowDownLeft;
import game.commands.SetArrowDownRightCommand;
import game.commands.SetArrowLeftCommand;
import game.commands.SetArrowRightCommand;
import game.commands.SetArrowUpCommand;
import game.commands.SetArrowUpLeftCommand;
import game.commands.SetArrowUpRightCommand;
import game.commands.SetGrayedCommand;
import game.commands.SetStarCommand;
import game.model.Field;
import game.model.Field.AllowedContent;
import game.ui.EditToolbar;
import game.ui.MainWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Der StarfieldViewHandler reagiert auf UserEvents auf dem Starfield. <br>
 * Bei einem Mausklick auf dem Spielfeld werden hier die entsprechenden Commands
 * ausgel�st.
 * 
 * @author Jan
 * 
 */
public class StarfieldViewHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent pE) {

	}

	@Override
	public void mouseEntered(MouseEvent pE) {

	}

	@Override
	public void mouseExited(MouseEvent pE) {

	}

	@Override
	public void mousePressed(MouseEvent pE) {
		Object o = pE.getSource();
		// Zur Sicherheit wird �berpr�ft ob das aufrufende Element ein Field von
		// unserem Modell ist
		if (o instanceof game.model.Field) {
			// Es wird ein leeres Command erstellt, das im folgenden mit dem
			// passenden Command gef�llt wird.
			AbstractCommand command = null;

			// Abh�ngig von der AppMode wird das passende Command festgelegt
			switch (MainWindow.getGamePrefs().getAppMode()) {
			case GAME_MODE:
			case LOAD_GAME_MODE:
				command = handlePlayEvent((Field) o, pE);
				break;
			case EDIT_MODE:
			case LOAD_EDIT_MODE:
				command = handleEditEvent((Field) o, pE);
				break;
			}

			// Ist das passende Command zur gew�nschten Aktion ermittelt worden,
			// kann es ausgef�hrt werden.
			if (command != null) {
				command.execute();
			}

		}
	}

	/**
	 * Bestimmt welches Command im GameMode ausgef�hrt werden muss
	 * 
	 * @return
	 */
	private AbstractCommand handlePlayEvent(Field field, MouseEvent pE) {

		if (pE.getButton() == MouseEvent.BUTTON1) {
			if (field.getUserContent() == AllowedContent.CONTENT_EMPTY)
				return new SetStarCommand(MainWindow.getCommandStack(), pE);
			else if (field.getUserContent() == AllowedContent.CONTENT_STAR)
				return new RemoveStarCommand(MainWindow.getCommandStack(), pE);
		}
		if (pE.getButton() == MouseEvent.BUTTON3) {
			if (field.getUserContent() == AllowedContent.CONTENT_EMPTY)
				return new SetGrayedCommand(MainWindow.getCommandStack(), pE);
			else if (field.getUserContent() == AllowedContent.CONTENT_GRAYED)
				return new RemoveGrayedCommand(MainWindow.getCommandStack(), pE);
		}
		return null;
	}

	/**
	 * Bestimmt welches Command im EditMode ausgef�hrt werden soll
	 * 
	 * @param field
	 * @param pE
	 * @return
	 */
	private AbstractCommand handleEditEvent(Field field, MouseEvent pE) {
		Object o = MainWindow.getActiveToolBar();
		if (o instanceof EditToolbar) {
			EditToolbar toolbar = (EditToolbar) o;

			// Linke Maustaste
			if (pE.getButton() == MouseEvent.BUTTON1) {
				if (field.getUserContent() == AllowedContent.CONTENT_EMPTY) {
					toolbar.setPlayable(false);
					return new SetStarCommand(MainWindow.getCommandStack(), pE);
				} else if (field.getUserContent() == AllowedContent.CONTENT_STAR) {
					toolbar.setPlayable(false);
					return new RemoveStarCommand(MainWindow.getCommandStack(),
							pE);
				}
			}
			// Rechte Maustaste
			if (pE.getButton() == MouseEvent.BUTTON3) {

				// �berpr�fen ob das Leerfeld ausgew�hlt ist
				if (toolbar.getSelectedArrow() == AllowedContent.CONTENT_EMPTY) {

					if (field.getUserContent().toString()
							.startsWith("CONTENT_ARROW")) {
						toolbar.setPlayable(false);
						return new RemoveArrowCommand(
								MainWindow.getCommandStack(), pE);
					}

				}

				// �berpr�fen ob im field ein anderer Pfeil ist, als ausgew�hlt
				// wurde
				if (field.getUserContent() != toolbar.getSelectedArrow()
						&& field.getUserContent() != AllowedContent.CONTENT_STAR) {
					switch (toolbar.getSelectedArrow()) {
					case CONTENT_ARROW_UL:
						toolbar.setPlayable(false);
						return new SetArrowUpLeftCommand(
								MainWindow.getCommandStack(), pE);
					case CONTENT_ARROW_U:
						toolbar.setPlayable(false);
						return new SetArrowUpCommand(
								MainWindow.getCommandStack(), pE);
					case CONTENT_ARROW_UR:
						toolbar.setPlayable(false);
						return new SetArrowUpRightCommand(
								MainWindow.getCommandStack(), pE);
					case CONTENT_ARROW_L:
						toolbar.setPlayable(false);
						return new SetArrowLeftCommand(
								MainWindow.getCommandStack(), pE);
					case CONTENT_ARROW_R:
						toolbar.setPlayable(false);
						return new SetArrowRightCommand(
								MainWindow.getCommandStack(), pE);
					case CONTENT_ARROW_DL:
						toolbar.setPlayable(false);
						return new SetArrowDownLeft(
								MainWindow.getCommandStack(), pE);
					case CONTENT_ARROW_D:
						toolbar.setPlayable(false);
						return new SetArrowDownCommand(
								MainWindow.getCommandStack(), pE);
					case CONTENT_ARROW_DR:
						toolbar.setPlayable(false);
						return new SetArrowDownRightCommand(
								MainWindow.getCommandStack(), pE);
					}
				} else if (field.getUserContent() != AllowedContent.CONTENT_STAR) {
					toolbar.setPlayable(false);
					return new RemoveArrowCommand(MainWindow.getCommandStack(),
							pE);
				}

			}
		}
		return null;
	}

	@Override
	public void mouseReleased(MouseEvent pE) {

	}

}
