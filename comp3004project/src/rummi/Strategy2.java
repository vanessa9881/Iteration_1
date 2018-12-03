package rummi;

import java.util.*;

// Actually Strategy 3
public class Strategy2 extends Player {
	
	//Add constructors, etc... 
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
				if (!(t.getColour().equals('j')));
					currMeld = new Meld(t);
					allMelds.add(currMeld);
			}
		
			// test all possible melds from each other item in hand
			for (Meld m : allMelds) {
				for (Tile t : this.hand) {
					if (m.getTiles().get(0).equals(t) ==  false && 
							(!(t.getColour().equals(Colour.JOKER)))) {
						m.addToMeld(t);
					} else if (t.getColour().equals(Colour.JOKER)) {
						if (m.checkGroup()) {
							// Set value to same 
							t.setJokerValue(m.getTiles().get(m.getSize() - 1).getValue());
							m.addToMeld(t);
						} else if (m.checkRun()) {
							// Set value to one above the last
							t.setJokerValue(m.getTiles().get(m.getSize() - 1).getValue() + 1);
							// Set colour to same 
							t.setJokerColour(m.getTiles().get(0).getColour());
						}
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
		ArrayList<Tile> toRemove = new ArrayList<Tile>();
		
		
		if (this.turnNumber > 1) {
			if (this.makeNewMelds(b.getPlayerList())) {
				//play turn while able to make new melds 
				for (int i = 0; i < melds.size(); i++) {
					b.addHandMeld(melds.get(i));
				}
				for (Meld m : melds) {
					for (int i = 0; i < m.getSize(); i++) {
						this.SetPlayerScore(m.getTiles().get(i));
					}
				}
				melds.clear();
			} else { 
				// If unable to make new melds, use board or draw
				// Play off the board 
				for(int i = 0; i < this.hand.size(); i++) {
					for (Meld m : b.getMelds()) {
						m.addToMeld(this.hand.get(i));
						if (m.checkValid()) {
							toRemove.add(this.hand.get(i));
							this.SetPlayerScore(this.hand.get(i));
						}
					}
				}
				// if there are no tiles to remove then draw tile
				// otherwise remove tiles from hand 
				if (toRemove.isEmpty()) {
					this.hand.add(b.drawTile());
				} else {
					this.hand.removeAll(toRemove);
					toRemove.clear();
				}
			} 
		} else {
			// for first turn plays 30+ points as fast as possible 
			if (!(this.initialTurnPlay())) {
				this.hand.add(b.drawTile());
				turnNumber = 0; 
			} else {
				// Play all possible melds
				for (int i = 0; i< melds.size(); i ++) {
					b.addHandMeld(melds.get(i));
				}
				for (Meld m : melds) {
					for (int i = 0; i < m.getSize(); i++) {
						this.SetPlayerScore(m.getTiles().get(i));
					}
				}
				melds.clear();
			}
		}
		this.turnNumber++;
		this.sort();
	}
	
}
