package game.ui.handler;

import game.commands.TimeLapseThread;
import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.ui.MainWindow;
import game.ui.ReplayToolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Nikolaj
 * 
 */
public class ToolbarReplayHandler implements ActionListener, ChangeListener {

	private ReplayToolbar _toolbar = null;
	private JSlider _slider = null;
	private TimeLapseThread _timeLapseThread = null;
	private ArrayList<JLabel> _markerList = null;
	private JLabel l = null;

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
		_markerList = _toolbar.getMarkerList();

		if (_toolbar == null)
			return;

		if (cmd.equals("play")) {
			if (_timeLapseThread == null) {
				if (_slider != null) {
					_timeLapseThread = new TimeLapseThread(_slider.getValue());
				}
				else {
					_timeLapseThread = new TimeLapseThread(3);
				}
			}
			switch (_timeLapseThread.getState()) {
			case NEW:
				_timeLapseThread.start();
				break;
			case TIMED_WAITING:
				_timeLapseThread.resume();
				break;
			case TERMINATED:
				_timeLapseThread = new TimeLapseThread(_slider.getValue());
				MainWindow.getInstance().getCurrentStarfield().clearUserContent().prepareUserContent(true);
				_timeLapseThread.start();
				break;
			default:
				break;
			}
		}
		
		if (cmd.equals("stop")) {
			_timeLapseThread.suspend();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent pE) {
		Object o = pE.getSource();
		if (o instanceof JSlider) {
			_slider = (JSlider) o;
		}

		if (_slider == null)
			return;
		
		if (_timeLapseThread != null) {
			_timeLapseThread.setSpeed(_slider.getValue());
		}
	}

	public void setMarker() {
		_markerList.get(firstOff()).setIcon(
				ImageResources.getIcon(Images.ICON_MARKER_ON));
	}

	public void removeSingleMarker() {
		_markerList.get(lastOn()).setIcon(
				ImageResources.getIcon(Images.ICON_MARKER_OFF));
	}

	public void removeMarkers() {
		for (int i = 0; i < _markerList.size(); i++) {
			l = _markerList.get(i);
			if (l.getIcon().equals(
					ImageResources.getIcon(Images.ICON_MARKER_ON)))
				_markerList.get(i).setIcon(
						ImageResources.getIcon(Images.ICON_MARKER_OFF));
		}
	}

	/**
	 * Ermittelt den von links gesehen letzten gesetzten Marker.
	 * 
	 * @return - letzter gesetzter Marker
	 */
	private int lastOn() {
		for (int i = _markerList.size() - 1; i >= 0; i--) {
			l = _markerList.get(i);
			if (l.getIcon().equals(
					ImageResources.getIcon(Images.ICON_MARKER_ON))) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Ermittelt den von links gesehen ersten <b>nicht</b> gesetzten Marker.
	 * 
	 * @return - erster nicht gesetzter Marker
	 */
	private int firstOff() {
		for (int i = 0; i < _markerList.size(); i++) {
			l = _markerList.get(i);
			if (l.getIcon().equals(
					ImageResources.getIcon(Images.ICON_MARKER_OFF))) {
				return i;
			}
		}
		return 4;
	}

}
