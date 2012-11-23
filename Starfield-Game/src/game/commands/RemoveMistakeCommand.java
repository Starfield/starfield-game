package game.commands;

import java.awt.AWTEvent;

/**
 * Der RemoveMistakeCommand ist für das Zurücksetzen zum fehlerfreien Spielstand verantwortlich.
 * 
 * @author Nikolaj
 */
public class RemoveMistakeCommand extends AbstractCommand {

	private static final long serialVersionUID = -3626073673873740417L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public RemoveMistakeCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	@Override
	public void execute() {
		if (getStacks().locateLastMistake() != 0) {
			getStacks().goBack(false);			
		}
	}

}
