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
		
		
		testPlayerThree.setHandValue(14);
		testPlayerOne.setHandValue(7);
		assertEquals("This turn should only play off of board", false, testPlayerThree.makeNewMelds(players)); 
		
		testPlayerThree.setHandValue(7);
		testPlayerOne.setHandValue(14);
		assertEquals("This turn should play new melds", true, testPlayerThree.makeNewMelds(players)); 
	}
	
	@Test
	public void testMeld() {
		testPlayerThree.hand.add(new Tile('b', 10));
		testPlayerThree.hand.add(new Tile('b', 11));
		testPlayerThree.hand.add(new Tile('b', 12));
	
		assertEquals("This should be valid", true, testPlayerThree.isValidMeld(testPlayerThree.hand));
		
		testPlayerThree.hand.clear();
		
		testPlayerThree.hand.add(new Tile('b', 10));
		testPlayerThree.hand.add(new Tile('k', 10));
		testPlayerThree.hand.add(new Tile('o', 10));
		testPlayerThree.hand.add(new Tile('o', 10));
	
		assertEquals("This should be valid", false, testPlayerThree.isValidMeld(testPlayerThree.hand));
	
		testPlayerThree.hand.clear();
		
		testPlayerThree.hand.add(new Tile('b', 10));
		testPlayerThree.hand.add(new Tile('k', 11));
		testPlayerThree.hand.add(new Tile('o', 12));
	
		assertEquals("This should be valid", false, testPlayerThree.isValidMeld(testPlayerThree.hand));
	
		
		
	}
}
