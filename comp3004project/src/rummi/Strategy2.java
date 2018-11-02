package rummi;

public class Strategy2 extends Player {
	
	//Add constructors, etc... 
	
	
	// Method to show if P3 can make new melds 
	public boolean makeNewMelds(Player player) {
		int handDifference = 0; 
		
		if (player.getHandValue() > this.getHandValue()) {
			return true; 
		} else {
			handDifference = this.getHandValue() - player.getHandValue();
			if (handDifference >= 3) {
				return false;
			} else {
				return true; 
			}
		}
	}
	
	// Method for Turn
	public void play(){
		// if (this.maneNewMelds(players hand value))
		// 		play turn without making new melds
		// else
		// 		play turn regular (code from player class)
		
	}

}
