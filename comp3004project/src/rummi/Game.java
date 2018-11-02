package rummi;

import java.util.ArrayList;

public class Game implements Subject {

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
	
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if(i >= 0) {
			observers.remove(i);
		}
	}
	
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer.update();
		}
	}
	
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
