package game.commands;

import game.model.Field;

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
	
	/** Liste aller fehlerhaften Eingaben des Nutzers */
	private ArrayList<Mistake> mistake = new ArrayList<Mistake>();
	
	private ArrayList<AbstractCommand> corrections = new ArrayList<AbstractCommand>();
	
	/** Korrespondierendes Starfield */
	private File starfieldFile = null;
	
	/** Zeit, die der Nutzer ein Spiel spielt */
	private long time = 0L;
	
	/** Versuche, die der Nutzer bisher ben�tigt hat */
	private int attempts = 0;
	
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
	 * F�gt einen Fehler zur Fehler ArrayList hinzu.
	 * 
	 * @param mistake
	 * - Position des Fehlers im Playstack
	 */
	public void addMistake(Field field) {
		this.mistake.add(new Mistake(playStack.size(), field));
	}
	
	/**
	 * Ermittelt die Position im Playstack, an welchem der erste Fehler begangen wurde.
	 *  
	 * @return
	 * - Position des Fehlers im Playstack
	 */
	public int locateFirstMistake() {
		if (mistake.size() != 0) {
			return mistake.get(0).getStackPos();
		}
		return 0;
	}
	
	/**
	 * Entfernt den Fehler an der angegeben Position
	 * 
	 * @param pos
	 * - Position des Fehlers in der Fehler ArrayList
	 */
	public void removeMistake(int pos) {
		this.mistake.remove(pos);
	}
	
	/**
	 * Gibt das Fehler Array zur�ck.
	 * 
	 * @return mistake
	 * - Array mit Fehlern
	 */
	public ArrayList<Mistake> getMistake() {
		return mistake;
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
			type = locateFirstMistake();
		}
		
		for (int i = stackSize - 1; i >= stackSize - (stackSize - type + 1); i--) {
			playStack.get(i).undo();
			playStack.remove(i);
		}
		
		if (!marker) {
			for (int i = 0; i < mistake.size(); i++) {
				mistake.remove(i);
			}
			for (int i = 0; i < corrections.size(); i++) {
				corrections.get(i).execute();
			}
		}
	}
	
	/**
	 * F�hrt das Laden eines Spielstands durch.
	 */
	public void loadSavegame() {
		int stackSize = playStack.size();
		for (int i = 0; i < stackSize; i++) {
			playStack.get(i).execute();
		}
		
		for (int i = playStack.size() - 1; i >= stackSize; i--) {
			playStack.remove(i);		
		}
	}
	
	/**
	 * Z�hlt die Versuche, die der Spieler bisher zum L�sen des Spiels ben�tigte.
	 * 
	 * @return count
	 * - Versuche
	 */
	public int countAttempts() {
		int count = 0;
		
		for (int i = 0; i < timeLapseStack.size(); i++) {
			AbstractCommand command = timeLapseStack.get(i).getCommand();
			if (command instanceof SetStarCommand ||
				command instanceof RemoveStarCommand ||
				command instanceof SetGrayedCommand ||
				command instanceof RemoveGrayedCommand) {
				count++;
			}
		}
		return count;
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

	/**
	 * Gibt die Zeit, die der Nutzer spielt, zur�ck.
	 * 
	 * @return time
	 * - Vergangene Zeit
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Setzt die Zeit, die der Nutzer spielt.
	 * 
	 * @param time
	 * - Vergangene Zeit
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * Gibt die Anzahl der Versuche, die der Nutzer bisher ben�tigt hat, zur�ck.
	 * 
	 * @return attempts
	 */
	public int getAttempts() {
		return attempts;
	}

	/**
	 * Setzt die Anzahl der Versuche, die der Nutzer bisher ben�tigt hat.
	 * 
	 * @param attempts
	 * - Anzahl der Versuche
	 */
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	/**
	 * Gibt die im Spiel korrigierten Fehler zur�ck.
	 * 
	 * @return corrections
	 * - Korrigierte Fehler
	 */
	public ArrayList<AbstractCommand> getCorrections() {
		return corrections;
	}
	
}
