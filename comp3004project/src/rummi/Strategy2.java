package rummi;

import java.util.*;

public class Strategy2 extends Player {
	
	//Add constructors, etc... 
	
	
	// Method to show if P3 can make new melds 
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
	
	// Method for Turn
	public void play(Game g){
		//if (this.maneNewMelds(playerlist)) {
			//play turn without making new melds 
		//} else {
			//play turn regular (code from player class) 
		//}
		
	}

}
