package game.commands;

import game.ui.MainWindow;
import game.ui.PlayToolbar;
import game.ui.ReplayToolbar;

import java.awt.AWTEvent;

/**
 * Der SetMarkerCommand ist für das Setzen eines Markers verantwortlich.
 * 
 * @author Nikolaj
 */
public class SetMarkerCommand extends AbstractCommand {

	private static final long serialVersionUID = 1321357760307364966L;

	/** Nummer des Markers */
	private final int number;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 * - CommandStack Referenz
	 * 
	 * @param e
	 * - Das den Command aufrufende Event
	 * 
	 * @param number
	 * - Nummer des Markers
	 */
	public SetMarkerCommand(CommandStack stacks, AWTEvent e, int number) {
		super(stacks, e);
		this.number = number;
	}

	/**
	 * Initiiert das Setzen eines Markers.
	 */
	@Override
	public void execute() {
		super.execute();
		getStacks().addMarker(number);
		if (MainWindow.getInstance().getActiveToolBar() instanceof PlayToolbar)
		{
			((PlayToolbar) MainWindow.getInstance().getActiveToolBar()).get_playHandler().setMarker();
		}
		else {
			((ReplayToolbar) MainWindow.getInstance().getActiveToolBar()).getReplayHandler().setMarker();
		}
	}

	/**
	 * Initiiert das Zurücksetzen eines Markers.
	 */
	@Override
	public void undo() {
		super.undo();
		getStacks().deleteMarker(number);
	}

}
