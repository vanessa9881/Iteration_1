package rummi;

import java.util.*;

// Actually strategy 2 
public class Strategy3 extends Player {
	
	//Add constructors, etc... 
	private int turnNumber = 0;
	
	public Strategy3() {
	}
	
	@Override
	public ArrayList<Meld> getMelds(){
		getMeldsFromHand();
		return melds;
	}
	
	// Method for initial 30+ point turn 
	public boolean initialTurnPlay(Board b) {
		int meldTotal = 0;
		boolean play = true;
		// If another player has not played their initial 
		// Then play = false 
		
		for (Player p : b.getPlayerList()) {
			if ((p.getTurnNumber() > 1) && (!(p.equals(this)))) {
		  play = false;}	 
		}
		
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
				if (!(t.getColour().equals('j')));
					currMeld = new Meld(t);
					allMelds.add(currMeld);
			}
		
			// test all possible melds from each other item in hand
			for (Meld m : allMelds) {
				for (Tile t : this.hand) {
					if (m.getTiles().get(0).equals(t) ==  false && 
							(!(t.getColour().equals(Colour.JOKER)))) {
						m.addLeftside(t);
						m.addRightside(t);
					} else if (t.getColour().equals(Colour.JOKER)) {
						if (m.checkGroup()) {
							// Set value to same 
							t.setJokerValue(m.getTiles().get(m.getSize() - 1).getValue());
							m.addLeftside(t);
						} else if (m.checkRun()) {
							// Set value to one above the last
							t.setJokerValue(m.getTiles().get(m.getSize() - 1).getValue() + 1);
							// Set colour to same 
							t.setJokerColour(m.getTiles().get(0).getColour());
							m.addLeftside(t);
							m.addRightside(t);
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
			
			this.printMelds();
			
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
		ArrayList<Tile> toRemove = new ArrayList<Tile>();
		this.getMeldsFromHand();
		
		if (this.turnNumber > 1) {
			// Play next turn 
			// Checks if all tiles can be played
			if (this.hand.isEmpty()) {
				// Play all melds and wins 
				for (int i = 0; i < melds.size(); i++) {
					b.addHandMeld(melds.get(i));
					b.addMeldToBoard(melds.get(i));
				}
				for (Meld m : melds) {
					for (int i = 0; i < m.getSize(); i++) {
						this.SetPlayerScore(m.getTiles().get(i));
					}
				}
				melds.clear();
			} else {
				// Play off the board 
				for(int i = 0; i < this.hand.size(); i++) {
					for (Meld m : b.getMelds()) {
						m.addLeftside(this.hand.get(i));
						m.addRightside(this.hand.get(i));
						if (m.checkValid()) {
							toRemove.add(this.hand.get(i));
							this.SetPlayerScore(this.hand.get(i));
						}
					}
				}
				// if there are no tiles to remove then draw tile
				// otherwise remove tiles from hand 
				if (toRemove.isEmpty()) {
					this.hand.add(b.getDeck().dealTile());
				} else {
					this.hand.removeAll(toRemove);
					toRemove.clear();
				}
			}
		} else {
			// for first turn plays 30+ points as fast as possible 
			if (!(this.initialTurnPlay(b))) {
				this.hand.add(b.getDeck().dealTile());
				turnNumber = 0; 
			} else {
				// Play all possible melds
				for (int i = 0; i < melds.size(); i++) {
					b.addHandMeld(melds.get(i));
					b.addMeldToBoard(melds.get(i));
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
