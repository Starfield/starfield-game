package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;

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
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_ARROW_R);
		}
	}

}
