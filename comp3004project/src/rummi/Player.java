package rummi;

import java.util.ArrayList;
import java.util.List;
import rummi.Tile;
	
	

public class Player {
	//To be done: Constructor with hand, values etc... 
	private int handValue;
	private ArrayList<Tile> hand;
	
	public Player() {
		this.hand = new ArrayList<Tile>();
		this.handValue = 0; 
	}
	
	public int getHandValue() {
		return this.handValue;
	}
	
	public void sortHand() {
		//Comparator cmp = new Comparator();
	}

	public void play(Game game) {
		// To be overriden by each strategy
		
	}

	public boolean checkWin() {
		if (getHandValue() == 0) {
			return true;
		}
		else {return false;}
	}

	public void play() {
		// TODO Auto-generated method stub
		
	}
}
