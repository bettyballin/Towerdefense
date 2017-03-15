package tests.tutors.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Input;

import tests.adapter.TowerdefenseTestAdapterMinimal;
import Towerdefense.ui.Towerdefense;

public class KeyboardInputTest {
	
	TowerdefenseTestAdapterMinimal adapter;
	
	String map = "testmaps/minimal/correct01";
	String stringRepresentation = "Map \"/assets/sandTexture.jpg\" \"correct01\" \"null\" 0 0 0\n" +
								  "Tower\"PlayerOne\" 1000 1000 10 10 3 3 30 5 0 10 300 200\n" +
								  "Tower\"OpponentTank0\" 30 30 1 1 0 0 1 5 270 10 100 200\n";
	
	@Before
	public void setUp() {
		adapter = new TowerdefenseTestAdapterMinimal();
	}
	
	@After
	public void finish() {
		adapter.stopGame();
	}
	
	@Test
	public void testNewGame() {
		adapter.initializeGame();
		assertTrue(adapter.getStateBasedGame().getCurrentStateID()==Towerdefense.MAINMENUSTATE);
		adapter.handleKeyPressN();
		assertTrue(adapter.getStateBasedGame().getCurrentStateID()==Towerdefense.GAMEPLAYSTATE);
		adapter.handleKeyPressed(0, Input.KEY_ESCAPE);
		adapter.stopGame();
	}
	
	@Test
	public void testMoveForward() {
		adapter.initializeGame();
		adapter.loadMapFromFile(new File(map));
		assertTrue("A correct map was detected as incorrect", adapter.isCorrectMap());
		adapter.handleKeyPressN();
		assertEquals("Your map should not change when starting a new game", stringRepresentation, adapter.getStringRepresentationOfMap());
		adapter.handleKeyDownUpArrow();
		assertTrue("Your Towershould move forward when pressing up arrow", adapter.getTankYPosition(0) < 200);
		adapter.stopGame();
	}
	
	@Test
	public void testMoveBackward() {
		adapter.initializeGame();
		adapter.loadMapFromFile(new File(map));
		assertTrue("A correct map was detected as incorrect", adapter.isCorrectMap());
		adapter.handleKeyPressN();
		assertEquals("Your map should not change when starting a new game", stringRepresentation, adapter.getStringRepresentationOfMap());
		adapter.handleKeyDownDownArrow();
		assertTrue("Your Towershould move backward when pressing down arrow", adapter.getTankYPosition(0) > 200);
		adapter.stopGame();
	}
	@Test
	public void testTurnClockwise() {
		adapter.initializeGame();
		adapter.loadMapFromFile(new File(map));
		assertTrue("A correct map was detected as incorrect", adapter.isCorrectMap());
		adapter.handleKeyPressN();
		assertEquals("Your map should not change when starting a new game", stringRepresentation, adapter.getStringRepresentationOfMap());
		adapter.handleKeyDownRightArrow();
		assertTrue("Your Towershould turn clockwise when pressing right arrow", adapter.getTankRotation(0) > 0);
		adapter.stopGame();
	}
	@Test
	public void testTurnCounterClockwise() {
		adapter.initializeGame();
		adapter.loadMapFromFile(new File(map));
		assertTrue("A correct map was detected as incorrect", adapter.isCorrectMap());
		adapter.handleKeyPressN();
		assertEquals("Your map should not change when starting a new game", stringRepresentation, adapter.getStringRepresentationOfMap());
		adapter.handleKeyDownLeftArrow();
		assertTrue("Your Towershould turn counter clockwise when pressing left arrow", adapter.getTankRotation(0) > 280);
		adapter.stopGame();
	}
	
	@Test
	public void testTowerdefensehot() {
		adapter.initializeGame();
		adapter.loadMapFromFile(new File(map));
		assertTrue("A correct map was detected as incorrect", adapter.isCorrectMap());
		adapter.handleKeyPressN();
		assertEquals("Your map should not change when starting a new game", stringRepresentation, adapter.getStringRepresentationOfMap());
		//assertEquals("The shots fired count should be 0", 0, adapter.getMapFiredShots());
		assertEquals("No shot entities should be present in the map", 0, adapter.getShotCount());
		adapter.handleKeyPressK();
		//assertEquals("After shooting the shots fired counter should increase (1)", 1, adapter.getMapFiredShots());
		assertEquals("Shot entitiy count should be 1", 1, adapter.getShotCount());
		adapter.handleKeyPressK();
		//assertEquals("After shooting the shots fired counter should increase (2)", 2, adapter.getMapFiredShots());
		assertEquals("Shot entitiy count should be 2", 2, adapter.getShotCount());
		adapter.handleKeyPressK();
		adapter.handleKeyPressK();
		//assertEquals("After shooting the shots fired counter should increase (4)", 4, adapter.getMapFiredShots());
		assertEquals("Shot entitiy count should be 4", 4, adapter.getShotCount());
		adapter.stopGame();
	}
	
}
