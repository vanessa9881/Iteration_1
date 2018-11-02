package testPackage;

import org.junit.Test;
import java.util.*;

import junit.framework.TestCase;
import rummi.Deck;
import rummi.Player;
import rummi.Strategy2;

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
}
