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
	
	// Checks whether this meld is a valid group
	public boolean checkGroup() {
		// Return false if the meld isn't 3 or 4 tiles
		if (meldTiles.size() < 3 || meldTiles.size() > 4) {return false;}
		// Check if all tiles have the same value
		int groupValue = meldTiles.get(0).getValue();
		for (Tile t : meldTiles) {
			if (t.getValue() != groupValue) {return false;}
		}
		// Check if tiles have different colours
		// Adding to a hashet will return false
		// if the element is already in the set
		HashSet<String> tempTileSet = new HashSet<String>();
		for (Tile t : meldTiles) {
			if(!tempTileSet.add(t.getColour())){return false;}
		}
		
		return true;
	}
	
	// Checks whether this meld is a valid rn
	public boolean checkRun() {
		// Return false if the meld isn't 3 or more tiles
		if (meldTiles.size() < 3) {return false;}
		
		// Check if tiles are the same colour
		HashSet<String> tempTileSet = new HashSet<String>();
		for (Tile t : meldTiles) {
			tempTileSet.add(t.getColour());
		}
		if (tempTileSet.size() > 1) {return false;}
		
		// Check if tiles are in a sequence increasing by 1
		// We assume that they are already sorted as they should be
		for (int index = 0; index < meldTiles.size() - 1; index++) {
			if (meldTiles.get(index).getValue() != meldTiles.get(index + 1).getValue() + 1) {
				return false;
			}
		}
		return true;
	}
	
	// Method for adding a tile to the meld
	public void addToMeld(Tile addedTile) {	
		boolean failedRun = false;
		boolean failedGroup = false;
		
		// Check if the added tile is valid for a 'group'
			// First check if the value is the same
		int groupValue = meldTiles.get(0).getValue();
		if (addedTile.getValue() != groupValue) {
			failedGroup = true;
		}
			// Now check if it is a different colour from
			// the other tiles in the meld
		String addedTileColour = addedTile.getColour();
		for (Tile t : meldTiles) {
			if (t.getColour().equals(addedTileColour)) {
				failedGroup = true;
			}
		}
		// Check if the added tile is valid for a 'group'
		// This expects tiles to be placed down in the 
		// correct sequence, as out of sequence placement
		// of tiles for a run is not supported!
			// First check if the tile's value is one
			// more than the previous tile
		Tile priorTile = meldTiles.get(meldTiles.size() - 1);
		if (priorTile.getValue()  + 1 != addedTile.getValue()) {
			failedRun = true;
		}
			// Now check if the tile is the same colour
			// as the previous tile
		if (!(priorTile.getColour().equals(addedTile.getColour()))) {
			failedRun = true;
		}
		
		// Now we check the boolean conditions.
		// If both are true, that means adding this
		// tile is not possible as it invalidates the meld
		if (failedGroup && failedRun) {
			throw new IllegalArgumentException("Not a valid tile to add!");
		}
		// If we're here, that means the tile does not
		// invalidate the meld and can be added
		else {
			meldTiles.add(addedTile);
			meldTiles.sort(new TileComparator());
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
			throw new IllegalStateException("Meld is now empty and needs to be de-referenced!");		
		}
		// Check if removing the tile invalidates the meld
		ArrayList<Tile> tempMeldTiles = meldTiles;
		tempMeldTiles.remove(removedTile);
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
}
