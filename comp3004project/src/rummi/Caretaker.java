package rummi;

import java.util.ArrayList;

public class Caretaker {

	ArrayList<Board> savedStates = new ArrayList<Board>();
	
	public void addMemento(Board m) {
		System.out.println("Added the Initial Board objects to the arraylist with address :  " + m.getDeckForMemento());
		savedStates.add(m); 
	}
	
	public Board getMemento(int index) {
		return savedStates.get(index);
	}
	
}
