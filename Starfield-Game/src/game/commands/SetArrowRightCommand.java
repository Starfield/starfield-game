package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;
import game.ui.MainWindow;

import java.awt.AWTEvent;

/**
 * Der SetArrowRightCommand ist für das Setzen eines Pfeils nach rechts verantwortlich.
 * 
 * @author Nikolaj
 */
public class SetArrowRightCommand extends AbstractCommand {

	private static final long serialVersionUID = 1034722387228874457L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public SetArrowRightCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Setzen eines Pfeils nach rechts durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void execute() {
		((Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord())).setUserContent(AllowedContent.CONTENT_ARROW_R);
	}

}
