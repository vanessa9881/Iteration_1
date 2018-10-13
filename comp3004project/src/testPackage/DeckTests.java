package testPackage;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import rummi.Deck;
import rummi.Tile;

public class DeckTests {

	private Deck testDeck = null;
	
	@Before
	public void setUp() {
		testDeck = new Deck();	
	}
	
	@Test
	public void testDeckSize() {
		assertEquals("Deck should contain 104 tiles", true, (testDeck.getNumberOfTiles() == 104));
		assertEquals("Deck contains one of each tile", true, false); //Not yet implemented
	}
	
	@Test
	public void testDeckDraw() {
		assertEquals("Deck should draw tile on top", true, (testDeck.peek().equals(testDeck.draw())));
		assertEquals("Deck should contain 103 tiles", true, (testDeck.getNumberOfTiles() == 103));
		assertEquals("Deck should not contain drawn tile", true, false); //Not yet implemented
	}
	
}
