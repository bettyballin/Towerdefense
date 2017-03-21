package tests.students.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterExtended3;

public class AdvancedLogicTest {

	TowerdefenseTestAdapterExtended3 adapter;

	@Before
	public void setUp() {
		adapter = new TowerdefenseTestAdapterExtended3();
	}

	@After
	public void finish() {
		adapter.stopGame();
	}

	@Test
	public void testWaves() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertEquals("Wave does not spawn enemies correctly", 3, adapter.enemiesSpawnInWaves(3));
	}

	@Test
	public void testUpdate() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower strength of first tower type",
				adapter.updateButtonIncreasesFirstTowerStrength());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower speed of first tower type",
				adapter.updateButtonIncreasesFirstTowerSpeed());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower range of first tower type",
				adapter.updateButtonIncreasesFirstTowerRange());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower slowdown of second tower",
				adapter.updateButtonIncreasesSecondTowerSlowdown());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Delete button does not delete tower", adapter.deleteButtonDeletesTower());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Deleting a tower does not result in having more money", adapter.deleteButtonIncreasesMoney());
	}
}
