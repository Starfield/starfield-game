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
		for (int x = 0; x < xNumber; x++) {
			ArrayList<Field> yList = new ArrayList<Field>();
			for (int y = 0; y < yNumber; y++) {
				yList.add(new Field(x, y));
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

	/**
	 * Diese Methode ändert die Größe des Starfields auf die übergebenen
	 * Paramatergrößen
	 * 
	 * @param newXSize
	 *            neue Anzahl an Spalten
	 * @param newYSize
	 *            neue Anzahl an Zeilen
	 */
	public Starfield changeSize(int newXSize, int newYSize) {

		// Überprüfen ob sich die Anzahl an Spalten geändert hat.
		if (newXSize != size.width) {
			// Hat sich die Anzahl geändert wird überprüft ob der Container
			// breiter oder kleiner gemacht werden muss
			if (newXSize > size.width) {
				// Soll der Container breiter werden werden nun Spalten
				// angehängt und mit leeren Feldern gefüllt.
				for (int i = size.width; i < newXSize; i++) {
					ArrayList<Field> spalte = new ArrayList<Field>();
					for (int row = 0; row < size.height; row++)
						spalte.add(new Field(i, row));
					listcontainer.add(spalte);
				}
			} else {
				// Soll der Container schmaler werden, werden nun überschüssige
				// Spalten gelöscht
				for (int i = size.width; i > newXSize; i--) {
					listcontainer.remove(i - 1);
				}

			}
			// Nachdem der Container angepasst wurde, wird die neue size gesetzt
			size.width = newXSize;
		}
		// Überprüfen ob sich die Anzahl an Zeilen geändert hat
		if (newYSize != size.height) {
			// Hat sich die Anzahl geändert wird überprüft ob der Container
			// länger oder kürzer gemacht werden soll
			if (newYSize > size.height) {
				// Soll der Container länger werden, werden nun an jeden
				// Spaltenvektor neue leere Field gehangen
				for (int i = 0; i < size.width; i++) {
					for (int column = size.height; column < newYSize; column++)
						listcontainer.get(i).add(new Field(i, column));
				}
			} else {
				// Soll der Container kürzer werden, wird von jeder Spalte die
				// Differenz an Zeilen gelöscht
				for (int i = 0; i < size.width; i++) {
					for (int column = size.height; column > newYSize; column--)
						listcontainer.get(i).remove(column - 1);
				}
			}
			// Nachdem der Container angepasst wurde, wird die neue size gesetzt
			size.height = newYSize;
		}
		return this;
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
		for (int y = 0; y < size.height; y++) {
			if (getField(column, y).getSolutionContent() == AllowedContent.CONTENT_STAR)
				starcounter++;
		}
		return starcounter;
	}

	/**
	 * Zählt die Sterne in einer Reihe
	 */
	public int getStarCountY(int row) {
		int starcounter = 0;
		for (int x = 0; x < size.width; x++) {
			if (getField(x, row).getSolutionContent() == AllowedContent.CONTENT_STAR)
				starcounter++;
		}
		return starcounter;
	}

	public boolean checkSolution() {
		boolean rightorwrong = true;
		for (int x = 0; x < size.getWidth(); x++) {
			for (int y = 0; y < size.getHeight(); y++) {

				if (!listcontainer
						.get(x)
						.get(y)
						.getUserContent()
						.equals(listcontainer.get(x).get(y)
								.getSolutionContent())) {
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
	 * Diese Methode dient dazu, ein bereits gespeichertes Starfield erneut im
	 * Editor zu bearbeiten. Damit der User die Lösung bearbeiten kann, werden
	 * die Daten in den UserContent geladen. <br>
	 * Diese Methode sollte nur direkt nach dem Laden eines Starfields aus einer
	 * serialisierten Datei aufgerufen werden. Wenn das Starfield im Anschluss
	 * im Editor bearbeitet werden soll.
	 * 
	 * @return - Das Starfield nach Bearbeitung
	 */
	public Starfield prepareUserContent(boolean forPlay) {
		for (int y = 0; y < size.getHeight(); y++) {
			for (int x = 0; x < size.getWidth(); x++) {
				AllowedContent solutionContent = getField(x, y)
						.getSolutionContent();
				if (forPlay) {
					if (solutionContent != AllowedContent.CONTENT_STAR)
						getField(x, y).setUserContent(solutionContent);
				} else
					getField(x, y).setUserContent(solutionContent);
			}
		}
		return this;
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
	 * Diese Methode dient dazu, nach dem Laden eines Starfields den UserContent
	 * zu leeren, damit ein neues Spiel begonnen werden kann. <br>
	 * Die Methode sollte nur direkt nach dem Laden eines Starfield aus der
	 * serialisierten Datei aufgerufen werden.
	 */
	public Starfield clearUserContent() {
		for (int y = 0; y < size.getHeight(); y++) {
			for (int x = 0; x < size.getWidth(); x++) {
				getField(x, y).setUserContent(AllowedContent.CONTENT_EMPTY);

			}
		}
		return this;
	}

	/**
	 * Diese Methode gibt eine ArrayList mit Argumenten zurück: An Pos. 0 Liste
	 * mit Felder, auf die ein Pfeil zeigt oder an Pos 1-8 ArrayListen mit
	 * Feldern um ein Stern rum in der jeweiligen Richtung.
	 * (UL,U,UR,R,DR,D,DL,L)
	 */
	private ArrayList<ArrayList<Field>> getFieldsInDirectionFromStarOrArrow(
			Field field) {
		ArrayList<ArrayList<Field>> args = new ArrayList<ArrayList<Field>>();
		ArrayList<Field> pointedFieldsFromArrow = new ArrayList<Field>();
		args.add(pointedFieldsFromArrow);
		ArrayList<Field> pointedFieldsFromStarUL = new ArrayList<Field>();
		ArrayList<Field> pointedFieldsFromStarU = new ArrayList<Field>();
		ArrayList<Field> pointedFieldsFromStarUR = new ArrayList<Field>();
		ArrayList<Field> pointedFieldsFromStarR = new ArrayList<Field>();
		ArrayList<Field> pointedFieldsFromStarDR = new ArrayList<Field>();
		ArrayList<Field> pointedFieldsFromStarD = new ArrayList<Field>();
		ArrayList<Field> pointedFieldsFromStarDL = new ArrayList<Field>();
		ArrayList<Field> pointedFieldsFromStarL = new ArrayList<Field>();
		args.add(pointedFieldsFromStarUL);
		args.add(pointedFieldsFromStarU);
		args.add(pointedFieldsFromStarUR);
		args.add(pointedFieldsFromStarR);
		args.add(pointedFieldsFromStarDR);
		args.add(pointedFieldsFromStarD);
		args.add(pointedFieldsFromStarDL);
		args.add(pointedFieldsFromStarL);
		switch (field.getUserContent()) {
		case CONTENT_STAR:
			Field field2 = field;
			while (!(getField_UL(field2) == null)) {
				field2 = getField_UL(field2);
				pointedFieldsFromStarUL.add(field2);
			}
			field2 = field;
			while (!(getField_U(field2) == (null))) {
				field2 = getField_U(field2);
				pointedFieldsFromStarU.add(field2);
			}
			field2 = field;
			while (!(getField_UR(field2) == (null))) {
				field2 = getField_UR(field2);
				pointedFieldsFromStarUR.add(field2);
			}
			field2 = field;
			while (!(getField_L(field2) == (null))) {
				field2 = getField_L(field2);
				pointedFieldsFromStarL.add(field2);
			}
			field2 = field;
			while (!(getField_R(field2) == (null))) {
				field2 = getField_R(field2);
				pointedFieldsFromStarR.add(field2);
			}
			field2 = field;
			while (!(getField_DL(field2) == (null))) {
				field2 = getField_DL(field2);
				pointedFieldsFromStarDL.add(field2);
			}
			field2 = field;
			while (!(getField_D(field2) == (null))) {
				field2 = getField_D(field2);
				pointedFieldsFromStarD.add(field2);
			}
			field2 = field;
			while (!(getField_DR(field2) == (null))) {
				field2 = getField_DR(field2);
				pointedFieldsFromStarDR.add(field2);
			}
			break;
		case CONTENT_EMPTY:
			break;
		case CONTENT_GRAYED:
			break;
		case CONTENT_ARROW_UL:
			while (!(getField_UL(field) == (null))) {
				field = getField_UL(field);
				pointedFieldsFromArrow.add(field);
			}
			break;
		case CONTENT_ARROW_U:
			while (!(getField_U(field) == (null))) {
				field = getField_U(field);
				pointedFieldsFromArrow.add(field);
			}
			break;
		case CONTENT_ARROW_UR:
			while (!(getField_UR(field) == (null))) {
				field = getField_UR(field);
				pointedFieldsFromArrow.add(field);
			}
			break;
		case CONTENT_ARROW_L:
			while (!(getField_L(field) == (null))) {
				field = getField_L(field);
				pointedFieldsFromArrow.add(field);
			}
			break;
		case CONTENT_ARROW_R:
			while (!(getField_R(field) == (null))) {
				field = getField_R(field);
				pointedFieldsFromArrow.add(field);
			}
			break;
		case CONTENT_ARROW_DL:
			while (!(getField_DL(field) == (null))) {
				field = getField_DL(field);
				pointedFieldsFromArrow.add(field);
			}
			break;
		case CONTENT_ARROW_D:
			while (!(getField_D(field) == (null))) {
				field = getField_D(field);
				pointedFieldsFromArrow.add(field);
			}
			break;
		case CONTENT_ARROW_DR:
			while (!(getField_DR(field) == (null))) {
				field = getField_DR(field);
				pointedFieldsFromArrow.add(field);
			}
			break;
		}

		return args;
	}

	/**
	 * Gibt das Feld linksoben vom übergebenen Feld aus.
	 * 
	 * @return
	 */
	public Field getField_UL(Field field) {
		if (field.getxPos() > 0 && field.getyPos() > 0) {
			return listcontainer.get(field.getxPos() - 1).get(
					field.getyPos() - 1);
		}
		return null;
	}

	/**
	 * Gibt das Feld oben vom übergebenen Feld aus.
	 * 
	 * @return
	 */
	public Field getField_U(Field field) {
		if (field.getyPos() > 0) {
			return listcontainer.get(field.getxPos()).get(field.getyPos() - 1);
		}
		return null;
	}

	/**
	 * Gibt das Feld rechtsoben vom übergebenen Feld aus.
	 * 
	 * @return
	 */
	public Field getField_UR(Field field) {
		if (field.getxPos() < (int) size.getSize().getWidth() - 1
				&& field.getyPos() > 0) {
			return listcontainer.get(field.getxPos() + 1).get(
					field.getyPos() - 1);
		}
		return null;
	}

	/**
	 * Gibt das Feld rechts vom übergebenen Feld aus.
	 * 
	 * @return
	 */
	public Field getField_R(Field field) {
		if (field.getxPos() < (int) size.getSize().getWidth() - 1) {
			return listcontainer.get(field.getxPos() + 1).get(field.getyPos());
		}
		return null;
	}

	/**
	 * Gibt das Feld rechtsunten vom übergebenen Feld aus.
	 * 
	 * @return
	 */
	public Field getField_DR(Field field) {
		if (field.getxPos() < (int) size.getSize().getWidth() - 1
				&& field.getyPos() < (int) size.getSize().getHeight() - 1) {
			return listcontainer.get(field.getxPos() + 1).get(
					field.getyPos() + 1);
		}
		return null;
	}

	/**
	 * Gibt das Feld unten vom übergebenen Feld aus.
	 * 
	 * @return
	 */
	public Field getField_D(Field field) {
		if (field.getyPos() < (int) size.getSize().getHeight() - 1) {
			return listcontainer.get(field.getxPos()).get(field.getyPos() + 1);
		}
		return null;
	}

	/**
	 * Gibt das Feld linksunten vom übergebenen Feld aus.
	 * 
	 * @return
	 */
	public Field getField_DL(Field field) {
		if (field.getxPos() > 0
				&& field.getyPos() < (int) size.getSize().getHeight() - 1) {
			return listcontainer.get(field.getxPos() - 1).get(
					field.getyPos() + 1);
		}
		return null;
	}

	/**
	 * Gibt das Feld links vom übergebenen Feld aus.
	 * 
	 * @return
	 */
	public Field getField_L(Field field) {
		if (field.getxPos() > 0) {
			return listcontainer.get(field.getxPos() - 1).get(field.getyPos());
		}
		return null;
	}

	/**
	 * Testet ob ein Stern in einer Reine/ Arrayliste liegt.
	 * 
	 * @return
	 */
	private Boolean ContainsStar(ArrayList<Field> directionFieldList) {
		for (Field f : directionFieldList) {
			if (f.getUserContent().equals(AllowedContent.CONTENT_STAR)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Testet ob der Stern von einem entsprechenden Arrow getroffen wird.
	 * 
	 * @return
	 */
	private Boolean IsHitByArrow(ArrayList<Field> FieldList,
			AllowedContent content) {
		switch (content) {
		case CONTENT_ARROW_UL:
			for (Field f : FieldList) {
				if (f.getUserContent().equals(AllowedContent.CONTENT_ARROW_UL)) {
					return true;
				}
			}
			break;
		case CONTENT_ARROW_U:
			for (Field f : FieldList) {
				if (f.getUserContent().equals(AllowedContent.CONTENT_ARROW_U)) {
					return true;
				}
			}
			break;
		case CONTENT_ARROW_UR:
			for (Field f : FieldList) {
				if (f.getUserContent().equals(AllowedContent.CONTENT_ARROW_UR)) {
					return true;
				}
			}
			break;
		case CONTENT_ARROW_L:
			for (Field f : FieldList) {
				if (f.getUserContent().equals(AllowedContent.CONTENT_ARROW_L)) {
					return true;
				}
			}
			break;
		case CONTENT_ARROW_R:
			for (Field f : FieldList) {
				if (f.getUserContent().equals(AllowedContent.CONTENT_ARROW_R)) {
					return true;
				}
			}
			break;
		case CONTENT_ARROW_DL:
			for (Field f : FieldList) {
				if (f.getUserContent().equals(AllowedContent.CONTENT_ARROW_DL)) {
					return true;
				}
			}
			break;
		case CONTENT_ARROW_D:
			for (Field f : FieldList) {
				if (f.getUserContent().equals(AllowedContent.CONTENT_ARROW_D)) {
					return true;
				}
			}
			break;
		case CONTENT_ARROW_DR:
			for (Field f : FieldList) {
				if (f.getUserContent().equals(AllowedContent.CONTENT_ARROW_DR)) {
					return true;
				}
			}
			break;

		}
		return false;
	}

	public boolean checkPlayable() {
		ArrayList<ArrayList<Field>> args = new ArrayList<ArrayList<Field>>();
		for (int x = 0; x < size.getWidth(); x++) {
			for (int y = 0; y < size.getHeight(); y++) {
				args = getFieldsInDirectionFromStarOrArrow(listcontainer.get(x)
						.get(y)); // Rückgabe der Argument ArrayListen
				if (!(args.get(0).isEmpty() && args.get(3).isEmpty() && args
						.get(6).isEmpty())) { // leeres Feld !!!
					if (args.get(3).isEmpty() && args.get(6).isEmpty()) {// Pfeil
						if (!ContainsStar(args.get(0))) { // Checkt ob der Pfeil
															// auf einen Stern
															// zeigt
							return false;
						}
					} else {
						if (!(IsHitByArrow(args.get(1),
								AllowedContent.CONTENT_ARROW_DR))
								&& !(IsHitByArrow(args.get(2),
										AllowedContent.CONTENT_ARROW_D))
								&& !(IsHitByArrow(args.get(3),
										AllowedContent.CONTENT_ARROW_DL))
								&& !(IsHitByArrow(args.get(4),
										AllowedContent.CONTENT_ARROW_L))
								&& !(IsHitByArrow(args.get(5),
										AllowedContent.CONTENT_ARROW_UL))
								&& !(IsHitByArrow(args.get(6),
										AllowedContent.CONTENT_ARROW_U))
								&& !(IsHitByArrow(args.get(7),
										AllowedContent.CONTENT_ARROW_UR))
								&& !(IsHitByArrow(args.get(8),
										AllowedContent.CONTENT_ARROW_R))) {
							return false;
						}
					}

				}
			}
		}

		return true;

	}

}
