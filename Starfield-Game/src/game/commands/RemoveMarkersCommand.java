package game.commands;

import game.ui.MainWindow;
import game.ui.PlayToolbar;
import game.ui.ReplayToolbar;

import java.awt.AWTEvent;

/**
 * Der SetStarCommand ist für das Zurücksetzen aller Marker verantwortlich.
 * 
 * @author Nikolaj
 */
public class RemoveMarkersCommand extends AbstractCommand {

	private static final long serialVersionUID = 4011301584079746726L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 * - CommandStack Referenz
	 * 
	 * @param e
	 * - Das den Command aufrufende Event
	 */
	public RemoveMarkersCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}

	/**
	 * Initiiert das Zurücksetzen aller Marker.
	 */
	@Override
	public void execute() {
		super.execute();
		getStacks().deleteMarkers();
		if (MainWindow.getInstance().getActiveToolBar() instanceof PlayToolbar)
		{
			((PlayToolbar) MainWindow.getInstance().getActiveToolBar()).get_playHandler().removeMarkers();
		}
		else {
			((ReplayToolbar) MainWindow.getInstance().getActiveToolBar()).getReplayHandler().removeMarkers();
		}
	}

}
