/**
 * 
 */
package game.ui.handler;

import game.commands.RemoveMarkerCommand;
import game.commands.SetMarkerCommand;
import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.ui.MainWindow;
import game.ui.PlayToolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JToolBar;

/**
 * @author Jan
 * 
 */
public class ToolbarPlayHandler implements ActionListener {

	private PlayToolbar _toolbar;
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
		
		if (cmd == "setMarker"){
			setMarker(pE);
		}
		if (cmd == "rücksprungMarker"){
			RemoveMarkerCommand c = new RemoveMarkerCommand(MainWindow.getCommandStack(), pE); 
			c.execute();
		}
		if (cmd == "removeMarker"){
			removeMarkers();
		}
		
		MainWindow.getActiveToolBar();
	}		

	public void setMarker(ActionEvent pE){
		ArrayList<JLabel> _markerList = _toolbar.getMarkerList();
		JLabel l = null; 		
		for (int i  = 0; i < _markerList.size(); i++){
			l = _markerList.get(i);
			if (l.getIcon().equals(ImageResources.getIcon(Images.ICON_MARKER_OFF)) ){
				_markerList.get(i).setIcon(ImageResources.getIcon(Images.ICON_MARKER_ON));				
				SetMarkerCommand command = new SetMarkerCommand(MainWindow.getCommandStack(), pE, i);
				command.execute();
				return;
			}
		}	
	}
	
	public void removeMarkers(){
		ArrayList<JLabel> _markerList = _toolbar.getMarkerList();
		JLabel l = null; 
		for (int i = 0; i < _markerList.size(); i++){
			l = _markerList.get(i);
			if (l.getIcon().equals(ImageResources.getIcon(Images.ICON_MARKER_ON)) )
				_markerList.get(i).setIcon(ImageResources.getIcon(Images.ICON_MARKER_OFF));	
		}
		
		MainWindow.getCommandStack().deleteMarkers();
	}
	
	
}
