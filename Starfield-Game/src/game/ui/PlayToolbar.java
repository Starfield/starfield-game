/**
 * 
 */
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

public class PlayToolbar extends AbstractToolbar
                         implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected JTextArea textArea;
    protected String newline = "\n";
    static final private String SETMARK = "SETMARK";
    static final private String REMOVEMARK = "REMOVEMARK";
    static final private String GOTOFAILURE = "GOTOFAILURE";

    public PlayToolbar() {
        super(new BorderLayout());

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

        //SetMarker button
        button = makeNavigationButton("", SETMARK, 
                                      "Setzt eine Markierung zu der später zurückgesprungen werden kann",
                                      "Markierung setzen");
        toolBar.add(button);

        //RemoveMarkers button
        button = makeNavigationButton("", REMOVEMARK, 
                                      "Entfernt alle Markierungen die gesetzt waren",
                                      "Markierungen entfernen");
        toolBar.add(button);

        //GotoFailure button
        button = makeNavigationButton("", GOTOFAILURE,
                                      "Springt zum letzten fehlerfreien Spielstand",
                                      "Zum letzten Fehler");
        toolBar.add(button);
    }

    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
        String imgLocation = "images/"
                + imageName
                + ".png"; // TODO Pfad funktioniert nicht!
        URL imageURL = PlayToolbar.class.getResource(imgLocation);
       
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
        
        if (SETMARK.equals(cmd)) { 
            description = "Sie haben eine Markierung gesetzt";
            // setMarker Action
        } else if (REMOVEMARK.equals(cmd)) { 
        	description = "Sie haben alle Markierungen gelöscht";
            // removeMarkers Action
        } else if (GOTOFAILURE.equals(cmd)) { 
        	description = "Sie sind zum letzten Fehler gesprungen";
            // GOTO Failure Action
        }
       
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
        frame.add(new PlayToolbar());

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