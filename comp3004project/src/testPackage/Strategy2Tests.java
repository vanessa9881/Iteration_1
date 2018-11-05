package testPackage;

import org.junit.Test;
import java.util.*;

import junit.framework.TestCase;
import rummi.Deck;
import rummi.Player;
import rummi.Strategy2;
import rummi.Tile;

public class Strategy2Tests extends TestCase {
	private Player testPlayerOne = new Player();
	private Strategy2 testPlayerThree = new Strategy2();
	private ArrayList<Player> players;
	
	
	
	@Test
	public void testTurn(){
		players = new ArrayList<Player>();
		players.add(testPlayerOne);
		players.add(testPlayerThree);
		players.add(testPlayerOne);
		players.add(testPlayerOne);
		
		
		
		testPlayerThree.setHandValue(14);
		testPlayerOne.setHandValue(7);
		assertEquals("This turn should only play off of board", false, testPlayerThree.makeNewMelds(players)); 
		
		testPlayerThree.setHandValue(7);
		testPlayerOne.setHandValue(14);
		assertEquals("This turn should play new melds", true, testPlayerThree.makeNewMelds(players)); 
	}
	
	@Test
	public void testInitialTurn() {
		// Tests initial turn function
		Strategy2 testPlayer = new Strategy2();
		
		testPlayer.hand.add(new Tile('k', 9));
		testPlayer.hand.add(new Tile('k', 10));
		testPlayer.hand.add(new Tile('k', 11));
		testPlayer.sortHand();
		testPlayer.getMeldsFromHand();
		
		assertEquals("This should play", true, testPlayer.initialTurnPlay());
		
	}
	
	@Test 
	public void testHighestMeld() {
		// Tests highest Meld method
	}
	
	@Test 
	public void testgetMeldsFromHands() {
		// Tests getMeldsFromHands method
	}
	
	@Test
	public void testPlay(){
		//tests play method 
	}
}
