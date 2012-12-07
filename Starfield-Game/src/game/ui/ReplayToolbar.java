/**
 * 
 */
package game.ui;

import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.ui.handler.ToolbarReplayHandler;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * @author Jan
 * 
 */
public class ReplayToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ToolbarReplayHandler _replayHandler;

	private JProgressBar _progressBar;

	/** MarkerList */
	private ArrayList<JLabel> _markerList;

	public ReplayToolbar() {
		// EventHandler erzeugen
		_replayHandler = new ToolbarReplayHandler();
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, VERTICAL);
		panel.setLayout(layout);

		panel.add(initMarker());
		panel.add(initControls());

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

	private Component initControls() {
		// Internes Panel zur Ablage
		JPanel panel = new JPanel();
		// Layout mit Optionen festlegen
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.CENTER;
		// Rahmen festlegen
		Border border = BorderFactory.createTitledBorder("Steuerung");
		panel.setBorder(border);

		// Play Button
		JButton button = new JButton("Play");
		button.addActionListener(_replayHandler);
		button.setActionCommand("play");
		c.gridx = 0;
		c.gridy = 0;
		panel.add(button, c);

		// Stop Button
		button = new JButton("Pause");
		button.addActionListener(_replayHandler);
		button.setActionCommand("stop");
		c.gridx = 1;
		panel.add(button, c);

		// Label
		JLabel label = new JLabel("Geschwindigkeit:");
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		panel.add(label, c);

		// Slider für Geschwindigkeit
		JSlider slider = new JSlider(HORIZONTAL, 1, 5, 3);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.addChangeListener(_replayHandler);
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 3;
		panel.add(slider, c);

		// Bilder für Geschwindigkeit
		label = new JLabel(ImageResources.getScaledIcon(24, Images.ICON_SLOW,
				Image.SCALE_SMOOTH));
		c.anchor = GridBagConstraints.LINE_START;
		c.gridy = 4;
		panel.add(label, c);

		label = new JLabel(ImageResources.getScaledIcon(24, Images.ICON_FAST,
				Image.SCALE_SMOOTH));
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 1;
		panel.add(label, c);

		// Leerzeile
		label = new JLabel(" ");
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 5;
		panel.add(label, c);

		// ProgressBar
		_progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
		c.gridx = 0;
		c.gridy = 6;
		panel.add(_progressBar, c);

		return panel;
	}

	public ToolbarReplayHandler getReplayHandler() {
		return _replayHandler;
	}

	public void setProgressMaximum(int maximum) {
		_progressBar.setMaximum(maximum);
		_progressBar.setMinimum(1);
		_progressBar.setValue(1);
		_progressBar.repaint();
	}

	public void setProgressTick() {
		_progressBar.setValue(_progressBar.getValue() + 1);
	}
}
