/**
 * 
 */
package game.menubar;

import game.commands.CommandStack;
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
		FileFilter ff = new FileNameExtensionFilter("Starfield-Spielstand", "save");
		jfc.addChoosableFileFilter(ff);
		int fileok = 1;
		do {
			fileok = 1;
			if (jfc.showOpenDialog(jfc) == JFileChooser.APPROVE_OPTION){
				String temppfad = jfc.getSelectedFile().getAbsolutePath();
				fileok = 1;
				if (temppfad.endsWith(".save")){
					try {
						File f = new File(temppfad);
						if (f.exists()){
							
						}
						FileInputStream fis = new FileInputStream(f);
						ObjectInputStream ois = new ObjectInputStream(fis);
						CommandStack commandStack = null;
						Object o = ois.readObject();
						ois.close();
						if (o instanceof CommandStack){
								commandStack = (CommandStack) o;
								MainWindow.getGamePrefs().setLoadedCommandStack(commandStack);
								File starfieldf = commandStack.getStarfieldFile();
								if (starfieldf.exists()){
									fis = new FileInputStream(starfieldf);
									ois = new ObjectInputStream(fis);
									o = ois.readObject();
									ois.close();
									if (o instanceof Starfield){
										MainWindow.getGamePrefs().setLoadedStarfield((Starfield) o);
										MainWindow.getGamePrefs().setAppMode(AppMode.LOAD_GAME_MODE);
										MainWindow.getGamePrefs().getMainWindow().initGame();
									}
									else {
										JOptionPane.showMessageDialog(null,
											    "Das zugehörige Starfield existiert leider nicht mehr.",
											    "Starfield existiert nicht",
											    JOptionPane.WARNING_MESSAGE);
										MainWindow.getGamePrefs().setAppMode(AppMode.GAME_MODE);
										MainWindow.getGamePrefs().removeLoadedCommandStack();
										MainWindow.getGamePrefs().removeLoadedStarfield();
									}
								}	
								else {
									JOptionPane.showMessageDialog(null,
										    "Das zugehörige Starfield existiert leider nicht mehr.",
										    "Starfield existiert nicht",
										    JOptionPane.WARNING_MESSAGE);
									MainWindow.getGamePrefs().setAppMode(AppMode.GAME_MODE);
									MainWindow.getGamePrefs().removeLoadedCommandStack();
									MainWindow.getGamePrefs().removeLoadedStarfield();
								}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,
						    "Wählen sie eine gültige Datei!",
						    "Dateityp ist ungültig",
						    JOptionPane.WARNING_MESSAGE);
					fileok = 0;
				}
			}
		}while (fileok == 0); 
	}
}
