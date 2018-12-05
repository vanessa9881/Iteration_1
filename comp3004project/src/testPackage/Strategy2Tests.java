package testPackage;

import org.junit.Test;

import javafx.scene.image.Image;

import java.util.*;

import junit.framework.TestCase;
import rummi.Colour;
import rummi.Deck;
import rummi.Meld;
import rummi.Number;
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
		
		
		/* 						***********COMMENTED THIS PART OUT*****************
		testPlayerThree.setHandValue(14);
		testPlayerOne.setHandValue(7);
		assertEquals("This turn should only play off of board", false, testPlayerThree.makeNewMelds(players)); 
		
		testPlayerThree.setHandValue(7);
		testPlayerOne.setHandValue(14);
		assertEquals("This turn should play new melds", true, testPlayerThree.makeNewMelds(players)); 
		*/
	}
	
	@Test
	public void testInitialTurn() {
		// Tests initial turn function
		Strategy2 testPlayer = new Strategy2();
		Meld testMeld = new Meld(new Tile(new Colour("Red", "r"), new Number("Ten", "10"), new Image("10r.gif")));
		testMeld.addToMeld(new Tile(new Colour("Blue", "g"), new Number("Ten", "10"), new Image("10g.gif")));
		testMeld.addToMeld(new Tile(new Colour("Yellow", "y"), new Number("Ten", "10"), new Image("10y.gif")));
		testPlayer.melds.add(testMeld);
		assertEquals("This should play", true, testPlayer.initialTurnPlay());
		testPlayer.melds.clear();
		
		testMeld = new Meld(new Tile(new Colour("Red", "r"), new Number("Five", "5"), new Image("5r.gif")));
		testMeld.addToMeld(new Tile(new Colour("Blue", "g"), new Number("Five", "5"), new Image("5g.gif")));
		testMeld.addToMeld(new Tile(new Colour("Yellow", "y"), new Number("Five", "5"), new Image("5y.gif")));
		testPlayer.melds.add(testMeld);
		assertEquals("This should not play", false, testPlayer.initialTurnPlay());
		testPlayer.melds.clear();
		
	}
	
	@Test 
	public void testHighestMeld() {
		// Tests highest Meld method
		Strategy2 testPlayer = new Strategy2();
		
		//Highest Meld 
		Meld testMeld = new Meld(new Tile(new Colour("Red", "r"), new Number("Ten", "10"), new Image("10r.gif")));
		testMeld.addToMeld(new Tile(new Colour("Blue", "g"), new Number("Ten", "10"), new Image("10g.gif")));
		testMeld.addToMeld(new Tile(new Colour("Yellow", "y"), new Number("Ten", "10"), new Image("10y.gif")));
		testMeld.addToMeld(new Tile(new Colour("Black", "b"), new Number("Ten", "10"), new Image("10b.gif")));
		testPlayer.melds.add(testMeld);
		
		
		Meld testMeld2 = new Meld(new Tile(new Colour("Red", "r"), new Number("Ten", "10"), new Image("10r.gif")));
		testMeld2.addToMeld(new Tile(new Colour("Blue", "g"), new Number("Ten", "10"), new Image("10g.gif")));
		testMeld2.addToMeld(new Tile(new Colour("Yellow", "y"), new Number("Yellow", "10"), new Image("10y.gif")));
		testPlayer.melds.add(testMeld2);
		
		assertEquals("This should play the firt meld", testMeld, testPlayer.highestMeld(testPlayer.melds));		
	}
	
	@Test 
	public void testgetMeldsFromHands() {
		// Tests getMeldsFromHands method
		Strategy2 testPlayer = new Strategy2();
		
		testPlayer.hand.add(new Tile(new Colour("Red", "r"), new Number("Ten", "10"), new Image("10r.gif")));
		testPlayer.hand.add(new Tile(new Colour("Black", "b"), new Number("Ten", "10"), new Image("10b.gif")));
		testPlayer.hand.add(new Tile(new Colour("Yellow", "y"), new Number("Yellow", "10"), new Image("10y.gif")));
		testPlayer.hand.add(new Tile(new Colour("Blue", "g"), new Number("Ten", "10"), new Image("10g.gif")));
		
		testPlayer.getMeldsFromHand();
		
		assertEquals("Hand should be empty", true, testPlayer.hand.isEmpty());
		
		testPlayer.hand.add(new Tile(new Colour("Red", "r"), new Number("Ten", "10"), new Image("10r.gif")));
		testPlayer.hand.add(new Tile(new Colour("Black", "b"), new Number("Ten", "10"), new Image("10b.gif")));
		testPlayer.hand.add(new Tile(new Colour("Yellow", "y"), new Number("Yellow", "10"), new Image("10y.gif")));
		testPlayer.hand.add(new Tile(new Colour("Blue", "g"), new Number("Ten", "10"), new Image("10g.gif")));
		testPlayer.hand.add(new Tile(new Colour("Blue", "g"), new Number("Ten", "10"), new Image("5g.gif")));
		
		testPlayer.getMeldsFromHand();
		
		assertEquals("Hand should be empty", false, testPlayer.hand.isEmpty());
		
	}
	
	@Test
	public void testPlay(){
		//tests play method 
	}
}
