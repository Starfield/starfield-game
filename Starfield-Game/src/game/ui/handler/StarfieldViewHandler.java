/**
 * 
 */
package game.ui.handler;

import game.core.ImageResources;
import game.core.ImageResources.Images;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

/**
 * @author Jan
 * 
 */
public class StarfieldViewHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent pE) {
		Object o = pE.getSource();
		if (o instanceof JLabel) {
			if (pE.getButton() == MouseEvent.BUTTON1)
				((JLabel) o).setIcon(ImageResources
						.getIcon(Images.CONTENT_GRAYED));
			if (pE.getButton() == MouseEvent.BUTTON3)
				((JLabel) o).setIcon(ImageResources
						.getIcon(Images.CONTENT_EMPTY));
		}
		System.out.println("Click");

	}

	@Override
	public void mouseEntered(MouseEvent pE) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent pE) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent pE) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent pE) {
		// TODO Auto-generated method stub

	}

}
