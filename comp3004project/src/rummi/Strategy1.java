package rummi;

import rummi.Meld;

//import java.util.*;

public class Strategy1 extends Player{

	//Constructor for Strategy 1
	public Strategy1() {
		
	}
	
	
	//Game Play
	public void play(Board game){
		//Creating list of sets and runs
		Meld meld_set;
		Meld meld_run;
		//Tracking turn for initial 30 tiles
		int initial_turn =0; 
		
		//Player draws tile if Hand score is below 30
		if(!firstTurn()&& initial_turn==0) {
			game.drawTile();
	}
		//Organizes into sets or runs if score is over 30
		if(score_of_valid_hand() >= 30) {
			//initializing and setting list of melds null
			meld_set = new Meld(null);
			meld_run= new Meld(null);
			//initial turn will now equal one, game board can now be used
			initial_turn=1;
			//iterating through hand to place into list of melds
			for(int i=0; i < getNumberOfTiles();i++) {
				int index= i+1;
				Tile current_tile = hand.get(i);
				Tile next_tile = hand.get(index);
				while(index != getNumberOfTiles() - 1){
				if((current_tile.getValue()== next_tile.getValue()+1 && current_tile.getColour() == next_tile.getColour())){
					meld_run.addRightside(hand.remove(i));
					meld_run.addRightside(hand.remove(index));
				}
				else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
					meld_set.addRightside(hand.remove(i));
					meld_set.addRightside(hand.remove(index));
				}
				else {
					continue;
				}
				index++; 
			}
				i++;
			}
			//checks if tiles in meld are a valid group
			meld_set.checkGroup();
			//iterates through list to add Tile to board
			//goes through different positions to check if in a null space
			for(int i=0; i<meld_set.getSize();i++) {
				int x=1;
				int y=1;
				if(game.addBoardTile(meld_set.getTiles().get(i),x,y)){
					game.addBoardTile(meld_set.getTiles().get(i),x,y);
					//x++;
					y++;
				}
				else if(game.addBoardTile(meld_set.getTiles().get(i),x,y)==false){
					if(y>=15) {
						x++;
					}
					y++;
				}
				
			}
			//checks if tiles in meld are in a valid run
			meld_run.checkRun();
			//iterates through list to add Tile to board
			//goes through different positions to check if in a null space
			for(int i=0; i<meld_run.getSize();i++) {
				int x=1;
				int y=1;
				if(game.addBoardTile(meld_run.getTiles().get(i),x,y)){
					game.addBoardTile(meld_run.getTiles().get(i),x,y);
					//x++;
					y++;
				}
				else if(game.addBoardTile(meld_run.getTiles().get(i),x,y)==false){
					if(y>=15) {
						x++;
					}
					y++;
				}
				
			}
			
		}
		
		if(initial_turn!=0 && score_of_valid_hand() > 3) {
			//initializes and sets and runs list of melds
			meld_run = new Meld(null);
			meld_set = new Meld(null);
			for(int i=0; i < getNumberOfTiles();i++) {
				int index= i+1;
				Tile current_tile = hand.get(i);
				Tile next_tile = hand.get(index);
				while(index != getHandValue() - 1) {
				
				if((current_tile.getValue() == next_tile.getValue() + 1 && current_tile.getColour() == next_tile.getColour())){
					meld_run.addRightside(hand.remove(i));
					meld_run.addRightside(hand.remove(index));
					meld_run.checkGroup();
				}
				else if((current_tile.getValue()==next_tile.getValue())&&(current_tile.getColour()!=next_tile.getColour())) {
					meld_set.addRightside(hand.remove(i));
					meld_set.addRightside(hand.remove(index));
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
			for(int i=0; i<meld_set.getSize();i++) {
				int x=1;
				int y=1;
				if(game.addBoardTile(meld_set.getTiles().get(i),x,y)){
					game.addBoardTile(meld_set.getTiles().get(i),x,y);
					//x++;
					y++;
				}
				else if(game.addBoardTile(meld_set.getTiles().get(i),x,y)==false){
					if(y>=15) {
						x++;
					}
					y++;
				}
				
			}
			meld_set.checkRun();
			for(int i=0; i<meld_run.getSize();i++) {
				int x=1;
				int y=1;
				if(game.addBoardTile(meld_run.getTiles().get(i),x,y)){
					game.addBoardTile(meld_run.getTiles().get(i),x,y);
					//x++;
					y++;
				}
				else if(game.addBoardTile(meld_run.getTiles().get(i),x,y)==false){
					if(y>=15) {
						x++;
					}
					y++;
				}
				
			}
			
		}
		
		if(initial_turn!=0) {
			for(int i=0; i<getHandValue();i++) {
				int count=0;
				Tile current_tile=hand.get(i);
				Tile current_meld=game.getBoardTiles().get(count);
				for(int j=0; j<game.getBoardTiles().size();j++) {
					if(current_tile.getValue()== current_meld.getValue()-1 && current_tile.getColour()==current_meld.getColour()) {
						//game.addBoardTile(current_tile, xpos, ypos)
						//game.t.getTile().getPos();
						
					}
					else if (current_tile.getValue()==current_meld.getValue()&&(current_tile.getColour()!=current_meld.getColour())){
						//game.addBoardTile(current_tile, xpos, ypos)
					}
					else {
						continue;
					}
				}
				
			}
			
		}
		if(useJoker() && score_of_valid_hand()==2) {
			
		}
		else {
			game.drawTile();
			
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
