package game.model;

import game.core.ImageResources;
import game.core.ImageResources.Images;

import javax.swing.JLabel;

/**
 * Model/Logik von einem Field
 * 
 * @author Alexander Arians
 * 
 */

public class Field extends JLabel {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Definiert Enums für den möglichen Inhalte von Field.
	 * 
	 * @author Nikolaj
	 */
	public enum AllowedContent {
		CONTENT_STAR,
		CONTENT_EMPTY,
		CONTENT_GRAYED,
		CONTENT_ARROW_U,
		CONTENT_ARROW_D,
		CONTENT_ARROW_L,
		CONTENT_ARROW_R,
		CONTENT_ARROW_UR,
		CONTENT_ARROW_UL,
		CONTENT_ARROW_DR,
		CONTENT_ARROW_DL;

	}

	int				xPos;
	int				yPos;
	AllowedContent	userContent;
	AllowedContent	solutionContent;

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void setUserContent(AllowedContent userContent) {

		Images newIcon = null;
		switch (userContent) {
		case CONTENT_STAR:
			newIcon = Images.CONTENT_STAR;
			break;
		case CONTENT_EMPTY:
			newIcon = Images.CONTENT_EMPTY;
			break;
		case CONTENT_GRAYED:
			newIcon = Images.CONTENT_GRAYED;
			break;
		case CONTENT_ARROW_R:
			newIcon = Images.CONTENT_ARROW_R;
			break;
		}
		if (newIcon == null)
			return;
		setIcon(ImageResources.getIcon(newIcon));
		this.userContent = userContent;
	}

	public void setSolutionContent(AllowedContent solutionContent) {
		this.solutionContent = solutionContent;
	}

	public Field(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public AllowedContent getUserContent() {
		return userContent;
	}

	public AllowedContent getSolutionContent() {
		return solutionContent;
	}

}
