/**
 * 
 */
package game.ui;

import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.ui.handler.ToolbarPlayHandler;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;

/**
 * @author schroeder_jan
 * 
 */

public class PlayToolbar extends JToolBar {

	private static final long serialVersionUID = 1L;

	/** MarkerHandler */
	private final ToolbarPlayHandler _playHandler;
	/** MarkerList */
	private ArrayList<JLabel> _markerList;

	/**
	 * Standardkonstruktor für die PlayToolbar
	 */
	public PlayToolbar() {
		// EventHandler erzeugen
		_playHandler = new ToolbarPlayHandler();
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, VERTICAL);
		panel.setLayout(layout);

		panel.add(initMarker());
		panel.add(initError());

		add(panel);
	}

	/**
	 * Initialisiert die Grundeinstellungen der Toolbar
	 */
	private JPanel initMarker() {
		// Internes Panel zur Ablage
		JPanel panel = new JPanel();
		// Layout mit Optionen festlegen
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		// Rahmen festlegen
		Border border = BorderFactory.createTitledBorder("Marker");
		panel.setBorder(border);

		// Markervisualierung hinzufügen
		_markerList = new ArrayList<JLabel>();

		JLabel label = new JLabel(
				ImageResources.getIcon(Images.ICON_MARKER_OFF));
		_markerList.add(label);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(label, c);
		label = new JLabel(ImageResources.getIcon(Images.ICON_MARKER_OFF));
		_markerList.add(label);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(label, c);
		label = new JLabel(ImageResources.getIcon(Images.ICON_MARKER_OFF));
		_markerList.add(label);
		c.gridx = 2;
		c.gridy = 0;
		panel.add(label, c);
		label = new JLabel(ImageResources.getIcon(Images.ICON_MARKER_OFF));
		_markerList.add(label);
		c.gridx = 3;
		c.gridy = 0;
		panel.add(label, c);
		label = new JLabel(ImageResources.getIcon(Images.ICON_MARKER_OFF));
		_markerList.add(label);
		c.gridx = 4;
		c.gridy = 0;
		panel.add(label, c);

		// Marker setzen Button hinzufügen
		JButton button = new JButton("Marker setzen");
		button.addActionListener(_playHandler);
		button.setActionCommand("setMarker");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5;
		panel.add(button, c);

		// Marker rücksprung Button hinzufügen
		button = new JButton("zum letzten Marker zurück");
		button.addActionListener(_playHandler);
		button.setActionCommand("undoMarker");
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 5;
		panel.add(button, c);

		// Marker entfernen Button hinzufügen
		button = new JButton("Alle Marker entfernen");
		button.addActionListener(_playHandler);
		button.setActionCommand("removeMarker");
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 5;
		panel.add(button, c);
		return panel;
	}

	private JPanel initError() {

		// Internes Panel zur Ablage
		JPanel panel = new JPanel(new GridLayout(2, 2));
		// Rahmen festlegen
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createTitledBorder("Fehlertracking");
		panel.setBorder(border);

		// Zurück zum Fehler Button hinzufügen
		JButton button = new JButton("Zurück zum Fehler springen");
		button.addActionListener(_playHandler);
		button.setActionCommand("undoError");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(button, c);
		return panel;
	}

	public ArrayList<JLabel> getMarkerList() {
		return this._markerList;
	}

	public void setNextMarker() {
		// Logik einfügen, dass beim Aufruf aus dem MarkerHandler das nächste
		// Icon in der markerList auf "AN" gesetzt wird
		_markerList.get(0).setIcon(
				ImageResources.getIcon(Images.ICON_MARKER_OFF));

	}

	public void removeLastMarker() {
		// Logik einfügen, dass beim Aufruf aus dem MarkerHandler das letzte
		// Icon auf der MarkerList auf "AUS" gesetzt wird
	}

	/**
	 * @return the _playHandler
	 */
	public ToolbarPlayHandler get_playHandler() {
		return _playHandler;
	}

	// public void removeListeners() {
	// getc
	// for (Component c : this.getComponents()) {
	// c.remove
	// }
	// }
}