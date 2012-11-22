/**
 * 
 */
package game.ui.handler;

import game.ui.MainWindow;
import game.ui.ReplayToolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Jan
 * 
 */
public class ToolbarReplayHandler implements ActionListener, ChangeListener {

	private ReplayToolbar _toolbar = null;
	private JSlider _slider = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pE) {
		String cmd = pE.getActionCommand();
		_toolbar = (ReplayToolbar) MainWindow.getInstance().getActiveToolBar();

		if (_toolbar == null)
			return;

		if (cmd.equals("play")) {
			// TODO Event zum Starten implementieren
			System.out.println("Play gedrückt");
		}
		if (cmd.equals("stop")) {
			// TODO Event zum stoppen implementieren
			System.out.println("Stop gedrückt");
		}

	}

	@Override
	public void stateChanged(ChangeEvent pE) {
		Object o = pE.getSource();
		if (o instanceof JSlider) {
			_slider = (JSlider) o;
		}

		if (_slider == null)
			return;

		// TODO Event zum Geschwindigkeitsändern einbauen
		System.out.println("Geschwindigkeit " + _slider.getValue()
				+ " gewählt.");
		// Das Event wird bei einem Klick dreimal ausgelöst, warum weiss ich
		// nicht. Musst sehen ob das deiner Logik egal ist, oder ob dadurch sehr
		// viel unnötiges im Hintergrund passiert. Die Werte des Sliders kannst
		// du anpassen wie du willst, kann ich dir bei Bedarf aber auch nochmal
		// zeigen, die liegen im Moment noch direkt bei der Erstellung des
		// Sliders in der ReplayToolbar, man kann die aber auch in die GamePrefs
		// ziehen wenn wir das lieber ist.
	}

}
