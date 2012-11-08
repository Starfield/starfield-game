package game.model;

import game.model.Field.AllowedContent;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model/Logik des Starfields
 * 
 * @author Alexander Arians
 * 
 */

public class Starfield implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<Field>> listcontainer;
	Boolean playable;

	Dimension size;

	public Starfield(int xNumber, int yNumber) {
		size = new Dimension();
		size.setSize(xNumber, yNumber);
		listcontainer = new ArrayList<ArrayList<Field>>();
		createStarfield(xNumber, yNumber);
	}

	public void createStarfield(int xNumber, int yNumber) {
		for (int i = 0; i < xNumber; i++) {
			ArrayList<Field> yList = new ArrayList<Field>();
			for (int y = 0; y < yNumber; y++) {
				yList.add(new Field(i, y));
			}
			listcontainer.add(yList);
		}

	}

	public void setPlayable(Boolean playable) {
		this.playable = playable;
	}

	public boolean isPlayable() {
		return playable;
	}

	public Field getField(int xCoord, int yCoord) {

		return listcontainer.get(xCoord).get(yCoord);
	}

	// zum HinzufÃ¼gen von Felder in der X-Dimension fÃ¼r den Editor
	public void addXFields(int addXRows) {
		size.setSize(size.getWidth() + addXRows, size.getHeight());
		for (int i = 0; i < addXRows; i++) {
			ArrayList<Field> yList = new ArrayList<Field>();
			for (int y = 0; y < size.getHeight(); y++) {
				yList.add(new Field(i, y));
			}
			listcontainer.add(yList);
		}
	}

	// zum HinzufÃ¼gen von Felder in der Y-Dimension fÃ¼r den Editor
	public void addYFields(int addYRows) {

		for (int i = 0; i < size.getWidth(); i++) {
			for (int y = 0; y < addYRows; y++) {

				listcontainer.get(i).add(
						new Field(i, (int) (size.getHeight() + y + 1)));
			}
		}
		size.setSize(size.getWidth(), size.getHeight() + addYRows);
	}

	// zum Ã„ndern des Feldes fÃ¼r den Editor X-Dimension
	public void deleteXFields(int deleteXRows) {
		size.setSize(size.getWidth() - deleteXRows, size.getHeight());
		for (int i = 0; i < deleteXRows; i++) {
			listcontainer.remove(listcontainer.size() - 1);
		}

	}

	// zum Ã„ndern des Feldes fÃ¼r den Editor Y-Dimension
	public void deleteYFields(int deleteYRows) {

		for (int y = 0; y < listcontainer.size(); y++) {
			listcontainer.get(y).remove(listcontainer.get(y).size() - 1);
		}

	}

	// gibt die GrÃ¶ÃŸe des Spieldfeldes zurÃ¼ck
	public Dimension getSize() {

		return size;
	}

	/**
	 * Zählt die Sterne in einer Spalte
	 */
	public int getStarCountX(int column) {
		int starcounter = 0;
		for (Field f : listcontainer.get(column)) {
			if (f.getSolutionContent().equals(AllowedContent.CONTENT_STAR)) {
				starcounter = starcounter++;
			}
		}

		return starcounter;
	}

	/**
	 * Zählt die Sterne in einer Reihe
	 */
	public int getStarCountY(int row) {
		int starcounter = 0;
		for (int i = 0; i < size.getWidth(); i++) {
			if (listcontainer.get(i).get(row).getSolutionContent()
					.equals(AllowedContent.CONTENT_STAR)) {
				starcounter = starcounter++;
			}
		}
		return starcounter;
	}

	public boolean checkSolution() {
		boolean rightorwrong = true;
		for (int x = 0; x < size.getWidth(); x++) {
			for (int y = 0; y < size.getHeight(); y++) {

				if (!listcontainer.get(x).get(y).getUserContent()
						.equals(listcontainer.get(x).get(y).getUserContent())) {
					rightorwrong = false;
				}

			}
		}

		return rightorwrong;
	}

	public ArrayList<Field> getWrongFields() {
		ArrayList<Field> wrongFields = new ArrayList<Field>();
		for (ArrayList<Field> list : this.listcontainer) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).IsCurrentContentRight()) {
					wrongFields.add(list.get(i));
				}

			}
		}
		return wrongFields;
	}

	/**
	 * Diese Methode dient dazu, vor der Speicherung eines neu Bearbeiteten
	 * Puzzles den Inhalt, den der User eingegeben hat, als Lösung für
	 * zukünftige Spielsessions vorzugeben. <br>
	 * Diese Methode sollte nur vor der Speicherung des Puzzles aufgerufen
	 * werden, sofern die Korrektheit des Puzzles überprüft worden ist.
	 * 
	 * @return - Das Starfield nach Bearbeitung
	 */
	public Starfield copyUserToSolutionContent() {
		for (int y = 0; y < size.getHeight(); y++) {
			for (int x = 0; x < size.getWidth(); x++) {
				getField(x, y).setSolutionContent(
						getField(x, y).getUserContent());
			}
		}
		return this;
	}

	/**
	 * Diese Methode dient dazu, ein bereits gespeichertes Starfield erneut im
	 * Editor zu bearbeiten. Damit der User die Lösung bearbeiten kann, werden
	 * die Daten in den UserContent geladen. <br>
	 * Diese Methode sollte nur direkt nach dem Laden eines Starfields aus einer
	 * serialisierten Datei aufgerufen werden. Wenn das Starfield im Anschluss
	 * im Editor bearbeitet werden soll.
	 * 
	 * @return - Das Starfield nach Bearbeitung
	 */
	public Starfield createSolutionfromUserContent() {
		for (int y = 0; y < size.getHeight(); y++) {
			for (int x = 0; x < size.getWidth(); x++) {
				getField(x, y).setUserContent(
						getField(x, y).getSolutionContent());
			}
		}
		return this;
	}

}
