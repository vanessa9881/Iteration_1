package testPackage;

import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rummi.Strategy1;

import rummi.Player;

public class Strategy1Tests {
	Player p2 = new Player ();
	Strategy1 strat1 = new Strategy1();
	
	
@Test
public void test_init_30 () {	
	assertTrue(30 <= strat1.init_30_hand());
	
}


}
