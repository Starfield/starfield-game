package game.model;

import java.awt.Dimension;
import java.util.ArrayList;

/** Model/Logik des Starfields
 * 
 * @author Alexander Arians
 *
 */



public class Starfield {

	ArrayList<ArrayList<Field>> listcontainer;
	Boolean playable;
	Dimension size;
	
		



public void createStarfield(int xNumber, int yNumber){
	size.setSize(xNumber, yNumber);
	for(int i=0; i< xNumber; i++){
		ArrayList<Field> yList = new ArrayList<Field>();
		for(int y=0; y<yNumber; y++){
			yList.add(new Field(i,y));
		}
		listcontainer.add(yList);	
	}

}


 public boolean isPlayable(){
	 return playable;
 }
 
 public Field getField(int xCoord, int yCoord){
	 
	 return listcontainer.get(xCoord).get(yCoord);
 }
 
 
 		// zum Ändern des Feldes für den Editor
// 		public void addFields(int addXRows, int addYRows){
// 			size.setSize(size.getWidth()+addXRows, size.getHeight()+addYRows);
//		for(int i=0; i< addXRows; i++){
//			ArrayList<Field> yList = new ArrayList<Field>();
//			for(int y=0; y<addYRows; y++){
//				yList.add(new Field(i,y));
//			}
//			listcontainer.add(yList);	
//		}
// 		}
 		
 		public void addXFields(int addXRows){
 			
 		}
		
		// zum Ändern des Feldes für den Editor
		public void deleteXFields(int deleteXRows){
			size.setSize(size.getWidth()-deleteXRows, size.getHeight());
			for(int i=0; i< deleteXRows; i++){
				listcontainer.remove(listcontainer.size()-1);
			}
			
			
		}
		public void deleteYFields(int deleteYRows){
			
			for(int y=0; y< listcontainer.size(); y++){
				listcontainer.get(y).remove(listcontainer.get(y).size()-1);
			}
			
		}
		
 }
 
 