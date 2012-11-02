package game.model;

import game.model.Field.AllowedContent;

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
	
		

public Starfield(int xNumber, int yNumber){
	size = new Dimension();
	size.setSize(xNumber, yNumber);
	listcontainer = new ArrayList<ArrayList<Field>>();
	createStarfield(xNumber, yNumber);
}

public void createStarfield(int xNumber, int yNumber){
	for(int i=0; i< xNumber; i++){
		ArrayList<Field> yList = new ArrayList<Field>();
		for(int y=0; y<yNumber; y++){
			yList.add(new Field(i,y));
		}
		listcontainer.add(yList);	
	}

}

public void setPlayable(Boolean playable) {
	this.playable = playable;
}


 public boolean isPlayable(){
	 return playable;
 }
 
 public Field getField(int xCoord, int yCoord){
	 
	 return listcontainer.get(xCoord).get(yCoord);
 }
 
 
 		
 	    //zum Hinzufügen von Felder in der X-Dimension für den Editor
 		public void addXFields(int addXRows){
 			size.setSize(size.getWidth()+addXRows,size.getHeight());
 			for(int i=0; i< addXRows; i++){
 				ArrayList<Field> yList = new ArrayList<Field>();
 				for(int y=0; y<size.getHeight(); y++){
 					yList.add(new Field(i,y));
 				}
 					listcontainer.add(yList);
 			}
 		}
 		
 		//zum Hinzufügen von Felder in der Y-Dimension für den Editor	
 		public void addYFields(int addYRows){
 			
 			for(int i=0; i< size.getWidth(); i++){
 				for (int y=0; y< addYRows;y++){
 					
 				listcontainer.get(i).add(new Field(i,(int) (size.getHeight()+y+1)));
 				}
 			}
 			size.setSize(size.getWidth(),size.getHeight()+addYRows);
 		}
 		
		
		// zum Ändern des Feldes für den Editor X-Dimension
		public void deleteXFields(int deleteXRows){
			size.setSize(size.getWidth()-deleteXRows, size.getHeight());
			for(int i=0; i< deleteXRows; i++){
				listcontainer.remove(listcontainer.size()-1);
			}
			
			
		}
		
		// zum Ändern des Feldes für den Editor Y-Dimension
		public void deleteYFields(int deleteYRows){
			
			for(int y=0; y< listcontainer.size(); y++){
				listcontainer.get(y).remove(listcontainer.get(y).size()-1);
			}
			
		}
		
		// gibt die Größe des Spieldfeldes zurück
		public Dimension getSize(){
			
		return size;
		}
		
		/**
		 * Z�hlt die Sterne in einer Spalte
		 */
		public int getStarCountX(int column){
			int starcounter = 0;
			for(Field f : listcontainer.get(column)){
				if(f.getSolutionContent().equals(AllowedContent.CONTENT_STAR)){	
				starcounter=starcounter++;
				}
			}
					
			return starcounter;
		}
		
		/**
		 * Z�hlt die Sterne in einer Reihe
		 */
		public int getStarCountY(int row){
			int starcounter = 0;
			for(int i = 0; i< size.getWidth(); i++){			
			if(listcontainer.get(i).get(row).getSolutionContent().equals(AllowedContent.CONTENT_STAR)){
				starcounter=starcounter++;
			}
			}
			return starcounter;
		}
		
		public boolean checkSolution(){
			boolean rightorwrong = true;
			for(int x = 0; x < size.getWidth(); x++){
				for(int y =0; y < size.getHeight(); y++){
					
			if(!listcontainer.get(x).get(y).getUserContent().equals(listcontainer.get(x).get(y).getUserContent())){
				rightorwrong = false;
			}
			
				}}
			
			
			return rightorwrong;
		}
		
		public ArrayList<Field> getWrongFields(){
			ArrayList<Field> wrongFields = new ArrayList();
			for(ArrayList<Field> list : this.listcontainer){
				for(int i = 0; i<list.size();i++){
					if(list.get(i).IsCurrentContentRight()){
						wrongFields.add(list.get(i));
					}
		
				}
			}
			return wrongFields;
		}
		
		
 }
 