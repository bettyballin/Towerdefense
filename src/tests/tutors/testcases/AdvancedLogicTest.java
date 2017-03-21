package tests.tutors.testcases;

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
		assertTrue("Wave entity does not exist", adapter.waveEntityExists());
		assertEquals("Wave does not spawn enemies correctly", 3, adapter.enemiesSpawnInWaves(3));
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertEquals("Wave does not spawn enemies correctly", 4, adapter.enemiesSpawnInWaves(4));
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertEquals("Wave does not spawn enemies correctly", 10, adapter.enemiesSpawnInWaves(10));
	}

	@Test
	public void testUpdate() {
		adapter.initializeGame();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower strength of first tower type",
				adapter.updateButtonIncreasesFirstTowerStrength());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower strength of second tower type",
				adapter.updateButtonIncreasesSecondTowerStrength());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower speed of first tower type",
				adapter.updateButtonIncreasesFirstTowerSpeed());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower speed of second tower type",
				adapter.updateButtonIncreasesSecondTowerSpeed());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower range of first tower type",
				adapter.updateButtonIncreasesFirstTowerRange());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower range of second tower type",
				adapter.updateButtonIncreasesSecondTowerRange());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Update button does not increase tower slowdown of second tower",
				adapter.updateButtonIncreasesSecondTowerSlowdown());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertFalse("Update button does increase tower slowdown of first tower",
				adapter.updateButtonIncreasesFirstTowerSlowdown());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Delete button does not delete tower", adapter.deleteButtonDeletesTower());
		adapter.handleKeyPressESC();
		adapter.handleKeyPressN();
		assertTrue("Deleting a tower does not result in having more money", adapter.deleteButtonIncreasesMoney());
	}
}
