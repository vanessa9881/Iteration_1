package rummi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;


public class Originator {
	private Board boardState = new Board();
    
	public void set(Board b ) {
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


