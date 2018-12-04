package rummi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

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
    	if (ypos != controller.BOARDSIZE) {
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
    		boardTiles.put(new Point(xpos, ypos), t);
    		controller.updateView();
    		return true;
    	}
    	
    	else if(leftTile != null) {
    		// Tile is added to the end of an existing meld
    		// Do meld stuff here
    		Meld meldToAddTo = null;
    		for (Meld m : melds) {
    			if (m.getTiles().contains(leftTile)) {
    				meldToAddTo = m;
    			}
    		}
    		if (meldToAddTo != null) {
    			if(meldToAddTo.addRightside(t)) {
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
    		// Tile is added to the beginning of an existing meld
    		// Do meld stuff here
    		// Tile is added to the end of an existing meld
    		// Do meld stuff here
    		Meld meldToAddTo = null;
    		for (Meld m : melds) {
    			if (m.getTiles().contains(rightTile)) {
    				meldToAddTo = m;
    			}
    		}
    		if (meldToAddTo != null) {
    			if(meldToAddTo.addLeftside(t)) {
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
    
    public void moveBoardTile() {
    	// Do something here
    	controller.updateView();
    }
    
    public void addHandTile(Tile t) {
    	handTiles.add(t);
    	handTiles.sort(new TileComparator());
    	controller.updateView();
    }
    
    public void removeHandTile(Tile t) {
    	handTiles.remove(t);
    	controller.updateView();
    }

	public ArrayList<Tile> getHandTiles() {
		return handTiles;
	}

	public HashMap<Point, Tile> getBoardTiles() {
		return boardTiles;
	}

	public void setController(BoardController controller) {
		this.controller = controller;
	}
}
