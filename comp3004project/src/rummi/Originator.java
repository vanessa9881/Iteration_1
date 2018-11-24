package rummi;

import java.awt.Point;
import java.util.HashMap;

public class Originator {
	private HashMap<Point, Tile> hMap;
	
	public void set(HashMap<Point, Tile> newHMap) {
		System.out.println("From Originator: Current version of HMap\n" + newHMap + "\n");	
		hMap = newHMap;
	}
	
	//Adding values to current HMap(Memento)
	public Board storeInBoard() {
		System.out.println("From Originator: Saving to Board");	
		Board temp = new Board();
		temp.setHMap(hMap);
		return temp;
	}
	
	//Restoring HMap
	public HashMap<Point, Tile> restoreFromBoard(Board board) {
		hMap = board.getSavedHMap();
		System.out.println("From originator: Previous HMap saved in Board\n" + hMap+ "\n");
		return hMap;
	}
}


