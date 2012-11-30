package game.ui;

/**
 * @author schroeder_jan
 *
 */
import game.core.ImageResources;
import game.core.ImageResources.Images;
import game.model.Field.AllowedContent;
import game.ui.handler.ToolbarEditHandler;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class EditToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Der EditHandler */
	private final ToolbarEditHandler _editHandler;
	/** Der ausgewählte Pfeil */
	private AllowedContent _selectedArrow;

	/** x-Achsenlänge */
	private JTextField _xSizeInput;
	/** y-Achsenlänge */
	private JTextField _ySizeInput;
	/** PlayableAnzeige */
	private JLabel _playableLabel;
	/** Schwierigkleitsanzeige */
	private JLabel _difficultyLabel;

	public EditToolbar() {
		_editHandler = new ToolbarEditHandler();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, VERTICAL));
		panel.add(initStar());
		panel.add(initArrows());
		panel.add(initGlobal());
		add(panel);
	}

	private Component initStar() {
		JPanel panel = new JPanel();
		Border border = BorderFactory.createTitledBorder("linke Maustaste");
		panel.setBorder(border);
		ButtonGroup bg = new ButtonGroup();

		JToggleButton button = new JToggleButton(
				ImageResources.getIcon(Images.ICON_STAR));
		button.addActionListener(_editHandler);
		// Da der Stern der einzige Button hier ist, muss er selektiert werden.
		button.setSelected(true);
		button.setFocusable(false);
		bg.add(button);
		panel.add(button);
		return panel;
	}

	private Component initArrows() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		Border border = BorderFactory.createTitledBorder("rechte Maustaste");
		panel.setBorder(border);

		ButtonGroup arrowBG = new ButtonGroup();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);

		JToggleButton button = createToggleButton(Images.ICON_ARROW_UL,
				"ARROW_UL");
		c.gridx = 0;
		c.gridy = 0;
		panel.add(button, c);
		arrowBG.add(button);

		button = createToggleButton(Images.ICON_ARROW_U, "ARROW_U");
		c.gridx = 1;
		panel.add(button, c);
		arrowBG.add(button);

		button = createToggleButton(Images.ICON_ARROW_UR, "ARROW_UR");
		c.gridx = 2;
		panel.add(button, c);
		arrowBG.add(button);

		button = createToggleButton(Images.ICON_ARROW_L, "ARROW_L");
		c.gridx = 0;
		c.gridy = 1;
		panel.add(button, c);
		arrowBG.add(button);

		button = createToggleButton(Images.ICON_ARROW_R, "ARROW_R");
		button.setSelected(true); // Standardmäßige Selektion
		setSelectedArrow(AllowedContent.CONTENT_ARROW_R);
		c.gridx = 2;
		panel.add(button, c);
		arrowBG.add(button);

		button = createToggleButton(Images.ICON_ARROW_DL, "ARROW_DL");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(button, c);
		arrowBG.add(button);

		button = createToggleButton(Images.ICON_ARROW_D, "ARROW_D");
		c.gridx = 1;
		panel.add(button, c);
		arrowBG.add(button);

		button = createToggleButton(Images.ICON_ARROW_DR, "ARROW_DR");
		c.gridx = 2;
		panel.add(button, c);
		arrowBG.add(button);

		// Button zum Löschen anbieten
		JToggleButton button2 = createToggleButton(Images.ICON_EMPTY, "EMPTY");
		c.gridx = 1;
		c.gridy = 1;
		panel.add(button2, c);
		arrowBG.add(button2);

		return panel;
	}

	/**
	 * @return
	 */
	private JToggleButton createToggleButton(Images icon, String name) {
		JToggleButton button = new JToggleButton(ImageResources.getIcon(icon));
		button.addActionListener(_editHandler);
		button.setActionCommand(name);
		button.setFocusable(false);
		return button;
	}

	private Component initGlobal() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.WEST;
		Border border = BorderFactory.createTitledBorder("Einstellungen");
		panel.setBorder(border);

		initSizeEdit(panel, c);
		initCheckPlayable(panel, c);

		return panel;
	}

	/**
	 * Erstellt die Zeile um die Größe des Starfields zu bearbeiten
	 * 
	 * @param JPanel
	 * @param GridBagConstraints
	 */
	private void initSizeEdit(JPanel panel, GridBagConstraints c) {
		// Erklärungstext einfügen
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Größe:"), c);
		// Textfield für x-Achsenlänge hinzufügen
		_xSizeInput = new JTextField(2);
		_xSizeInput.setDocument(new CustomDocument());
		_xSizeInput.setToolTipText("Breite einstellen (3 - 99)");
		// _xSizeInput.setText(Double.toString(MainWindow.getStarfieldView()
		// .getCurrentStarfield().getSize().height));
		c.gridx = 1;
		panel.add(_xSizeInput, c);
		// Zwischenlabel einfügen
		c.gridx = 2;
		panel.add(new JLabel("x"), c);
		// Textfield für y-Achsenlänge hinzufügen
		_ySizeInput = new JTextField(2);
		_ySizeInput.setDocument(new CustomDocument());
		_ySizeInput.setToolTipText("Höhe einstellen (3 - 99)");
		// _ySizeInput.setText(Double.toString(MainWindow.getStarfieldView()
		// .getCurrentStarfield().getSize().width));
		c.gridx = 3;
		panel.add(_ySizeInput, c);
		// Anwenden Button
		JButton button = new JButton("Anwenden");
		button.setActionCommand("APPLY");
		button.addActionListener(_editHandler);
		// button.setFocusable(false);
		c.gridx = 4;
		panel.add(button, c);
	}

	private void initCheckPlayable(JPanel panel, GridBagConstraints c) {
		// Erklärungstext einfügen
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel("Spielbar:"), c);
		// Anzeige ob spielbar oder nicht
		_playableLabel = new JLabel(
				ImageResources.getIcon(Images.ICON_PLAYABLE_FALSE));
		c.gridx = 1;
		c.gridwidth = 3;
		panel.add(_playableLabel, c);
		// Button zum Check einfügen
		JButton button = new JButton("Prüfen");
		button.addActionListener(_editHandler);
		button.setFocusable(false);
		c.gridx = 4;
		button.setActionCommand("CHECK");
		panel.add(button, c);

		// Label für Schwierigkeit
		JLabel label = new JLabel("Schwierigkeit:");

		c.gridx = 0;
		c.gridwidth = 4;
		c.gridy = 4;
		panel.add(label, c);

		// Difficulty anzeigen
		_difficultyLabel = new JLabel("Nicht geprüft!");
		c.gridy = 5;
		panel.add(_difficultyLabel, c);

	}

	/**
	 * Diese Methode soll vom ToolbarEditHandler aufgerufen werden und bestimmt,
	 * welcher Arrow auf dem UI ausgewählt ist, der mit der rechten Maustaste
	 * auf das UI gesetzt werden kann.
	 */
	public void setSelectedArrow(AllowedContent newContent) {
		_selectedArrow = newContent;
	}

	/**
	 * Liefert den zur Zeit ausgewählten Pfeil
	 * 
	 * @return - AllowedContent des zurzeit gewählten Pfeils
	 */
	public AllowedContent getSelectedArrow() {
		return _selectedArrow;
	}

	public void setPlayable(boolean pTruth, boolean fromUserChange) {
		if (fromUserChange) {
			_playableLabel.setIcon(ImageResources
					.getIcon(Images.ICON_PLAYABLE_UNSURE));
		} else {
			if (pTruth)
				_playableLabel.setIcon(ImageResources
						.getIcon(Images.ICON_PLAYABLE_TRUE));
			else
				_playableLabel.setIcon(ImageResources
						.getIcon(Images.ICON_PLAYABLE_FALSE));
		}
		MainWindow.getInstance().getCurrentStarfield().setPlayable(pTruth);
	}

	/**
	 * Liefert den vom User eingegebenen Wert für die Länge der x-Achse zurück.
	 * 
	 * @return den Wert des User oder die aktuelle Größe, sofern der User nichts
	 *         eingegeben hat
	 */
	public int getInputSizeX() {
		return getInputSize(_xSizeInput);
	}

	/**
	 * Liefert den vom User eingegebenen Wert für die Länge der Y-Achse zurück.
	 * 
	 * @return den Wert des User oder die aktuelle Größe, sofern der User nichts
	 *         eingegeben hat
	 */
	public int getInputSizeY() {
		return getInputSize(_ySizeInput);
	}

	private int getInputSize(JTextField text) {

		String input = text.getText();
		int size = 0;
		if (input.equals("")) {
			Dimension dim = MainWindow.getInstance().getCurrentStarfield()
					.getSize();
			if (text == _xSizeInput)
				size = (int) dim.getWidth();
			if (text == _ySizeInput)
				size = (int) dim.getHeight();
		} else {
			size = Integer.valueOf(input);
		}

		return size;
	}

	public void changeDifficulty() {

		_difficultyLabel.setText(MainWindow.getInstance().getCurrentStarfield()
				.checkDifficulty());
	}

	private class CustomDocument extends PlainDocument {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void insertString(int offset, String s, AttributeSet attributeSet) {

			// Nicht numerische Zahlen ausschließen
			try {
				Integer.parseInt(s);
			} catch (NumberFormatException e) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			// Nicht einfügen wenn Text schon Länge 2 hat
			if (getLength() >= 2) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			// Nicht einfügen, wenn Text + Change Länge 2 überschreitet
			if ((getLength() + s.length()) > 2) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			// Ansonsten Änderung einfügen
			try {
				super.insertString(offset, s, attributeSet);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}