package testPackage;

import org.junit.Test;
import java.util.*;

import junit.framework.TestCase;
import rummi.Deck;
import rummi.Meld;
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
		Meld testMeld = new Meld(new Tile('r', 10));
		testMeld.addToMeld(new Tile('b', 10));
		testMeld.addToMeld(new Tile('o', 10));
		testPlayer.melds.add(testMeld);
		assertEquals("This should play", true, testPlayer.initialTurnPlay());
		testPlayer.melds.clear();
		
		testMeld = new Meld(new Tile('r', 5));
		testMeld.addToMeld(new Tile('b', 5));
		testMeld.addToMeld(new Tile('o', 5));
		testPlayer.melds.add(testMeld);
		assertEquals("This should not play", false, testPlayer.initialTurnPlay());
		testPlayer.melds.clear();
		
	}
	
	@Test 
	public void testHighestMeld() {
		// Tests highest Meld method
		Strategy2 testPlayer = new Strategy2();
		
		//Highest Meld 
		Meld testMeld = new Meld(new Tile('r', 10));
		testMeld.addToMeld(new Tile('b', 10));
		testMeld.addToMeld(new Tile('o', 10));
		testMeld.addToMeld(new Tile('k', 10));
		testPlayer.melds.add(testMeld);
		
		
		Meld testMeld2 = new Meld(new Tile('r', 10));
		testMeld2.addToMeld(new Tile('b', 10));
		testMeld2.addToMeld(new Tile('o', 10));
		testPlayer.melds.add(testMeld2);
		
		assertEquals("This should play the firt meld", testMeld, testPlayer.highestMeld(testPlayer.melds));		
	}
	
	@Test 
	public void testgetMeldsFromHands() {
		// Tests getMeldsFromHands method
		Strategy2 testPlayer = new Strategy2();
		
		testPlayer.hand.add(new Tile('r', 10));
		testPlayer.hand.add(new Tile('k', 10));
		testPlayer.hand.add(new Tile('o', 10));
		testPlayer.hand.add(new Tile('b', 10));
		
		testPlayer.getMeldsFromHand();
		
		assertEquals("Hand should be empty", true, testPlayer.hand.isEmpty());
	}
	
	@Test
	public void testPlay(){
		//tests play method 
	}
}
