package tests.students.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.exceptions.SemanticException;
import model.exceptions.SyntaxException;
import tests.adapter.TowerdefenseTestAdapterMinimal;

/**
 * Tests if a given map is correctly parsed.
 */
public class ParseMapTest {
	
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
	public final void testLoadMapFromFile() {
		
	}
	
	@Test
	public final void testStringRepresentationOfMap() {
		/*
		
			assertTrue("A correct map was detected as incorrect: " + map, adapter.isCorrectMap());
			assertNotNull("String representation of a correctly loaded map should never be null", stringRepresentation);
			assertEquals("String representation of " + map + " is wrong" , stringRepresentations[i], stringRepresentation);
		*/
	}


	@Test
	public final void testMapEntities() {
		/*
		adapter.loadMapFromFile(new File("testmaps/minimal/entityTest01"));
		assertTrue("A correct map was detected as incorrect", adapter.isCorrectMap());
		
		assertEquals("Incorrect Towercount", 5, adapter.getTankCount());
		assertEquals("Incorrect border count", 4, adapter.getBorderCount());
		assertEquals("Incorrect wall count", 5, adapter.getWallCount());
		assertEquals("Incorrect shot count", 7, adapter.getShotCount());
		assertEquals("Incorrect explosion count", 2, adapter.getExplosionCount());
		*/
	}
	
	
	
	@Test
	public final void testEntitiyValues() {
		
		adapter.loadMapFromFile(new File("testmaps/minimal/valueTest"));
		assertTrue("A correct map was detected as incorrect", adapter.isCorrectMap());
		
		// Maps infos
		assertEquals("Incorrect value of map background", "/assets/sandTexture.jpg", adapter.getMapBackgroundTexture());
		assertEquals("Incorrect value of map highscore file", "valueTest", adapter.getMapName());
		assertEquals("Incorrect value of next map", "/maps/map00", adapter.getMapNextMap());
		assertEquals("Incorrect value of map maximum duration", 100, adapter.getMapMaxDuration());
		assertEquals("Incorrect value of map elapsed time", 10, adapter.getMapElapsedTime());
		assertEquals("Incorrect value of map fired shots", 5, adapter.getMapFiredShots());
		// Towerdefense
		assertEquals("Incorrect value of Toweramount", 2, adapter.getTankCount());
		// PlayerTank
		assertEquals("Incorrect value of Towername", "PlayerOne", adapter.getTankName(0));
		assertEquals("Incorrect value of Towermaximum life", 1000, adapter.getTankMaxLife(0));
		assertEquals("Incorrect value of Toweractual life", 875, adapter.getTankActualLife(0));
		assertEquals("Incorrect value of Towermaximum shot amount", 10, adapter.getTankMaxShot(0));
		assertEquals("Incorrect value of Toweractual shot amount", 5, adapter.getTankActualShot(0));
		assertEquals("Incorrect value of Towermaximum mine amount", 3, adapter.getTankMaxMine(0));
		assertEquals("Incorrect value of Toweractual mine amount", 2, adapter.getTankActualMine(0));
		assertEquals("Incorrect value of Towerstrength", 30, adapter.getTowerdefensetrength(0));
		assertEquals("Incorrect value of Towerspeed", 5, adapter.getTowerdefensepeed(0));
		assertEquals("Incorrect value of Towerrotation", 0, adapter.getTankRotation(0));
		assertEquals("Incorrect value of Towerscale", 10, adapter.getTowerdefensecale(0));
		assertEquals("Incorrect value of Towerx position", 300, adapter.getTankXPosition(0));
		assertEquals("Incorrect value of Towery position", 200, adapter.getTankYPosition(0));
		// OpponentTank
		assertEquals("Incorrect value of Towername", "OpponentTank0", adapter.getTankName(1));
		assertEquals("Incorrect value of Towermaximum life",  30, adapter.getTankMaxLife(1));
		assertEquals("Incorrect value of Toweractual life", 11, adapter.getTankActualLife(1));
		assertEquals("Incorrect value of Towermaximum shot amount", 1, adapter.getTankMaxShot(1));
		assertEquals("Incorrect value of Toweractual shot amount", 1, adapter.getTankActualShot(1));
		assertEquals("Incorrect value of Towermaximum mine amount", 0, adapter.getTankMaxMine(1));
		assertEquals("Incorrect value of Toweractual mine amount", 0, adapter.getTankActualMine(1));
		assertEquals("Incorrect value of Towerstrength", 1, adapter.getTowerdefensetrength(1));
		assertEquals("Incorrect value of Towerspeed", 4, adapter.getTowerdefensepeed(1));
		assertEquals("Incorrect value of Towerrotation", 270, adapter.getTankRotation(1));
		assertEquals("Incorrect value of Towerscale", 10, adapter.getTowerdefensecale(1));
		assertEquals("Incorrect value of Towerx position", 100, adapter.getTankXPosition(1));
		assertEquals("Incorrect value of Towery position", 200, adapter.getTankYPosition(1));
		// Border
		assertEquals("Incorrect value of wall amount", 1, adapter.getBorderCount());
		assertEquals("Incorrect value of border x position", 400, adapter.getBorderXPosition(0));
		assertEquals("Incorrect value of border y position", 0, adapter.getBorderYPosition(0));
		assertEquals("Incorrect value of border x size", 800, adapter.getBorderXSize(0));
		assertEquals("Incorrect value of border y size", 0, adapter.getBorderYSize(0));
		// Wall
		assertEquals("Incorrect value of wall amount", 1, adapter.getWallCount());
		assertEquals("Incorrect value of wall maximum life", 100, adapter.getWallMaxLife(0));
		assertEquals("Incorrect value of wall actual life", 33, adapter.getWallActualLife(0));
		assertEquals("Incorrect value of wall rotation", 0, adapter.getWallRotation(0));
		assertEquals("Incorrect value of wall scale", 10, adapter.getWallScale(0));
		assertEquals("Incorrect value of wall x position", 100, adapter.getWallXPosition(0));
		assertEquals("Incorrect value of wall y position", 100, adapter.getWallYPosition(0));;
		// Shot
		assertEquals("Incorrect value of shot amount", 1, adapter.getShotCount());
		assertEquals("Incorrect value of shot strength", 5, adapter.getShotStrength(0));
		assertEquals("Incorrect value of shot rotation", 280, adapter.getShotRotation(0));
		assertEquals("Incorrect value of shot scale", 10, adapter.getShotScale(0));
		assertEquals("Incorrect value of shot x position", 50, adapter.getShotXPosition(0));
		assertEquals("Incorrect value of shot y position", 50, adapter.getShotYPosition(0));;
		// Explosion
		assertEquals("Incorrect value of explosion amount", 1, adapter.getExplosionCount());
		assertEquals("Incorrect value of explosion width", 25, adapter.getExplosionWidth(0));
		assertEquals("Incorrect value of explosion height", 40, adapter.getExplosionHeight(0));
		assertEquals("Incorrect value of explosion speed", 1, adapter.getExplosionSpeed(0));
		assertEquals("Incorrect value of explosion x position", 10, adapter.getExplosionXPosition(0));
		assertEquals("Incorrect value of explosion y position", 7, adapter.getExplosionYPosition(0));;
	}
	
	@Test
	public final void testSyntaxException() {
		
		boolean sytaxException;
		
		// No map info
		sytaxException = false;
		try {
			adapter.loadMapFromFileWithExceptions(new File("testmaps/minimal/incorrect01"));
			fail("Loading an syntactically incorrect map should throw a SyntaxException");
		} catch (SemanticException e) {
		} catch (SyntaxException e) {
			sytaxException = true;
		}
		assertTrue("Loading an syntactically incorrect map should throw a SyntaxException", sytaxException);
	}
	
}
