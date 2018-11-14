package rummi;

import java.util.ArrayList;
import rummi.Tile;


public class Player {
	private int handValue;	//----------To do
	public ArrayList<Tile> hand;	//Hand is a refrence to a players hand. (hand = players hand)
	
	public Player() {
		hand = new ArrayList<Tile>();
		handValue = 0; 
	}
	
	public int getHandValue() {
		return this.handValue;
	}
	
	public void addTile(Tile tile) {
		hand.add(tile);
	}
	   
	public Tile getTile(int index) {
		   return (Tile) hand.get(index);
	}
	
	public int getNumberOfTiles() {
	      return hand.size();
	}
	
	public void sort() {
	      hand.sort(new TileComparator());;
	}
	
	public int findTile(Tile tile) {
	      return hand.indexOf(tile);
	}
	
	public void play(RummiMain g) {
		// TODO Auto-generated method stub		
	}
	
	   		/*
		   public void discardHand() {
		      hand.clear();
		      total = 0; 
		      soft = 0; 
		   }
		   */

		   /* Observer pattern here?
		   public boolean isEmpty() {
		      return hand.isEmpty();
		   }
		   */       

		   	/*
		   @Override
		    public String toString() {
		        return hand.toString();
		    }


public class Player implements Observer {

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

	// For Testing purposes
	public void setHandValue(int x) {
		this.handValue = x;
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
	
	public ArrayList<Tile> getHand() {
		return hand;
	}

	*/
}
