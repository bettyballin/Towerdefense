package tests.tutors.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Towerdefense.model.exceptions.SemanticException;
import Towerdefense.model.exceptions.SyntaxException;
import tests.adapter.TowerdefenseTestAdapterExtended1;
import Towerdefense.ui.Towerdefense;

public class SaveGameTest {

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
	public final void testSaveGame() {
		
		// initialize the game in MAINMENUSTATE
		adapter.initializeGame();
		
		// load the testmap for testing 'save a game'
		try {
			adapter.loadMapFromFileWithExceptions(new File("testmaps/extended1/correcte1"));
		} catch (SyntaxException e1) {
			assertTrue("The loaded map does not have syntax errors, your parser seems to be buggy", false);
		} catch (SemanticException e1) {
			assertTrue("The loaded map does not have semantic errors, your parser seems to be buggy", false);
		}
		
		assertTrue("The game has not been started in MAINMENUSTATE, current state id = "+adapter.getStateBasedGame().getCurrentStateID(), 
				Towerdefense.MAINMENUSTATE == adapter.getStateBasedGame().getCurrentStateID());

		// go into GAMEPLAYSTATE
		adapter.handleKeyPressN();
		assertTrue("The game is not in GAMEPLAYSTATE, current state id = "+adapter.getStateBasedGame().getCurrentStateID(), 
				Towerdefense.GAMEPLAYSTATE == adapter.getStateBasedGame().getCurrentStateID());
		
		String original = adapter.getStringRepresentationOfMap();
		
		// go into MENU
		adapter.handleKeyPressEscape();
		assertTrue("The state has not been changed. The game is in GAMEPLAYSTATE!"+adapter.getStateBasedGame().getCurrentStateID(), 
				Towerdefense.GAMEPLAYSTATE != adapter.getStateBasedGame().getCurrentStateID());
		
		// save the game into the file 'saves/autosave.Towerdefense'
		adapter.handleKeyPressS();
		
		try {
			// load file autosave.Towerdefense in folder saves
			// get current String representation
			File f = new File("saves/autosave.Towerdefense");
			adapter.loadMapFromFileWithExceptions(f);
			String current = adapter.getStringRepresentationOfMap();
			
			assertEquals("saved output is not equal, ", current, original);
			
			// delete file
			f.delete();
			
		} catch(NullPointerException e) {
			assertTrue("No file /saves/autosave.Towerdefense created!", false);
		} catch (SyntaxException e) {
			assertTrue("Your saved map was saved with syntax errors or not parsed correctly", false);
		} catch (SemanticException e) {
			assertTrue("Your saved map was saved with semantic errors or not parsed correctly", false);
		}
	}
}
