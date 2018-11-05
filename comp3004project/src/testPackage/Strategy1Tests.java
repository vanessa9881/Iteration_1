package testPackage;

import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rummi.Strategy1;

//import rummi.Player;

public class Strategy1Tests {
	//Player p2 = new Player ();
	Strategy1 strat1 = new Strategy1();
	
	
@Test
public void test_30_or_over() {	
	assert.True(30 <= strat1.score_of_valid_hand());
}

@Test
public void initial_turn() {
	assert.True(true , strat1.firstTurn());
}

@Test
public void playing_turn() {
	assert.True("p1 has finished their turn", strat1.play(game));
}

}
