package game.ui;

/**
 * @author schroeder_jan
 *
 */
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.net.URL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditToolbar extends AbstractToolbar
                         implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected JTextArea textArea;
    protected String newline = "\n";
    static final private String SETARROW = "SETARROW";
    static final private String SETSTAR = "SETSTAR";
    static final private String CHECKSOLUTION = "CHECKSOLUTION";

    public EditToolbar() {
        super("");

        //Erstellen der Toolbar
        JToolBar toolBar = new JToolBar("Play");
        addButtons(toolBar);

        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        setPreferredSize(new Dimension(450, 130));
        add(toolBar, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
    }

    protected void addButtons(JToolBar toolBar) { //TODO Image einbindung
        JButton button = null;

        //SETARROW button
        button = makeNavigationButton("", SETARROW, 
                                      "Ermöglicht es einen Pfeil auf einem Feld zu platzieren",
                                      "Pfeil setzen");
        toolBar.add(button);

        //SETSTAR button
        button = makeNavigationButton("", SETSTAR, 
                                      "Ermöglicht es einen Stern auf einem Feld zu setzen",
                                      "Stern setze");
        toolBar.add(button);

        //CHECKSOLUTION button
        button = makeNavigationButton("", CHECKSOLUTION,
                                      "Überprüft ob das Spiel lösbar ist",
                                      "Lösbarkeit prüfen");
        toolBar.add(button);
    }

    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
        String imgLocation = "images/"
                + imageName
                + ".png"; // TODO Pfad funktioniert nicht!
        URL imageURL = EditToolbar.class.getResource(imgLocation);
       
        //Button erstellen
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.addActionListener(this);

        if (imageURL != null) {                      //image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                               + imgLocation);
        }

        return button;
    }

    public void actionPerformed(ActionEvent e) {// TODO
        String cmd = e.getActionCommand();
        String description = null;
        
        if (SETARROW.equals(cmd)) { 
            // SETARROW Action
        } else if (SETSTAR.equals(cmd)) { 
            // SETSTAR Action
        } else if (CHECKSOLUTION.equals(cmd)) { 
        	description = "Lösbarkeit wird überprüft...";
            // GOTO Failure Action
        }
       
        if (description != null)
        	displayResult(description);
        
    }

    
    
    
    

    
    protected void displayResult(String actionDescription) {
        textArea.append(actionDescription + newline);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    // ab hier ist irrelevant für die anderen Klassen nur zum testen der Toolbar

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new EditToolbar());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
	        UIManager.put("swing.boldMetal", Boolean.FALSE);
	        createAndShowGUI();
            }
        });
    }
}