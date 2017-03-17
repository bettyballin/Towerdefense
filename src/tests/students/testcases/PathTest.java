package tests.students.testcases;

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
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Background is not visible", adapter.backgroundVisible());
		assertTrue("Path array does not have the size 6x8", adapter.pathArrayIs6x8());
		assertTrue("Other values than 0 and 1 detected in path", adapter.pathArrayContainsOnly0and1());
		assertTrue("Path does not start at position x=0,y=0", adapter.pathArrayStartsAtX0Y0());
		assertTrue("Path does not end at position x=7,y=5", adapter.pathArrayEndsAtX7Y5());
		assertTrue("Path is not connected", adapter.pathArrayContainsPath());
		assertTrue("Game does not contain the same amount of pathtiles as the precomputed patharray",
				adapter.pathArrayContainsRightAmountOfPathTiles());
		assertTrue("Other values than 0,1 and 2 are detected in towertile array",
				adapter.towerTileArrayContainsOnly012());
		assertTrue("Tower tiles are not next to path tiles", adapter.towerTilesAreNextToPath());
		assertTrue("Game does not contain the same amount of towertiles as the precomputed patharray",
				adapter.pathArrayContainsRightAmountOfTowerTiles());
		adapter.stopGame();
	}

}
