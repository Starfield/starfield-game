/**
 * 
 */
package game.ui;

import game.commands.CommandStack;

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
	private JLabel _movesLabel;
	/** Zeitanzeige */
	private JLabel _timeLabel;
	/** Zeit */
	private long _time;
	/** bisher gebrauchte Zeit beim laden */
	private long _timeOffset;
	private int _moves;
	/** bisher gebrauchte Aktionen beim Laden */
	private int _movesOffset;
	/** Startzeit */
	private final Date _startTime = new Date();
	/** der Zeitthread */
	private ClockThread _cThread;

	public StatusBar() {
		BorderLayout layout = new BorderLayout(2, 2);
		setLayout(layout);
		// Zeit und Aktionen aus dem CommandStack laden
		Object o = MainWindow.getInstance().getCommandStack();
		if (o instanceof CommandStack) {
			_timeOffset = ((CommandStack) o).getTime();
			_movesOffset = ((CommandStack) o).getAttempts();
		}
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
		_movesLabel = new JLabel(String.valueOf(_movesOffset));
		panel.add(_movesLabel);

		return panel;
	}

	/**
	 * Setzt den Versuchszähler um eins nach oben
	 */
	public void increaseMove() {
		_moves += 1;
		_movesLabel.setText(getMoveCount());
		// Zeit in CommandStack speichern
		Object o = MainWindow.getInstance().getCommandStack();
		if (o instanceof CommandStack) {
			((CommandStack) o).setAttempts(_moves + _movesOffset);
		}
	}

	/**
	 * Liefert die Anzahl der benötigten Aktionen
	 * 
	 * @return - die Anzahl der Aktionen
	 */
	public String getMoveCount() {
		int sumMoves = _moves + _movesOffset;
		return String.valueOf(sumMoves);
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
		_timeLabel = new JLabel();
		panel.add(_timeLabel);
		_cThread = new ClockThread();
		return panel;
	}

	/**
	 * Startet die Zeiterfassung der Uhr
	 */
	public void startClock() {
		_cThread.start();
		// _moves += _moveOffset;
	}

	/**
	 * Stoppt die Uhr und gibt die benötigte Zeit zurück
	 * 
	 * @return die benötigte Zeit
	 */
	public String stopClock() {
		_cThread.pause();
		return _timeLabel.getText();
	}

	public void setTimeOffset(long time) {
		_timeOffset = time;
	}

	public void setMovesOffset(int moves) {
		_movesOffset = moves;
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
					_time = currentTime.getTime() - _startTime.getTime();
					_time += _timeOffset;
					// Total hässliches unperformantes Setzen der Zeit in den
					// Commandstack
					Object o = MainWindow.getInstance().getCommandStack();
					if (o instanceof CommandStack) {
						((CommandStack) o).setTime(_time);
					}
					final String timeString = String.format("%1$tM:%1$tS",
							_time).toString();
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							_timeLabel.setText(String.valueOf(timeString));
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
