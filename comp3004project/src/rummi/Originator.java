package rummi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;


public class Originator {
	private Board boardState;
    
	public void set(Board b ) {
		System.out.println("I get here");
		boardState = b;
	}
    
	
	//Adding values to current BoardState(Memento)
	public Board saveToMemento() {
		return new Board(boardState);
	}
	
	//Restoring BoardState
	public Board restoreFromMemento(Board board) {	
		boardState = board;
		return boardState;
	}

}


