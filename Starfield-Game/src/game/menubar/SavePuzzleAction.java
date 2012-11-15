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
			jfc.addChoosableFileFilter(ff);
			// Nur speichern wenn OK gedrückt
			if (jfc.showSaveDialog(jfc) == JFileChooser.APPROVE_OPTION) {
				String temppfad = jfc.getSelectedFile().getAbsolutePath();
				if (temppfad.endsWith(".star")) {
				} else {
					temppfad = temppfad + ".star";
				}
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
	}
}
