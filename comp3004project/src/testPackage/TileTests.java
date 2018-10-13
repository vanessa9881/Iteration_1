package testPackage;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import rummi.Tile;

public class TileTests {
	
	private Tile tile1 = null;
	private Tile tile2 = null;
	private Tile tile3 = null;
	private Tile tile4 = null;
	
	@Before
	public void setUp() {
		tile1 = new Tile('k', 1); //Black 1 tile
		tile2 = new Tile('o', 9); //Orange 9 tile
		tile3 = new Tile('b', 13); //Blue 13 tile
		tile4 = new Tile('r', 10); //Red 10 tile	
	}
	
	@Test
	public void testTile1() {
		assertEquals("Tile should be a black 1", true, (tile1.getColour().equals("Black") && tile1.getValue() == 1));
	}
	
	@Test
	public void testTile2() {
		assertEquals("Tile should be a orange 9", true, (tile1.getColour().equals("Orange") && tile1.getValue() == 9));
	}
	
	@Test
	public void testTile3() {
		assertEquals("Tile should be a blue 13", true, (tile1.getColour().equals("Blue") && tile1.getValue() == 13));
	}
	
	@Test
	public void testTile4() {
		assertEquals("Tile should be a red 10", true, (tile1.getColour().equals("Red") && tile1.getValue() == 10));
	}
	
}