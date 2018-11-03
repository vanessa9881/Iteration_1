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
	
	// Method for initial 30+ point turn 
	public void initialTurn() {
		// if hand melds total >= 30 then 
		// play all available melds in hand
		// check hand
	}
	
	
	// Method for Turn
	@Override
	public void play(Game g){
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
