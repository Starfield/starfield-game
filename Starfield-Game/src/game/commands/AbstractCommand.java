package game.commands;

import java.awt.AWTEvent;
import java.io.Serializable;

/**
 * Die Klasse AbstractCommand stellt sicher, dass aufgerufene Commands in die zwei Stacks eingetragen werden.
 * 
 * @author Nikolaj
 */
public abstract class AbstractCommand implements Serializable {
	
	private static final long serialVersionUID = -588990136956564122L;

	/** Speichert eine Instanz des Command Stacks */
	private CommandStack stacks = null;
	
	/** Speichert das beim Aufruf eines Commands erzeugte Event */
	private AWTEvent e = null;
	
	/**
	 * Konstruktor
	 * 
	 * @param stacks
	 *  - CommandStack Referenz
	 *  
	 * @param e
	 *  - Das den Command aufrufende Event
	 */
	public AbstractCommand(CommandStack stacks, AWTEvent e) {
		this.stacks = stacks;
		this.e = e;
	}
	
	/**
	 * Übernimmt die Eintragung des ausgeführten Commands in den Play Stack und den Time Lapse Stack.
	 */
	public void execute() {
		stacks.addPlayCommand(this);
		stacks.addTimeLapseCommand(this, true);
	}

	/**
	 * Übernimmt die Eintragung des ausgeführten Commands in den Time Lapse Stack.
	 */
	public void undo() {
		stacks.addTimeLapseCommand(this, false);
	}

	/**
	 * Gibt das CommandStack Element zurück.
	 * 
	 * @return CommandStack
	 */
	public CommandStack getStacks() {
		return stacks;
	}

	/**
	 * @param stacks
	 *  - Das CommandStack Element
	 */
	public void setStacks(CommandStack stacks) {
		this.stacks = stacks;
	}

	/**
	 * Gibt das Event des Aufrufers zurück.
	 * 
	 * @return Das Event
	 */
	public AWTEvent getE() {
		return e;
	}

	/**
	 * Setzt das Event des Aufrufers.
	 * 
	 * @param e
	 *  - Das Event
	 */
	public void setE(AWTEvent e) {
		this.e = e;
	}

}
