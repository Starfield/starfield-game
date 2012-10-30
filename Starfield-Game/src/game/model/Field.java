package game.model;

/** Model/Logik von einem Field
 * 
 * @author Alexander Arians
 *
 */

public class Field {

	int xPos;
	int yPos;
	IContent userContent;
	IContent solutionContent;
	
	
	
	public Field(int xPos, int yPos){
	 this.xPos = xPos;
	 this.yPos = yPos;
	}
	
	public userContent getUserContent(){
	return userContent;	
	}
		
	public solutionContent getSolutionContent(){
	return solutionContent;	
	}
}
