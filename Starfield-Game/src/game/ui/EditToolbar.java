package game.ui;

/**
 * @author schroeder_jan
 *
 */
import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.model.Field.AllowedContent;
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

	/** Der EditHandler */
	private final ToolbarEditHandler _editHandler;
	/** Die ButtonGroup der Arrows */
	private ButtonGroup _arrowBG;
	/** Der ausgew‰hlte Pfeil */
	private AllowedContent _selectedArrow;

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
		Border border = BorderFactory.createTitledBorder("linke Maustaste");
		panel.setBorder(border);
		ButtonGroup bg = new ButtonGroup();

		JToggleButton button = new JToggleButton(
				ImageResources.getIcon(Images.ICON_STAR));
		button.addActionListener(_editHandler);
		// Da der Stern der einzige Button hier ist, muss er selektiert werden.
		button.setSelected(true);
		bg.add(button);
		panel.add(button);
		return panel;
	}

	private JPanel initArrows() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		Border border = BorderFactory.createTitledBorder("rechte Maustaste");
		panel.setBorder(border);
		_arrowBG = new ButtonGroup();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);

		JToggleButton button = new JToggleButton(
				ImageResources.getIcon(Images.ICON_ARROW_UL));
		button.addActionListener(_editHandler);
		button.setName("ARROW_UL");
		c.gridx = 0;
		c.gridy = 0;
		panel.add(button, c);
		_arrowBG.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_U));
		button.addActionListener(_editHandler);
		button.setName("ARROW_U");
		c.gridx = 1;
		c.gridy = 0;
		panel.add(button, c);
		_arrowBG.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_UR));
		button.addActionListener(_editHandler);
		button.setName("ARROW_UR");
		c.gridx = 2;
		c.gridy = 0;
		panel.add(button, c);
		_arrowBG.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_L));
		button.addActionListener(_editHandler);
		button.setName("ARROW_L");
		c.gridx = 0;
		c.gridy = 1;
		panel.add(button, c);
		_arrowBG.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_R));
		button.addActionListener(_editHandler);
		button.setName("ARROW_R");
		button.setSelected(true); // Standardm‰ﬂige Selektion
		setSelectedArrow(AllowedContent.CONTENT_ARROW_R);
		c.gridx = 2;
		c.gridy = 1;
		panel.add(button, c);
		_arrowBG.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_DL));
		button.addActionListener(_editHandler);
		button.setName("ARROW_DL");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(button, c);
		_arrowBG.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_D));
		button.addActionListener(_editHandler);
		button.setName("ARROW_D");
		c.gridx = 1;
		c.gridy = 2;
		panel.add(button, c);
		_arrowBG.add(button);

		button = new JToggleButton(ImageResources.getIcon(Images.ICON_ARROW_DR));
		button.addActionListener(_editHandler);
		button.setName("ARROW_DR");
		c.gridx = 2;
		c.gridy = 2;
		panel.add(button, c);
		_arrowBG.add(button);

		return panel;
	}

	/**
	 * Diese Methode soll vom ToolbarEditHandler aufgerufen werden und bestimmt,
	 * welcher Arrow auf dem UI ausgew‰hlt ist, der mit der rechten Maustaste
	 * auf das UI gesetzt werden kann.
	 */
	public void setSelectedArrow(AllowedContent newContent) {
		_selectedArrow = newContent;
	}

	/**
	 * Liefert den zur Zeit ausgew‰hlten Pfeil
	 * 
	 * @return - AllowedContent des zurzeit gew‰hlten Pfeils
	 */
	public AllowedContent getSelectedArrow() {
		return _selectedArrow;
	}

}