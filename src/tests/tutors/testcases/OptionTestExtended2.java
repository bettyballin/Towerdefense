package tests.tutors.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterExtended2;

public class OptionTestExtended2 {

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
	public void testOptions() {
		adapter.initializeGame();
		adapter.handleKeyPressE();
		assertEquals("The game is not in OPTIONSSTATE after pressing E, current state id = "
				+ adapter.getStateBasedGame().getCurrentStateID(),
		2, adapter.getStateBasedGame().getCurrentStateID());
	}
}
