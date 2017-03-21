package tests.students.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterMinimal;

public class EntityTest {

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
	public void testTower() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Home tower is not on the last path tile", adapter.homeTowerIsOnLastTile());
		assertTrue("Tower does not spawn after clicking on tower tile", adapter.towerSpawnsAfterClickingOnTowerTile());
	}

}
