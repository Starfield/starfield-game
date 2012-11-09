package game.commands;

import java.awt.AWTEvent;

/**
 * Der RemoveMarkerCommand ist f�r das Zur�cksetzen auf den Spielstandes eines Markers verantwortlich.
 * 
 * @author Nikolaj
 */
public class RemoveMarkerCommand extends AbstractCommand {

	private static final long serialVersionUID = -198454667649613172L;
	
	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public RemoveMarkerCommand(CommandStack stacks, AWTEvent e) {
		super(stacks, e);
	}
	
	/**
	 * Initiiert das Zur�cksetzen auf den Spielstand des Markers.
	 */
	@Override
	public void execute() {
		getStacks().undoMarker();
	}

}
