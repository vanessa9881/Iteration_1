package testPackage;

import org.junit.Test;

import javafx.scene.image.Image;

import static org.junit.Assert.assertTrue;

import java.util.*;

import junit.framework.TestCase;
import rummi.Colour;
import rummi.Deck;
import rummi.Meld;
import rummi.Number;
import rummi.Player;
import rummi.Strategy1;
import rummi.Tile;

public class Strategy1Tests {
	Player p2 = new Player ();
	Strategy1 strat1 = new Strategy1();
	//Strategy1 strat1 = new Strategy1();
	
	//Tile one = new Tile(new Colour("Red", "r"), new Number("Ten", "10"), new Image("10r.gif"));
	//Tile two= new Tile(new Colour("Blue", "g"), new Number("Ten", "10"), new Image("10g.gif"));
	//Tile three= new Tile(new Colour("Black", "b"), new Number("Ten", "10"), new Image("10b.gif"));
	
	
	 
@Test
public void testfirst_turn() {
	Tile one = new Tile(new Colour("Red", "r"), new Number("Ten", "10"), new Image("10r.gif"));
	Tile two= new Tile(new Colour("Blue", "g"), new Number("Ten", "10"), new Image("10g.gif"));
	Tile three= new Tile(new Colour("Black", "b"), new Number("Ten", "10"), new Image("10b.gif"));
	strat1.hand.add(one);
	strat1.hand.add(two);
	strat1.hand.add(three);
	
	
	assertTrue(strat1.getHandValue()>=30);
}

/*@Test
public void playing_turn() {
	assert.True("p1 has finished their turn", strat1.play(game));
}
*/
}
