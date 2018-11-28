package rummi;

import java.util.ArrayList;


public class Originator {
	private ArrayList<Object> state = new ArrayList<Object>();
    
	public void setState(ArrayList<Object> b) {
		//state = new ArrayList<Object>();			can we inti here?
		state.add(b.get(0));
		state.add(b.get(1));
		state.add(b.get(2));
		state.add(b.get(3));
		state.add(b.get(4));		
	}
	
	//Adding values to current BoardState(Memento)
	public Board storeInMemento() {
		System.out.println("From Originator: Saving board state (Address of board being saved:  " + state.get(1) + ")");	
		Board memento = new Board(state);
		return memento;
	}
	
	//Restoring BoardState
	public ArrayList<Object> restoreFromMemento(Board board) {		
		state = board.getState();		
		System.out.println("From originator: Previous BoardState saved in Board:  " + state.get(1) + "\n");
		return state;
	}
	
}


