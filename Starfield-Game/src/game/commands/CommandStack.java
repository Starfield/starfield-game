package game.commands;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Die Klasse CommandStack verwaltet die zwei Command Stacks (Play und Time Lapse) und ist außerdem für die Funktionalität
 * der Marker und des Rücksetzens zum fehlerfreien Spielstand verantwortlich.
 * 
 * @author Nikolaj
 */
public class CommandStack implements Serializable {

	private static final long serialVersionUID = -3220534695170663622L;

	/** Liste für die Aufnahme aller Commands, zur Darstellung des Zeitraffers am Ende eines Spiels. */
	private ArrayList<AbstractCommand> timeLapseStack = new ArrayList<AbstractCommand>();
	
	/** Liste für die Aufnahme aller Commands um zu einem bestimmten Stand (Fehler oder Marker) zurückspringen zu können. */
	private ArrayList<AbstractCommand> playStack = new ArrayList<AbstractCommand>();
	
	/** Liste der fünf individuell zu setzenden Marker. */
	private ArrayList<Integer> marker = new ArrayList<Integer>(5);
	
	/** Position des ersten Fehlers im Play Stack. */
	private int mistake;
	
	/**
	 * Fügt einen Command zum Time Lapse Stack hinzu.
	 * 
	 * @param command
	 *  - Vom User initiierter Command
	 */
	public void addTimeLapseCommand(AbstractCommand command) {
		timeLapseStack.add(command);
	}
	
	/**
	 * Fügt einen Command zum Play Stack hinzu.
	 * 
	 * @param command
	 *  - Vom User initiierter Command
	 */
	public void addPlayCommand(AbstractCommand command) {
		playStack.add(command);
	}
	
	/**
	 * Setzt einen neuen Marker an der angegebenen Position in der Liste.
	 */
	public void addMarker() {
		marker.add(playStack.size());
	}
	
	/**
	 * Löscht alle gesetzten Marker.
	 */
	public void deleteMarkers() {
		marker.clear();
	}
	
	/**
	 * Gibt den Wert des gewünschten Markers (bzw. die Position im Play Stack) zurück.
	 * 
	 * @param nr
	 *  - Übergebener Marker (1-5)
	 *  
	 * @return Position im Play Stack
	 */
	private int getMarker(int nr) {
		return marker.get(nr);
	}
	
	/**
	 * Sorgt dafür, dass der Spielstand des gewählten Markers wiederhergestellt wird.
	 * 
	 * @param nr
	 *  - Übergebener Marker (1-5)
	 */
	public void undoMarker(int nr) {
		for (int i = playStack.size()-1; i > (playStack.size() - 1 - getMarker(nr)); i--) {
			playStack.get(i).undo();
			playStack.remove(i);
		}
	}
	
	/**
	 * Sorgt dafür, dass der fehlerfreie Spielstand wiederhergestellt wird.
	 */
	public void undoMistake() {
		for (int i = playStack.size()-1; i > (playStack.size() - 1 - getMistake()); i--) {
			playStack.get(i).undo();
			playStack.remove(i);
		}
	}

	/**
	 * Gibt die Position des ersten Fehlers im Play Stack zurück.
	 * 
	 * @return Position des ersten Fehlers im Play Stack
	 */
	private int getMistake() {
		return mistake;
	}

	/**
	 * Füllt das Attribut mistake, um zum letzten fehlerfreien Spielstand zurückkehren zu können.
	 */
	public void setMistake() {
		this.mistake = playStack.size();
	}
	
	/**
	 * Führt das Laden eines Spielstands durch.
	 */
	public void loadGame() {
		for (AbstractCommand c : playStack) {
			c.execute();
		}
	}
	
}
