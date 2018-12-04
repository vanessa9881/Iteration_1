package rummi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import rummi.Tile;
import rummi.Number;
import rummi.Colour;
import javafx.scene.image.Image;

public class Deck {
	
	private ArrayList<Tile> tileList;
	
	public Deck() {
		tileList = new ArrayList<Tile>();
		
		try{
			for(int i = 0; i<2; i++){
				Iterator<Colour> colourIterator = Colour.VALUES.iterator();
				while ( colourIterator.hasNext() ) {
					Colour colour = (Colour) colourIterator.next();
					Iterator<Number> numberIterator = Number.VALUES.iterator();
					while ( numberIterator.hasNext() ) {
						Number number = (Number) numberIterator.next();
						Tile tile = new Tile( colour, number, new Image(Tile.getFilename( colour, number)) );
						addTile(tile);
					}
				}
			}
			shuffle(); 
		} 
		catch (Exception exception){
			System.out.println(exception.getMessage()); 
    	}
	}
	
	public Deck (int n) {
		tileList = new ArrayList<Tile>();
	}
	
	public Deck(Deck d) {
		tileList = d.getDeck();
	}
	public Tile getTile(int index)
	{
		return tileList.get(index);
	}
	
	 public void addTile(Tile tile) {
	      tileList.add(tile);
	 }
	 
	 public int deckSize() {
		    return tileList.size();
	 }

	public Tile dealTile() {
		   if (deckSize() == 0) {
			   throw new NullPointerException("Deck is empty!");
		   }
		   else {
			   Tile tileToReturn = tileList.get(0);
			   tileList.remove(0);
			   return tileToReturn;
		   }
	}
	
	public Tile dealRiggedTile(String c, String n) {
		for (Tile t : tileList) {
			if (t.getColour().getSymbol().equals(c) && t.getValue() == Integer.parseInt(n)){
				Tile rigged = t;
				//Add this tile to the hand before removing it from the deck. the level 5 shit goes in here?
				deleteTile(tileList.indexOf(t));
				return rigged;
			}
		}
		return null;
	}
	
	
	
	public Tile deleteTile(int index) {
		Tile tileToDelete = tileList.get(index);
		tileList.remove(index);
		return tileToDelete;
	}

	public void shuffle() {
		Collections.shuffle(tileList);
	}
	
	public ArrayList<Tile> getDeck() {
		return tileList;
	}



}
