package rummi;

import java.util.ArrayList;
//<<<<<<< HEAD
import java.util.Comparator;
//=======
import java.util.Collections;
import java.util.List;
import rummi.Tile;
//>>>>>>> Manish

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
<<<<<<< HEAD
	
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
=======
	*/
//>>>>>>> Manish

}
