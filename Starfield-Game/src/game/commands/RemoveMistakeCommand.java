package game.commands;

import game.ui.MainWindow;
import game.ui.PlayToolbar;

import java.awt.AWTEvent;

/**
 * Der RemoveMistakeCommand ist f�r das Zur�cksetzen zum fehlerfreien Spielstand verantwortlich.
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
		super.execute();
		if (getStacks().locateFirstMistake() != 0) {
			getStacks().goBack(false);
			if (MainWindow.getInstance().getActiveToolBar() instanceof PlayToolbar) {
				((PlayToolbar)MainWindow.getInstance().getActiveToolBar()).get_playHandler().removeMarkers();
			}
		}
	}

}
