package rummi;

import java.util.ArrayList;

public class Caretaker {

	ArrayList<Board> savedStates = new ArrayList<Board>();
	
	public void addMemento(Board m) {
		savedStates.add(m); 
	}
	
	public Board getMemento(int index) {
		return savedStates.get(index);
	}
	
}
