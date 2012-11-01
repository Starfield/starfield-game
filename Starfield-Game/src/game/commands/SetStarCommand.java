package game.commands;

import java.awt.AWTEvent;

import game.model.Field;
import game.model.Field.AllowedContent;


/**
 * Das SetStarCommand ist für das Setzen eines Sterns verantwortlich.
 * 
 * @author Nikolaj
 */
public class SetStarCommand extends AbstractCommand {

	private static final long serialVersionUID = 1936391762275520778L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public SetStarCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}

	/**
	 * Initiiert das Setzen eines Sterns durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void execute() {
		super.execute();
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_STAR);
		}
	}

	/**
	 * Initiiert das Entfernen des Sterns durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void undo() {
		super.undo();
		if (this.getE().getSource() instanceof Field) {
			((Field) this.getE().getSource()).setUserContent(AllowedContent.CONTENT_EMPTY);
		}
	}

}
