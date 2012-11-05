/**
 * 
 */
package game.menubar;

import game.core.GamePreferences.AppMode;
import game.model.Starfield;
import game.ui.MainWindow;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

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
		if (MainWindow.getGamePrefs().getAppMode() == AppMode.EDIT_MODE
				|| MainWindow.getGamePrefs().getAppMode() == AppMode.LOAD_EDIT_MODE) {
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int choice = jfc.showSaveDialog(null);
			// Nur speichern wenn OK gedrückt
			if (choice == JFileChooser.APPROVE_OPTION) {
				String temppfad = jfc.getSelectedFile().getAbsolutePath();
				System.out.println(temppfad);
				if (temppfad.endsWith(".star")) {
				} else {
					temppfad = temppfad + ".star";
				}
				System.out.println(temppfad);
				try {
					File f = new File(temppfad);
					FileOutputStream fos = new FileOutputStream(f);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.flush();
					// TO DO Unklar wie wir ans Starfield kommen
					oos.writeObject(new Starfield(10, 10));
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
