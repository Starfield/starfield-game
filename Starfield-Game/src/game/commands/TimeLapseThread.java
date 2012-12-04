package game.commands;

import java.util.ArrayList;

import game.ui.MainWindow;

/**
 * Der TimeLapseThread ist ein neuer Thread, der für das Abspielen des Zeitraffers am Ende eines Spiels verantwortlich ist.
 * 
 * @author Nikolaj
 */
public class TimeLapseThread extends Thread {

	/** Speichert den Time Lapse Stack des zuvor gespielten Spiels */
	private ArrayList<TimeLapseItem> timeLapseStack = MainWindow.getInstance().getCommandStack().getTimeLapseStack();
	
	/** Anzahl der Commands im Time Lapse Stack */
	private int timeLapseSize = timeLapseStack.size();
	
	/** Geschwindigkeit des Zeitraffers */
	private int speed = 1500;
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		for (int i = 0; i < timeLapseSize; i++) {
			if (timeLapseStack.get(i).isExecute()) {
				timeLapseStack.get(i).getCommand().execute();	
			}
			else {
				timeLapseStack.get(i).getCommand().undo();
			}
			
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Gibt die Geschwindigkeit des Zeitraffers zurück. 
	 * 
	 * @return speed
	 * - Geschwindigkeit des Zeitraffers
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Setzt die Geschwindigkeit des Zeitraffers.
	 * 
	 * @param speed
	 * - Geschwindigkeit des Zeitraffers
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
