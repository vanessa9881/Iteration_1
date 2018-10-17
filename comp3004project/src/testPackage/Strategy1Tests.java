package testPackage;

import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rummi.Strategy1;

public class Strategy1Tests {
	
@Test
public void test_init_30 () {
	Strategy1 strat1 = new Strategy1();
	
	assertTrue(30 <= strat1.hand());
	
}


}
