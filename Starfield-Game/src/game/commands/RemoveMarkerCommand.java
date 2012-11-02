package game.commands;

import java.awt.AWTEvent;

/**
 * Der RemoveMarkerCommand ist f�r das Zur�cksetzen auf den Spielstandes eines Markers verantwortlich.
 * 
 * @author Nikolaj
 */
public class RemoveMarkerCommand extends AbstractCommand {

	private static final long serialVersionUID = -198454667649613172L;

	/** Nummer des Markers */
	private int number;
	
	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 * 
	 * @param e
	 *  - Das den Command aufrufende Event
	 *  
	 *  @param number
	 *  - Nummer des Markers
	 */
	public RemoveMarkerCommand(CommandStack stacks, AWTEvent e, int number) {
		super(stacks, e);
		this.number = number;
	}
	
	/**
	 * Initiiert das Zur�cksetzen auf den Spielstand des Markers.
	 */
	@Override
	public void execute() {
		super.execute();
		if (getStacks().getMarker(number) != 0) {
			getStacks().undoMarker(number);
		}
	}

}
