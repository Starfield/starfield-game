package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;

import java.awt.AWTEvent;

/**
 * Der SetArrowDownLeftCommand ist für das Setzen eines Pfeils nach unten links verantwortlich.
 * 
 * @author Nikolaj
 */
public class SetArrowDownLeft extends AbstractCommand {

	private static final long serialVersionUID = -8157046805641173807L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public SetArrowDownLeft(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Setzen eines Pfeils nach unten links durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void execute() {
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_ARROW_DL);
		}
	}

}
