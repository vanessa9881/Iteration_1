package rummi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Random;

public class Board {
	
	private ArrayList<Player> playerList;
	private Deck tileDeck;  
    private HashMap<Point, Tile> boardTiles;
    private ArrayList<Tile> handTiles;
    private ArrayList<Meld> melds;
    private BoardController controller;
    
    public Board() {
    	// Playerlist should have players added to it with a gui button!
    	playerList = new ArrayList<Player>();
    	tileDeck = new Deck();
    	melds = new ArrayList<Meld>();
    	handTiles = new ArrayList<Tile>();
    	boardTiles = new HashMap<Point, Tile>();
    	
    	// Construct the board with each board space being null
    	for(int x = 1; x <= controller.BOARDSIZE; x++) {
    		for (int y = 1; y <= controller.BOARDSIZE; y++) {
    			boardTiles.put(new Point(x,y), null);
    		}
    	}
    	// Board looks like this:
    	// [1,1] [1,2] [1,3] [1,4] [1,5] [1,6] [1,7] [1,8] [1,9] [1,10] [1,11] [1,12] ...
    	// [2,1] [2,2] [2,3] [2,4] [2,5] [2,6] [2,7] [2,8] [2,9] [2,10] [2,11] [2,12] ...
    	// etc
    }
    
    public ArrayList<Player> getPlayerList() {
    	return this.playerList;
    }
    
    public void drawTile() {
		handTiles.add(tileDeck.dealTile());
		handTiles.sort(new TileComparator());
		controller.updateView();
    }
    
    public boolean addBoardTile(Tile t, int xpos, int ypos) {
    	// The space the tile is going has already been checked that it's empty
    	// Now we check the spaces to the left and right to see if we're adding
    	// to an existing meld or creating a new one
    	Tile leftTile = null;
    	Tile rightTile = null;
    	if (ypos != 1) {
    		// Aka if the tile isn't at the leftmost side
    		leftTile = boardTiles.get(new Point(xpos - 1, ypos));
    	}    	
    	if (ypos != BoardController.BOARDSIZE) {
    		// Aka if the tile isn't at the rightmost side
    		rightTile = boardTiles.get(new Point(xpos + 1, ypos));
    	}
    	
    	if (leftTile != null && rightTile != null) {
    		// Can not put a tile BETWEEN TWO existing tiles!
    		System.out.println("1");
    		return false;
    	}
    	
    	else if (leftTile == null && rightTile == null) {
    		// Tile is placed on the board as the start of a new meld!
    		melds.add(new Meld(t));
    		if (boardTiles.containsValue(t)) {
    			removeBoardTile(t);
			}
			else {
				removeHandTile(t);
			}
			boardTiles.put(new Point(xpos, ypos), t);
			controller.updateView();
			return true;
    	}
    	
    	else if(leftTile != null) {
    		// Tile is added to the end of an existing meld
    		// Do meld stuff here
    		Meld meldToAddTo = findMeld(leftTile);
    		if (meldToAddTo != null) {
    			if(meldToAddTo.addRightside(t)) {
    				if (boardTiles.containsValue(t)) {
    					removeBoardTile(t);
    				}
    				else {
    					removeHandTile(t);
    				}
    				boardTiles.put(new Point(xpos, ypos), t);
        			controller.updateView();
        			return true;
    			}
    			return false;
    		}
    		else {
    			// The tile that was already on the board is
    			// somehow not found in any meld. Should not
    			// reach here!
    			System.out.println("2");
    			return false;
    		}
    	}
    	
    	else if(rightTile != null) {
    		Meld meldToAddTo = findMeld(rightTile);
    		if (meldToAddTo != null) {
    			if(meldToAddTo.addLeftside(t)) {
    				if (boardTiles.containsValue(t)) {
    					removeBoardTile(t);
    				}
    				else {
    					removeHandTile(t);
    				}
    				boardTiles.put(new Point(xpos, ypos), t);
        			controller.updateView();
        			return true;
    			}
    			return false;
    		}
    		else {
    			// The tile that was already on the board is
    			// somehow not found in any meld. Should not
    			// reach here!
    			System.out.println("3");
    			return false;
    		}
    	}
    	else {
    		// Should not reach here, throw an error if it does
    		System.out.println("4");
    		return false;
    	}
    }
    
    public Meld findMeld(Tile t) {
    	Meld meldToAddTo = null;
		for (Meld m : melds) {
			if (m.getTiles().contains(t)) {
				meldToAddTo = m;
			}
		}
		return meldToAddTo;
    }
    
    public boolean removeBoardTile(Tile t) {
    	// Remove tile from board tiles if it was already on board
		// Aka a tile moved from one spot on board to another
		Set<Entry<Point, Tile>> entrySet = boardTiles.entrySet();
		for (Iterator<Entry<Point, Tile>> iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<Point, Tile> entry = iterator.next();
			if (entry.getValue() != null) {
				if (entry.getValue().equals(t)) {
					entry.setValue(null);
					return true;
				}
			}
		}
		return false;
    }
    
    public boolean moveBoardTile(int x, int y, Tile t) {
    	// First find the meld the moved tile was in
    	Meld m = findMeld(t);
    	if (m != null) {
    		if (m.getTiles().size() == 1) {
    			// Meld has only this single tile, can move it freely.
    			if (addBoardTile(t, x, y)) {
    				melds.remove(m);
    			}
    		}
    		
    		else if (m.getTiles().indexOf(t) == 0) {
    			// Tile is moved from the start of existing meld which means it
    			// Will not mess up how it looks on the board
    			if (addBoardTile(t, x, y)) {
    				m.removeFromMeld(t);
    			}
    		}
    		
    		else if (m.getTiles().indexOf(t) == m.getSize() - 1){
    			// Tile is moved from the end of existing meld which means it
    			// Will not mess up how it looks on the board
    			if (addBoardTile(t, x, y)) {
    				m.removeFromMeld(t);
    			}
    		}
    		else {
    			// Tile is moved from the middle of a meld,
    			// Need to split the meld into two new melds by where
    			// the tile was moved from
    		}
    	}
		return false;
    }
    
    public void addHandTile(Tile t) {
    	handTiles.add(t);
    	handTiles.sort(new TileComparator());
    	controller.updateView();
    }
    
    public void addHandMeld(Meld m) {
    	melds.add(m);
    }
    
    public void removeHandTile(Tile t) {
    	handTiles.remove(t);
    }

	public ArrayList<Tile> getHandTiles() {
		return handTiles;
	}
	
	public ArrayList<Meld> getMelds(){
		return melds;
	}

	public HashMap<Point, Tile> getBoardTiles() {
		return boardTiles;
	}

	public void setController(BoardController controller) {
		this.controller = controller;
	}
	
	public Deck getDeck() {
		return this.tileDeck;
	}
	
	// Sets number of human players then number of AI players 
	public void setPlayers(int numPlayersHuman, int numPlayersAI) {
		Random rand = new Random();
		int randNum = rand.nextInt(3) + 1;
		
		if (!(numPlayersHuman == 0)) {
			for (int i = 0; i < numPlayersHuman; i++){this.playerList.add(new HumanPlayer());}
		}
		if (!(numPlayersAI == 0)) {
			for (int i = 0; i < numPlayersAI; i++){
				randNum = rand.nextInt(3) + 1;
				if (randNum == 1) {this.playerList.add(new Strategy2());}
				if (randNum == 2) {this.playerList.add(new Strategy2());}
				if (randNum == 3) {this.playerList.add(new Strategy3());}
				//if (randNum == 4) {this.playerList.add(new Strategy4());}
			}
		}
	}
	
	// Deals initial hands to all players
	public void dealInitialPlayerHands() {
		int temp = 0;
		System.out.println("This is the deck size before dealing: " + this.tileDeck.deckSize());
		for (Player p : this.playerList) {
			temp++;
			for (int i = 0; i < 14; i ++) {
				p.hand.add(this.tileDeck.dealTile());
			}
			p.sort();
			System.out.println("Player " + temp + "'s hand: " + p.getHandTiles());
			System.out.println("_______________________________________________");
		}
		//System.out.println(this.tileDeck.deckSize());
	}
	
	public void turns() {
		int temp = 0;
		for (Player p : this.playerList) {
			temp++;
			p.play(this);
			System.out.println("Player " + temp + "'s melds: " + p.getMelds());
			System.out.println("_______________________________________________");
		}
	}
}
