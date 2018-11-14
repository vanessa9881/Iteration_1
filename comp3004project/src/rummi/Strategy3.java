package rummi;

import java.util.*;

// Actually strategy 2 
public class Strategy3 extends Player {
	
	//Add constructors, etc... 
	private int turnNumber = 0;
	public ArrayList<Meld> melds = new ArrayList<Meld>(); 
	
	public Strategy3() {
	}
	
	public ArrayList<Meld> getMelds(){
		return this.melds;
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
	@Override
	public void play(RummiMain g){
		
		this.getMeldsFromHand();
		
		if (this.turnNumber > 1) {
			// Play next turn 
			// Checks if all tiles can be played
			if (this.hand.isEmpty()) {
				// Play all melds and wins 
			} else {
				// Play off the board 
				// For now just draws tile until board is completed
				g.drawTile(this);
			}
		} else {
			// for first turn plays 30+ points as fast as possible 
			if (!(this.initialTurnPlay())) {
				g.drawTile(this);
				turnNumber = 0; 
			} else {
				// Play all possible melds
			}
		}
		this.turnNumber++;
		this.sort();
	}

}
