/**
 * 
 */
package game.ui;

/**
 * @author schroeder_jan
 *
 */
import game.core.ImageResources;
import game.core.ImageResources.Images;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
    static final private String SETMARK = "SETMARK";
    static final private String REMOVEMARK = "REMOVEMARK";
    static final private String GOTOFAILURE = "GOTOFAILURE";
    private JLabel j1,j2,j3,j4,j5;

    public PlayToolbar() {
        super("Play");
    }

    protected void addButtons(JToolBar toolBar) { //TODO Image einbindung
        JButton button = null;

        //SetMarker button
        button = createButton(1, SETMARK, 
                                      "Setzt eine Markierung zu der später zurückgesprungen werden kann");
        toolBar.add(button);

        //RemoveMarkers button
        button = createButton(2, REMOVEMARK, 
                                      "Entfernt alle Markierungen die gesetzt waren");
        toolBar.add(button);

        //GotoFailure button
        button = createButton(3, GOTOFAILURE,
                                      "Springt zum letzten fehlerfreien Spielstand");
        toolBar.add(button);
        

        createLabels();        
    	toolBar.add(j1);
    	toolBar.add(j2);
    	toolBar.add(j3);
    	toolBar.add(j4);
    	toolBar.add(j5);
    }

    
    public void createLabels(){
    	j1 = new JLabel();
    	j2 = new JLabel();
    	j3 = new JLabel();
    	j4 = new JLabel();
    	j5 = new JLabel();
    	j1.setIcon(ImageResources.getIcon(Images.ICON_STAR));
    	j2.setIcon(ImageResources.getIcon(Images.ICON_STAR));
    	j3.setIcon(ImageResources.getIcon(Images.ICON_STAR));
    	j4.setIcon(ImageResources.getIcon(Images.ICON_STAR));
    	j5.setIcon(ImageResources.getIcon(Images.ICON_STAR));  
    	j1.setVisible(false);
    	j2.setVisible(false);
    	j3.setVisible(false);
    	j4.setVisible(false);
    	j5.setVisible(false);
    }
    
    public void removeLabelIcons(){
//    	j1.setIcon(ImageResources.getIcon(Images.CONTENT_EMPTY));
//    	j2.setIcon(ImageResources.getIcon(Images.CONTENT_EMPTY));
//    	j3.setIcon(ImageResources.getIcon(Images.CONTENT_EMPTY));
//    	j4.setIcon(ImageResources.getIcon(Images.CONTENT_EMPTY));
//    	j5.setIcon(ImageResources.getIcon(Images.CONTENT_EMPTY));
    	j1.setVisible(false);
    	j2.setVisible(false);
    	j3.setVisible(false);
    	j4.setVisible(false);
    	j5.setVisible(false);
    }
    
    public void addLabelIcon(){
    	if (j1.isVisible() == false)
    		{
    			j1.setVisible(true);
    			return;
    		}
    	if (j2.isVisible() == false)
		{
			j2.setVisible(true);
			return;
		}
    	if (j3.isVisible() == false)
		{
			j3.setVisible(true);
			return;
		}
    	if (j4.isVisible() == false)
		{
			j4.setVisible(true);
			return;
		}
    	if (j5.isVisible() == false)
		{
			j5.setVisible(true);
			return;
		}
    }
    
    protected JButton createButton(int imagenr,
                                           String actionCommand,
                                           String toolTipText) {

        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
        
        switch (imagenr)
        {
        	case 1:
        		button.setIcon(ImageResources.getIcon(Images.ICON_STAR));
        		break;
        	case 2:
        		button.setIcon(ImageResources.getIcon(Images.CONTENT_ARROW_D));
        		break;
        	case 3:
        		button.setIcon(ImageResources.getIcon(Images.CONTENT_ARROW_L));
        		break;
        }
        
        return button;
    }

    public void actionPerformed(ActionEvent e) {// TODO
        String cmd = e.getActionCommand();
        String description = null;
        
        if (SETMARK.equals(cmd)) { 
            description = "Sie haben eine Markierung gesetzt";   
            addLabelIcon();
            // setMarker Action
        } else if (REMOVEMARK.equals(cmd)) { 
        	description = "Sie haben alle Markierungen gelöscht";
        	removeLabelIcons();
            // removeMarkers Action
        } else if (GOTOFAILURE.equals(cmd)) { 
        	description = "Sie sind zum letzten Fehler gesprungen";
            // GOTO Failure Action
        }
       
        System.out.println(description);
        
    }

    
    
}