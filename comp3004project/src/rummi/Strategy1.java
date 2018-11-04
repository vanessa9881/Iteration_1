package rummi;

import java.util.ArrayList;

public class Strategy1 extends Player{
	Player p1= new Player();
	

	public Strategy1() {
		p1.sortHand();
	}
	
	
	public void play(Game game){
		
		if(score_of_valid_hand()>= 30) {
		for(int i=0; i < p1.getHandValue();i++) {
			int index= i+1;
			Tile current_tile = p1.getHand().get(i);
			Tile next_tile = p1.getHand().get(index);
			if(index!=p1.getHandValue()-1) {
			
			if((current_tile.getValue()== next_tile.getValue()+1 && current_tile.getColour() == next_tile.getColour())){
				p1.getHand().remove(i);
				p1.getHand().remove(index);
			}
			else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
				p1.getHand().remove(i);
				p1.getHand().remove(index);
			}
			else {
				continue;
			}
			index++; 
		}
			i++;
		}
		
	}
		else if(score_of_valid_hand() > 0) {
			for(int i=0; i < p1.getHandValue();i++) {
				int index= i+1;
				Tile current_tile = p1.getHand().get(i);
				Tile next_tile = p1.getHand().get(index);
				if(index!=p1.getHandValue()-1) {
				
				if((current_tile.getValue()== next_tile.getValue()+1 && current_tile.getColour() == next_tile.getColour())){
					p1.getHand().remove(i);
					p1.getHand().remove(index);
				}
				else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
					p1.getHand().remove(i);
					p1.getHand().remove(index);
				}
				else {
					continue;
				}
				index++; 
			}
				i++;
			
		}
		}
		else {
			
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
					p1.getHand().remove(i);
					p1.getHand().remove(index);
				}
				else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
					count++;
					score+= current_tile.getValue()+next_tile.getValue();
					p1.getHand().remove(i);
					p1.getHand().remove(index);
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
