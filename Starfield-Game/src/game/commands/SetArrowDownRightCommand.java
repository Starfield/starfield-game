package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;

import java.awt.AWTEvent;

/**
 * Der SetArrowDownRightCommand ist f�r das Setzen eines Pfeils nach unten rechts verantwortlich.
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
	 * Initiiert das Setzen eines Pfeils nach unten rechts durch die im Event �bergebene Quelle Field.
	 */
	@Override
	public void execute() {
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_ARROW_DR);
		}
	}

}
