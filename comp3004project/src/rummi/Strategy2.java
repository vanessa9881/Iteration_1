package rummi;

import java.util.*;

public class Strategy2 extends Player {
	
	//Add constructors, etc... 
	private int turnNumber = 0;
	
	// Method to show if P3 can make new melds based on hands of other players
	public boolean makeNewMelds(ArrayList<Player> players) {
		boolean makenew = false;
		
		for (Player player : players) {
			int handDifference = 0; 
			if (player.equals(this)) {
				makenew = makenew; 
			} else if (player.getHandValue() > this.getHandValue()) {
				makenew =  true; 
			} else {
				handDifference = this.getHandValue() - player.getHandValue();
				if (handDifference >= 3) {
					makenew = false;
				} else {
					makenew = true; 
				}
			}
			
		}
		return makenew;
	}
	
	
/*	
	// Method for valid melds 
	public boolean isValidMeld(ArrayList<Tile> tiles) {
		boolean valid = false;
		int numBlack, numOrange, numRed, numBlue;
		
		numBlack = 0;
		numBlue = 0;
		numRed = 0;
		numOrange = 0;
		
		if (tiles.size() < 3) {return false;}
		
		for (int i = 1; i < tiles.size(); i++) {
			
			if (tiles.get(i).getColour() == "Orange") {numOrange++;}
			if (tiles.get(i).getColour() == "Black") {numBlack++;}
			if (tiles.get(i).getColour() == "Blue") {numBlue++;}
			if (tiles.get(i).getColour() == "Red") {numRed++;}
		}
		for (int i = 1; i < tiles.size(); i++) {		
			
			// Check run (Same colour in increasing value)
			if (tiles.get(i - 1).getValue() + 1 == tiles.get(i).getValue() && 
					(numBlack == tiles.size())|| (numOrange == tiles.size()) || 
					(numBlue == tiles.size()) || (numRed == tiles.size())) {
				valid = true;
				
			} else if (tiles.get(i - 1).getValue() == tiles.get(i).getValue() && 
					(numBlack == 1) && (numBlue == 1) && (numRed == 1) && (numOrange == 1)) {
				valid = true;
			} else {
				valid = false;
			}
		}
		
		return valid;
	}
*/
	// Method for initial 30+ point turn 
	public void initialTurn() {
		// if hand melds total >= 30 then 
		// play all available melds in hand
		// check hand
	}
	
	
	
	// Method for Turn
	@Override
	public void play(Game g){
		this.sortHand();
		
		if (this.turnNumber > 1) {
			if (this.makeNewMelds(g.playerList)) {
				//play turn while able to make new melds 
			} else {
				// If unable to make new melds, player draws 
				this.hand.add(g.playingDeck.draw());
			} 
		} else {
			// for first turn plays 30+ points as fast as possible 
			this.initialTurn();
		}
		this.turnNumber++;
		
	}

	
	
}
