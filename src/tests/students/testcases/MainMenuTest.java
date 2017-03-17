package tests.students.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterMinimal;
import ui.Towerdefense;

public class MainMenuTest {

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
	public void testNewGame() {
		// initialize the game in MAINMENUSTATE
		adapter.initializeGame();
		assertTrue(
				"The game has not been started in MAINMENUSTATE, current state id = "
						+ adapter.getStateBasedGame().getCurrentStateID(),
				0 == adapter.getStateBasedGame().getCurrentStateID());

		// go into GAMEPLAYSTATE
		adapter.handleKeyPressN();
		assertTrue(
				"The game is not in GAMEPLAYSTATE after pressing N, current state id = "
						+ adapter.getStateBasedGame().getCurrentStateID(),
				1 == adapter.getStateBasedGame().getCurrentStateID());

		// run game for 1 ms
		adapter.runGame(1);
		assertTrue(
				"The game is not in GAMEPLAYSTATE after running for 1 ms, current state id = "
						+ adapter.getStateBasedGame().getCurrentStateID(),
				adapter.getStateBasedGame().getCurrentStateID() == Towerdefense.GAMEPLAYSTATE);
		
		// end game and return to MAINMENUSTATE
		adapter.handleKeyPressESC();
		assertTrue(
				"The game does not return to MAINMENUSTATE after pressing ESC, current state id = "
						+ adapter.getStateBasedGame().getCurrentStateID(),
				0 == adapter.getStateBasedGame().getCurrentStateID());
		adapter.stopGame();
	}
}
