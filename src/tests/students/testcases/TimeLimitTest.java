package tests.students.testcases;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterExtended2;

public class TimeLimitTest {

	TowerdefenseTestAdapterExtended2  adapter;
	
	@Before
	public void setUp() {
		adapter = new TowerdefenseTestAdapterExtended2();
	}
	
	@After
	public void finish() {
		adapter.stopGame();
	}
	
	@Test
	public void testTimeLimit() {
		
		// initialize the game in MAINMENUSTATE
		adapter.initializeGame();
		assertTrue("The game has not been started in MAINMENUSTATE, current state id = "+adapter.getStateBasedGame().getCurrentStateID(), 
				0 == adapter.getStateBasedGame().getCurrentStateID());
		
		// go into GAMEPLAYSTATE
		adapter.handleKeyPressN();
		assertTrue("The game is not in GAMEPLAYSTATE, current state id = "+adapter.getStateBasedGame().getCurrentStateID(), 
				2 == adapter.getStateBasedGame().getCurrentStateID());
		
		adapter.runGame(1);
		assertTrue("The game is not in MAINMENUSTATE, current state id = "+adapter.getStateBasedGame().getCurrentStateID(), 
				0 == adapter.getStateBasedGame().getCurrentStateID());
		
	}
	
}
