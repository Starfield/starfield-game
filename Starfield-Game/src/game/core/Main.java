/**
 * 
 */
package game.core;

import game.ui.MainWindow;

/**
 * StarterKlasse für das Projekt <br>
 * In dieser Klasse werden die Rahmenbedingungen gesetzt und das Hauptfenster
 * der Anwendung erstellt
 * 
 * @author Jan
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Singleton-Aufruf
		MainWindow.getInstance();

	}

}
