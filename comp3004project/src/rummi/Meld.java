package rummi;

import java.util.ArrayList;
import java.util.HashSet;

public class Meld {
	
	private ArrayList<Tile> meldTiles;
	
	public Meld(Tile initialTile) {
		// Creates a meld object from an initial tile.
		// If any tile is placed beside this tile on the board
		// it is added to this meld using the addToMeld method
		meldTiles = new ArrayList<Tile>();
		meldTiles.add(initialTile);
	}
	
	public boolean addRightside(Tile t) {
		Tile endTile = meldTiles.get(meldTiles.size() - 1);

		// Check to see if tile to add is 1 more than the previous
		if (endTile.getValue() + 1 == t.getValue()) {
			// Check if all colours are the same
			if (checkColours(t)) {
				// Add to end as a run
				meldTiles.add(t);
				return true;
			}
		}
		
		if (addAsGroup(t)) {
			// Add to end as a group
			meldTiles.add(t);
			return true;
		}
		return false;
	}

	public boolean addLeftside(Tile t) {
		Tile frontTile = meldTiles.get(0);

		// Check to see if tile to add is 1 less than the previous
		if (frontTile.getValue() - 1 == t.getValue()) {
			// Check if all colours are the same
			if (checkColours(t)) {
				// Add to end as a run
				meldTiles.add(0, t);
				return true;
			}
		}
		
		if (addAsGroup(t)) {
			// Add to end as a group
			meldTiles.add(0, t);
			return true;
		}
		return false;
	}
	
	// Checks if all of the colours in the meld are the same as the tile's colour
	public boolean checkColours(Tile t) {
		String col = t.getColour().getName();
		for (Tile tile : meldTiles) {
			if (!tile.getColour().getName().equals(col)) {
				return false;
			}
		}
		return true;
	}
	
	// Checks whether this meld is a group
	public boolean checkGroup() {
		// Check if all tiles have the same value
		int groupValue = meldTiles.get(0).getValue();
		for (Tile t : meldTiles) {
			if (t.getValue() != groupValue) {return false;}
			// Check if tiles all have the different colours
			for (Tile t2 : meldTiles) {
				if (t.getColour().getName().equals(t2.getColour().getName())) {return false;}
			}	
		}
		return true;
	}
	
	// Checks whether this meld is a run
	public boolean checkRun() {		
		// Check if tiles are the same colour
		String col = meldTiles.get(0).getColour().getName();
		for (Tile t : meldTiles) {
			if (t.getColour().getName().equals(col)) {return false;}	
		}
		
		// Check if tiles are in a sequence increasing by 1
		// We assume that they are already sorted as they should be
		for (int index = 0; index < meldTiles.size() - 1; index++) {
			if (meldTiles.get(index).getValue() != meldTiles.get(index + 1).getValue() + 1) {
				return false;
			}
		}
		return true;
	}
	
	// Checks a meld to see if it is valid.
	public boolean checkValid() {
		// Check if the meld is too small
		if (meldTiles.size() < 3) {
			return false;
		}
		// Check if it is either a valid group or run
		else if(!this.checkGroup() && !this.checkRun()){
			return false;
		}
		else {
			return true;
		}
	}
	
	// Should be called after the removed tile has been confirmed
	// that it can be added to a new meld
	public void removeFromMeld(Tile removedTile) {
		// First, check to see if the meld even contains
		// the tile of interest!
		if (!meldTiles.contains(removedTile)) {
			throw new IllegalArgumentException("Tile was never in the meld!");
		}
		// Meld only had one tile
		// Maybe someone misplaced a tile and would
		// like to move it again?
		if (meldTiles.size() == 1) {
			meldTiles.remove(removedTile);
			// Meld is now empty and therefore should be de-referenced!
			//throw new IllegalStateException("Meld is now empty and needs to be de-referenced!");		
		}
		// Check if removing the tile invalidates the meld
		// ArrayList<Tile> tempMeldTiles = meldTiles;
		// tempMeldTiles.remove(removedTile);
		
		// Let user instead worry about invalidating the meld when taking a tile,
		// as they may invalidate it only for this single move and fix it next move
		meldTiles.remove(removedTile);
	}
	
	// Checks to see whether this tile can be added to this meld
	// if this meld were to be a developing group
	public boolean addAsGroup(Tile addedTile) {
		// Check if the added tile is valid for a 'group'
			// First check if the value is the same as the other values
		for (Tile t : meldTiles) {
			if (t.getValue() != addedTile.getValue()) {
				return false;
			}
		}
		// Now check if it is a different colour from
		// the other tiles in the meld
		String addedTileColour = addedTile.getColour().toString();
		for (Tile t : meldTiles) {
			if (t.getColour().toString().equals(addedTileColour)) {
				return false;
			}
		}
		return true;
	}
	
	// Function for getting value of the meld 
	public int getValue() {
		int value = 0; 
		
		for (int i = 0; i < this.meldTiles.size(); i++) {
			value = value + meldTiles.get(i).getValue();
		}
		
		return value; 
	}
	
	// Function for getting the size of the meld
	public int getSize() {
		return meldTiles.size();
	}
	
	// Function for obtaining the meld 
	public ArrayList<Tile> getTiles(){
		return this.meldTiles;
	}
	
}
