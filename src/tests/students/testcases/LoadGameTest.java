package tests.students.testcases;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.adapter.TowerdefenseTestAdapterExtended1;

public class LoadGameTest {
	
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
	public final void testLoadGame() {
		
		adapter.loadSaveGameFromFile(new File(saveGame));
		
		assertTrue("A correct map of a loaded savegame was detected as incorrect", adapter.isCorrectMap());
		assertNotNull("The string representation of a map of a loaded savegame was null", adapter.getStringRepresentationOfMap());
		assertEquals("The string representation of the loaded savegame was incorrect", saveGameRepresentation, adapter.getStringRepresentationOfMap());
		
	}
	
	@Test
	public final void testLoadedValues() {
		
		adapter.loadMapFromFile(new File(saveGame));
		
		assertEquals("The value of the background picture loaded from the save game file was wrong", "/assets/sandTexture.jpg", adapter.getMapBackgroundTexture());
		assertEquals("The value of the next map loaded from the save game file was wrong", "null", adapter.getMapNextMap());
		assertEquals("The value of the fired shots loaded from the save game file was wrong", 6, adapter.getShotsFiredOfLoadedMap());
		assertEquals("The value of the passed time loaded from the save game file was wrong", 9788, adapter.getTimePassedOfLoadedMap());
		
		assertEquals(2, adapter.getTankCount());
		assertEquals(3, adapter.getShotCount());
	}
	
}
