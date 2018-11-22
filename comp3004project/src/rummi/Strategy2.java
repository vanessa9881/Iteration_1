package rummi;

import java.util.*;

// Actually Strategy 3
public class Strategy2 extends Player {
	
	//Add constructors, etc... 
	private int turnNumber = 0;
	private ArrayList<Meld> melds = new ArrayList<Meld>(); 
	
	public Strategy2() {
	}
	
	public ArrayList<Meld> getMelds(){
		return this.melds;
	}
	
	// Method to show if P3 can make new melds based on hands of other players
	public boolean makeNewMelds(ArrayList<Player> players) {
		boolean makenew = false;
		
		for (Player player : players) {
			int handDifference = 0; 
			if (player.equals(this)) {
				// Do nothing
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
	public Meld highestMeld(ArrayList<Meld> melds) {
		Meld highMeld = melds.get(0); 
		for (Meld m : melds) {
			if (m.getValue() > highMeld.getValue()) {
				highMeld = m;
			}
		}
		return highMeld; 
	}
	
	public void getMeldsFromHand(){
		Meld currMeld;
		Meld highMeld; 
		ArrayList<Meld> allMelds = new ArrayList<Meld>();
		ArrayList<Tile> toRemove = new ArrayList<Tile>();
		ArrayList<Meld> toRemoveMeld = new ArrayList<Meld>();
		
		// TODO: Iterate through hand to get highest possible melds   
		while (true) {
			
			// Breaks if hand is empty
			if (hand.isEmpty()) {break;}
		
			// for each item in the players hand start new meld for each hand item 		
			for (Tile t : this.hand) {
				currMeld = new Meld(t);
				allMelds.add(currMeld);
			}
		
			// test all possible melds from each other item in hand
			for (Meld m : allMelds) {
				for (Tile t : this.hand) {
					if (m.getTiles().get(0).equals(t) ==  false) {
						m.addToMeld(t);
					}
				}
			}
		
			// checks if all melds assembled are valid 
			for (Meld m : allMelds) {
				if (m.checkValid() == false) {
					toRemoveMeld.add(m);
				}
			}
			allMelds.removeAll(toRemoveMeld);
			
			
			
			// Breaks if no melds
			if (allMelds.isEmpty()) {break;}
			
			// find highest value meld from this
			highMeld = highestMeld(allMelds);
		
			// Add highest melds to be played 
			this.melds.add(highMeld);
			
			// remove tiles that exist in meld from hand
			for (int i = 0; i < highMeld.getSize(); i++) {
				if (this.hand.contains(highMeld.getTiles().get(i))) {
					toRemove.add(highMeld.getTiles().get(i)); 
				}
			}
			this.hand.removeAll(toRemove);
			
			// Reset allMelds for next iteration
			allMelds.clear();
			toRemove.clear();
		}
		
	}
	
	
	// Method for Turn
	public void play(Board b){
		this.getMeldsFromHand();
		
		if (this.turnNumber > 1) {
			if (this.makeNewMelds(b.getPlayerList())) {
				//play turn while able to make new melds 
				for (int i = 0; i < melds.size(); i++) {
					b.addHandMeld(melds.get(i));
				}
				melds.clear();
			} else {
				// If unable to make new melds, player draws 
				// TO ADD: If unable to make new melds, use board
				this.hand.add(b.drawTile());
			} 
		} else {
			// for first turn plays 30+ points as fast as possible 
			if (!(this.initialTurnPlay())) {
				g.drawTile(this);
				turnNumber = 0; 
			} else {
				// Play all possible melds
				for (int i = 0; i< melds.size(); i ++) {
					b.addHandMeld(melds.get(i));
				}
				melds.clear();
			}
		}
		this.turnNumber++;
		this.sort();
	}

	
	
}
