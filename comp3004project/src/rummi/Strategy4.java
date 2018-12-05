package rummi;

import java.util.*;

//Strategy 4 will 

public class Strategy4 extends Player {

	public Strategy4() {
		
	}
	
	
	//method for checking if strategy 4 AI has a higher score than previous
	//player
	
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
	
	public boolean higher_than_previous() {
		
		return true;
		
	}
}


