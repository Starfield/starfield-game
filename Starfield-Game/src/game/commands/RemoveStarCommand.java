package game.commands;

import java.awt.AWTEvent;

import game.model.Field;
import game.model.Field.AllowedContent;
import game.ui.MainWindow;

/**
 * Der RemoveStarCommand ist für das Entfernen eines Sterns verantwortlich.
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
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public RemoveStarCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}

	/**
	 * Initiiert das Entfernen des Sterns durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void execute() {
		super.execute();
		((Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord())).setUserContent(AllowedContent.CONTENT_EMPTY);
	}

	/**
	 * Initiiert das Setzen eines Sterns durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void undo() {
		super.undo();
		((Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord())).setUserContent(AllowedContent.CONTENT_STAR);
	}
}
