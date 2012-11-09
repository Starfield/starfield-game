package game.commands;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Die Klasse CommandStack verwaltet die zwei Command Stacks (Play und Time Lapse) und ist au�erdem f�r die Funktionalit�t
 * der Marker und des R�cksetzens zum fehlerfreien Spielstand verantwortlich.
 * 
 * @author Nikolaj
 */
public class CommandStack implements Serializable {

	private static final long serialVersionUID = -3220534695170663622L;

	/** Liste f�r die Aufnahme aller Commands, zur Darstellung des Zeitraffers am Ende eines Spiels. */
	private ArrayList<TimeLapseItem> timeLapseStack = new ArrayList<TimeLapseItem>();
	
	/** Liste f�r die Aufnahme aller Commands um zu einem bestimmten Stand (Fehler oder Marker) zur�ckspringen zu k�nnen. */
	private ArrayList<AbstractCommand> playStack = new ArrayList<AbstractCommand>();
	
	/** Liste der f�nf individuell zu setzenden Marker. */
	private ArrayList<Integer> marker = new ArrayList<Integer>(5);
	
	/** Position des ersten Fehlers im Play Stack. */
	private int mistake = 0;
	
	/** Korrespondierendes Starfield */
	private File starfieldFile = null;
	
	/**
	 * Konstruktor
	 */
	public CommandStack() {
		
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param starfield
	 * - Geladenes Starfield
	 */
	public CommandStack(File starfield) {
		this.starfieldFile = starfield;
	}
	
	/**
	 * F�gt einen Command zum Time Lapse Stack hinzu.
	 * 
	 * @param command
	 *  - Vom User initiierter Command
	 *  
	 *  @param execute
	 *  - boolean ob es sich um einen execute Befehl handelt
	 */
	public void addTimeLapseCommand(AbstractCommand command, boolean execute) {
		timeLapseStack.add(new TimeLapseItem(command, execute));
	}
	
	/**
	 * F�gt einen Command zum Play Stack hinzu.
	 * 
	 * @param command
	 *  - Vom User initiierter Command
	 */
	public void addPlayCommand(AbstractCommand command) {
		playStack.add(command);
	}
	
	/**
	 * Setzt einen neuen Marker an der angegebenen Position in die Liste.
	 * 
	 * @param number
	 *  - �bergebener Marker (1-5)
	 */
	public void addMarker(int number) {
		marker.add(number, playStack.size());
	}
	
	/**
	 * Setzt einen vorhandenen Marker an der angegebenen Position zur�ck (Wert = 0).
	 * 
	 * @param number
	 *  - �bergebener Marker (1-5)
	 */
	public void deleteMarker(int number) {
		marker.set(number, 0);
	}
	
	/**
	 * L�scht alle gesetzten Marker.
	 */
	public void deleteMarkers() {
		// TODO: Marker aus den Playstack entfernen? (Time Lapse nicht)
		for (int i : marker) {
			marker.set(i, 0);
		}
	}
	
	/**
	 * Gibt den Wert des gew�nschten Markers (bzw. die Position im Play Stack) zur�ck.
	 * 
	 * @param nr
	 *  - �bergebener Marker (1-5)
	 *  
	 * @return Position im Play Stack
	 */
	public int getMarker(int nr) {
		return marker.get(nr);
	}
	
	/**
	 * Gibt den Wert des zuletzt gesetzten Markers zur�ck.
	 * 
	 * @return marker
	 * - Letzter gesetzter Marker
	 */
	public int getCurrentMarker() {
		for (int i = marker.size() - 1; i >= 0; i--) {
			if (marker.get(i) != 0) {
				return marker.get(i);
			}
		}
		return 0;
	}
	
	/**
	 * Sorgt daf�r, dass der Spielstand des zuletzt gesetzten Markers wiederhergestellt wird.
	 */
	public void undoMarker() {
		int j = playStack.size();
		
		for (int i = j - 1; i > (j - 1 - getMarker(getCurrentMarker())); i--) {
			playStack.get(i).undo();
			playStack.remove(i);
		}
	}
	
	/**
	 * Sorgt daf�r, dass der fehlerfreie Spielstand wiederhergestellt wird.
	 */
	public void undoMistake() {
		for (int i = playStack.size() - 1; i > (getMistake() - 2); i--) {
			playStack.get(i).undo();
			playStack.remove(i);
		}
	}

	/**
	 * Gibt die Position des ersten Fehlers im Play Stack zur�ck.
	 * 
	 * @return Position des ersten Fehlers im Play Stack
	 */
	public int getMistake() {
		return mistake;
	}

	/**
	 * F�llt das Attribut mistake, um zum letzten fehlerfreien Spielstand zur�ckkehren zu k�nnen.
	 */
	public void setMistake() {
		this.mistake = playStack.size();
	}
	
	/**
	 * F�hrt das Laden eines Spielstands durch.
	 */
	public void loadGame() {
		for (AbstractCommand c : playStack) {
			c.execute();
		}
	}

	/**
	 * Gibt das korrespondierende Starfield als File zur�ck.
	 * 
	 * @return starfieldFile
	 * - Korrespondierendes Starfield als File
	 */
	public File getStarfieldFile() {
		return starfieldFile;
	}
	
}
