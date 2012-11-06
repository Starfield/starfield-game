package game.commands;

import java.io.Serializable;

/**
 * Die Klasse TimeLapseItem wird benötigt, da im Time Lapse Stack gespeichert werden muss, ob die execute oder die undo Methode eines Commands aufgerufen wurde.
 * 
 * @author Nikolaj
 */
public class TimeLapseItem implements Serializable {

	private static final long serialVersionUID = 8911805439059026960L;

	/** Aufgerufener Command */
	private AbstractCommand command = null;
	
	/** boolean ob es sich um einen execute Aufruf handelte */
	private boolean execute;
	
	/**
	 * Konstruktor
	 * 
	 * @param command
	 *  - Vom User initiierter Command
	 *  
	 *  @param execute
	 *  - boolean ob es sich um einen execute Befehl handelt
	 */
	public TimeLapseItem (AbstractCommand command, boolean execute) {
		this.command = command;
		this.execute = execute;
	}

	/**
	 * Gibt den Command zurück.
	 * 
	 * @return Den Command
	 */
	public AbstractCommand getCommand() {
		return command;
	}

	/**
	 * Gibt zurück ob es sich um einen execute Aufruf handelte oder nicht.
	 * 
	 * @return true für execute, false für undo
	 */
	public boolean isExecute() {
		return execute;
	}
	
}
