/**
 * 
 */
package game.menubar;

import game.ui.MainWindow;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author schroeder_jan
 *
 */
public class SaveGameAction extends AbstractAction {

	public SaveGameAction(String text, ImageIcon icon){
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
	public void actionPerformed(ActionEvent pE) {
		// TODO Speicherdialog aufrufen
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileHidingEnabled(true);
		FileFilter ff = new FileNameExtensionFilter("Starfield-Spielstand", "save");
		jfc.addChoosableFileFilter(ff);
		if (jfc.showSaveDialog(jfc) == JFileChooser.APPROVE_OPTION) {
	        String temppfad = jfc.getSelectedFile().getAbsolutePath();
			if (temppfad.endsWith(".save")){
			}
			else {
				temppfad = temppfad + ".save";
			}
			try {
				File f = new File(temppfad);
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.flush();
				oos.writeObject(MainWindow.getCommandStack());
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	    }		
}
