package game.model;

/** Model/Logik von einem Field
 * 
 * @author Alexander Arians
 *
 */

public class Field {

	int xPos;
	int yPos;
	AbstractContent userContent;
	AbstractContent solutionContent;
	
	
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

	public void setUserContent(AbstractContent userContent) {
		this.userContent = userContent;
	}

	public void setSolutionContent(AbstractContent solutionContent) {
		this.solutionContent = solutionContent;
	}

	public Field(int xPos, int yPos){
	 this.xPos = xPos;
	 this.yPos = yPos;
	}
	
	public AbstractContent getUserContent(){
	return userContent;	
	}
		
	public AbstractContent getSolutionContent(){
	return solutionContent;	
	}
	
}
