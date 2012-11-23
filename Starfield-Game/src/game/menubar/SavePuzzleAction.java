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
 * @author Jan
 * 
 */
public class SavePuzzleAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SavePuzzleAction(String text, ImageIcon icon) {
		super(text, icon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pE) {
		// Speichern nur möglich wenn im Edit-Modus
		if (MainWindow.getInstance().getGamePrefs().getAppMode() == AppMode.EDIT_MODE
				|| MainWindow.getInstance().getGamePrefs().getAppMode() == AppMode.LOAD_EDIT_MODE) {
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileHidingEnabled(true);
			FileFilter ff = new FileNameExtensionFilter("Starfield-Puzzle",
					"star");
			jfc.setFileFilter(ff);
			//setzt ausgangspfad und erstellt ordner des ausgangspfads wenn nicht vorhanden
			File dirfile = new File("Puzzle");
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
					if (temppfad.endsWith(".star")) {
					} else {
						temppfad = temppfad + ".star";
					}
					//prüft ob die datei bereits existiert
					File tempfile = new File(temppfad);
					if(tempfile.exists()){
						int auswahl = JOptionPane.showConfirmDialog(null, "Wollen Sie das Puzzle überschreiben?", "Puzzle existiert", JOptionPane.YES_NO_CANCEL_OPTION);
						switch (auswahl){
						case 0:
							try {
								File f = new File(temppfad);
								FileOutputStream fos = new FileOutputStream(f);
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								oos.flush();
								oos.writeObject(MainWindow.getInstance()
										.getCurrentStarfield().copyUserToSolutionContent());
								oos.close();
							} catch (IOException e) {
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
					else {
						try {
							File f = new File(temppfad);
							FileOutputStream fos = new FileOutputStream(f);
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							oos.flush();
							oos.writeObject(MainWindow.getInstance()
									.getCurrentStarfield().copyUserToSolutionContent());
							oos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}while(w==1);
		}
	}
}
