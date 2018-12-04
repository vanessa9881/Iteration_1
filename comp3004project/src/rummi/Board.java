package rummi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;

public class Board {
	
	private ArrayList<Player> playerList;
	private Deck tileDeck;  
    private HashMap<Point, Tile> boardTiles;
    private ArrayList<Tile> handTiles;
    private ArrayList<Meld> melds;
    

    public Board() {
    	// Playerlist should have players added to it with a gui button!
    	playerList = new ArrayList<Player>();
    	tileDeck = new Deck();
    	melds = new ArrayList<Meld>();
    	handTiles = new ArrayList<Tile>();
    	boardTiles = new HashMap<Point, Tile>();
    	// Construct the board with each board space being null
    	for(int x = 1; x <= 12; x++) {
    		for (int y = 1; y <= 12; y++) {
    			boardTiles.put(new Point(x,y), null);
    		}
    	}
    	/*
		Meld tempMeld = new Meld(0);
		tempMeld.addToMeld(new Tile(new Colour("Yellow", "y"), new Number("Five", "5"),new Image("file:resources/5y.gif")));
		tempMeld.addToMeld(new Tile(new Colour("Red", "r"), new Number("Five", "5"),new Image("file:resources/5r.gif")));
		tempMeld.addToMeld(new Tile(new Colour("Black", "b"), new Number("Five", "5"),new Image("file:resources/5b.gif")));
		melds.add(tempMeld);
    	// Board looks like this:
    	// [1,1] [1,2] [1,3] [1,4] [1,5] [1,6] [1,7] [1,8] [1,9] [1,10] [1,11] [1,12]
    	// [2,1] [2,2] [2,3] [2,4] [2,5] [2,6] [2,7] [2,8] [2,9] [2,10] [2,11] [2,12]
    	// etc
    	 */
    }
    

    public Board(Board duplicate) { 	
    	//Deep copy for Deck object
    	ArrayList<Tile> tempTileDeck = duplicate.getDeck();
    	tileDeck = new Deck(0);
    	for(Tile t: tempTileDeck) {
    		Number numb = new Number(t.getNumberValue().getNameValue(), t.getNumberValue().getSymbol());
    		Colour color = new Colour(t.getColour().getName(), t.getColour().getSymbol());
    		Image image = new Image(t.getFilename(color, numb));
    		tileDeck.addTile(new Tile(color, numb, image));
    		
    	}
    	//Deep copy for handTiles object
    	ArrayList<Tile> tempHandTiles = duplicate.getHandTiles();
    	handTiles= new ArrayList<Tile>();
    	for(Tile t: tempHandTiles) {
    		Number numb = new Number(t.getNumberValue().getNameValue(), t.getNumberValue().getSymbol());
    		Colour color = new Colour(t.getColour().getName(), t.getColour().getSymbol());
    		Image image = new Image(t.getFilename(color, numb));
    		handTiles.add(new Tile(color, numb, image)); 		
    	}
    	
    	//Deep copy for boardTiles object
    	boardTiles = new HashMap<Point, Tile>(); 	
    	for(HashMap.Entry<Point, Tile> entry: duplicate.getBoardTiles().entrySet()) {	//Original = duplicate
    		 boardTiles.put(entry.getKey(), entry.getValue());
    	}
    	//Deep copy for meld object
    	ArrayList<Meld> tempMeld = duplicate.getMeld();
    	melds = new ArrayList<Meld>();	
    	for(Meld m: tempMeld) {
    		Meld n = new Meld(0);
	    	for(Tile t: m.getTiles()) {
	    		Number numb = new Number(t.getNumberValue().getNameValue(), t.getNumberValue().getSymbol());
	    		Colour color = new Colour(t.getColour().getName(), t.getColour().getSymbol());
	    		Image image = new Image(t.getFilename(color, numb));
	    		n.addToMeld(new Tile(color, numb, image));	
	    	}
	    	melds.add(n);
    	}
    	
    	
    	//Deep copy for playerList object
    	playerList = new ArrayList<Player>();
   
    }
    

    
	public ArrayList<Object> getSavedState(){
		ArrayList<Object> temp = new ArrayList<Object>();
		temp.add(playerList);
		temp.add(tileDeck);
		temp.add(boardTiles);
		temp.add(handTiles);
		temp.add(melds);
		return temp;
	}
	
	public void setBoard(Board b) {
		
		playerList = b.getPlayerList();
		tileDeck = b.getDeckForMemento();
		boardTiles = b.getBoardTiles();
		handTiles = b.getHandTiles();
		melds = b.getMeld();
	}

    
    public void drawTile() {
		handTiles.add(tileDeck.dealTile());
    }
    
    public Tile drawRiggedTile(String c, String n) {
		return tileDeck.dealRiggedTile(c,n);
    }
    
    public boolean addBoardTile(Tile t, int xpos, int ypos) {
    	// The space the tile is going has already been checked that it's empty
    	// Now we check the spaces to the left and right to see if we're adding
    	// to an existing meld or creating a new one
    	Tile leftTile;
    	Tile rightTile;
    	if (ypos != 1) {
    		// Aka if the tile isn't at the leftmost side
    		leftTile = boardTiles.get(new Point(xpos, ypos - 1));
    	}
    	else {
    		leftTile = null;
    	}
    	
    	if (ypos != 12) {
    		// Aka if the tile isn't at the rightmost side
    		rightTile = boardTiles.get(new Point(xpos, ypos + 1));
    	}
    	else {
    		rightTile = null;
    	}
    	
    	if (leftTile != null && rightTile != null) {
    		// Can not put a tile BETWEEN TWO existing tiles!
    		return false;
    	}
    	
    	else if (leftTile == null && rightTile == null) {
    		// Tile is placed on the board as the start of a new meld!
    		// Do meld stuff here
    		melds.add(new Meld(t));
    		boardTiles.put(new Point(xpos, ypos), t);
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
    			meldToAddTo.addToMeld(t);
    			//TODO:
    			// CHANGE addToMeld to return a boolean
    			// to determine if it was actually added
    			boardTiles.put(new Point(xpos, ypos), t);
    			return true;
    		}
    		else {
    			// The tile that was already on the board is
    			// somehow not found in any meld. Should not
    			// reach here!
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
    			if (m.getTiles().contains(leftTile)) {
    				meldToAddTo = m;
    			}
    		}
    		if (meldToAddTo != null) {
    			meldToAddTo.addToMeld(t);
    			//TODO:
    			// CHANGE addToMeld to return a boolean
    			// to determine if it was actually added
    			boardTiles.put(new Point(xpos, ypos), t);
    			return true;
    		}
    		else {
    			// The tile that was already on the board is
    			// somehow not found in any meld. Should not
    			// reach here!
    			return false;
    		}
    	}
    	else {
    		// Should not reach here, throw an error if it does
    		return false;
    	}
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
	
	public void addMeld(Meld m) {
		melds.add(m);
	}
	
	public void createMeld() {
		Meld tempMeld = new Meld(0);
		tempMeld.addToMeld(new Tile(new Colour("Yellow", "y"), new Number("Five", "5"),new Image("file:resources/5y.gif")));
		tempMeld.addToMeld(new Tile(new Colour("Red", "r"), new Number("Five", "5"),new Image("file:resources/5r.gif")));
		tempMeld.addToMeld(new Tile(new Colour("Black", "b"), new Number("Five", "5"),new Image("file:resources/5b.gif")));
		melds.add(tempMeld);
	}
	
	public void clearMelds() {
		melds.clear();
	}
	//This returns all tiles in deck
	public ArrayList<Tile> getDeck() {
		return tileDeck.getDeck();
	}
	
	public Deck getDeckForMemento() {
		return tileDeck;
	}


	public ArrayList<Player> getPlayerList() {
		return playerList;
	}


	public ArrayList<Meld> getMeld() {
		return melds;
	}
	
	public ArrayList<Tile> getMeldTiles() {
		return melds.get(0).getTiles();
	}
	
	public HashMap<Point, Tile> getBoardTiles() {
		return boardTiles;
	}
}
