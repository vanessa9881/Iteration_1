package rummi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Random;

import javafx.scene.image.Image;

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
    	for(int x = 1; x <= BoardController.BOARDSIZE; x++) {
    		for (int y = 1; y <= BoardController.BOARDSIZE; y++) {
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
    	// [1,1] [1,2] [1,3] [1,4] [1,5] [1,6] [1,7] [1,8] [1,9] [1,10] [1,11] [1,12] ...
    	// [2,1] [2,2] [2,3] [2,4] [2,5] [2,6] [2,7] [2,8] [2,9] [2,10] [2,11] [2,12] ...
    	// etc
    	 */
    }
    
    public Boolean checkBoardState() {
    	for (Meld m: melds) {
    		m.checkValid();
    		if (m.checkValid() == false) {
    			return false;
    		} 
    	}
		return true;
    }
    

    public Board(Board duplicate) { 	
    	//Deep copy for Deck object
    	ArrayList<Tile> tempTileDeck = duplicate.getDeckTiles();
    	tileDeck = new Deck();
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
    	ArrayList<Meld> tempMeld = duplicate.getMelds();
    	melds = new ArrayList<Meld>();	
    	for(Meld m: tempMeld) {
    		Meld n = new Meld();
	    	for(Tile t: m.getTiles()) {
	    		Number numb = new Number(t.getNumberValue().getNameValue(), t.getNumberValue().getSymbol());
	    		Colour color = new Colour(t.getColour().getName(), t.getColour().getSymbol());
	    		Image image = new Image(t.getFilename(color, numb));
	    		n.addRightside(new Tile(color, numb, image));	
	    	}
	    	melds.add(n);
    	}
    	
    	
    	//Deep copy for playerList object
    	playerList = new ArrayList<Player>();
   
    }
    

	public void setBoard(Board b) {
		
		playerList = b.getPlayerList();
		tileDeck = b.getDeck();
		boardTiles = b.getBoardTiles();
		handTiles = b.getHandTiles();
		melds = b.getMelds();
	}

    public ArrayList<Player> getPlayerList() {
    	return this.playerList;
    }
    
    public void drawTile() {
		handTiles.add(tileDeck.dealTile());
		handTiles.sort(new TileComparator());
		controller.updateView();
    }
    
    public Tile drawRiggedTile(String c, String n) {
		return tileDeck.dealRiggedTile(c,n);
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
    		// Tile placed on the board between two existing melds
    		// NOTE: If the tile is moved a space to the left or right, and that space is 
    		// not occupied, and there is another existing meld beside that space,
    		// then the two melds are the meld it was moved beside and itself
    		Meld leftMeld = findMeld(leftTile);
    		Meld rightMeld = findMeld(rightTile);
    		
    		// First check if either meld is the single tile being moved
    		if (leftMeld.getTiles().contains(t)) {
    			// Treat as adding only that tile
    			if(rightMeld.addLeftside(t)) {
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
    		else if (rightMeld.getTiles().contains(t)) {
    			// Treat as adding only that tile
    			if(leftMeld.addRightside(t)) {
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
    		else if (leftMeld.addRightside(t)) {
    			if(combineMeld(leftMeld, rightMeld)) {
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
    			else {
    				leftMeld.removeFromMeld(t);
    				return false;
    			}
    		}
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
    		// OR tile is moved to the right and the left tile is itself!
    		
    		if (leftTile.equals(t)) {
    			if (findMeld(t).getSize() == 1) {
    				melds.remove(findMeld(t));
    			}
    			else {
    				findMeld(t).removeFromMeld(t);
    			}
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
    		
    		if (rightTile.equals(t)) {
    			if (findMeld(t).getSize() == 1) {
    				melds.remove(findMeld(t));
    			}
    			else {
    				findMeld(t).removeFromMeld(t);
    			}
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
    
    private boolean combineMeld(Meld lMeld, Meld rMeld) {
    	if (rMeld.getSize() == 1) {
    		// Treat as if adding a tile to the end of the meld
    		if (lMeld.addRightside(rMeld.getTiles().get(0))) {
    			melds.remove(rMeld);
    			return true;
    		}
    		return false;
    	}
    	
    	else if (lMeld.checkRun() && rMeld.checkRun()) {
			if (lMeld.getTiles().get(lMeld.getSize() - 1).getValue() == rMeld.getTiles().get(0).getValue() - 1) {
				lMeld.combineMeld(rMeld);
				melds.remove(rMeld);
    			return true;
			}
			return false;
		}
		
		else if (lMeld.checkGroup() && rMeld.checkGroup()){
			if (lMeld.getValue() == rMeld.getValue()) {
				for (Tile t : lMeld.getTiles()) {
					for (Tile t2 : rMeld.getTiles()) {
						if (t.getColour().getName().equals(t2.getColour().getName())) {
							return false;
						}
					}
				}
				lMeld.combineMeld(rMeld);
				melds.remove(rMeld);
				return true;
			}
		}
		return false;
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
    				return true;
    			}
    		}
    		
    		else if (m.getTiles().indexOf(t) == 0) {
    			// Tile is moved from the start of existing meld which means it
    			// Will not mess up how it looks on the board
    			if (addBoardTile(t, x, y)) {
    				m.removeFromMeld(t);
    				return true;
    			}
    		}
    		
    		else if (m.getTiles().indexOf(t) == m.getSize() - 1){
    			// Tile is moved from the end of existing meld which means it
    			// Will not mess up how it looks on the board
    			if (addBoardTile(t, x, y)) {
    				m.removeFromMeld(t);
    				return true;
    			}
    		}
    		else {
    			// Tile is moved from the middle of a meld,
    			// Need to split the meld into two new melds by where
    			// the tile was moved from
    			if (addBoardTile(t, x, y)) {
        			int i;
        			int splitIndex =  m.getTiles().indexOf(t);
        			
        			Meld leftMeld = new Meld(m.getTiles().get(0));
        			for (i = 1; i < splitIndex; i++) {
        				leftMeld.add((m.getTiles().get(i)));
        			}
        			
        			Meld rightMeld = new Meld(m.getTiles().get(splitIndex + 1));
        			for (i = splitIndex + 1; i < m.getSize(); i++) {
        				rightMeld.add((m.getTiles().get(i)));
        			}
        			
        			melds.remove(m);
        			melds.add(leftMeld);
        			melds.add(rightMeld);
        			return true;
    			}    			
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
	
	public void addMeld(Meld m) {
		melds.add(m);
	}
	
	public void createMeld() {
		Meld tempMeld = new Meld();
		tempMeld.addLeftside(new Tile(new Colour("Yellow", "y"), new Number("Five", "5"),new Image("file:resources/5y.gif")));
		tempMeld.addLeftside(new Tile(new Colour("Red", "r"), new Number("Five", "5"),new Image("file:resources/5r.gif")));
		tempMeld.addLeftside(new Tile(new Colour("Black", "b"), new Number("Five", "5"),new Image("file:resources/5b.gif")));
		melds.add(tempMeld);

	}
	
	public void clearMelds() {
		melds.clear();
	}
	//This returns all tiles in deck
	public ArrayList<Tile> getDeckTiles() {
		return tileDeck.getDeck();
	}

	public ArrayList<Meld> getMelds(){
		return melds;
	}
	public ArrayList<Tile> getMeldTiles() {
		return melds.get(0).getTiles();
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
		System.out.println("_______________________________________________");
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
	
	// Method for player turns 
	public void turns() {
		int temp = 0;
		this.startOrder();
		System.out.println("_______________________________________________");
		for (Player p : this.playerList) {
			temp++;
			p.play(this);
			//System.out.println("Player " + temp + "'s melds: " + p.getMelds());
			System.out.println("_______________________________________________");
		}
	}
	
	// Determines start order of game
	public void startOrder() {
		Deck tempDeck = new Deck();
		ArrayList<Player> highPlayerList = new ArrayList<Player>();
		int temp = 0;
		for (Player p : this.playerList) {
			temp++;
			p.hand.add(tempDeck.dealTile());
			System.out.println("Player " + temp + "'s hand: " + p.getHandTiles());
		}
		
		while (playerList.isEmpty() == false) {	
			Player min = playerList.get(0);
			for(Player i : playerList) {
				if (i.getHandValue() > min.getHandValue()) {min = i;}
			}
			System.out.println("LOWEST VALUE: " + min.getHandValue());
			highPlayerList.add(min);
			playerList.remove(min);
		}
		this.playerList = highPlayerList;	
		temp = 0; 
		for(Player p : this.playerList) {
			temp++;
			System.out.println("Before Clear,  Player " + temp + "'s hand: " + p.getHandTiles());
		}
		
		System.out.println("_____________________________________________________________________");
		for (Player p : playerList) {
			p.hand.clear();
		}	
		temp =0;
		for(Player p : this.playerList) {
			temp++;
			System.out.println("Player " + temp + "'s hand: " + p.getHandTiles());
		}
		
			//System.out.println("Player melds: " + playerList.get(0).getMelds());
	
	}
}
