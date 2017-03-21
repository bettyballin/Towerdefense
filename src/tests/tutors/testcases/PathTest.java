package tests.tutors.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterMinimal;

public class PathTest {

	TowerdefenseTestAdapterMinimal adapter;

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

		// go into GAMEPLAYSTATE
		adapter.handleKeyPressN();
		adapter.runGame(1);
		assertTrue("Other values than 0 and 1 detected in path", adapter.pathArrayContainsRightEntries());
		assertTrue("Path does not start at position x=0,y=0", adapter.pathArrayStartsAtX0Y0());
		assertTrue("Path does not end at position x=7,y=5", adapter.pathArrayEndsAtX7Y5());
		assertTrue("Path is not connected", adapter.pathArrayContainsPath());
		assertTrue("Game does not contain the same amount of pathTiles as the precomputed patharray",
				adapter.pathArrayContainsRightAmountOfPathTiles());
		assertTrue("Game does not contain the same amount of towerTiles as the precomputed patharray",
				adapter.pathArrayContainsRightAmountOfTowerTiles());
		assertTrue("Path tiles do not correspond to the 1s in the precomputed patharray",
				adapter.pathArrayCorrespondsToPathTilePositions());
		assertTrue("Tower tiles do not correspond to the 2s in the precomputed patharray",
				adapter.pathArrayCorrespondsToTowerTilePositions());
	}

}
