package rummi;

public class Strategy2 extends Player {
	
	//Add constructors, etc... 
	
	
	// Method to show if P3 can make new melds 
	public boolean makeNewMelds(int playerHand) {
		int handDifference = 0; 
		
		if (playerHand > this.getHandValue()) {
			return true; 
		} else {
			handDifference = this.getHandValue() - playerHand;
			if (handDifference >= 3) {
				return false;
			} else {
				return true; 
			}
		}
	}

}
