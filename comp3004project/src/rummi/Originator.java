package rummi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;


public class Originator {
	private ArrayList<Object> state = new ArrayList<Object>();
    
	public void set(ArrayList<Object> b) {
		System.out.println("From Originator: Current version of board.deck state:	" + b.get(1));
		
		ArrayList<Player> pListO = (ArrayList<Player>) b.get(0);
		Deck  deckO = new Deck();
		//deckO = (Deck) b.get(1);
		System.out.println("This should have a fucking diff address: " + deckO);
		HashMap<Point, Tile> boardTilesO = (HashMap<Point, Tile>) b.get(2);
		ArrayList<Tile> handTilesO =  (ArrayList<Tile>) b.get(3);
		ArrayList<Meld> meldsO =  (ArrayList<Meld>) b.get(4);
		
		state.add(pListO);
		state.add(deckO);
		state.add(boardTilesO);
		state.add(handTilesO);
		state.add(meldsO);		
		System.out.println("From Originator: Current version of board.deck state after creating new in originator(set):	" + state.get(1));
		
	}
	
	//Adding values to current BoardState(Memento)
	public Board saveToMemento() {
		System.out.println("From Originator: Setting a new Board to be saved in ArrayList of boards with values of:	" + state.get(1) + "\n\n\n");	
		Board memento = new Board();
		memento.setState(state);
		return memento;
	}
	
	//Restoring BoardState
	//Wiki
	public void restoreFromMemento(Board board) {		
		state = board.getSavedState();		
		System.out.println("From Originator: BoardState saved in Board: \n");	
	}
	
	public ArrayList<Object> getState(){
		return state;
	}
}


