package game.commands;

import java.awt.AWTEvent;

import game.model.Field;
import game.model.Field.AllowedContent;
import game.ui.MainWindow;

/**
 * Der SetStarCommand ist für das Setzen eines Sterns verantwortlich.
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
		Field f = (Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord());
		f.setUserContent(AllowedContent.CONTENT_STAR);
		if (!f.IsCurrentContentRight()) {
			getStacks().addMistake();
		}
	}

	/**
	 * Initiiert das Entfernen des Sterns durch die im Event übergebene Quelle Field.
	 */
	@Override
	public void undo() {
		super.undo();
		((Field) MainWindow.getInstance().getCurrentStarfield().getField(getxCoord(), getyCoord())).setUserContent(AllowedContent.CONTENT_EMPTY);
	}

}
