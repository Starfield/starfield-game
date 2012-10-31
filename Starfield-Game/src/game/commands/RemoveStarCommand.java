package game.commands;

import game.model.Field;
import game.model.Field.Content;

import java.awt.event.ActionEvent;

/**
 * Das RemoveStarCommand ist f�r das Entfernen eines Sterns verantwortlich.
 * 
 * @author Nikolaj
 */
public class RemoveStarCommand extends AbstractCommand {

	private static final long serialVersionUID = 5283066481581629117L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 */
	public RemoveStarCommand(CommandStack stacks, ActionEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Entfernen des Sterns durch die im Event �bergebene Quelle Field.
	 */
	@Override
	public void execute() {
		super.execute();
		if (this.getE().getSource() instanceof Field) {
			((Field)this.getE().getSource()).setUserContent(Content.EMPTY.getContent());			
		}
	}
	
	/**
	 * Initiiert das Setzen eines Sterns durch die im Event �bergebene Quelle Field.
	 */
	@Override
	public void undo() {
		super.undo();
		if (this.getE().getSource() instanceof Field) {
			((Field)this.getE().getSource()).setUserContent(Content.STAR.getContent());
		}
	}

}
