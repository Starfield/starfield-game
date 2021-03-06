package game.model;

import game.core.ImageResources;
import game.core.ImageResources.Images;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Field extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Model/Logik von einem Field
	 * 
	 * @author Alexander Arians
	 * 
	 */

	public enum AllowedContent {
		CONTENT_STAR, CONTENT_EMPTY, CONTENT_GRAYED, CONTENT_ARROW_U, CONTENT_ARROW_D, CONTENT_ARROW_L, CONTENT_ARROW_R, CONTENT_ARROW_UR, CONTENT_ARROW_UL, CONTENT_ARROW_DR, CONTENT_ARROW_DL;

	}

	int xPos;
	int yPos;
	AllowedContent userContent;
	AllowedContent solutionContent;

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
		case CONTENT_ARROW_UL:
			newIcon = Images.CONTENT_ARROW_UL;
			break;
		case CONTENT_ARROW_U:
			newIcon = Images.CONTENT_ARROW_U;
			break;
		case CONTENT_ARROW_UR:
			newIcon = Images.CONTENT_ARROW_UR;
			break;
		case CONTENT_ARROW_L:
			newIcon = Images.CONTENT_ARROW_L;
			break;
		case CONTENT_ARROW_R:
			newIcon = Images.CONTENT_ARROW_R;
			break;
		case CONTENT_ARROW_DL:
			newIcon = Images.CONTENT_ARROW_DL;
			break;
		case CONTENT_ARROW_D:
			newIcon = Images.CONTENT_ARROW_D;
			break;
		case CONTENT_ARROW_DR:
			newIcon = Images.CONTENT_ARROW_DR;
			break;
		}
		if (newIcon == null)
			return;
		setIcon(ImageResources.getIcon(newIcon));
		this.userContent = userContent;
	}

	/**
	 * Setzt die richtige L�sung
	 */
	public void setSolutionContent(AllowedContent solutionContent) {
		this.solutionContent = solutionContent;
	}

	public Field(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		setUserContent(AllowedContent.CONTENT_EMPTY);
		setSolutionContent(AllowedContent.CONTENT_EMPTY);
		setBorder(new LineBorder(Color.WHITE, 1));

	}

	/**
	 * Liefert den vom User gew�hlten Content
	 */
	public AllowedContent getUserContent() {
		if (userContent == null) {
			userContent = AllowedContent.CONTENT_EMPTY;
		}
		return userContent;
	}

	/**
	 * Liefert die richtige L�sung f�r das Feld
	 */
	public AllowedContent getSolutionContent() {
		if (solutionContent == null) {
			solutionContent = AllowedContent.CONTENT_EMPTY;
		}
		return solutionContent;
	}

	/**
	 * �berpr�ft ob aktueller UserContent die richtige L�sung ist
	 */
	public boolean IsCurrentContentRight() {
		if (userContent.equals(solutionContent)) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ausgabe = getSolutionContent()+" "+getxPos()+" "+getyPos();
		return ausgabe;
	}
}
