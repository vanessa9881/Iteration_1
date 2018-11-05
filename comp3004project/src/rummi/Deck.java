package rummi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import rummi.Tile;
import rummi.Number;
import rummi.Colour;
import javafx.scene.image.Image;

public class Deck {
	
	private ArrayList<Tile> tileList;
	private int index;
	
	public Deck(int numberOfAllTiles) {
		tileList = new ArrayList<Tile>();
		index = 0;
	
    try{
        for(int i = 0; i<numberOfAllTiles; i++){
            Iterator colourIterator = Colour.VALUES.iterator();
            while ( colourIterator.hasNext() ) {
                Colour colour = (Colour) colourIterator.next();
                Iterator numberIterator = Number.VALUES.iterator();
                while ( numberIterator.hasNext() ) {
                    Number number = (Number) numberIterator.next();
                    Tile tile = new Tile( colour, number, new Image(Tile.getFilename( colour, number)) );
                    addTile(tile);
                }
            }
        }
        shuffle(); 
    } catch (Exception exception){
        System.out.println(exception.getMessage()); 
    	}
	}
	
	public Tile getTile(int number)
	{
		return tileList.get(number);
	}
	
	
	 public void addTile(Tile tile) {
	      tileList.add(tile);
	 }
	 
	 public int getSizeOfAllTiles() {
		    return tileList.size();
	 }
	  
	public int getNumberOfTiles() {
		return tileList.size()-index;
	}

	public Tile dealTile() {
		   if ( index >= tileList.size() )
		      return null;
		   else
		      return (Tile) tileList.get(index++);
	}

	//public ArrayList<Tile> getDeck() {
	//	return tileList;
	//}
	
	public boolean isEmpty() {
		   if ( index >= tileList.size() )
		      return true;
		   else
		      return false;
	}
	   
	public void restoreDeck() {
		   index = 0;
	}

	public void shuffle() {
		Collections.shuffle(tileList);
	}

}
