package tests.students.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterExtended2;

public class OptionTest {

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
		
		assertTrue("There is no class called Options", adapter.classOptionsExists());
		assertTrue("Class Options does not have a method called 'getDifficulty()'", adapter.optionsHaveDifficulty());
		
		adapter.handleKeyPressE();
		assertEquals(
				"The game is not in OPTIONSSTATE after pressing E, current state id = "
						+ adapter.getStateBasedGame().getCurrentStateID(),
				2, adapter.getStateBasedGame().getCurrentStateID());
		assertTrue("Life is not less when difficulty gets higher", adapter.lifeIsLessInHigherDifficulty());
		assertTrue("First enemy does not have more life when difficulty gets higher",
				adapter.firstEnemyHasMoreLifeInHigherDifficulty());
		assertTrue("Second enemy does not have more speed when difficulty gets higher",
				adapter.secondEnemyIsFasterInHigherDifficulty());

	}
}
