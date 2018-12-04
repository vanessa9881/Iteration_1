package rummi;

import java.util.ArrayList;

public class Caretaker {

	ArrayList<Board> savedBoards = new ArrayList<Board>();
	
	public void addMementoBoard(Board m) {
		Board boardToAdd = m;
		savedBoards.add(boardToAdd); 
	}
	
	public Board getMementoBoard(int index) {
		return savedBoards.get(index);
	}
	
	public int getLastIndex() {
		int i = (savedBoards.size()-1);
		return i;
	}
	
}
