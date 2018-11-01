package testPackage;

import org.junit.Test;

import junit.framework.TestCase;
import rummi.Deck;
import rummi.Player;
import rummi.Strategy2;

public class Strategy2Tests extends TestCase {
	private Player testPlayerOne = new Player();
	private Strategy2 testPlayerThree = new Strategy2();
	
	
	@Test
	public void testTurn(){
		
		testPlayerThree.setHandValue(14);
		testPlayerOne.setHandValue(7);
		assertEquals("This turn should only play off of board", false, testPlayerThree.makeNewMelds(testPlayerOne.getHandValue())); 
		
		testPlayerThree.setHandValue(7);
		testPlayerOne.setHandValue(14);
		assertEquals("This turn should play new melds", true, testPlayerThree.makeNewMelds(testPlayerOne.getHandValue())); 
	}
}
