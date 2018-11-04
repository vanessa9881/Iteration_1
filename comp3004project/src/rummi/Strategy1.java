package rummi;

import java.util.ArrayList;
import rummi.Meld;

public class Strategy1 extends Player{
	Player p1= new Player();
	

	public Strategy1() {
		p1.sortHand();
	}
	
	
	public void play(Game game){
		Meld meld_set;
		Meld meld_run;
		if(score_of_valid_hand() >= 30) {
		meld_set = new Meld(null);
		meld_run= new Meld(null);
		for(int i=0; i < p1.getHandValue();i++) {
			int index= i+1;
			Tile current_tile = p1.getHand().get(i);
			Tile next_tile = p1.getHand().get(index);
			if(index!=p1.getHandValue()-1) {
			
			if((current_tile.getValue()== next_tile.getValue()+1 && current_tile.getColour() == next_tile.getColour())){
				meld_run.addToMeld(p1.getHand().remove(i));
				meld_run.addToMeld(p1.getHand().remove(index));
			}
			else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
				meld_set.addToMeld(p1.getHand().remove(i));
				meld_set.addToMeld(p1.getHand().remove(index));
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
		else if(score_of_valid_hand() > 0) {
			meld_run = new Meld(null);
			meld_set = new Meld(null);
			for(int i=0; i < p1.getHandValue();i++) {
				int index= i+1;
				Tile current_tile = p1.getHand().get(i);
				Tile next_tile = p1.getHand().get(index);
				if(index!=p1.getHandValue()-1) {
				
				if((current_tile.getValue()== next_tile.getValue()+1 && current_tile.getColour() == next_tile.getColour())){
					meld_run.addToMeld(p1.getHand().remove(i));
					meld_run.addToMeld(p1.getHand().remove(index));
					meld_run.checkGroup();
				}
				else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
					meld_set.addToMeld(p1.getHand().remove(i));
					meld_set.addToMeld(p1.getHand().remove(index));
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
		else {
			game.playingDeck.draw();
		}
	}

	public int score_of_valid_hand() {
		// TODO Auto-generated method stub
			int count = 0;
			int score =0;
			//int index=0;
 
			
			for(int i=0; i<p1.getHandValue();i++) {
				int index= i+1;
				Tile current_tile = p1.getHand().get(i);
				Tile next_tile = p1.getHand().get(index);
				if(index!=p1.getHandValue()-1) {
				
				if((current_tile.getValue()== next_tile.getValue()+1 && current_tile.getColour() == next_tile.getColour())){
					count++;
					score+= current_tile.getValue()+next_tile.getValue();
					//p1.getHand().remove(i);
					//p1.getHand().remove(index);
				}
				else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
					count++;
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
