package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;

import java.awt.AWTEvent;

/**
 * Der SetArrowDownCommand ist für das Setzen eines Pfeils nach unten verantwortlich.
 * 
 * @author Nikolaj
 */
public class SetArrowDownCommand extends AbstractCommand {

	private static final long serialVersionUID = -5180458938846150481L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public SetArrowDownCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Setzen eines Pfeils nach unten durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void execute() {
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_ARROW_D);
		}
	}

}
