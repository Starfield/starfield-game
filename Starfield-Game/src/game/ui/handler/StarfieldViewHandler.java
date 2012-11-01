/**
 * 
 */
package game.ui.handler;

import game.model.Field.AllowedContent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Jan
 * 
 */
public class StarfieldViewHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent pE) {
		Object o = pE.getSource();
		if (o instanceof game.model.Field) {
			if (pE.getButton() == MouseEvent.BUTTON1) {
				if (((game.model.Field) o).getUserContent() == AllowedContent.CONTENT_EMPTY)
					((game.model.Field) o)
							.setUserContent(AllowedContent.CONTENT_STAR);
				else if (((game.model.Field) o).getUserContent() == AllowedContent.CONTENT_STAR)
					((game.model.Field) o)
							.setUserContent(AllowedContent.CONTENT_EMPTY);
			}
			if (pE.getButton() == MouseEvent.BUTTON2) {
				if (((game.model.Field) o).getUserContent() == AllowedContent.CONTENT_EMPTY)
					((game.model.Field) o)
							.setUserContent(AllowedContent.CONTENT_ARROW_R);
				else if (((game.model.Field) o).getUserContent() == AllowedContent.CONTENT_ARROW_R)
					((game.model.Field) o)
							.setUserContent(AllowedContent.CONTENT_EMPTY);
			}
			if (pE.getButton() == MouseEvent.BUTTON3) {
				if (((game.model.Field) o).getUserContent() == AllowedContent.CONTENT_EMPTY)
					((game.model.Field) o)
							.setUserContent(AllowedContent.CONTENT_GRAYED);
				else if (((game.model.Field) o).getUserContent() == AllowedContent.CONTENT_GRAYED)
					((game.model.Field) o)
							.setUserContent(AllowedContent.CONTENT_EMPTY);
			}
		}

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
