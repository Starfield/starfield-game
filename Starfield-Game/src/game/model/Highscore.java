package game.model;

import game.ui.MainWindow;

import java.io.Serializable;

/**
 * Diese Klasse speichert einen Highscoredatensatz.
 * 
 * @author Nikolaj
 */
public class Highscore implements Serializable {

	private static final long serialVersionUID = 6303659531368081229L;
	
	private String name;
	private long time;
	private int attempts;
	private int points;
	
	/**
	 * Konstruktor
	 * 
	 * @param name
	 * - Name des Spielers
	 * 
	 * @param time
	 * - Gespielte Zeit
	 * 
	 * @param attempts
	 * - Get�tigte Versuche
	 */
	public Highscore(String name, long time, int attempts) {
		this.name = name;
		this.time = time;
		this.attempts = attempts;
		calculatePoints();
	}

	/**
	 * Berechnet die Punktzahl anhand der Feldgr��e, der Spielzeit und den Versuchen, die ein Spieler ben�tigt hat.
	 * 
	 * @return
	 * - Punktzahl
	 */
	public int calculatePoints() {
		int fieldSize = MainWindow.getInstance().getCurrentStarfield().getSize().height * MainWindow.getInstance().getCurrentStarfield().getSize().width;
		int points = 0;
		int intTime = ((Long)time).intValue();
		int multiplier = calculateMultiplier(fieldSize);
		if (time != 0 && attempts != 0) {
			points = fieldSize*1/intTime*multiplier + fieldSize*1/attempts*multiplier; 
		}
		return points;
	}
	
	/**
	 * Berechnet den Multiplikator, welcher f�r die Punktzahl genutzt wird.
	 * 
	 * @param fieldSize
	 * - Feldgr��e
	 * 
	 * @return
	 * - Multiplikator
	 */
	private int calculateMultiplier(int fieldSize) {
		int i = 1;
		do {
			i = i*10;
		} while (fieldSize < i);
		return i;
	}
	
	/**
	 * Gibt den Namen des Spielers zur�ck.
	 * 
	 * @return name
	 * - Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gibt die Zeit des Spielers zur�ck.
	 * 
	 * @return time
	 * - Zeit
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * Gibt die Versuche des Spielers zur�ck.
	 * 
	 * @return attempts
	 * - Versuche
	 */
	public int getAttempts() {
		return attempts;
	}
	
	/**
	 * Gibt die Punktzahl des Spielers zur�ck.
	 * 
	 * @return points
	 * - Punktzahl
	 */
	public int getPoints() {
		return points;
	}

}
