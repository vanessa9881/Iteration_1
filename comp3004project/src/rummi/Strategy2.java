package rummi;

import java.util.*;

public class Strategy2 extends Player {
	
	//Add constructors, etc... 
	private int turnNumber = 0;
	private ArrayList<Meld> melds = new ArrayList<Meld>(); 
	
	
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
	public boolean initialTurnPlay() {
		int meldTotal = 0;
		boolean play = true;
		// if hand melds total >= 30 then 
		// play all available melds in hand
		// check hand
		for (int i = 0; i < melds.size(); i++) {
			meldTotal = meldTotal + melds.get(i).getValue();
		}
		if (meldTotal < 30) {
			play = false; 
		}
		return play; 
		
	}
	
	// Method for getting highest meld available 
	private Meld highestMeld(ArrayList<Meld> melds) {
		Meld highMeld = melds.get(0); 
		for (Meld m : melds) {
			if (m.getValue() > highMeld.getValue()) {
				highMeld = m;
			}
		}
		return highMeld; 
	}
	
	public void getMeldsFromHand(){
		this.sortHand();
		Meld currMeld;
		Meld highMeld; 
		ArrayList<Meld> allMelds = new ArrayList<Meld>();
		int meldTotal; 
		
		
		// TODO: Iterate through hand to get highest possible melds   
		while (true) {
			
			// Breaks if hand is empty
			if (hand.isEmpty()) {break;}
		
			// for each item in the players hand start new meld for each hand item 		
			for (Tile t : this.hand) {
				currMeld = new Meld(t);
				allMelds.add(currMeld);
			}
			
			// Breaks if no melds
			if (allMelds.isEmpty()) {break;}
		
			// test all possible melds from each other item in hand
			for (int i = 0; i < allMelds.size(); i++) {
				if (!(this.hand.get(i).equals(allMelds.get(i)))){
					allMelds.get(i).addToMeld(this.hand.get(i));
				}
			}
		
			// find highest value meld from this
			highMeld = highestMeld(allMelds);
		
			// Add highest melds to be played 
			this.melds.add(highMeld);
		
			// remove tiles that exist in meld from hand
			for (int i = 0; i < highMeld.getSize(); i++) {
				if (this.hand.contains(highMeld.getTiles().get(i))) {
					this.hand.remove(i); 
				}
			}
		
			// Reset allMelds for next iteration
			allMelds.clear();
		
		}
		
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
			while(!(this.initialTurnPlay())) {
				this.hand.add(g.playingDeck.draw());
			}
		}
		this.turnNumber++;
		
	}

	
	
}
