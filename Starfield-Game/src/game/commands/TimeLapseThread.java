package game.commands;

import java.util.ArrayList;

import game.ui.MainWindow;
import game.ui.ReplayToolbar;

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
	private int speed = 0;
	
	/**
	 * Konstruktor
	 * 
	 * @param slider
	 * - Position bzw. Geschwindigkeit des Sliders
	 */
	public TimeLapseThread(int slider) {
		setSpeed(slider);
		((ReplayToolbar)MainWindow.getInstance().getActiveToolBar()).setProgressMaximum(timeLapseStack.size());
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		((ReplayToolbar)MainWindow.getInstance().getActiveToolBar()).getReplayHandler().removeMarkers();
		for (int i = 0; i < timeLapseSize; i++) {
			if (timeLapseStack.get(i).isExecute()) {
				timeLapseStack.get(i).getCommand().execute();
				((ReplayToolbar)MainWindow.getInstance().getActiveToolBar()).setProgressTick();
//				timeLapseStack.remove(timeLapseStack.size()-1);
			}
			else {
				timeLapseStack.get(i).getCommand().undo();
				((ReplayToolbar)MainWindow.getInstance().getActiveToolBar()).setProgressTick();
//				timeLapseStack.remove(timeLapseStack.size()-1);
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
		switch (speed) {
		case 1:
			this.speed = 800;
			break;
		case 2:
			this.speed = 500;
			break;
		case 3:
			this.speed = 400;
			break;
		case 4:
			this.speed = 300;
			break;
		case 5:
			this.speed = 100;
			break;
		default:
			this.speed = 300;
			break;
		};
	}
}
