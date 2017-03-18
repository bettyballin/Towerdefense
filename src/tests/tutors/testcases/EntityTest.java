package tests.tutors.testcases;

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
		assertFalse("Tower spawns after clicking on something else than a towerTile",
				adapter.towerSpawnsAfterNotClickingOnTowerTile());
		assertTrue("Tower does not shoot enemy", adapter.towerShootsEnemy());
	}

	@Test
	public void testEnemy() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Spider enemy does not appear after creating the entity with an enemy factory", adapter.enemyDoesSpawn());
		assertTrue("Enemy does not move", adapter.enemyDoesMove());
		assertTrue("Enemy does not move on path", adapter.enemyMovesOnPath());
		assertTrue("Enemy does not disappear after being shot", adapter.enemyDiesAfterBeingShot());
	}
	
	@Test
	public void testLife() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Life entity does not exist", adapter.gameHasLife());
		assertTrue("Enemy does not move on path", adapter.enemyMovesOnPath());
		assertTrue("Life is the same after enemy went through homeTower", adapter.gameLosesLifeAfterEnemyThroughHomeTower());
	}
}
