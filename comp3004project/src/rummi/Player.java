package rummi;

import java.util.ArrayList;

public class Player {
	private int handValue;	//----------To do
	public ArrayList<Tile> hand;	//Hand is a refrence to a players hand. (hand = players hand)
	protected int turnNumber;
	protected int score;
	protected ArrayList<Meld> melds = new ArrayList<Meld>(); 
	
	public Player() {
		hand = new ArrayList<Tile>();
		handValue = 0;
		turnNumber = 0;
		score = 0;
	}
	
	public ArrayList<Tile> getMelds(){
		return this.melds.get(0).getTiles();
	}
	
	public int getTurnNumber() {
		return this.turnNumber;
	}
	
	public int getHandValue() {
		return this.handValue;
	}
	
	public void addTile(Tile tile) {
		hand.add(tile);
	}
	   
	public Tile getTile(int index) {
		return (Tile) hand.get(index);
	}
	
	public int getNumberOfTiles() {
		return hand.size();
	}
	
	public ArrayList<Tile> getHandTiles(){
		return this.hand;
	}
	
	public void sort() {
		hand.sort(new TileComparator());;
	}
	
	public int findTile(Tile tile) {
		return hand.indexOf(tile);
	}
	
	public void play(Board g) {
		// TODO Auto-generated method stub		
	}
	
	//Setting the graphic of the removed tile to null
	public void deleteTile(Tile tile) {
		hand.remove(tile);
	}
	
	protected void SetPlayerScore(Tile t) {
		this.score = this.score + t.getValue();
	}
		   	
	@Override
	public String toString() {
		return hand.toString();
	}
	
	public void endTurn() {
		
	}
}
