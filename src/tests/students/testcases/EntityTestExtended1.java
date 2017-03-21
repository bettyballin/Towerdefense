package tests.students.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterExtended1;

public class EntityTestExtended1 {

	TowerdefenseTestAdapterExtended1 adapter;

	@Before
	public void setUp() {
		adapter = new TowerdefenseTestAdapterExtended1();
	}

	@After
	public void finish() {
		adapter.stopGame();
	}

	@Test
	public void testTower() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Tower does not have a strength attribute", adapter.towerHasStrength());
		assertTrue("Tower does not decrease the life attribute of enemy after shooting it", adapter.shootDecreasesLifeOfEnemy());
	}

	@Test
	public void testEnemy() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Enemy does not move on path", adapter.enemyMovesInCenterOfPath());
		assertTrue("Enemy does not have a life attribute", adapter.enemyHasLife());
		assertTrue("Explosion entity does not exist", adapter.explosionEntityExists());
	}
	
	@Test
	public void testMoney() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Money does not increase after enemy dies", adapter.moneyIncreasesAfterEnemyDies());
		assertTrue("Money does not decrease after building a tower", adapter.moneyDecreasesAfterBuildingTower());
	}
	
	@Test
	public void testPath() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Path is not generated randomly", adapter.pathIsGeneratedRandomly());
	}
}
