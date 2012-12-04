/**
 * 
 */
package game.ui.handler;

import game.commands.TimeLapseThread;
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
	private TimeLapseThread _timeLapseThread = new TimeLapseThread();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent pE) {
		String cmd = pE.getActionCommand();
		_toolbar = (ReplayToolbar) MainWindow.getInstance().getActiveToolBar();

		if (_toolbar == null)
			return;

		if (cmd.equals("play")) {
			if (!_timeLapseThread.isAlive()) {
				_timeLapseThread.start();
			}
			else {
				_timeLapseThread.resume();
			}
		}
		
		if (cmd.equals("stop")) {
			_timeLapseThread.suspend();
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
		
		_timeLapseThread.setSpeed(_slider.getValue()*500);
	}

}
