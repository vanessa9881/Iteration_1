package rumi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class testSuite {
	@Test
	void testTileCreation() {
		// Create red 1
		Tile tile1 = new Tile(1, 'r');
		assertTrue(tile1.value == 6);
		assertTrue(tile1.colour == "Red");
	}
	
	@Test
	void deckCreation() {
		// Create the standard deck of tiles
	}

}
