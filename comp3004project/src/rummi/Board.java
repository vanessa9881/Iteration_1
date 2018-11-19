package rummi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

public class Board {
	
	private Deck tileDeck;  
    private Player playerHand;
    private Map<Point, Tile> boardTiles;
    private ArrayList<Tile> handTiles;
    private ArrayList<Meld> melds;
    private Tile selectedTile;
    
    public Board() {
    	tileDeck = new Deck();
    	handTiles = new ArrayList<Tile>();
    	melds = new ArrayList<Meld>();
    	handTiles = new ArrayList<Tile>();
    	
    	// Construct the board with each board space being null
    	for(int x = 1; x <= 12; x++) {
    		for (int y = 1; y <= 12; y++) {
    			boardTiles.put(new Point(x,y), null);
    		}
    	}
    	
    }
    
    public Tile drawTile() {
		return tileDeck.dealTile();
    }
    
    public boolean addBoardTile(Tile t, int xpos, int ypos) {
		return false;
    }
    
    public void moveBoardTile() {
    	
    }
    
    public void addHandTile(Tile t) {
    	handTiles.add(t);
    }
    
    public void removeHandTile(Tile t) {
    	handTiles.remove(t);
    }

	public ArrayList<Tile> getHandTiles() {
		return handTiles;
	}
}
