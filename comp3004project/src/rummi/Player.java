package rummi;

import java.util.ArrayList;
import java.util.List;
import rummi.Tile;
	
	

public class Player{
		private ArrayList<Tile> hand;
		private int handValue;
		
		
		public Player() {
			this.hand = new ArrayList<Tile>();
			this.handValue = 0;
		}
		
		public int getHandValue() {
			return this.handValue; 
		}
		
		}



	

