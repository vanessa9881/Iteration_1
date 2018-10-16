package rummi;

import java.util.ArrayList;
import java.util.List;

public class Player {
	//To be done: Constructor with hand, values etc... 
	private int handValue;
	private List<Tile> hand;
	
	public void Player() {
		this.hand = new ArrayList<Tile>();
		this.handValue = 0; 
	}
	
	public int getHandValue() {
		return this.handValue;
	}

}
