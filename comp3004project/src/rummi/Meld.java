package rummi;

import java.util.ArrayList;

public class Meld {
	
	private ArrayList<Tile> meldTiles;
	
	public Meld(Tile initialTile) {
		// Creates a meld object from an initial tile.
		// If any tile is placed beside this tile on the board
		// it is added to this meld using the addToMeld method
		meldTiles = new ArrayList<Tile>();
		meldTiles.add(initialTile);
	}

	 

	
	public Meld () {
		meldTiles = new ArrayList<Tile>();
	}
	
	// Add method that bypasses checks do not use
	// for player moves
	public void add(Tile t) {
		meldTiles.add(t);
	}
	

	public boolean addRightside(Tile t) {
		if (meldTiles.size() == 0) {
			meldTiles.add(t);
			return true;
		}
		
		Tile endTile = meldTiles.get(meldTiles.size() - 1);

		// First check if the added tile is a joker
		if (t.getID() > 9) {
			return addJoker(t, 1);
		}
		
		// Check to see if tile to add is 1 more than the previous
		if (endTile.getValue() + 1 == t.getValue()) {
			// Check if all colours are the same
			if (checkColours(t)) {
				meldTiles.add(t);
				return true;
			}
		}
		
		if (addAsGroup(t)) {
			// Now check group size
			if (meldTiles.size() == 4) {
				return false;
			}
			// Add to end as a group
			meldTiles.add(t);
			return true;
		}
		return false;
	}

	public boolean addLeftside(Tile t) {
		if (meldTiles.size() == 0) {
			meldTiles.add(t);
			return true;
		}
		
		Tile frontTile = meldTiles.get(0);

		// First check if the added tile is a joker
		if (t.getID() > 9) {
			return addJoker(t, 0);
		}
		
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
			// Now check group size
			if (meldTiles.size() == 4) {
				return false;
			}
			// Add to end as a group
			meldTiles.add(0, t);
			return true;
		}
		return false;
	}
	
	// Adds a joker and sets it attributes to the tile it was added as
	// 0 means leftside add, 1 means rightside add
	private boolean addJoker(Tile t, int i) {
		if (meldTiles.size() == 1) {
			if (meldTiles.get(0).getID() > 9) {
				System.out.print("Error! Cannot put two jokers beside eachother!");
				return false;
			}
			else {
				if (i == 0) {
					meldTiles.add(0, t);
				}
				else {
					meldTiles.add(t);
				}
				return true;
			}
		}
		
		if (i == 0) {
			if (meldTiles.get(0).getValue() == 1) {
				if (!this.checkGroup()) {
					// This meld is a run that starts at 1. Cannot add
					// any more tiles to the beginning
					System.out.println("Not group!!");
					return false;
				}
			}
			
			if (this.checkGroup()) {
				if (meldTiles.size() == 4) {
					return false;
				}
				// don't change colour, as if it's a group it can be a few colours
				t.setJokerValue(meldTiles.get(0).getValue());
			}
			else {
				t.setJokerColour(meldTiles.get(0).getColour());
				t.setJokerValue(meldTiles.get(0).getValue() - 1);
			}
			meldTiles.add(0, t);
			return true;
		}
		
		if (i == 1) {
			if (meldTiles.get(meldTiles.size() - 1).getValue() == 13) {
				if (!this.checkGroup()) {
					// This meld is a run that ends at 13. Cannot add
					// any more tiles to the end
					return false;
				}
			}
			
			if (this.checkGroup()) {
				if (meldTiles.size() == 4) {
					return false;
				}
				// don't change colour, as if it's a group it can be a few colours
				t.setJokerValue(meldTiles.get(0).getValue());
			}
			else {
				t.setJokerColour(meldTiles.get(0).getColour());
				t.setJokerValue(meldTiles.get(meldTiles.size() - 1).getValue() + 1);
			}
			meldTiles.add(t);
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
		if (meldTiles.size() == 1) {
			// could be the start of a group with only 1 tile
			return true;
		}
		
		// Check if all tiles have the same value
		int groupValue = meldTiles.get(0).getValue();
		for (Tile t : meldTiles) {
			if (t.getValue() != groupValue) {return false;}
			// Check if tiles all have the different colours
			for (Tile t2 : meldTiles) {
				if (!t.equals(t2) && t.getColour().getName().equals(t2.getColour().getName())) {return false;}
			}	
		}
		return true;
	}
	
	// Checks whether this meld is a run
	public boolean checkRun() {		
		if (meldTiles.size() == 1) {
			// could be the start of a run with only 1 tile
			return true;
		}
		
		// Check if tiles are the same colour		
		String col = meldTiles.get(0).getColour().getName();
		for (Tile t : meldTiles) {
			if (!t.getColour().getName().equals(col)) {return false;}	
		}
		
		// Check if tiles are in a sequence increasing by 1
		// We assume that they are already sorted as they should be
		int sequenceCheck = meldTiles.get(0).getValue() - 1;
		for (Tile t : meldTiles) {
			if (sequenceCheck + 1!= t.getValue()) {
				return false;
			}
			sequenceCheck = t.getValue();
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
			//throw new IllegalArgumentException("Tile was never in the meld!");
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

	public void combineMeld(Meld rMeld) {
		for (Tile t : rMeld.getTiles()) {
			meldTiles.add(t);
		}
	}
	
	public void printMeld() {
		System.out.println(this.getTiles());
	}

	// Used for setting the joker within a meld
	// after a third tile has been added to it
	// i at 0 means leftside add, i at 1 means rightside add
	public boolean setMeld(Tile t, int i) {
		// Get index of non joker tile
		int nonJokeIndex;
		if (meldTiles.get(0).getID() > 9) {
			nonJokeIndex = 1;
		}
		else {
			nonJokeIndex = 0;
		}
		
		if (i == 0) {
			if (meldTiles.get(nonJokeIndex).getValue() == t.getValue()) {
				// player intends for the meld to be a group
				if (t.getColour().getName().equals(meldTiles.get(nonJokeIndex).getColour().getName())) {
					return false;
				}
				if (nonJokeIndex == 1) {
					meldTiles.get(0).setJokerValue(meldTiles.get(nonJokeIndex).getValue());
				}
				else {
					meldTiles.get(1).setJokerValue(meldTiles.get(nonJokeIndex).getValue());	
				}
				meldTiles.add(0, t);
				return true;
			}
			else if (t.getColour().getName().equals(meldTiles.get(nonJokeIndex).getColour().getName())) {
				// player intends for meld to be a run
				if (nonJokeIndex == 0 && t.getValue() > 0 && t.getValue() + 1 == meldTiles.get(nonJokeIndex).getValue()) {
					meldTiles.get(1).setJokerValue(t.getValue() + 2);
					meldTiles.add(0, t);
					return true;
				}
				else if (nonJokeIndex == 1 && t.getValue() - 2 > 0 && t.getValue() + 2 == meldTiles.get(nonJokeIndex).getValue()) {
					meldTiles.get(0).setJokerValue(t.getValue() + 1);
					meldTiles.add(0, t);
					return true;
				}
				return false;
			}
		}
		
		if (i == 1) {
			if (meldTiles.get(nonJokeIndex).getValue() == t.getValue()) {
				// player intends for the meld to be a group
				if (t.getColour().getName().equals(meldTiles.get(nonJokeIndex).getColour().getName())) {
					return false;
				}
				if (nonJokeIndex == 1) {
					meldTiles.get(0).setJokerValue(meldTiles.get(nonJokeIndex).getValue());
				}
				else {
					meldTiles.get(1).setJokerValue(meldTiles.get(nonJokeIndex).getValue());	
				}
				meldTiles.add(t);
				return true;
			}
			else if (t.getColour().getName().equals(meldTiles.get(nonJokeIndex).getColour().getName())) {
				// player intends for meld to be a run
				if (nonJokeIndex == 0 && t.getValue() + 2 < 14 && t.getValue() - 2 == meldTiles.get(nonJokeIndex).getValue()) {
					meldTiles.add(t);
					meldTiles.get(1).setJokerValue(t.getValue() - 1);
					return true;
				}
				else if (nonJokeIndex == 1 && t.getValue() > 0 && t.getValue() + 2 == meldTiles.get(nonJokeIndex).getValue()) {
					meldTiles.add(t);
					meldTiles.get(0).setJokerValue(meldTiles.get(nonJokeIndex).getValue() - 1);
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
}
