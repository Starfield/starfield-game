package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;
import game.ui.MainWindow;

import java.awt.AWTEvent;

/**
 * Der SetArrowUpLeftCommand ist für das Setzen eines Pfeils nach oben links verantwortlich.
 * 
 * @author Nikolaj
 */
public class SetArrowUpLeftCommand extends AbstractCommand {

	private static final long serialVersionUID = -1867103282692154596L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public SetArrowUpLeftCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Setzen eines Pfeils nach oben links durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void execute() {
		((Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord())).setUserContent(AllowedContent.CONTENT_ARROW_UL);
	}

}
