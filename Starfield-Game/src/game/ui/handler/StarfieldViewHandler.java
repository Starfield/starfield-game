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
import game.core.GamePreferences.LinealMode;
import game.model.Field;
import game.model.Field.AllowedContent;
import game.model.Starfield;
import game.ui.EditToolbar;
import game.ui.MainWindow;
import game.ui.PlayToolbar;
import game.ui.StatusBar;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.LineBorder;

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

	}

	@Override
	public void mouseEntered(MouseEvent pE) {
		Object o = pE.getSource();
		LineBorder blackBorder = new LineBorder(Color.BLACK, 1);
		LineBorder grayBorder = new LineBorder(Color.LIGHT_GRAY, 1);

		if (o instanceof Field) {
			Field selField = (Field) o;
			Starfield starfield = MainWindow.getInstance()
					.getCurrentStarfield();
			if (starfield == null)
				return;
			if (MainWindow.getInstance().getGamePrefs().getLinealMode() != LinealMode.NO) {
				// Selektiertes Feld mit schwarzem Hintergrund setzen
				((Field) o).setBorder(blackBorder);
				// Alle Felder nach oben grau umranden
				Field nextField = selField;
				while ((nextField = starfield.getField_U(nextField)) != null) {
					nextField.setBorder(grayBorder);

				}
				// ALle Felder nach unten umranden
				nextField = selField;
				while ((nextField = starfield.getField_D(nextField)) != null) {
					nextField.setBorder(grayBorder);

				}
				// ALle Felder nach links umranden
				nextField = selField;
				while ((nextField = starfield.getField_L(nextField)) != null) {
					nextField.setBorder(grayBorder);

				}
				// ALle Felder nach rechts umranden
				nextField = selField;
				while ((nextField = starfield.getField_R(nextField)) != null) {
					nextField.setBorder(grayBorder);

				}

				// Zusätzlich wenn STAR ausgewählt
				if (MainWindow.getInstance().getGamePrefs().getLinealMode() == LinealMode.STAR) {
					// ALle Felder nach links oben umranden
					nextField = selField;
					while ((nextField = starfield.getField_UL(nextField)) != null) {
						nextField.setBorder(grayBorder);

					}
					// ALle Felder nach links unten umranden
					nextField = selField;
					while ((nextField = starfield.getField_DL(nextField)) != null) {
						nextField.setBorder(grayBorder);

					}
					// ALle Felder nach rechts oben umranden
					nextField = selField;
					while ((nextField = starfield.getField_UR(nextField)) != null) {
						nextField.setBorder(grayBorder);

					}
					// ALle Felder nach rechts unten umranden
					nextField = selField;
					while ((nextField = starfield.getField_DR(nextField)) != null) {
						nextField.setBorder(grayBorder);

					}
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent pE) {

		Object o = pE.getSource();
		LineBorder whiteBorder = new LineBorder(Color.WHITE, 1);

		if (o instanceof Field) {
			Field selField = (Field) o;
			Starfield starfield = MainWindow.getInstance()
					.getCurrentStarfield();
			if (starfield == null)
				return;

			if (MainWindow.getInstance().getGamePrefs().getLinealMode() != LinealMode.NO) {

				// Selektiertes Feld Umrandung löschen
				selField.setBorder(whiteBorder);

				// Alle Felder nach oben Umrandung wegmachen
				Field nextField = selField;
				while ((nextField = starfield.getField_U(nextField)) != null) {
					nextField.setBorder(whiteBorder);

				}
				// Alle Felder nach unten Umrandung wegmachen
				nextField = selField;
				while ((nextField = starfield.getField_D(nextField)) != null) {
					nextField.setBorder(whiteBorder);

				}
				// Alle Felder nach links Umrandung wegmachen
				nextField = selField;
				while ((nextField = starfield.getField_L(nextField)) != null) {
					nextField.setBorder(whiteBorder);

				}
				// Alle Felder nach rechts Umrandung wegmachen
				nextField = selField;
				while ((nextField = starfield.getField_R(nextField)) != null) {
					nextField.setBorder(whiteBorder);

				}

				// Zusätzlich wenn STAR ausgewählt
				if (MainWindow.getInstance().getGamePrefs().getLinealMode() == LinealMode.STAR) {
					// ALle Felder nach links oben umranden
					nextField = selField;
					while ((nextField = starfield.getField_UL(nextField)) != null) {
						nextField.setBorder(whiteBorder);

					}
					// ALle Felder nach links unten umranden
					nextField = selField;
					while ((nextField = starfield.getField_DL(nextField)) != null) {
						nextField.setBorder(whiteBorder);

					}
					// ALle Felder nach rechts oben umranden
					nextField = selField;
					while ((nextField = starfield.getField_UR(nextField)) != null) {
						nextField.setBorder(whiteBorder);

					}
					// ALle Felder nach rechts unten umranden
					nextField = selField;
					while ((nextField = starfield.getField_DR(nextField)) != null) {
						nextField.setBorder(whiteBorder);

					}
				}

			}
		}
	}

	@Override
	public void mousePressed(MouseEvent pE) {
		Object o = pE.getSource();
		// Zur Sicherheit wird überprüft ob das aufrufende Element ein Field von
		// unserem Modell ist
		if (o instanceof game.model.Field) {
			// Es wird ein leeres Command erstellt, das im folgenden mit dem
			// passenden Command gefüllt wird.
			AbstractCommand command = null;

			// Abhängig von der AppMode wird das passende Command festgelegt
			switch (MainWindow.getInstance().getGamePrefs().getAppMode()) {
			case GAME_MODE:
			case LOAD_GAME_MODE:
				command = handlePlayEvent((Field) o, pE);
				break;
			case EDIT_MODE:
			case LOAD_EDIT_MODE:
				command = handleEditEvent((Field) o, pE);
				break;
			default:
				break;
			}

			// Ist das passende Command zur gewünschten Aktion ermittelt worden,
			// kann es ausgeführt werden.
			if (command != null) {
				command.execute();
				// Gibt es eine Statusbar, werden die SpielerAktionen
				// aktualisiert
				o = MainWindow.getInstance().getStatusBar();
				if (o instanceof StatusBar)
					((StatusBar) o).increaseMove();
				// Im Spielmodus wird überprüft ob das Spiel gewonnen ist
				o = MainWindow.getInstance().getActiveToolBar();
				if (o instanceof PlayToolbar) {
					((PlayToolbar) o).get_playHandler().checkInput();
				}
				// Im Editmodus wird überprüft, ob das Spielfeld lösbar ist
				if (o instanceof EditToolbar)
					((EditToolbar) o).setPlayable(MainWindow.getInstance()
							.getCurrentStarfield().checkPlayable(), true);
			}

		}
	}

	/**
	 * Bestimmt welches Command im GameMode ausgeführt werden muss
	 * 
	 * @return
	 */
	private AbstractCommand handlePlayEvent(Field field, MouseEvent pE) {

		if (pE.getButton() == MouseEvent.BUTTON1) {
			if (field.getUserContent() == AllowedContent.CONTENT_EMPTY)
				return new SetStarCommand(MainWindow.getInstance()
						.getCommandStack(), pE);
			else if (field.getUserContent() == AllowedContent.CONTENT_STAR)
				return new RemoveStarCommand(MainWindow.getInstance()
						.getCommandStack(), pE);
		}
		if (pE.getButton() == MouseEvent.BUTTON3) {
			if (field.getUserContent() == AllowedContent.CONTENT_EMPTY)
				return new SetGrayedCommand(MainWindow.getInstance()
						.getCommandStack(), pE);
			else if (field.getUserContent() == AllowedContent.CONTENT_GRAYED)
				return new RemoveGrayedCommand(MainWindow.getInstance()
						.getCommandStack(), pE);
		}
		return null;
	}

	/**
	 * Bestimmt welches Command im EditMode ausgeführt werden soll
	 * 
	 * @param field
	 * @param pE
	 * @return
	 */
	private AbstractCommand handleEditEvent(Field field, MouseEvent pE) {
		Object o = MainWindow.getInstance().getActiveToolBar();
		if (o instanceof EditToolbar) {
			EditToolbar toolbar = (EditToolbar) o;

			// Linke Maustaste
			if (pE.getButton() == MouseEvent.BUTTON1) {
				if (field.getUserContent() == AllowedContent.CONTENT_EMPTY) {
					return new SetStarCommand(MainWindow.getInstance()
							.getCommandStack(), pE);
				} else if (field.getUserContent() == AllowedContent.CONTENT_STAR) {
					return new RemoveStarCommand(MainWindow.getInstance()
							.getCommandStack(), pE);
				}
			}
			// Rechte Maustaste
			if (pE.getButton() == MouseEvent.BUTTON3) {

				// Überprüfen ob das Leerfeld ausgewählt ist
				if (toolbar.getSelectedArrow() == AllowedContent.CONTENT_EMPTY) {

					if (field.getUserContent().toString()
							.startsWith("CONTENT_ARROW")) {
						return new RemoveArrowCommand(MainWindow.getInstance()
								.getCommandStack(), pE);
					}

				}

				// Überprüfen ob im field ein anderer Pfeil ist, als ausgewählt
				// wurde
				if (field.getUserContent() != toolbar.getSelectedArrow()
						&& field.getUserContent() != AllowedContent.CONTENT_STAR) {
					switch (toolbar.getSelectedArrow()) {
					case CONTENT_ARROW_UL:
						return new SetArrowUpLeftCommand(MainWindow
								.getInstance().getCommandStack(), pE);
					case CONTENT_ARROW_U:
						return new SetArrowUpCommand(MainWindow.getInstance()
								.getCommandStack(), pE);
					case CONTENT_ARROW_UR:
						return new SetArrowUpRightCommand(MainWindow
								.getInstance().getCommandStack(), pE);
					case CONTENT_ARROW_L:
						return new SetArrowLeftCommand(MainWindow.getInstance()
								.getCommandStack(), pE);
					case CONTENT_ARROW_R:
						return new SetArrowRightCommand(MainWindow
								.getInstance().getCommandStack(), pE);
					case CONTENT_ARROW_DL:
						return new SetArrowDownLeft(MainWindow.getInstance()
								.getCommandStack(), pE);
					case CONTENT_ARROW_D:
						return new SetArrowDownCommand(MainWindow.getInstance()
								.getCommandStack(), pE);
					case CONTENT_ARROW_DR:
						return new SetArrowDownRightCommand(MainWindow
								.getInstance().getCommandStack(), pE);
					default:
						break;
					}
				} else if (field.getUserContent() != AllowedContent.CONTENT_STAR)
					return new RemoveArrowCommand(MainWindow.getInstance()
							.getCommandStack(), pE);

			}
		}
		return null;
	}

	@Override
	public void mouseReleased(MouseEvent pE) {

	}

}
