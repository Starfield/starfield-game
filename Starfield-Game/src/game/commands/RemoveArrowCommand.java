package game.commands;

import game.model.Field;
import game.model.Field.AllowedContent;

import java.awt.AWTEvent;

/**
 * Der RemoveArrowCommand ist für das Entfernen eines Pfeils verantwortlich.
 * 
 * @author Nikolaj
 */
public class RemoveArrowCommand extends AbstractCommand {

	private static final long serialVersionUID = -5609261504063576278L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public RemoveArrowCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Entfernen eines Pfeils durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void execute() {
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_EMPTY);
		}
	}

}
