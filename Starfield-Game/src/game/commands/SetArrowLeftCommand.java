package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;
import game.ui.MainWindow;

import java.awt.AWTEvent;

/**
 * Der SetArrowLeftCommand ist für das Setzen eines Pfeils nach links verantwortlich.
 * 
 * @author Nikolaj
 */
public class SetArrowLeftCommand extends AbstractCommand {

	private static final long serialVersionUID = -5726554831591382522L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public SetArrowLeftCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Setzen eines Pfeils nach links durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void execute() {
		((Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord())).setUserContent(AllowedContent.CONTENT_ARROW_L);
	}

}
