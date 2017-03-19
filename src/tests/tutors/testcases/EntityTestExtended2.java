package tests.tutors.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterExtended2;

public class EntityTestExtended2 {

	TowerdefenseTestAdapterExtended2 adapter;

	@Before
	public void setUp() {
		adapter = new TowerdefenseTestAdapterExtended2();
	}

	@After
	public void finish() {
		adapter.stopGame();
	}

	@Test
	public void testTower() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Second tower type does not slow down enemies", adapter.secondTowerTypeSlowsDownEnemies());
		assertTrue("Selection buttons do not appear while mouse over tower tile", adapter.towerSelectionButtonsAppear());
		assertTrue("Selection buttons do not work for first tower", adapter.towerSelectionWorksForFirstTower());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Selection buttons do not work for second tower", adapter.towerSelectionWorksForSecondTower());
	}

	@Test
	public void testEnemy() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Second type of enemy is not faster than first type", adapter.secondEnemyTypeIsFasterThanFirst());
	}
}
