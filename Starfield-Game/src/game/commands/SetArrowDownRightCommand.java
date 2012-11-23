package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;
import game.ui.MainWindow;

import java.awt.AWTEvent;

/**
 * Der SetArrowDownRightCommand ist für das Setzen eines Pfeils nach unten rechts verantwortlich.
 * 
 * @author Nikolaj
 */
public class SetArrowDownRightCommand extends AbstractCommand {

	private static final long serialVersionUID = 6545750686703901481L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public SetArrowDownRightCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Setzen eines Pfeils nach unten rechts durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void execute() {
		((Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord())).setUserContent(AllowedContent.CONTENT_ARROW_DR);
	}

}
