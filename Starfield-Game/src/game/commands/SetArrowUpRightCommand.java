package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;
import game.ui.MainWindow;

import java.awt.AWTEvent;

/**
 * Der SetArrowUpRightCommand ist für das Setzen eines Pfeils nach oben rechts verantwortlich.
 * 
 * @author Nikolaj
 */
public class SetArrowUpRightCommand extends AbstractCommand {

	private static final long serialVersionUID = -1256876950243729502L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public SetArrowUpRightCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Setzen eines Pfeils nach oben rechts durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void execute() {
		((Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord())).setUserContent(AllowedContent.CONTENT_ARROW_UR);
	}

}
