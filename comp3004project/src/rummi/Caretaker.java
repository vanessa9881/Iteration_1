package rummi;

import java.util.ArrayList;

public class Caretaker {

	ArrayList<Board> savedBoards = new ArrayList<Board>();
	
	public void addMemento(Board m) {
		Board boardToAdd = m;
		savedBoards.add(boardToAdd); 
	}
	
	public Board getMemento(int index) {
		return savedBoards.get(index);
	}
	
}
