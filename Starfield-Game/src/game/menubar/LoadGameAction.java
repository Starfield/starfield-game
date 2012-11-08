/**
 * 
 */
package game.menubar;

import game.commands.CommandStack;
import game.ui.MainWindow;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Diese Action öffnet ein Fenster zur Auswahl eines gespeicherten Spiels. <br>
 * Im Anschluss wird das aktuelle Spiel beendet und das gewählte Spiel geladen
 * @author schroeder_jan
 *
 */
public class LoadGameAction extends AbstractAction {

	
	public LoadGameAction(String text, ImageIcon icon){
		super(text, icon);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pArg0) {
		// TODO FileChooser zum Laden eines gespeicherten Spiels
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileHidingEnabled(true);
		FileFilter ff = new FileNameExtensionFilter("Spielstand", "star");
		jfc.addChoosableFileFilter(ff);
		if (jfc.showOpenDialog(jfc) == JFileChooser.APPROVE_OPTION){
			String temppfad = jfc.getSelectedFile().getAbsolutePath();
			if (temppfad.endsWith(".star")){
			}
			else {
				temppfad = temppfad + ".star";
			}
			System.out.println(temppfad);
			if (temppfad.endsWith(".star")){
				try {
					File f = new File(temppfad);
					FileInputStream fis = new FileInputStream(f);
					ObjectInputStream ois = new ObjectInputStream(fis);
					CommandStack commandStack = null;
					Object o = ois.readObject();
					if (o instanceof CommandStack){
							commandStack = (CommandStack) o;
							MainWindow.getGamePrefs().setLoadedCommandStack(commandStack);
					}
					ois.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}
}
