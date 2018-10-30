package rummi;

import java.util.ArrayList;
import java.util.List;
import rummi.Tile;
	
	

public class Player{
		private ArrayList<Tile> hand;
		int matching_card=0;
		
		public Player() {
			hand= new ArrayList<Tile>();
			
		}
		
		public void add (Tile tile) {
			hand.add(tile);
		}
		public boolean valid_hand(Tile current) {
			current=hand.get(0);
			for(int i=1; i< hand.size();i++) {
				if (current.getColour() == hand.get(i).getColour()) {
					matching_card+=1;
				}
				else if(current.getValue()==hand.get(i).getValue()) {
					matching_card+=1;
				}
				else {
					continue;
				}
				
			}
			return true;
		}
	}
	

