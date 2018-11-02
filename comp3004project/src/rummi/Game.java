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
	
<<<<<<< HEAD
	public void boardChanged() {
		setChanged();
		notifyObservers();
	}
	
		
	
	public void play() {
		// TODO Auto-generated method stub
=======
	public void start() {
		playingDeck.shuffle();
		int playerIndex = 0;
		while(true) {
			// Reset player index back to first player after the fourth player
			// had their turn
			if (playerIndex == 4) {
				playerIndex = 0;
			}
			Player currentPlayer = playerList.get(playerIndex);
			currentPlayer.play();
			if(currentPlayer.checkWin() == true){
				break;
			}
			else {
				playerIndex++;
			}
		}
>>>>>>> master
		
	}

}
