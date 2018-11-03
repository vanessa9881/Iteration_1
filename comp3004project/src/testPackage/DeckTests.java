package testPackage;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import rummi.Deck;
import rummi.Tile;

public class DeckTests {

	private Deck testDeck = null;
	private ArrayList<Tile> hardcodeDeck;
	
	@Before
	public void setUp() {
		testDeck = new Deck();
		// Did not use a for loop, as would that not have to be tested too?
		// But I did cheat and used a single loop of a while loop so I wouldn't have to type it all twice!
		
		// If you want these tests to pass, make sure the deck is constructed
		// in this order! (Then do a .shuffle() when starting a game)
		hardcodeDeck = new ArrayList<Tile>();
		int x = 0;
		while(x != 2) {
			hardcodeDeck.add(new Tile('k', 1));
			hardcodeDeck.add(new Tile('k', 1));
			hardcodeDeck.add(new Tile('k', 2));
			hardcodeDeck.add(new Tile('k', 2));
			hardcodeDeck.add(new Tile('k', 3));
			hardcodeDeck.add(new Tile('k', 3));
			hardcodeDeck.add(new Tile('k', 4));
			hardcodeDeck.add(new Tile('k', 4));
			hardcodeDeck.add(new Tile('k', 5));
			hardcodeDeck.add(new Tile('k', 5));
			hardcodeDeck.add(new Tile('k', 6));
			hardcodeDeck.add(new Tile('k', 6));
			hardcodeDeck.add(new Tile('k', 7));
			hardcodeDeck.add(new Tile('k', 7));
			hardcodeDeck.add(new Tile('k', 8));
			hardcodeDeck.add(new Tile('k', 8));
			hardcodeDeck.add(new Tile('k', 9));
			hardcodeDeck.add(new Tile('k', 9));
			hardcodeDeck.add(new Tile('k', 10));
			hardcodeDeck.add(new Tile('k', 10));
			hardcodeDeck.add(new Tile('k', 11));
			hardcodeDeck.add(new Tile('k', 11));
			hardcodeDeck.add(new Tile('k', 12));
			hardcodeDeck.add(new Tile('k', 12));
			hardcodeDeck.add(new Tile('k', 13));
			hardcodeDeck.add(new Tile('k', 13));
		
			hardcodeDeck.add(new Tile('r', 1));
			hardcodeDeck.add(new Tile('r', 1));
			hardcodeDeck.add(new Tile('r', 2));
			hardcodeDeck.add(new Tile('r', 2));
			hardcodeDeck.add(new Tile('r', 3));
			hardcodeDeck.add(new Tile('r', 3));
			hardcodeDeck.add(new Tile('r', 4));
			hardcodeDeck.add(new Tile('r', 4));
			hardcodeDeck.add(new Tile('r', 5));
			hardcodeDeck.add(new Tile('r', 5));
			hardcodeDeck.add(new Tile('r', 6));
			hardcodeDeck.add(new Tile('r', 6));
			hardcodeDeck.add(new Tile('r', 7));
			hardcodeDeck.add(new Tile('r', 7));
			hardcodeDeck.add(new Tile('r', 8));
			hardcodeDeck.add(new Tile('r', 8));
			hardcodeDeck.add(new Tile('r', 9));
			hardcodeDeck.add(new Tile('r', 9));
			hardcodeDeck.add(new Tile('r', 10));
			hardcodeDeck.add(new Tile('r', 10));
			hardcodeDeck.add(new Tile('r', 11));
			hardcodeDeck.add(new Tile('r', 11));
			hardcodeDeck.add(new Tile('r', 12));
			hardcodeDeck.add(new Tile('r', 12));
			hardcodeDeck.add(new Tile('r', 13));
			hardcodeDeck.add(new Tile('r', 13));

			hardcodeDeck.add(new Tile('o', 1));
			hardcodeDeck.add(new Tile('o', 1));
			hardcodeDeck.add(new Tile('o', 2));
			hardcodeDeck.add(new Tile('o', 2));
			hardcodeDeck.add(new Tile('o', 3));
			hardcodeDeck.add(new Tile('o', 3));
			hardcodeDeck.add(new Tile('o', 4));
			hardcodeDeck.add(new Tile('o', 4));
			hardcodeDeck.add(new Tile('o', 5));
			hardcodeDeck.add(new Tile('o', 5));
			hardcodeDeck.add(new Tile('o', 6));
			hardcodeDeck.add(new Tile('o', 6));
			hardcodeDeck.add(new Tile('o', 7));
			hardcodeDeck.add(new Tile('o', 7));
			hardcodeDeck.add(new Tile('o', 8));
			hardcodeDeck.add(new Tile('o', 8));
			hardcodeDeck.add(new Tile('o', 9));
			hardcodeDeck.add(new Tile('o', 9));
			hardcodeDeck.add(new Tile('o', 10));
			hardcodeDeck.add(new Tile('o', 10));
			hardcodeDeck.add(new Tile('o', 11));
			hardcodeDeck.add(new Tile('o', 11));
			hardcodeDeck.add(new Tile('o', 12));
			hardcodeDeck.add(new Tile('o', 12));
			hardcodeDeck.add(new Tile('o', 13));
			hardcodeDeck.add(new Tile('o', 13));

			hardcodeDeck.add(new Tile('b', 1));
			hardcodeDeck.add(new Tile('b', 1));
			hardcodeDeck.add(new Tile('b', 2));
			hardcodeDeck.add(new Tile('b', 2));
			hardcodeDeck.add(new Tile('b', 3));
			hardcodeDeck.add(new Tile('b', 3));
			hardcodeDeck.add(new Tile('b', 4));
			hardcodeDeck.add(new Tile('b', 4));
			hardcodeDeck.add(new Tile('b', 5));
			hardcodeDeck.add(new Tile('b', 5));
			hardcodeDeck.add(new Tile('b', 6));
			hardcodeDeck.add(new Tile('b', 6));
			hardcodeDeck.add(new Tile('b', 7));
			hardcodeDeck.add(new Tile('b', 7));
			hardcodeDeck.add(new Tile('b', 8));
			hardcodeDeck.add(new Tile('b', 8));
			hardcodeDeck.add(new Tile('b', 9));
			hardcodeDeck.add(new Tile('b', 9));
			hardcodeDeck.add(new Tile('b', 10));
			hardcodeDeck.add(new Tile('b', 10));
			hardcodeDeck.add(new Tile('b', 11));
			hardcodeDeck.add(new Tile('b', 11));
			hardcodeDeck.add(new Tile('b', 12));
			hardcodeDeck.add(new Tile('b', 12));
			hardcodeDeck.add(new Tile('b', 13));
			hardcodeDeck.add(new Tile('b', 13));
			x++;
		}
	}
	
	@Test
	public void testDeckSize() {
		assertEquals("Deck should contain 104 tiles", true, (testDeck.getNumberOfTiles() == 104));
		assertEquals("Deck contains two of each tile", true, (testDeck.getDeck().containsAll(hardcodeDeck)));
	}
	
	@Test
	public void testDeckDraw() {
		Tile drawnTile = testDeck.peek();
		assertEquals("Deck should draw tile on top", true, (drawnTile.equals(testDeck.draw())));
		assertEquals("Deck should not contain more than one other copy of drawn tile", true, (Collections.frequency(hardcodeDeck, drawnTile) == 1));
		assertEquals("Deck should contain 103 tiles after draw", true, (testDeck.getNumberOfTiles() == 103));
	}
	
	@Test
	public void testDeckShuffle() {
		Deck preShuffleDeck = testDeck;
		testDeck.shuffle();
		assertEquals("Deck after shuffle should be ordered differently", true, !(preShuffleDeck.equals(testDeck.getDeck())));
	}
	
}
