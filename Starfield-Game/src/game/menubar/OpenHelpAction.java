/**
 * 
 */
package game.menubar;

import game.help.HelpWindow;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * @author Jan
 * 
 */
public class OpenHelpAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpenHelpAction(String text, ImageIcon icon) {
		super(text, icon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent pArg0) {
		// TODO HilfeSeite aufrufen
		HelpWindow.getInstance();
	}

}
