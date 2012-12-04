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
 * Action f�r ein neues Spiel <br>
 * Durch die Action wird der Dialog zur Auswahl eines neues Puzzles ausgef�hrt.
 * Wird dieser mit OK best�tigt wird das alte Puzzle beendet und das neue Puzzle
 * geladen.
 * 
 * 
 * @author Jan
 * 
 */
public class NewGameAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Standardkonstruktur mit Text und Icon
	 * 
	 * @param text
	 *            - Name der Aktion
	 * @param icon
	 *            - AnzeigeIcon
	 */
	public NewGameAction(String text, ImageIcon icon) {
		super(text, icon);
		// Beispiel f�r weitere Properties zur Action
		putValue(SHORT_DESCRIPTION, "Ein neues Spiel starten");
		putValue(MNEMONIC_KEY, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pArg0) {
		boolean goOn = true;
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileHidingEnabled(true);
		FileFilter ff = new FileNameExtensionFilter("Starfield-Puzzle", "star");
		jfc.setFileFilter(ff);
		File dirfile = new File("Puzzle");
		jfc.setCurrentDirectory(dirfile.getAbsoluteFile());
		StarfieldPreviewComponent previewComp = new StarfieldPreviewComponent();
		jfc.setAccessory(previewComp);
		jfc.addPropertyChangeListener(previewComp);

		// Nur laden wenn User OK geklickt hat
		do {
			if (jfc.showOpenDialog(jfc) == JFileChooser.APPROVE_OPTION) {
				String temppfad = jfc.getSelectedFile().getAbsolutePath();
				if (temppfad.endsWith(".star")) {
					try {
						File f = new File(temppfad);
						FileInputStream fis = new FileInputStream(f);
						ObjectInputStream ois = new ObjectInputStream(fis);
						Object o = ois.readObject();
						if (o instanceof Starfield) {
							if (((Starfield) o).isPlayable()) {
								// Starfield zur Bearbeitung vorbereiten
								((Starfield) o).clearUserContent();
								((Starfield) o).prepareUserContent(true);
								// Starfield in die GamePrefs zur Abholung
								// bereit
								// stellen
								MainWindow.getInstance().getGamePrefs()
										.setLoadedStarfield((Starfield) o);
								MainWindow.getInstance().getGamePrefs()
										.setStarfieldFile(f);
								MainWindow.getInstance().getGamePrefs()
										.setAppMode(AppMode.GAME_MODE);
								MainWindow.getInstance().initGame();
								goOn = false;
							} else {
								JOptionPane.showMessageDialog(null,
										"W�hlen sie eine g�ltige Datei!",
										"Dateityp ist ung�ltig",
										JOptionPane.WARNING_MESSAGE);

							}
						} else {
							JOptionPane.showMessageDialog(null,
									"W�hlen sie eine g�ltige Datei!",
									"Dateityp ist ung�ltig",
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
