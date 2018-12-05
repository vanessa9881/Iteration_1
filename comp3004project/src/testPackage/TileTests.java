package testPackage;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import javafx.scene.image.Image;
import rummi.Colour;
import rummi.Number;
import rummi.Tile;


public class TileTests {
	
	private Tile tile1 = null;
	private Tile tile2 = null;
	private Tile tile3 = null;
	private Tile tile4 = null;
	
	@Before
	public void setUp() {
		tile1 = new Tile(new Colour("Black", "b"), new Number("One", "1"), new Image("1b.gif")); //Black 1 tile
		tile2 = new Tile(new Colour("Yellow", "y"), new Number("Nine", "9"), new Image("9y.gif")); //Yellow 9 tile
		tile3 = new Tile(new Colour("Blue", "g"), new Number("Thirteen", "13"), new Image("13g.gif")); //Blue 13 tile
		tile4 = new Tile(new Colour("Red", "b"), new Number("Ten", "10"), new Image("10r.gif")); //Red 10 tile

	}
	
	@Test
	public void testTile1() {
		assertEquals("Tile should be a black 1", true, (tile1.getColour().toString().equals("Black") && tile1.getValue() == 1));
	}
	
	@Test
	public void testTile2() {
		assertEquals("Tile should be a Yellow 9", true, (tile1.getColour().toString().equals("Yellow") && tile1.getValue() == 9));
	}
	
	@Test
	public void testTile3() {
		assertEquals("Tile should be a blue 13", true, (tile1.getColour().toString().equals("Blue") && tile1.getValue() == 13));
	}
	
	@Test
	public void testTile4() {
		assertEquals("Tile should be a red 10", true, (tile1.getColour().toString().equals("Red") && tile1.getValue() == 10));
	}
	
}