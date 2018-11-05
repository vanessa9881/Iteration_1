package rummi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.control.Button;
import rummi.Tile;

public class Player {
	private ArrayList<Tile> hand;
	private int handValue;	//----------To do	//Hand is a refrence to a players hand. (hand = players hand)
	
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
		     // hand.sort(new Comparator());
		   }

		   public int findTile(Tile tile) {
		      return hand.indexOf(tile);
		   }		       

		   //Setting the graphic of the removed tile to null
			public void deleteTile(Tile tile,Button b) {
				hand.remove(tile);
				b.setGraphic(null);
			
			}
		   	
		   @Override
		    public String toString() {
		        return hand.toString();
		    }

		public void sort(TileComparator tc) {
				hand.sort(tc);
		}
		    
		     /*
	//To be done: Constructor with hand, values etc... 
	private int handValue;
	private List<Tile> hand;
	
	public Player() {
		this.hand = new ArrayList<Tile>();
		this.handValue = 0; 
	}
	
	public int getHandValue() {
		return this.handValue;
	}
	*/

}
