/**
 * 
 */
package game.menubar;

import game.core.GamePreferences.AppMode;
import game.model.Starfield;
import game.ui.MainWindow;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author schroeder_jan
 * 
 */
public class LoadPuzzleAction extends AbstractAction {

	public LoadPuzzleAction(String text, ImageIcon icon) {
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
		boolean goOn = true;
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileHidingEnabled(true);
		FileFilter ff = new FileNameExtensionFilter("Starfield-Puzzle", "star");
		jfc.setFileFilter(ff);
		File dirfile = new File("Puzzle");
		jfc.setCurrentDirectory(dirfile.getAbsoluteFile());
		do {
			// Nur laden wenn User OK geklickt hat
			if (jfc.showOpenDialog(jfc) == JFileChooser.APPROVE_OPTION) {
				String temppfad = jfc.getSelectedFile().getAbsolutePath();
				if (temppfad.endsWith(".star")) {
					try {
						File f = new File(temppfad);
						FileInputStream fis = new FileInputStream(f);
						ObjectInputStream ois = new ObjectInputStream(fis);
						Object o = ois.readObject();
						if (o instanceof Starfield) {
							// Starfield zur Bearbeitung vorbereiten
							((Starfield) o).prepareUserContent(false);
							// Starfield in die GamePrefs zur Abholung bereit
							// stellen
							MainWindow.getInstance().getGamePrefs()
									.setLoadedStarfield((Starfield) o);
							// Dem MainWindow bescheid geben, die Elemente neu zu
							// erstellen
							MainWindow.getInstance().getGamePrefs()
									.setAppMode(AppMode.LOAD_EDIT_MODE);
							MainWindow.getInstance().initGame();
							goOn = false;
						}
						else {
							JOptionPane.showMessageDialog(null,
									"Bitte w�hlen Sie ein spielbares Puzzle!",
									"Puzzle ist nicht spielbar",
									JOptionPane.WARNING_MESSAGE);
						}
						ois.close();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"W�hlen sie eine g�ltige Datei!",
								"Dateityp ist ung�ltig",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null,
							"W�hlen sie eine g�ltige Datei!",
							"Dateityp ist ung�ltig",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				goOn = false;
			}
		} while (goOn);
	}
}
