package rummi;

import java.util.ArrayList;


public class Originator {
	private ArrayList<Object> state;
    
	public void setState(ArrayList<Object> b) {
		//state.set(0, b.get(0));
		state.set(1, b.get(1));
		state.set(2, b.get(2));
		state.set(3, b.get(3));
		state.set(4, b.get(4));		
	}
	
	//Adding values to current HMap(Memento)
	public Board storeInMemento() {
		System.out.println("From Originator: Saving board state");	
		Board memento = new Board(state);
		return memento;
	}
	
	//Restoring HMap
	public ArrayList<Object> restoreFromMemento(Board board) {		
		state = board.getState();
		
		System.out.println("From originator: Previous HMap saved in Board\n" + state + "\n");
		return state;
	}
	
}


