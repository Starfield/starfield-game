package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;
import game.ui.MainWindow;

import java.awt.AWTEvent;

/**
 * Der SetGrayedCommand ist für ausgrauen eines Fields verantwortlich.
 * 
 * @author Nikolaj
 */
public class SetGrayedCommand extends AbstractCommand {

	private static final long serialVersionUID = 4084113149367388520L;
	
	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public SetGrayedCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Ausgrauen eines Fields.
	 */
	@Override
	public void execute() {
		super.execute();
		Field f = (Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord());
		f.setUserContent(AllowedContent.CONTENT_GRAYED);
		if (!f.IsCurrentContentRight()) {
			getStacks().addMistake(f);
		}
	}
	
	/**
	 * Initiiert das Leeren eines Fields.
	 */
	@Override
	public void undo() {
		super.undo();
		((Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord())).setUserContent(AllowedContent.CONTENT_EMPTY);
	}

}
