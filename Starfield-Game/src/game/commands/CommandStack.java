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
	private ArrayList<Integer> marker = new ArrayList<Integer>();
	
	/** Position des ersten Fehlers im Play Stack. */
	private int mistake = 0;
	
	/** Korrespondierendes Starfield */
	private File starfieldFile = null;
	
	/**
	 * Standardkonstruktor
	 */
	public CommandStack() {
		initMarker();
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param starfield
	 * - Geladenes Starfield
	 */
	public CommandStack(File starfield) {
		this.starfieldFile = starfield;
		initMarker();
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
	
	private void initMarker() {
		for (int i = 0; i < 5; i++) {
			marker.add(0);
		}
	}
	
	/**
	 * Gibt die ArrayList der Marker zur�ck.
	 * 
	 * @return marker
	 * - Marker ArrayList
	 */
	public ArrayList<Integer> getMarker() {
		return marker;
	}

	/**
	 * Setzt einen neuen Marker an der angegebenen Position in die Liste.
	 * 
	 * @param number
	 *  - �bergebener Marker (1-5)
	 */
	public void addMarker(int number) {
		if (0 <= number && number <= 4) {
			marker.set(number, playStack.size());
		}
	}
	
	/**
	 * Setzt einen vorhandenen Marker an der angegebenen Position zur�ck (Wert = 0).
	 * 
	 * @param number
	 *  - �bergebener Marker (1-5)
	 */
	public void deleteMarker(int number) {
		if (0 <= number && number <= 4) {
			marker.set(number, 0);
		}
	}
	
	/**
	 * L�scht alle gesetzten Marker.
	 */
	public void deleteMarkers() {
		for (int i = 0; i < marker.size(); i++) {
			marker.set(i, 0);
		}
	}
	
	/**
	 * Gibt den Wert des zuletzt gesetzten Markers zur�ck.
	 * 
	 * @return marker
	 * - Letzter gesetzter Marker
	 */
	public int locateCurrentMarker() {
		for (int i = 4; i >= 0; i--) {
			if (marker.get(i) != 0) {
				return marker.get(i);
			}
		}
		return 0;
	}
	
	/**
	 * Sorgt daf�r, dass auf einen vom Parameter <b>marker</b> abh�ngigen Spielstand zur�ckgesetzt wird.<br>
	 * <b>true</b> bedeutet, dass auf einen markierten, <b>false</b> auf einen fehlerfreien Spielstand zur�ckgesetzt werden soll.
	 *  
	 * @param marker
	 *  - true f�r Marker, false f�r Fehler
	 */
	public void goBack(boolean marker) {
		int stackSize = playStack.size();
		int type = 0;
		
		if (marker) {
			type = locateCurrentMarker();
		}
		else {
			type = getMistake();
		}
		
		for (int i = stackSize - 1; i >= stackSize - (stackSize - type + 1); i--) {
			playStack.get(i).undo();
			playStack.remove(i);
		}
	}
	
	/**
	 * F�hrt das Laden eines Spielstands durch.
	 */
	public void loadSavegame() {
		for (AbstractCommand command : playStack) {
			command.execute();
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
	 * Gibt das korrespondierende Starfield als File zur�ck.
	 * 
	 * @return starfieldFile
	 * - Korrespondierendes Starfield als File
	 */
	public File getStarfieldFile() {
		return starfieldFile;
	}

	/**
	 * Gibt den Time Lapse Stack zur�ck.
	 * 
	 * @return timeLapseStack
	 * - Time Lapse Stack
	 */
	public ArrayList<TimeLapseItem> getTimeLapseStack() {
		return timeLapseStack;
	}
	
}
