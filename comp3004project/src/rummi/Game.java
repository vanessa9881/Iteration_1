package rummi;

import java.util.ArrayList;

public class Game {

	Deck playingDeck;
	ArrayList<Player> playerList;
	
	public Game() {
		playingDeck = new Deck();
		playerList = new ArrayList<Player>();
		playerList.add(new HumanPlayer());
		playerList.add(new Strategy1());
		playerList.add(new Strategy2());
		playerList.add(new Strategy2());
	}
	
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
