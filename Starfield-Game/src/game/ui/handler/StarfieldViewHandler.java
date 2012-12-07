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
import game.core.GamePreferences.HelpMode;
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
import java.util.HashSet;
import java.util.Set;

import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

/**
 * Der StarfieldViewHandler reagiert auf UserEvents auf dem Starfield. <br>
 * Bei einem Mausklick auf dem Spielfeld werden hier die entsprechenden Commands
 * ausgel�st.
 * 
 * @author Jan
 * 
 */
public class StarfieldViewHandler implements MouseListener {

	// Border f�r MouseEntered
	LineBorder blackBorder = new LineBorder(Color.BLACK, 1);
	LineBorder linealBorder = new LineBorder(Color.BLUE, 1);
	LineBorder helpBorder = new LineBorder(Color.GREEN, 1);
	LineBorder redLightBorder = new LineBorder(new Color(200, 95, 95), 1);

	// Border f�r MouseExited
	LineBorder whiteBorder = new LineBorder(Color.WHITE, 1);
	LineBorder redBorder = new LineBorder(Color.RED, 1);

	@Override
	public void mouseClicked(MouseEvent pE) {

	}

	@Override
	public void mouseEntered(MouseEvent pE) {
		Object o = pE.getSource();

		if (o instanceof Field) {
			Field selField = (Field) o;
			Starfield starfield = MainWindow.getInstance()
					.getCurrentStarfield();
			if (starfield == null)
				return;

			JToolBar toolbar = MainWindow.getInstance().getActiveToolBar();
			Set<Field> shownErrorFields = null;
			if (toolbar instanceof EditToolbar)
				shownErrorFields = ((EditToolbar) toolbar).getErrorFields();
			// Gibts keine Fehlerfelder wird einfach ein leeres erstellt
			if (shownErrorFields == null)
				shownErrorFields = new HashSet<Field>();

			if (MainWindow.getInstance().getGamePrefs().getLinealMode() != LinealMode.NO) {
				// Selektiertes Feld mit schwarzem Hintergrund setzen
				if (shownErrorFields.contains(selField))
					selField.setBorder(new LineBorder(new Color(240, 75, 75), 1));
				else
					selField.setBorder(blackBorder);
				// Alle Felder nach oben grau umranden
				Field nextField = selField;
				while ((nextField = starfield.getField_U(nextField)) != null) {
					if (shownErrorFields.contains(nextField))
						nextField.setBorder(redLightBorder);
					else
						nextField.setBorder(linealBorder);
				}
				// ALle Felder nach unten umranden
				nextField = selField;
				while ((nextField = starfield.getField_D(nextField)) != null) {
					if (shownErrorFields.contains(nextField))
						nextField.setBorder(redLightBorder);
					else
						nextField.setBorder(linealBorder);

				}
				// ALle Felder nach links umranden
				nextField = selField;
				while ((nextField = starfield.getField_L(nextField)) != null) {
					if (shownErrorFields.contains(nextField))
						nextField.setBorder(redLightBorder);
					else
						nextField.setBorder(linealBorder);

				}
				// ALle Felder nach rechts umranden
				nextField = selField;
				while ((nextField = starfield.getField_R(nextField)) != null) {
					if (shownErrorFields.contains(nextField))
						nextField.setBorder(redLightBorder);
					else
						nextField.setBorder(linealBorder);

				}

				// Zus�tzlich wenn STAR ausgew�hlt
				if (MainWindow.getInstance().getGamePrefs().getLinealMode() == LinealMode.STAR) {
					// ALle Felder nach links oben umranden
					nextField = selField;
					while ((nextField = starfield.getField_UL(nextField)) != null) {
						if (shownErrorFields.contains(nextField))
							nextField.setBorder(redLightBorder);
						else
							nextField.setBorder(linealBorder);

					}
					// ALle Felder nach links unten umranden
					nextField = selField;
					while ((nextField = starfield.getField_DL(nextField)) != null) {
						if (shownErrorFields.contains(nextField))
							nextField.setBorder(redLightBorder);
						else
							nextField.setBorder(linealBorder);

					}
					// ALle Felder nach rechts oben umranden
					nextField = selField;
					while ((nextField = starfield.getField_UR(nextField)) != null) {
						if (shownErrorFields.contains(nextField))
							nextField.setBorder(redLightBorder);
						else
							nextField.setBorder(linealBorder);

					}
					// ALle Felder nach rechts unten umranden
					nextField = selField;
					while ((nextField = starfield.getField_DR(nextField)) != null) {
						if (shownErrorFields.contains(nextField))
							nextField.setBorder(redLightBorder);
						else
							nextField.setBorder(linealBorder);

					}
				}
			}

			if (MainWindow.getInstance().getGamePrefs().getHelpMode() == HelpMode.ON) {
				switch (selField.getUserContent()) {
				case CONTENT_STAR:
					// Im Falle eines Sterns m�ssen alle Felder auf Pfeile
					// durchsucht werden, die in Richtung des Sterns zeigen
					highlightArrows(selField, starfield);
					break;
				case CONTENT_EMPTY:
				case CONTENT_GRAYED:
					// Diese Felder brauchen nicht gesondert beachtet werden
					break;
				default:
					// Die �brigbleibenden Felder sind Felder mit Pfeilen drin,
					// hier werden die Felder gehighlightet auf die der Pfeil
					// zeigt.
					highlightLane(selField, starfield);
					break;
				}
			}
		}
	}

	private void highlightArrows(Field selField, Starfield starfield) {
		// Felder nach oben durchsuchen
		Field nextField = selField;
		while ((nextField = starfield.getField_U(nextField)) != null) {
			if (nextField.getUserContent() == AllowedContent.CONTENT_ARROW_D) {
				nextField.setBorder(helpBorder);
			}
		}
		// Felder nach oben-rechts durchsuchen
		nextField = selField;
		while ((nextField = starfield.getField_UR(nextField)) != null) {
			if (nextField.getUserContent() == AllowedContent.CONTENT_ARROW_DL) {
				nextField.setBorder(helpBorder);
			}
		}
		// Felder nach rechts durchsuchen
		nextField = selField;
		while ((nextField = starfield.getField_R(nextField)) != null) {
			if (nextField.getUserContent() == AllowedContent.CONTENT_ARROW_L) {
				nextField.setBorder(helpBorder);
			}
		}
		// Felder nach unten-rechts durchsuchen
		nextField = selField;
		while ((nextField = starfield.getField_DR(nextField)) != null) {
			if (nextField.getUserContent() == AllowedContent.CONTENT_ARROW_UL) {
				nextField.setBorder(helpBorder);
			}
		}
		// Felder nach unten durchsuchen
		nextField = selField;
		while ((nextField = starfield.getField_D(nextField)) != null) {
			if (nextField.getUserContent() == AllowedContent.CONTENT_ARROW_U) {
				nextField.setBorder(helpBorder);
			}
		}
		// Felder nach unten-links durchsuchen
		nextField = selField;
		while ((nextField = starfield.getField_DL(nextField)) != null) {
			if (nextField.getUserContent() == AllowedContent.CONTENT_ARROW_UR) {
				nextField.setBorder(helpBorder);
			}
		}
		// Felder nach links durchsuchen
		nextField = selField;
		while ((nextField = starfield.getField_L(nextField)) != null) {
			if (nextField.getUserContent() == AllowedContent.CONTENT_ARROW_R) {
				nextField.setBorder(helpBorder);
			}
		}
		// Felder nach oben-links durchsuchen
		nextField = selField;
		while ((nextField = starfield.getField_UL(nextField)) != null) {
			if (nextField.getUserContent() == AllowedContent.CONTENT_ARROW_DR) {
				nextField.setBorder(helpBorder);
			}
		}

	}

	private void highlightLane(Field selField, Starfield starfield) {
		// Nach der Art des Pfeils werden alle Zellen in der Richtung
		// gekennzeichnet
		Field nextField;
		switch (selField.getUserContent()) {
		case CONTENT_ARROW_U:
			nextField = selField;
			while ((nextField = starfield.getField_U(nextField)) != null) {
				nextField.setBorder(helpBorder);
			}
			break;
		case CONTENT_ARROW_UR:
			nextField = selField;
			while ((nextField = starfield.getField_UR(nextField)) != null) {
				nextField.setBorder(helpBorder);
			}
			break;
		case CONTENT_ARROW_R:
			nextField = selField;
			while ((nextField = starfield.getField_R(nextField)) != null) {
				nextField.setBorder(helpBorder);
			}
			break;
		case CONTENT_ARROW_DR:
			nextField = selField;
			while ((nextField = starfield.getField_DR(nextField)) != null) {
				nextField.setBorder(helpBorder);
			}
			break;
		case CONTENT_ARROW_D:
			nextField = selField;
			while ((nextField = starfield.getField_D(nextField)) != null) {
				nextField.setBorder(helpBorder);
			}
			break;
		case CONTENT_ARROW_DL:
			nextField = selField;
			while ((nextField = starfield.getField_DL(nextField)) != null) {
				nextField.setBorder(helpBorder);
			}
			break;
		case CONTENT_ARROW_L:
			nextField = selField;
			while ((nextField = starfield.getField_L(nextField)) != null) {
				nextField.setBorder(helpBorder);
			}
			break;
		case CONTENT_ARROW_UL:
			nextField = selField;
			while ((nextField = starfield.getField_UL(nextField)) != null) {
				nextField.setBorder(helpBorder);
			}
			break;
		default:
			return;
		}

	}

	@Override
	public void mouseExited(MouseEvent pE) {

		Object o = pE.getSource();

		if (o instanceof Field) {
			Field selField = (Field) o;
			Starfield starfield = MainWindow.getInstance()
					.getCurrentStarfield();
			if (starfield == null)
				return;
			JToolBar toolbar = MainWindow.getInstance().getActiveToolBar();
			Set<Field> shownErrorFields = null;
			if (toolbar instanceof EditToolbar)
				shownErrorFields = ((EditToolbar) toolbar).getErrorFields();
			// Gibts keine Fehlerfelder wird einfach ein leeres erstellt
			if (shownErrorFields == null)
				shownErrorFields = new HashSet<Field>();

			if (MainWindow.getInstance().getGamePrefs().getLinealMode() != LinealMode.NO) {

				// Selektiertes Feld Umrandung l�schen
				if (shownErrorFields.contains(selField))
					selField.setBorder(redBorder);
				else
					selField.setBorder(whiteBorder);

				// Alle Felder nach oben Umrandung wegmachen
				Field nextField = selField;
				while ((nextField = starfield.getField_U(nextField)) != null) {
					if (shownErrorFields.contains(nextField))
						nextField.setBorder(redBorder);
					else
						nextField.setBorder(whiteBorder);

				}
				// Alle Felder nach unten Umrandung wegmachen
				nextField = selField;
				while ((nextField = starfield.getField_D(nextField)) != null) {
					if (shownErrorFields.contains(nextField))
						nextField.setBorder(redBorder);
					else
						nextField.setBorder(whiteBorder);

				}
				// Alle Felder nach links Umrandung wegmachen
				nextField = selField;
				while ((nextField = starfield.getField_L(nextField)) != null) {
					if (shownErrorFields.contains(nextField))
						nextField.setBorder(redBorder);
					else
						nextField.setBorder(whiteBorder);

				}
				// Alle Felder nach rechts Umrandung wegmachen
				nextField = selField;
				while ((nextField = starfield.getField_R(nextField)) != null) {
					if (shownErrorFields.contains(nextField))
						nextField.setBorder(redBorder);
					else
						nextField.setBorder(whiteBorder);

				}

				// Zus�tzlich wenn STAR ausgew�hlt
				if (MainWindow.getInstance().getGamePrefs().getLinealMode() == LinealMode.STAR) {
					// ALle Felder nach links oben umranden
					nextField = selField;
					while ((nextField = starfield.getField_UL(nextField)) != null) {
						if (shownErrorFields.contains(nextField))
							nextField.setBorder(redBorder);
						else
							nextField.setBorder(whiteBorder);

					}
					// ALle Felder nach links unten umranden
					nextField = selField;
					while ((nextField = starfield.getField_DL(nextField)) != null) {
						if (shownErrorFields.contains(nextField))
							nextField.setBorder(redBorder);
						else
							nextField.setBorder(whiteBorder);

					}
					// ALle Felder nach rechts oben umranden
					nextField = selField;
					while ((nextField = starfield.getField_UR(nextField)) != null) {
						if (shownErrorFields.contains(nextField))
							nextField.setBorder(redBorder);
						else
							nextField.setBorder(whiteBorder);

					}
					// ALle Felder nach rechts unten umranden
					nextField = selField;
					while ((nextField = starfield.getField_DR(nextField)) != null) {
						if (shownErrorFields.contains(nextField))
							nextField.setBorder(redBorder);
						else
							nextField.setBorder(whiteBorder);

					}
				}

			}

			if (MainWindow.getInstance().getGamePrefs().getHelpMode() == HelpMode.ON) {
				// Alle Felder werden durchlaufen, hat ein Feld einen Rand vom
				// HelpModus so wird der alte Rand wieder gesetzt
				for (int x = 0; x < starfield.getSize().width; x++) {
					for (int y = 0; y < starfield.getSize().height; y++) {
						final Field f = starfield.getField(x, y);
						if (f.getBorder() == helpBorder) {
							if (shownErrorFields.contains(f))
								f.setBorder(redBorder);
							else
								f.setBorder(whiteBorder);
						}

					}
				}
			}
		}
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

			// Ist das passende Command zur gew�nschten Aktion ermittelt worden,
			// kann es ausgef�hrt werden.
			if (command != null) {
				command.execute();
				// Gibt es eine Statusbar, werden die SpielerAktionen
				// aktualisiert
				o = MainWindow.getInstance().getStatusBar();
				if (o instanceof StatusBar)
					((StatusBar) o).increaseMove();
				// Im Spielmodus wird �berpr�ft ob das Spiel gewonnen ist
				o = MainWindow.getInstance().getActiveToolBar();
				if (o instanceof PlayToolbar) {
					((PlayToolbar) o).get_playHandler().checkInput();
				}
				// Im Editmodus wird �berpr�ft, ob das Spielfeld l�sbar ist
				if (o instanceof EditToolbar)
					((EditToolbar) o).setPlayable(MainWindow.getInstance()
							.getCurrentStarfield().checkPlayable().size() == 0,
							true);
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
	 * Bestimmt welches Command im EditMode ausgef�hrt werden soll
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

				// �berpr�fen ob das Leerfeld ausgew�hlt ist
				if (toolbar.getSelectedArrow() == AllowedContent.CONTENT_EMPTY) {

					if (field.getUserContent().toString()
							.startsWith("CONTENT_ARROW")) {
						return new RemoveArrowCommand(MainWindow.getInstance()
								.getCommandStack(), pE);
					}

				}

				// �berpr�fen ob im field ein anderer Pfeil ist, als ausgew�hlt
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
