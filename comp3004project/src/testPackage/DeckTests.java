package testPackage;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;
import rummi.Colour;
import rummi.Deck;
import rummi.Number;
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
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("One", "1"), new Image("1b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("One", "1"), new Image("1b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Two", "2"), new Image("2b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Two", "2"), new Image("2b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Three", "3"), new Image("3b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Three", "3"), new Image("3b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Four", "4"), new Image("4b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Four", "4"), new Image("4b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Five", "5"), new Image("5b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Five", "5"), new Image("5b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Six", "6"), new Image("6b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Six", "6"), new Image("6b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Seven", "7"), new Image("7b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Seven", "7"), new Image("7b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Eight", "8"), new Image("8b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Eight", "8"), new Image("8b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Nine", "9"), new Image("9b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Nine", "9"), new Image("9b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Ten", "10"), new Image("10b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Ten", "10"), new Image("10b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Eleven", "11"), new Image("11b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Eleven", "11"), new Image("11b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Twelve", "12"), new Image("12b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Twelve", "12"), new Image("12b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Thirteen", "13"), new Image("13b.gif")));
			hardcodeDeck.add(new Tile(new Colour("Black", "b"), new Number("Thirteen", "13"), new Image("13b.gif")));

			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("One", "1"), new Image("1g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("One", "1"), new Image("1g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Two", "2"), new Image("2g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Two", "2"), new Image("2g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Three", "3"), new Image("3g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Three", "3"), new Image("3g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Four", "4"), new Image("4g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Four", "4"), new Image("4g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Five", "5"), new Image("5g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Five", "5"), new Image("5g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Six", "6"), new Image("6g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Six", "6"), new Image("6g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Seven", "7"), new Image("7g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Seven", "7"), new Image("7g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Eight", "8"), new Image("8g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Eight", "8"), new Image("8g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Nine", "9"), new Image("9g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Nine", "9"), new Image("9g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Ten", "10"), new Image("10g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Ten", "10"), new Image("10g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Eleven", "11"), new Image("11g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Eleven", "11"), new Image("11g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Twelve", "12"), new Image("12g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Twelve", "12"), new Image("12g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Thirteen", "13"), new Image("13g.gif")));
			hardcodeDeck.add(new Tile(new Colour("Blue", "g"), new Number("Thirteen", "13"), new Image("13g.gif")));
		
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("One", "1"), new Image("1r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("One", "1"), new Image("1r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Two", "2"), new Image("2r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Two", "2"), new Image("2r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Three", "3"), new Image("3r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Three", "3"), new Image("3r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Four", "4"), new Image("4r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Four", "4"), new Image("4r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Five", "5"), new Image("5r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Five", "5"), new Image("5r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Six", "6"), new Image("6r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Six", "6"), new Image("6r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Seven", "7"), new Image("7r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Seven", "7"), new Image("7r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Eight", "8"), new Image("8r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Eight", "8"), new Image("8r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Nine", "9"), new Image("9r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Nine", "9"), new Image("9r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Ten", "10"), new Image("10r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Ten", "10"), new Image("10r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Eleven", "11"), new Image("11r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Eleven", "11"), new Image("11r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Twelve", "12"), new Image("12r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Twelve", "12"), new Image("12r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Thirteen", "13"), new Image("13r.gif")));
			hardcodeDeck.add(new Tile(new Colour("Red", "r"), new Number("Thirteen", "13"), new Image("13r.gif")));

			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("One", "1"), new Image("1y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("One", "1"), new Image("1y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Two", "2"), new Image("2y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Two", "2"), new Image("2y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Three", "3"), new Image("3y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Three", "3"), new Image("3y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Four", "4"), new Image("4y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Four", "4"), new Image("4y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Five", "5"), new Image("5y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Five", "5"), new Image("5y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Six", "6"), new Image("6y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Six", "6"), new Image("6y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Seven", "7"), new Image("7y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Seven", "7"), new Image("7y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Eight", "8"), new Image("8y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Eight", "8"), new Image("8y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Nine", "9"), new Image("9y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Nine", "9"), new Image("9y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Ten", "10"), new Image("10y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Ten", "10"), new Image("10y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Eleven", "11"), new Image("11y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Eleven", "11"), new Image("11y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Twelve", "12"), new Image("12y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Twelve", "12"), new Image("12y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Thirteen", "13"), new Image("13y.gif")));
			hardcodeDeck.add(new Tile(new Colour("Yellow", "y"), new Number("Thirteen", "13"), new Image("13y.gif")));
			x++;
		}
	}
	
	@Test
	public void testDeckSize() {
		assertEquals("Deck should contain 104 tiles", true, (testDeck.deckSize() == 104));
		assertEquals("Deck contains two of each tile", true, (testDeck.getDeck().containsAll(hardcodeDeck)));
	}
	
	@Test
	public void testDeckDraw() {
		Tile drawnTile = testDeck.dealTile();
		assertEquals("Deck should draw tile on top", true, (drawnTile.equals(testDeck.dealTile())));
		assertEquals("Deck should not contain more than one other copy of drawn tile", true, (Collections.frequency(hardcodeDeck, drawnTile) == 1));
		assertEquals("Deck should contain 103 tiles after draw", true, (testDeck.deckSize() == 103));
	}
	
	@Test
	public void testDeckShuffle() {
		Deck preShuffleDeck = testDeck;
		testDeck.shuffle();
		assertEquals("Deck after shuffle should be ordered differently", true, !(preShuffleDeck.equals(testDeck.getDeck())));
	}
	
}
