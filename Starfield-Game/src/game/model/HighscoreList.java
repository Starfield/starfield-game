package game.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Diese Klasse speichert bis zu 10 Highscores zu einem Starfield.
 * 
 * @author Nikolaj
 */
public class HighscoreList implements Serializable {

	private static final long serialVersionUID = -540584146513677462L;
	
	/** Liste der 10 höchsten Punktzahlen. Länge wird nach dem Hinzufügen eines neuen Highscores gekürzt. */
	private ArrayList<Highscore> highscores = new ArrayList<Highscore>();
	
	/**
	 * Prüft, ob eine Punktzahl für die Highscoreliste ausreicht.
	 * 
	 * @param time
	 * - Gespielte Zeit
	 * @param attempts
	 * - Getätigte Versuche
	 * @return
	 * - <b>true</b>, falls in Highscoreliste, <b>false</b> falls nicht
	 */
	public boolean checkHighscore(long time, int attempts) {
		Highscore score = new Highscore(null, time, attempts);
		if (highscores.get(9).getPoints() < score.calculatePoints()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Fügt einen Highscore in die Liste der Highscores an entsprechender Stelle hinzu.
	 * Kürzt anschließen die Liste auf 10 Highscores. 
	 * 
	 * @param highscore
	 * - Hinzuzufügender Highscore
	 */
	public void addHighscore(Highscore highscore) {
		int i = 0;
		while (highscore.getPoints() < highscores.get(i).getPoints()) {
			i++;
		}
		highscores.add(i, highscore);
		
		for (int j = 10; j < highscores.size(); j++) {
			highscores.remove(i);
		}
	}

	/**
	 * Gibt die Highscore Liste zurück.
	 * 
	 * @return highscores
	 * - Liste der 10 Highscores
	 */
	public ArrayList<Highscore> getHighscores() {
		return highscores;
	}

}
