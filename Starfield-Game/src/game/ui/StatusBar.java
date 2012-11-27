/**
 * 
 */
package game.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Date;
import java.util.Formatter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Die Statusleiste des Spiels. Ermöglicht das Anzeigen verschiedener
 * Informationen. Am rechten Rand der Leiste befindet sich eine Aktionsanzeige
 * in Form einer JProgressBar, die von anderen Funktionen benutzt werden kann,
 * um Programmaktivität zu kennzeichnen.
 * 
 * @author Jan
 * 
 */
public class StatusBar extends JPanel {

	/** SerialID */
	private static final long serialVersionUID = 1L;

	/** Ladebalken */
	private JProgressBar _progress;
	/** Anzahl Moves */
	private JLabel _moves;
	/** Zeitanzeige */
	private JLabel _time;
	/** Startzeit */
	private final Date _startTime = new Date();
	/** der Zeitthread */
	private ClockThread _cThread;

	public StatusBar() {
		BorderLayout layout = new BorderLayout(2, 2);
		setLayout(layout);
		initPrefs();
		setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		stopProgress();
		startClock();
	}

	private void initPrefs() {
		// Anzahl Versuche erstellen
		add(createMovesAnzeige(), BorderLayout.WEST);

		// Zeit erstellen
		add(createZeitAnzeige(), BorderLayout.CENTER);

		// Ladebalken erstellen
		_progress = new JProgressBar(SwingConstants.HORIZONTAL);
		add(_progress, BorderLayout.EAST);
	}

	public void startProgress() {
		_progress.setIndeterminate(true);
		_progress.setVisible(true);

	}

	public void stopProgress() {
		_progress.setIndeterminate(false);
		_progress.setVisible(false);
	}

	private Component createMovesAnzeige() {
		JPanel panel = new JPanel();

		panel.add(new JLabel("Aktionen: "));
		_moves = new JLabel("0");
		panel.add(_moves);

		return panel;
	}

	/**
	 * Setzt den Versuchszähler um eins nach oben
	 */
	public void increaseMove() {
		int moves = Integer.parseInt(_moves.getText());
		_moves.setText(String.valueOf(moves + 1));
	}

	/**
	 * Liefert die Anzahl der benötigten Aktionen
	 * 
	 * @return - die Anzahl der Aktionen
	 */
	public String getMoveCount() {
		return _moves.getText();
	}

	/**
	 * Erstellt die Anzeige der bisher verbrauchten Zeit in einem Spiel
	 * 
	 * @return - die Zeitanzeige
	 */
	private Component createZeitAnzeige() {
		JPanel panel = new JPanel();

		// Erklärungstext
		panel.add(new JLabel("Zeit: "));
		// laufender Ticker
		_time = new JLabel();
		panel.add(_time);
		_cThread = new ClockThread();
		// sec anzeige
		panel.add(new JLabel(" Sekunden"));
		return panel;
	}

	/**
	 * Startet die Zeiterfassung der Uhr
	 */
	public void startClock() {
		_cThread.start();
	}

	/**
	 * Stoppt die Uhr und gibt die benötigte Zeit zurück
	 * 
	 * @return die benötigte Zeit
	 */
	public String stopClock() {
		_cThread.pause();
		return _time.getText();
	}

	public void close() {
		_cThread.interrupt();
	}

	/**
	 * Unterklasse zur Zeitmessung. Erweitert einen Thread der nebenher läuft
	 * und jede Sekunde das Label der Uhrenanzeige anpasst.
	 * 
	 * @author Jan
	 * 
	 */
	private class ClockThread extends Thread {

		private boolean fpause = false;

		/**
		 * Dient dem anhalten der Uhr und umgeht die Benutzung der stop()
		 * Methode von Thread
		 */
		public void pause() {
			fpause = true;
		}

		@Override
		public void run() {
			while (!isInterrupted()) {
				final Formatter formatter = new Formatter();
				try {

					final Date currentTime = new Date();
					final String time = String.format("%1$tM:%1$tS",
							currentTime.getTime() - _startTime.getTime())
							.toString();
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							_time.setText(String.valueOf(time));
						}
					});

					synchronized (this) {
						while (fpause) {
							try {
								wait();
							} catch (InterruptedException e) {
							}
						}
					}
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					interrupt();
					formatter.close();
				}
			}

		}
	}

}
