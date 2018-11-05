package rummi;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rummi.Tile;
<<<<<<< HEAD
	

import java.util.*;

public class Player implements Observer {

=======

public class Player {
	private int handValue;	//----------To do
	private List hand = new ArrayList();	//Hand is a refrence to a players hand. (hand = players hand)
	
	public Player() {
		this.hand = new ArrayList<Tile>();
		this.handValue = 0; 
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
	   		/*
		   public void discardHand() {
		      hand.clear();
		      total = 0; 
		      soft = 0; 
		   }
		   */

		   public int getNumberOfTiles() {
		      return hand.size();
		   }

		   public void sort() {
		      Collections.sort(hand);
		   }
		   /* Observer pattern here?
		   public boolean isEmpty() {
		      return hand.isEmpty();
		   }
		   */

		   public int findTile(Tile tile) {
		      return hand.indexOf(tile);
		   }		       

		   	/*
		   @Override
		    public String toString() {
		        return hand.toString();
		    }
		    
		     
>>>>>>> origin/manish
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
	
	public void sortHand() {
		//Comparator cmp = new Comparator();
	}
=======
	*/
>>>>>>> origin/manish

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

	public void play() {
		// TODO Auto-generated method stub
		
	}
}
