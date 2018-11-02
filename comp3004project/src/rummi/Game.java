package rummi;

import java.util.ArrayList;
import java.util.*;

public class Game extends Observable {

	Deck playingDeck;
	ArrayList<Player> playerList;
	ArrayList observers;
	
	public Game() {
		playingDeck = new Deck();
		playerList = new ArrayList<Player>();
		playerList.add(new HumanPlayer());
		playerList.add(new Strategy1());
		playerList.add(new Strategy2());
		playerList.add(new Strategy2());
	}
	
	public void boardChanged() {
		setChanged();
		notifyObservers();
	}
	
		
	
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
