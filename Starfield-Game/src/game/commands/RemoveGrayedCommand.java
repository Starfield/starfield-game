package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;

import java.awt.AWTEvent;

/**
 * Das RemoveGrayedCommand ist für das Leeren eines Fields verantwortlich.
 * 
 * @author Nikolaj
 */
public class RemoveGrayedCommand extends AbstractCommand {

	private static final long serialVersionUID = -3291781300514149404L;
	
	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public RemoveGrayedCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Leeren eines Fields.
	 */
	@Override
	public void execute() {
		super.execute();
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_EMPTY);
		}
	}
	
	/**
	 * Initiiert das Ausgrauen eines Fields.
	 */
	@Override
	public void undo() {
		super.undo();
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_GRAYED);
		}
	}

}
