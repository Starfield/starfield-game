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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
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

	public ReplayToolbar() {
		// EventHandler erzeugen
		_replayHandler = new ToolbarReplayHandler();
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, VERTICAL);
		panel.setLayout(layout);

		panel.add(initControls());

		add(panel);
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
		button = new JButton("Stop");
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

		return panel;
	}

	public ToolbarReplayHandler getReplayHandler() {
		return _replayHandler;
	}
}
