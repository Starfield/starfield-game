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
	
	/** Liste der 10 h�chsten Punktzahlen. L�nge wird nach dem Hinzuf�gen eines neuen Highscores gek�rzt. */
	private ArrayList<Highscore> highscores = new ArrayList<Highscore>();
	
	/**
	 * Pr�ft, ob eine Punktzahl f�r die Highscoreliste ausreicht.
	 * 
	 * @param time
	 * - Gespielte Zeit
	 * @param attempts
	 * - Get�tigte Versuche
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
	 * F�gt einen Highscore in die Liste der Highscores an entsprechender Stelle hinzu.
	 * K�rzt anschlie�en die Liste auf 10 Highscores. 
	 * 
	 * @param highscore
	 * - Hinzuzuf�gender Highscore
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
	 * Gibt die Highscore Liste zur�ck.
	 * 
	 * @return highscores
	 * - Liste der 10 Highscores
	 */
	public ArrayList<Highscore> getHighscores() {
		return highscores;
	}

}
