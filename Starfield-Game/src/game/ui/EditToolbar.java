package game.ui;

/**
 * @author schroeder_jan
 *
 */
import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.ui.handler.ToolbarEditHandler;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;

public class EditToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ToolbarEditHandler _editHandler;

	public EditToolbar() {
		_editHandler = new ToolbarEditHandler();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, VERTICAL));
		panel.add(initStar());
		panel.add(initArrows());
		add(panel);
	}

	private JPanel initStar() {
		JPanel panel = new JPanel();
		Border border = BorderFactory.createTitledBorder("Stern");
		panel.setBorder(border);

		JToggleButton button = new JToggleButton(
				ImageResources.getIcon(Images.ICON_STAR));
		button.addActionListener(_editHandler);
		panel.add(button);
		return panel;
	}

	private JPanel initArrows() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		Border border = BorderFactory.createTitledBorder("Pfeile");
		panel.setBorder(border);
		ButtonGroup bg = new ButtonGroup();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);

		JToggleButton button = new JToggleButton(
				ImageResources.getIcon(Images.ICON_ARROW_UL));
		button.addActionListener(_editHandler);

		c.gridx = 0;
		c.gridy = 0;
		panel.add(button, c);
		bg.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_U));
		button.addActionListener(_editHandler);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(button, c);
		bg.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_UR));
		button.addActionListener(_editHandler);
		c.gridx = 2;
		c.gridy = 0;
		panel.add(button, c);
		bg.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_L));
		button.addActionListener(_editHandler);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(button, c);
		bg.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_R));
		button.addActionListener(_editHandler);
		c.gridx = 2;
		c.gridy = 1;
		panel.add(button, c);
		bg.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_DL));
		button.addActionListener(_editHandler);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(button, c);
		bg.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_D));
		button.addActionListener(_editHandler);
		c.gridx = 1;
		c.gridy = 2;
		panel.add(button, c);
		bg.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_DR));
		button.addActionListener(_editHandler);
		c.gridx = 2;
		c.gridy = 2;
		panel.add(button, c);
		bg.add(button);

		return panel;
	}

}