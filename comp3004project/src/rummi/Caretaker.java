package rummi;

import java.util.ArrayList;




//Memento(Board) is just a "Holder" for the HMap? right lol idk
//So getMemento is is getting the HMap at an idex from board? -_-
public class Caretaker {

	ArrayList<Board> savedHMaps = new ArrayList<Board>();
	
	public void addBoard(Board m) {
		savedHMaps.add(m); 
	}
	
	public Board getBoard(int index) {
		return savedHMaps.get(index);
	}
	
}

//ARE WE SAVING BOARD OBJECTS OR HASHMAPS????		WHICE ONE??!?!?!?!?!?!?!?!!?!!!^^^^ OR vvvv

/*
public class Caretaker {

	ArrayList<HashMap> savedHMaps = new ArrayList<HashMap>();
	
	public void addBoard(HashMap m) {
		savedHMaps.add(m); 
	}
	
	public HashMap getMemento(int index) {
		return savedHMaps.get(index);
	}
	
}
*/