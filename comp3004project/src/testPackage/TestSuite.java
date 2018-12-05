package testPackage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({TileTests.class, DeckTests.class, GameLogicTests.class, Strategy1Tests.class, Strategy2Tests.class})

public class TestSuite {
} 