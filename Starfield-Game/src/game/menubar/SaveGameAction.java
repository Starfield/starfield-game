/**
 * 
 */
package game.menubar;

import game.core.GamePreferences.AppMode;
import game.ui.MainWindow;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author schroeder_jan
 * 
 */
public class SaveGameAction extends AbstractAction {

	public SaveGameAction(String text, ImageIcon icon) {
		super(text, icon);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pE) {
		if (MainWindow.getInstance().getGamePrefs().getAppMode() == AppMode.GAME_MODE
				|| MainWindow.getInstance().getGamePrefs().getAppMode() == AppMode.LOAD_GAME_MODE){
			// TODO Speicherdialog aufrufen
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileHidingEnabled(true);
			FileFilter ff = new FileNameExtensionFilter("Starfield-Spielstand",
					"save");
			jfc.setFileFilter(ff);
			//setzt ausgangspfad und erstellt ordner des ausgangspfads wenn nicht vorhanden
			File dirfile = new File("Spielstand");
			if (dirfile.getAbsoluteFile().exists()){
				jfc.setCurrentDirectory(dirfile.getAbsoluteFile());
			}
			else {
				try {
					dirfile.mkdir();
					jfc.setCurrentDirectory(dirfile.getAbsoluteFile());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//-----------------
			int w=0;
			do{
				w=0;
				if (jfc.showSaveDialog(jfc) == JFileChooser.APPROVE_OPTION) {
					String temppfad = jfc.getSelectedFile().getAbsolutePath();
					if (temppfad.endsWith(".save")) {
					} else {
						temppfad = temppfad + ".save";
					}
					//prüft ob die datei bereits existiert
					File tempfile = new File(temppfad);
					if(tempfile.exists()){
						int auswahl = JOptionPane.showConfirmDialog(null, "Wollen Sie den Spielstand überschreiben?", "Spielstand existiert", JOptionPane.YES_NO_CANCEL_OPTION);
						switch (auswahl){
						case 0:
							try {
								File f = new File(temppfad);
								FileOutputStream fos = new FileOutputStream(f);
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								oos.flush();
								oos.writeObject(MainWindow.getInstance().getCommandStack());
								oos.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							w=0;
							break;
						case 1:
							w=1;
							break;
						case 2:
							w=0;
							break;
						}
					}
					else{
						try {
							File f = new File(temppfad);
							FileOutputStream fos = new FileOutputStream(f);
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							oos.flush();
							oos.writeObject(MainWindow.getInstance().getCommandStack());
							oos.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						w=0;
					}
					
				}
			}while(w==1);
	
		}
	}
}
