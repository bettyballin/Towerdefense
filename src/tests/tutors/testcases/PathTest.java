package tests.tutors.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterMinimal;

public class PathTest {

	TowerdefenseTestAdapterMinimal  adapter;
	
	@Before
	public void setUp() {
		adapter = new TowerdefenseTestAdapterMinimal();
	}
	
	@After
	public void finish() {
		adapter.stopGame();
	}
	
	@Test
	public void testGameInit() {
		
		// initialize the game in MAINMENUSTATE
		adapter.initializeGame();
		assertTrue("The game has not been started in MAINMENUSTATE, current state id = "+adapter.getStateBasedGame().getCurrentStateID(), 
				0 == adapter.getStateBasedGame().getCurrentStateID());
		
		// go into GAMEPLAYSTATE
		adapter.handleKeyPressN();
		assertTrue("The game is not in GAMEPLAYSTATE after pressing N, current state id = "+adapter.getStateBasedGame().getCurrentStateID(), 
				1 == adapter.getStateBasedGame().getCurrentStateID());
		
		adapter.runGame(1);
		assertTrue("Background is not visible",adapter.backgroundVisible());
		assertTrue("Other values than 0 and 1 detected in path", adapter.pathArrayContainsOnly0and1());
		assertTrue("model.path does not start at position x=0,y=0", adapter.pathArrayStartsAtX0Y0());
		assertTrue("model.path does not end at position x=7,y=5", adapter.pathArrayEndsAtX7Y5());
		assertTrue("model.path is not connected", adapter.pathArrayContainsPath());
		assertTrue("Game does not contain the same amount of pathtiles as the precomputed patharray", adapter.pathArrayContainsRightAmountOfTowerTiles());
		adapter.handleKeyPressESC();
		assertTrue("The game does not return to MAINMENUSTATE after pressing ESC, current state id = "+adapter.getStateBasedGame().getCurrentStateID(), 
				0 == adapter.getStateBasedGame().getCurrentStateID());
	}
	
}
