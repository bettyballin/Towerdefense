package tests.students.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterExtended3;

public class MultiplayerTest {

	TowerdefenseTestAdapterExtended3 adapter;

	String map = "testmaps/extended3/correcte3";
	String stringRepresentation = "Map \"/assets/sandTexture.jpg\" \"correcte3\" \"null\" 0 0 0\n" +
								  "Tower\"PlayerOne\" 1000 1000 10 10 3 3 30 5 270 10 600 400\n" +
								  "Tower\"PlayerTwo\" 500 300 10 5 2 1 20 5 90 10 200 200\n";
	
	@Before
	public void setUp() {
		adapter = new TowerdefenseTestAdapterExtended3();
	}
	
	@After
	public void finish() {
		adapter.stopGame();
	}
	
	@Test
	public void testSecondPlayerTankValues() {
		adapter.loadMapFromFile(new File(map));
		
		assertTrue("A correct map was detected as incorrect (second player Tower): " + map, adapter.isCorrectMap());
		assertEquals("String representation of " + map + " is wrong" , stringRepresentation, adapter.getStringRepresentationOfMap());
		
		assertEquals("Incorrect value of second player Towername", "PlayerTwo", adapter.getSecondPlayerTankName());
		assertEquals("Incorrect value of second player Towermaximum life", 500, adapter.getSecondPlayerTankMaxLife());
		assertEquals("Incorrect value of second player Toweractual life", 300, adapter.getSecondPlayerTankActualLife());
		assertEquals("Incorrect value of second player Towerx position", 200, adapter.getSecondPlayerTankXPosition());
		assertEquals("Incorrect value of second player Towery position", 200, adapter.getSecondPlayerTankYPosition());
	}
	
	@Test
	public void testSecondPlayerMoveForward() {
		adapter.initializeGame();
		adapter.loadMapFromFile(new File(map));
		assertTrue("A correct map was detected as incorrect", adapter.isCorrectMap());
		adapter.handleKeyPressN();
		assertEquals("Your map should not change when starting a new game", stringRepresentation, adapter.getStringRepresentationOfMap());
		adapter.handleKeyDownW();
		assertTrue("The second player Towershould move backward when pressing w", adapter.getSecondPlayerTankXPosition() > 200);
	}
	
	@Test
	public void testSecondPlayerTowerdefensehot() {
		adapter.initializeGame();
		adapter.loadMapFromFile(new File(map));
		assertTrue("A correct map was detected as incorrect", adapter.isCorrectMap());
		adapter.handleKeyPressN();
		assertEquals("Your map should not change when starting a new game", stringRepresentation, adapter.getStringRepresentationOfMap());
		assertEquals("No shot entities should be present in the map", 0, adapter.getShotCount());
		adapter.handleKeyPressG();
		assertEquals("Shot entitiy count should be 1", 1, adapter.getShotCount());
	}
}
