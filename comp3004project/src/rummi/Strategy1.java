package rummi;

import rummi.Meld;

//import java.util.*;

public class Strategy1 extends Player{

	public Strategy1() {
	}
	
	public void play(Board game){
		Meld meld_set;
		Meld meld_run;
		int initial_turn =0; 
		if(!firstTurn()&& initial_turn==0) {
			addTile(game.drawTile());
	}
		if(score_of_valid_hand() >= 30) {
			meld_set = new Meld(null);
			meld_run= new Meld(null);
			initial_turn=1;
			for(int i=0; i < getHandValue();i++) {
				int index= i+1;
				Tile current_tile = hand.get(i);
				Tile next_tile = hand.get(index);
				if(index != getHandValue() - 1) {
				
				if((current_tile.getValue()== next_tile.getValue()+1 && current_tile.getColour() == next_tile.getColour())){
					meld_run.addToMeld(hand.remove(i));
					meld_run.addToMeld(hand.remove(index));
				}
				else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
					meld_set.addToMeld(hand.remove(i));
					meld_set.addToMeld(hand.remove(index));
				}
				else {
					continue;
				}
				index++; 
			}
				i++;
			}
			meld_set.checkGroup();
			meld_set.checkRun();
			
		}
		if(score_of_valid_hand() > 3) {
			meld_run = new Meld(null);
			meld_set = new Meld(null);
			for(int i=0; i < getHandValue();i++) {
				int index= i+1;
				Tile current_tile = hand.get(i);
				Tile next_tile = hand.get(index);
				if(index != getHandValue() - 1) {
				
				if((current_tile.getValue() == next_tile.getValue() + 1 && current_tile.getColour() == next_tile.getColour())){
					meld_run.addToMeld(hand.remove(i));
					meld_run.addToMeld(hand.remove(index));
					meld_run.checkGroup();
				}
				else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
					meld_set.addToMeld(hand.remove(i));
					meld_set.addToMeld(hand.remove(index));
					meld_set.checkGroup();
				}
				else {
					continue;
				}
				index++; 
			}
				i++;
			
		}
			meld_set.checkGroup();
			meld_set.checkRun();
		}
		if(initial_turn!=0) {
			for(int i=0; i<getHandValue();i++) {
				int count=0;
				Tile current_tile=hand.get(i);
				Meld current_meld=game.getMelds().get(count);
				for(Tile t : current_meld.getTiles()) {
					if(current_tile.getValue()== t.getValue()-1 && current_tile.getColour()==t.getColour()) {
						//game.addBoardTile(current_tile, xpos, ypos)
						//game.t.getTile().getPos();
						
					}
					else if (current_tile.getValue()==t.getValue()&&(current_tile.getColour()!=t.getColour())){
						//game.addBoardTile(current_tile, xpos, ypos)
					}
					else {
						continue;
					}
				}
				
			}
			
		}
		else {
			addTile(game.drawTile());
			
		}
	}

	
	public boolean firstTurn() {
		if(score_of_valid_hand()>=30) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean useJoker() {
		if(getNumberOfTiles()<= 4) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int score_of_valid_hand() {
		// TODO Auto-generated method stub			
			int score =0;
			//int count = 0;
			//int index=0;
 
			
			for(int i=0; i < getHandValue();i++) {
				int index= i+1;
				Tile current_tile = hand.get(i);
				Tile next_tile = hand.get(index);
				if(index != getHandValue() - 1) {
				
				if((current_tile.getValue()== next_tile.getValue()+1 && current_tile.getColour() == next_tile.getColour())){
					//count++;
					score+= current_tile.getValue()+next_tile.getValue();
					//p1.getHand().remove(i);
					//p1.getHand().remove(index);
				}
				else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
					//count++;
					score+= current_tile.getValue()+next_tile.getValue();
					//p1.getHand().remove(i);
					//p1.getHand().remove(index);
				}
				else {
					continue;
				}
				index++; 
			}
				i++;
			}
			
		return score;
	}

	

	
}
