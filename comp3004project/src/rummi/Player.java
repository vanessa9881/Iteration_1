package rummi;

<<<<<<< HEAD
import java.util.*;
public class Player implements Observer {
=======
import java.util.ArrayList;
import java.util.Comparator;

public class Player {
>>>>>>> master
	//To be done: Constructor with hand, values etc... 
	Observable observable;
	private int handValue;
	private ArrayList<Tile> hand;
	
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
	
<<<<<<< HEAD
	// For Testing purposes
	public void setHandValue(int x) {
		this.handValue = x;
	}
	
=======
	public void sortHand() {
		//Comparator cmp = new Comparator();
	}

	public void play() {
		// To be overriden by each strategy
		
	}

	public boolean checkWin() {
		if (getHandValue() == 0) {
			return true;
		}
		else {return false;}
	}

>>>>>>> master
}
