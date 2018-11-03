package testPackage;

import org.junit.Test;

import junit.framework.TestCase;
import rummi.Player;
import rummi.Strategy2;

public class Strategy2Tests extends TestCase {
	private Player testPlayerOne = new Player();
	private Strategy2 testPlayerThree = new Strategy2();
	
	@Test
	public void testTurn(){
		assertEquals("This turn should only play off of board", false, testPlayerThree.makeNewMelds(testPlayerOne.getHandValue())); // Not yet implemented
		assertEquals("This turn should play new melds", true, testPlayerThree.makeNewMelds(testPlayerOne.getHandValue())); // Not yet implemented
	}
}
