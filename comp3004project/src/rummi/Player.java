package rummi;


import java.util.*;

public class Player implements Observer {

	//To be done: Constructor with hand, values etc... 
	Observable observable;
	private int handValue;
	public ArrayList<Tile> hand;
	
	// To be modified to fit the board observable methods
	public void update(Observable obs, Object args) {
		if (obs instanceof Game) {
			// Code to be finished
			Game game = (Game)obs;
			//this.handValue = ;
			 ;
			
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
