package game.commands;

import game.ui.MainWindow;
import game.ui.PlayToolbar;

import java.awt.AWTEvent;

/**
 * Der RemoveMarkerCommand ist für das Zurücksetzen auf den Spielstandes eines
 * Markers verantwortlich.
 * 
 * @author Nikolaj
 */
public class RemoveMarkerCommand extends AbstractCommand {

	private static final long serialVersionUID = -198454667649613172L;

	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *            - CommandStack Referenz
	 * 
	 * @param e
	 *            - Das den Command aufrufende Event
	 */
	public RemoveMarkerCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}

	/**
	 * Initiiert das Zurücksetzen auf den Spielstand des Markers.
	 */
	@Override
	public void execute() {
		super.execute();
		getStacks().goBack(true);
		((PlayToolbar) MainWindow.getInstance().getActiveToolBar())
				.get_playHandler().removeSingleMarker();
	}

}
