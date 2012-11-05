/**
 * 
 */
package game.menubar;

import game.model.Starfield;
import game.ui.MainWindow;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

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
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int choice = jfc.showOpenDialog(null);
		// Nur laden wenn User OK geklickt hat
		if (choice == JFileChooser.APPROVE_OPTION) {
			String temppfad = jfc.getSelectedFile().getAbsolutePath();
			if (temppfad.endsWith(".estar")) {
				try {
					File f = new File(temppfad);
					FileInputStream fis = new FileInputStream(f);
					ObjectInputStream ois = new ObjectInputStream(fis);
					Starfield starfield = null;
					Object o = ois.readObject();
					if (o instanceof Starfield) {
						starfield = (Starfield) o;
						MainWindow.getGamePrefs().setLoadedStarfield(starfield);
						MainWindow.getGamePrefs().getMainWindow().initGame();
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
