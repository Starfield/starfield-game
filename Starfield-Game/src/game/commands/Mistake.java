package game.commands;

import java.io.Serializable;

import game.model.Field;

/**
 * Diese Klasse bildet Objekte vom Typ Mistake ab.
 * Fehler werden in dieser Form gespeichert, da sie sich auf ein Feld beziehen und dieses gespeichert werden muss.
 * Die Position im Stack wird wiederum für das reibungslose Zurücksetzen zum fehlerfreien Stand benötigt.
 * 
 * @author Nikolaj
 */
public class Mistake implements Serializable {

	private static final long serialVersionUID = 1199451520364465550L;

	/** Position des Fehlers im Play Stack */
	private int stackPos;
	
	/** Feld, auf dem der Fehler auftrat */
	private Field field;

	public Mistake(int stackPos, Field field) {
		this.stackPos = stackPos;
		this.field = field;
	}
	
	/**
	 * Gibt die Position des Fehlers im Play Stack zurück.
	 * 
	 * @return stackPos
	 * - Position im Play Stack
	 */
	public int getStackPos() {
		return stackPos;
	}

	/**
	 * Setzt die Position im Play Stack, an der der Fehler auftrat.
	 * 
	 * @param stackPos
	 * - Position im Play Stack
	 */
	public void setStackPos(int stackPos) {
		this.stackPos = stackPos;
	}

	/**
	 * Gibt das Feld, auf dem der Fehler auftrat, zurück.
	 * 
	 * @return field
	 * - Feld des Fehlers
	 */
	public Field getField() {
		return field;
	}

	/**
	 * Setzt das Feld, auf dem der Fehler auftrat.
	 * 
	 * @param field
	 * - Feld des Fehlers
	 */
	public void setField(Field field) {
		this.field = field;
	}
}
