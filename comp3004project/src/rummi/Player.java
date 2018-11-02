package rummi;

import java.util.*;
public class Player implements Observer {
	//To be done: Constructor with hand, values etc... 
	Observable observable;
	private int handValue;
	private List<Tile> hand;
	
	public void update(Observable obs, Object args) {
		if (obs instanceof Game) {
			// Code to be finished
		}
	}
	
	public Player() {
		this.hand = new ArrayList<Tile>();
		this.handValue = 0; 
	}
	
	public int getHandValue() {
		return this.handValue;
	}
	
	// For Testing purposes
	public void setHandValue(int x) {
		this.handValue = x;
	}
	
}
