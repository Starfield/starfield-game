package game.ui.handler;

import game.commands.RemoveMarkerCommand;
import game.commands.RemoveMarkersCommand;
import game.commands.RemoveMistakeCommand;
import game.commands.SetMarkerCommand;
import game.core.GamePreferences.AppMode;
import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.model.Starfield;
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
		_toolbar = (PlayToolbar) MainWindow.getInstance().getActiveToolBar();
		_markerList = _toolbar.getMarkerList();

		if (cmd == "setMarker") {
			if (lastOn() != 4) {
				SetMarkerCommand command = new SetMarkerCommand(MainWindow
						.getInstance().getCommandStack(), pE, firstOff());
				command.execute();
			} else {
				JOptionPane.showMessageDialog(null,
						"Die maximale Anzahl an Markern ist verbraucht!");
			}
		}

		if (cmd == "undoMarker") {
			if (firstOff() != 0) {
				RemoveMarkerCommand command = new RemoveMarkerCommand(
						MainWindow.getInstance().getCommandStack(), pE);
				command.execute();
			} else {
				JOptionPane.showMessageDialog(null,
						"Es wurde bisher noch keine Marker gesetzt!");
			}
		}

		if (cmd == "removeMarker") {
			if (firstOff() != 0) {
				RemoveMarkersCommand command = new RemoveMarkersCommand(
						MainWindow.getInstance().getCommandStack(), pE);
				command.execute();
			} else {
				JOptionPane.showMessageDialog(null,
						"Es wurde bisher noch keine Marker gesetzt!");
			}
		}

		if (cmd == "undoError") {
			if (MainWindow.getInstance().getCommandStack().getMistake() != 0) {
				RemoveMistakeCommand command = new RemoveMistakeCommand(
						MainWindow.getInstance().getCommandStack(), pE);
				command.execute();
			} else {
				JOptionPane.showMessageDialog(null, "Es gibt keinen Fehler!");
			}
		}

		if (cmd == "checkSolution") {
			checkInput();
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

	public void checkInput() {
		if (MainWindow.getInstance().getCurrentStarfield().checkSolution()) {
			String success = "<html><p>Herzlichen Glückwunsch</p><p>Sie haben das Spiel gewonnen</p><p></p><p>Wollen Sie das Replay ansehen?</p></html>";
			if (JOptionPane.showConfirmDialog(MainWindow.getInstance(),
					success, "Spiel erfolgreich beendet",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Das Starfield wird geleert und zwischengespeichert
				Starfield starfield = MainWindow.getInstance()
						.getCurrentStarfield();
				starfield.clearUserContent();
				starfield.prepareUserContent(true);
				MainWindow.getInstance().getGamePrefs()
						.setLoadedStarfield(starfield);
				// Der aktuelle CommandStack wird zwischengespeichert
				MainWindow
						.getInstance()
						.getGamePrefs()
						.setLoadedCommandStack(
								MainWindow.getInstance().getCommandStack());
				// Die Replay Ansicht wird hergestellt
				MainWindow.getInstance().getGamePrefs()
						.setAppMode(AppMode.REPLAY_MODE);
				MainWindow.getInstance().initGame();
			}

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
