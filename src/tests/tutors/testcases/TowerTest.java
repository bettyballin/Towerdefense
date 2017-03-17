package tests.tutors.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterMinimal;

public class TowerTest {

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
	public void testLimitedAmmo() {
		adapter.initializeGame();
		adapter.handleKeyPressN();/*
		assertEquals("The maximum ammo amount the player has should be 3", 3, adapter.getLimitedAmmoAmount());
		assertEquals("The player should have 2 shots lefts", 2, adapter.getAmmoLeft());
		assertEquals("No shot entities should be present", 0, adapter.getShotCount());
		*/
	}
	
}
