package game.commands;

import game.model.Field;

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
	
	/** Speichert die X-Koordinate des Felds, auf welches der Command ausgeführt wurde */
	private int xCoord = 0;

	/** Speichert die Y-Koordinate des Felds, auf welches der Command ausgeführt wurde */
	private int yCoord = 0;
	
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
		if (e.getSource() instanceof Field) {
			this.xCoord = ((Field)e.getSource()).getxPos();
			this.yCoord = ((Field)e.getSource()).getyPos();
		}
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
	 * Gibt die X-Koordinate des Felds, auf welches der Command ausgefüht wurde, zurück.
	 * 
	 * @return the xCoord
	 * - X-Koordinate
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * Gibt die Y-Koordinate des Felds, auf welches der Command ausgefüht wurde, zurück.
	 * 
	 * @return the yCoord
	 * - Y-Koordinate
	 */
	public int getyCoord() {
		return yCoord;
	}

}
