package game.ui.handler;

import game.commands.RemoveMarkerCommand;
import game.commands.RemoveMarkersCommand;
import game.commands.SetMarkerCommand;
import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.ui.MainWindow;
import game.ui.PlayToolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 * 
 * @author Nikolaj
 */
public class ToolbarPlayHandler implements ActionListener {

	private PlayToolbar _toolbar = null;
	
	ArrayList<JLabel> _markerList = null;

	JLabel l = null;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pE) {
		String cmd = pE.getActionCommand();
		_toolbar = (PlayToolbar) MainWindow.getActiveToolBar();
		_markerList = _toolbar.getMarkerList();
		
		if (cmd == "setMarker") {
			if (lastOn() != 4) {
				SetMarkerCommand command = new SetMarkerCommand(MainWindow.getCommandStack(), pE, firstOff());
				command.execute();
			}
			else {
				JOptionPane.showMessageDialog(null, "Die maximale Anzahl an Markern ist verbraucht!");
			}
		}
		
		if (cmd == "undoMarker") {
			if (firstOff() != 0) {
				RemoveMarkerCommand command = new RemoveMarkerCommand(MainWindow.getCommandStack(), pE); 
				command.execute();	
			}
			else {
				JOptionPane.showMessageDialog(null, "Es wurde bisher noch keine Marker gesetzt!");
			}
		}
		
		if (cmd == "removeMarker") {
			if (firstOff() != 0) {
				RemoveMarkersCommand command = new RemoveMarkersCommand(MainWindow.getCommandStack(), pE);
				command.execute();
			}
			else {
				JOptionPane.showMessageDialog(null, "Es wurde bisher noch keine Marker gesetzt!");
			}
		}
		
		if (cmd == "checkSolution") {
			checkInput();
		}
	}		

	public void setMarker() {
		_markerList.get(firstOff()).setIcon(ImageResources.getIcon(Images.ICON_MARKER_ON));
	}
	
	public void removeSingleMarker() {
		_markerList.get(lastOn()).setIcon(ImageResources.getIcon(Images.ICON_MARKER_OFF));
	}
	
	public void removeMarkers() {
		for (int i = 0; i < _markerList.size(); i++) {
			l = _markerList.get(i);
			if (l.getIcon().equals(ImageResources.getIcon(Images.ICON_MARKER_ON)))
				_markerList.get(i).setIcon(ImageResources.getIcon(Images.ICON_MARKER_OFF));
		}
	}
	
	public void checkInput() {
		if (MainWindow.getStarfieldView().getCurrentStarfield().checkSolution()) {
			JOptionPane.showMessageDialog(null, "Richtig!");
		}
		else {
			JOptionPane.showMessageDialog(null, "Falsch!");
		}
	}
	
	/**
	 * Ermittelt den von links gesehen letzten gesetzten Marker.
	 * 
	 * @return
	 *  - letzter gesetzter Marker
	 */
	private int lastOn() {
		for (int i = _markerList.size() - 1; i >= 0; i--) {
			l = _markerList.get(i);
			if (l.getIcon().equals(ImageResources.getIcon(Images.ICON_MARKER_ON))){
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * Ermittelt den von links gesehen ersten <b>nicht</b> gesetzten Marker.
	 * 
	 * @return
	 *  - erster nicht gesetzter Marker 
	 */
	private int firstOff() {
		for (int i = 0; i < _markerList.size(); i++) {
			l = _markerList.get(i);
			if (l.getIcon().equals(ImageResources.getIcon(Images.ICON_MARKER_OFF))){
				return i;
			}
		}
		return 4;
	}
	
}
