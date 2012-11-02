package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;

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
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_GRAYED);
		}
	}
	
	/**
	 * Initiiert das Leeren eines Fields.
	 */
	@Override
	public void undo() {
		super.undo();
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_EMPTY);
		}
	}

}
