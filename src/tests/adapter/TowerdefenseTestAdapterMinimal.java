package tests.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import ui.Towerdefense;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.test.TestAppGameContainer;
import model.entities.Enemy;
import model.entities.Life;
import model.entities.PathTile;
import model.entities.Shoot;
import model.entities.Tower;
import model.entities.TowerTile;
import model.factory.EnemyFactory;
import model.factory.ShootFactory;
import model.factory.TowerFactory;
import model.factory.TowerTileFactory;
import model.path.Path;

/**
 * This is the test adapter for the minimal stage of completion. You <b>must</b>
 * implement the method stubs and match them to your concrete implementation.
 * Please read all the Javadoc of a method before implementing it. <br>
 * <strong>Important:</strong> this class should not contain any real game
 * logic, you should rather only match the method stubs to your game. <br>
 * Example: in {@link #isCorrectMap()} you may return the value
 * <i>Towerdefense.isCorrectMap()</i>, if you have a variable
 * <i>Towerdefense</i> and a map has before been loaded via
 * {@link #loadMapFromFile(File)}. What you mustn't do is to implement the
 * actual logic of the method in this class. <br>
 * <br>
 * If you have implemented the minimal stage of completion, you should be able
 * to implement all method stubs. The public and private JUnit tests for the
 * minimal stage of completion will be run on this test adapter. The other test
 * adapters will inherit from this class, because they need the basic methods
 * (like loading a map), too. <br>
 * <br>
 * The methods of all test adapters need to function without any kind of user
 * interaction.</br>
 * 
 * <i>Note:</i> All other test adapters will inherit from this class.
 * 
 * @see TowerdefenseTestAdapterExtended1
 * @see TowerdefenseTestAdapterExtended2
 * @see TowerdefenseTestAdapterExtended3
 */
public class TowerdefenseTestAdapterMinimal {

	Towerdefense Towerdefense; // erbt von StateBasedGame
	TestAppGameContainer app; // spezielle Variante des AppGameContainer,
								// welche keine UI erzeugt (nur fuer Tests!)
	boolean syntaxException; // gibt es Syntax-Fehler
	boolean semanticException; // gibt es Semantik-Fehler

	/**
	 * Verwenden Sie diesen Konstruktor zur Initialisierung von allem, was sie
	 * benoetigen
	 * 
	 * Use this constructor to set up everything you need.
	 */
	public TowerdefenseTestAdapterMinimal() {
		super();
		Towerdefense = null;
		syntaxException = true;
		semanticException = true;
	}

	/*
	 * ********* initialize, run, stop the game **********
	 */

	public StateBasedGame getStateBasedGame() {
		return Towerdefense;
	}

	/**
	 * Diese Methode initialisiert das Spiel im Test-Modus.
	 * 
	 * Sie muessen diese Methode erweitern.
	 */
	public void initializeGame() {

		// Setze den library Pfad abhaengig vom Betriebssystem
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/windows");
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/macosx");
		} else {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + "/native/" + System.getProperty("os.name").toLowerCase());
		}
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		System.err.println(System.getProperty("os.name") + ": " + System.getProperty("org.lwjgl.librarypath"));
		// Initialisiere das Spiel Towerdefense im Debug-Modus (ohne UI-Ausgabe)
		Towerdefense = new Towerdefense();

		try {
			app = new TestAppGameContainer(Towerdefense);
			app.setShowFPS(false);
			app.setDisplayMode(800, 600, false);
			Display.create();
			app.start(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Stop the game which is running in background
	 */
	public void stopGame() {
		if (app != null) {
			app.exit();
			app.destroy();
		}
		StateBasedEntityManager.getInstance().clearAllStates();
		Towerdefense = null;
	}

	/**
	 * Run the game for a specified duration
	 * 
	 * @param ms
	 *            duration of runtime of the game
	 */
	public void runGame(int ms) {
		if (Towerdefense != null && app != null) {
			try {
				app.updateGame(ms);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return true if background exists, else false
	 */
	public boolean backgroundExists() {

		if (Towerdefense == null) {
			System.err.println("The game Towerdefense has not been initialized, Towerdefense is null!");
			return false;
		}

		// get list of each entity in the game
		List<Entity> entities = new ArrayList<Entity>();
		entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		if (entities.size() == 0) {
			System.err.println("The game Towerdefense does not have any entities!");
			return false;
		}

		// get background entity
		for (Entity entity : entities) {
			if (entity.getID().contains("background")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return true if background is shown, else false
	 */
	public boolean backgroundIsVisible() {

		// get list of each entity in the game
		List<Entity> entities = new ArrayList<Entity>();
		entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		if (entities.size() == 0) {
			System.err.println("The game Towerdefense does not have any entities!");
			return false;
		}

		// get background entity
		for (Entity entity : entities) {
			if (entity.getID().contains("background")) {
				if (entity.getSize().x == 0 || entity.getSize().y == 0)
					return false;
				return true;
			}
		}
		return false;
	}

	/*
	 * *************************************************** ********************
	 * Path *************************
	 * ***************************************************
	 */

	/**
	 * @return true if path entity exists, false if not
	 */
	public boolean pathExists() {
		if (Towerdefense == null) {
			System.err.println("The game Towerdefense has not been initialized, Towerdefense is null!");
			return false;
		}
		Entity path = null;
		// try to get path entity from StateBasedEntityManager
		try {
			path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		} catch (Exception e) {
			System.err.println("The path entity does not exist!");
			return false;
		}
		if (path == null) {
			System.err.println("The path entity has not been initialized, path is null!");
			return false;
		}
		return true;
	}

	/**
	 * @return true if path array is of size 6x8, false if not
	 */
	public boolean pathArrayIs6x8() {
		Entity path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		int[][] pathArray = null;
		// try to get path array from Path
		try {
			pathArray = ((Path) path).getPathArray();
		} catch (Exception e) {
			System.err.println("Path array cannot be accessed by the method getPathArray()!");
			return false;
		}
		if (pathArray == null) {
			System.err.println("The path array has not been initialized, path array is null!");
			return false;
		}
		return (pathArray.length == 6 && pathArray[0].length == 8);
	}

	/**
	 * @return true if path array starts at the top left corner, false if not
	 */
	public boolean pathArrayStartsAtX0Y0() {
		Path path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		int[][] pathlist = path.getPathArray();
		return (pathlist[0][0] == 1);
	}

	/**
	 * @return true if path array ends at the bottom right corner, false if not
	 */
	public boolean pathArrayEndsAtX7Y5() {
		Path path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		int[][] pathlist = path.getPathArray();
		return (pathlist[5][7] == 1);
	}

	/**
	 * @return true if path array contains a connected path of 1s, false if not
	 */
	public boolean pathArrayContainsPath() {
		Path path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		int[][] pathlist = path.getPathArray();

		boolean hasPath = false;
		// go through each entry of the path array and check if path is
		// connected
		for (int column = 0; column < 5; column++) {
			for (int row = 0; row < 7; row++) {
				// if current position is occupied by a pathTile
				if (pathlist[column][row] == 1) {
					hasPath = false;
					// look at neighbors to determine if there is a path
					if (pathlist[column + 1][row] == 1 || pathlist[column][row + 1] == 1) {
						hasPath = true;
					}
				}
				if (!hasPath)
					return false;
			}
		}
		return hasPath;
	}

	/**
	 * @return true if there is a pathTile entity for each 1 in pathTile array
	 */
	public boolean pathArrayContainsRightAmountOfPathTiles() {
		// get list of each entity in the game
		List<Entity> entities = new ArrayList<Entity>();
		entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		if (entities.size() == 0) {
			System.err.println("The game Towerdefense does not have any entities!");
			return false;
		}

		Path path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");

		// get the path array
		int[][] pathlist = path.getPathArray();

		// get each path tile entity that is currently in the game
		List<Entity> pathTiles = new ArrayList<Entity>();
		for (Entity entity : entities) {
			if (entity.getID().startsWith("pathTile")) {
				pathTiles.add(entity);
			}
		}
		// go through each entry of the path array and count the 1s
		int ones = 0;
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				if (pathlist[column][row] == 1)
					ones++;
			}
		}
		return pathTiles.size() == ones;
	}

	/**
	 * @return true if path array does contain the right elements, false if not
	 */
	public boolean pathArrayContainsRightEntries() {
		Path path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		int[][] pathlist = path.getPathArray();

		// go through each entry of the path array and check if the path has
		// only
		// 0s, 1s or 2s
		boolean rightEntry = false;
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				rightEntry = false;
				if (pathlist[column][row] >= 0 && pathlist[column][row] <= 2)
					rightEntry = true;
				if (!rightEntry)
					return false;
			}
		}
		return rightEntry;
	}

	/**
	 * @return true if path tiles have the same position as in the path array,
	 *         false if not
	 */
	public boolean pathArrayCorrespondsToPathTilePositions() {
		Path path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// get the path array
		int[][] pathArray = path.getPathArray();

		// get each path tile entity that is currently in the game
		List<PathTile> pathTiles = new ArrayList<PathTile>();
		for (Entity entity : entities) {
			if (entity.getID().startsWith("pathTile")) {
				pathTiles.add((PathTile) entity);
			}
		}
		// go through each entry of the path array
		boolean rightEntry = false;
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				// if there is a 1 at the certain position
				if (pathArray[column][row] == 1) {
					rightEntry = false;
					// look for pathTile that has the same position
					for (PathTile tile : pathTiles) {
						if (tile.getPosition().x == row * 100 + 50 && tile.getPosition().y == column * 100 + 50) {
							rightEntry = true;
							break;
						}
					}
					if (!rightEntry)
						return false;
				}
			}
		}
		return rightEntry;
	}

	/**
	 * @return true if path tiles have the same position as in the path array,
	 *         false if not
	 */
	public boolean pathArrayCorrespondsToTowerTilePositions() {
		Path path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		try {
			path.createTowerTileArray();
		} catch (Exception e) {
			System.err.println("The method createTowerTileArray() does not exist in Path");
			return false;
		}
		// get the path array
		int[][] pathArray = path.getPathArray();
		// get each path tile entity that is currently in the game
		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity entity : entities) {
			if (entity.getID().startsWith("towerTile")) {
				towerTiles.add((TowerTile) entity);
			}
		}
		// go through each entry of the path array
		boolean rightEntry = false;
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				// if there is a 2 at the certain position
				if (pathArray[column][row] == 2) {
					rightEntry = false;
					// look for towerTile that has the same position
					for (TowerTile tile : towerTiles) {
						if (tile.getPosition().x == row * 100 + 50 && tile.getPosition().y == column * 100 + 50) {
							rightEntry = true;
							break;
						}
					}
					if (!rightEntry)
						return false;
				}
			}
		}
		return rightEntry;
	}

	/**
	 * @return true if path array does contain the right elements, false if not
	 */
	public boolean towerTilesAreNextToPath() {
		Path path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		path.createTowerTileArray();
		int[][] pathArray = path.getPathArray();
		// go through every entry of the updated pathArray
		// and see if the 2s are placed correctly
		boolean rightEntries = false;
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				rightEntries = true;
				if (row < 7) {
					if (pathArray[column][row + 1] == 1 && pathArray[column][row] == 1) {
						if (column > 0 && row < 7)
							if (pathArray[column - 1][row + 1] != 2)
								return false;
						if (column < 5)
							if (pathArray[column + 1][row] != 2)
								return false;
					}
				}
				if (column < 5) {
					if (pathArray[column + 1][row] == 1 && pathArray[column][row] == 1) {
						if (row < 7)
							if (pathArray[column][row + 1] != 2)
								return false;
						if (column < 5 && row > 0)
							if (pathArray[column + 1][row - 1] != 2)
								return false;
					}
				}
			}
		}
		return rightEntries;
	}

	/**
	 * @return true if there is a towerTile entity for each 2 in pathArray,
	 *         false if not
	 */
	public boolean pathArrayContainsRightAmountOfTowerTiles() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		Path path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");

		List<Entity> towerTiles = new ArrayList<Entity>();
		int[][] pathlist = path.getPathArray();
		// add the towerTiles (2s) into the array
		path.createTowerTileArray();
		for (Entity entity : entities) {
			if (entity.getID().startsWith("towerTile")) {
				towerTiles.add(entity);
			}
		}
		// count each occurence of 2 in the array
		int twos = 0;
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				if (pathlist[column][row] == 2)
					twos++;
			}
		}
		return towerTiles.size() == twos;
	}

	/*
	 * ***************************************************
	 * ********************** Tower **********************
	 * ***************************************************
	 */

	/*
	 * @return true if hometower is on the last pathtile, false if not
	 */
	public boolean homeTowerIsOnLastTile() {
		Entity homeTower = null;
		try {
			homeTower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE,
					"homeTower");
		} catch (Exception e) {
			System.err.println("homeTower class does not exist");
			return false;
		}

		if (homeTower == null) {
			System.err.println("homeTower entity does not exist in the StatebasedEntityManager");
			return false;
		}
		// check if homeTower is located somewhere on the last pathTile
		return (homeTower.getPosition().x >= 700 && homeTower.getPosition().x <= 800 && homeTower.getPosition().y >= 500
				&& homeTower.getPosition().y <= 600);
	}

	/*
	 * @return true if a tower spawns after clicking on a towerTile, false if
	 * not
	 */
	public boolean towerSpawnsAfterClickingOnTowerTile() {
		List<Entity> entities = new ArrayList<Entity>();
		TowerTile towerTile = null;
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		} else {
			System.err.println("The game Towerdefense has not been initialized, Towerdefense is null!");
			return false;
		}
		if (entities.size() == 0) {
			System.err.println("There are no entities in the StateBasedEntityManager!");
			return false;
		}
		int tower = 0;
		for (Entity e : entities) {
			// get amount of tower already in game
			if (e.getID().endsWith("Tower")) {
				tower++;
			}
			// get a random tower tile for testing
			if (e.getID().startsWith("towerTile")) {
				towerTile = (TowerTile) e;
			}
		}
		if (towerTile == null) {
			System.err.println("There are no towerTile entities in the StateBasedEntityManager!");
			return false;
		}
		// put mouse over towerTile and click
		handleMouseOverEntity(towerTile);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(1);
		// count occurrences of tower again
		int tower2 = 0;
		for (Entity e : entities) {
			if (e.getID().endsWith("Tower")) {
				tower2++;
			}
		}
		return tower + 1 == tower2;
	}

	/*
	 * @return true if tower spawns after clicking on something else than a
	 * towertile, false if not
	 */
	public boolean towerSpawnsAfterNotClickingOnTowerTile() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		Entity notTowerTile = null;
		int tower = 0;
		for (Entity e : entities) {
			// count the amount of towers in the game
			if (e.getID().endsWith("Tower")) {
				tower++;
			}
			// test if tower spawns if we are over a pathtile
			if (e.getID().startsWith("pathTile")) {
				notTowerTile = e;
			}
		}
		if (notTowerTile == null) {
			System.err.println("There are no pathTile entities in the game");
			return false;
		}

		// click on pathTile
		handleMouseOverEntity(notTowerTile);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);

		// return false if amount of towers in game increases
		int tower2 = 0;
		for (Entity e : entities) {
			if (e.getID().endsWith("Tower")) {
				tower2++;
			}
		}
		return tower + 1 == tower2;
	}

	/*
	 * @return true if tower detects enemy, false if not
	 */
	public boolean towerShootsOnEnemy() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// create new spider enemy entity
		EnemyFactory enemyFactory = new EnemyFactory("spider");
		Entity enemy = enemyFactory.createEntity();
		if (enemy == null) {
			System.err.println("EnemyFactory does not create an enemy");
			return false;
		}
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);

		Tower tower = null;
		List<PathTile> pathTiles = new ArrayList<PathTile>();
		for (Entity e : entities) {
			// build one tower on first towerTile in entity list
			if (tower == null && e.getID().startsWith("towerTile")) {
				TowerFactory towerFactory = new TowerFactory("bulletTower", e.getPosition());
				tower = (Tower) towerFactory.createEntity();
				StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, tower);
			}
			if (e.getID().startsWith("pathTile")) {
				pathTiles.add((PathTile) e);
			}
		}
		if (tower == null) {
			System.err.println("There was a problem with creating a tower with a TowerFactory");
			return false;
		}

		Vector2f nearTower = null;
		// find a pathTile which is near the tower
		for (PathTile tile : pathTiles) {
			if (tower.getPosition().x >= tile.getPosition().x - 100
					&& tower.getPosition().x <= tile.getPosition().x + 100
					&& tower.getPosition().y >= tile.getPosition().y - 100
					&& tower.getPosition().y <= tile.getPosition().y + 100) {
				nearTower = tower.getPosition();
				break;
			}
		}
		if (nearTower == null) {
			System.err.println("There is no pathTile found in the near of a certain towerTile");
			return false;
		}
		// put enemy in the range of the tower
		enemy.setPosition(nearTower);
		int time = 100000;
		// try to run the game until tower shoots
		while (StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "shoot") == null
				&& time > 0) {
			runGame(100);
			time--;
		}
		return time > 0;
	}

	/*
	 * 
	 * 
	 * /* ***************************************************
	 * ********************** Enemy **********************
	 * ***************************************************
	 */

	/*
	 * @return true if there is an enemy entity after calling enemyFactory,
	 * false if not
	 */
	public boolean enemyDoesSpawn() {
		EnemyFactory factory = new EnemyFactory("spider");
		return factory.createEntity() != null;
	}

	/*
	 * @return true if the enemy moves while running the game, false if not
	 */
	public boolean enemyDoesMove() {
		EnemyFactory factory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) factory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);

		float x = enemy.getPosition().x;
		float y = enemy.getPosition().y;
		runGame(1);

		// check if position is not the same after running the game for 1 ms
		return (x != enemy.getPosition().x || y != enemy.getPosition().y);
	}

	/*
	 * @return true if enemy moves on path, false if not
	 */
	public boolean enemyMovesOnPath() {
		// create spider enemy and add it to StateBasedEntityManager
		EnemyFactory factory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) factory.createEntity();

		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		List<PathTile> pathTiles = new ArrayList<PathTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("pathTile"))
				pathTiles.add((PathTile) e);
		}

		int time = 100;
		boolean isOnPath = false;

		// run game for 1.5 seconds
		while (time <= 500) {
			runGame(time);
			isOnPath = false;
			// check if enemy moves on the path or on the border of it
			for (PathTile tile : pathTiles) {
				if (enemy.getPosition().x >= tile.getPosition().x - 51
						&& enemy.getPosition().x <= tile.getPosition().x + 51
						&& enemy.getPosition().y >= tile.getPosition().y - 51
						&& enemy.getPosition().y <= tile.getPosition().y + 51) {
					isOnPath = true;
				}
			}
			if (!isOnPath)
				return false;
			time += 100;
		}
		return isOnPath;
	}

	/*
	 * @return true if enemy dies after being hit by a shot, false if not
	 */
	public boolean enemyDiesAfterBeingShot() {
		EnemyFactory factory = new EnemyFactory("spider");
		Enemy spider = (Enemy) factory.createEntity();

		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, spider);
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// create tower for shoot
		TowerFactory towerFactory = new TowerFactory("bulletTower", new Vector2f(100, 100));
		Tower tower = (Tower) towerFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, tower);

		// create shoot for shooting enemy, using the previously created tower
		// and setting shoot rotation to 0
		ShootFactory shootFactory = new ShootFactory(tower, 0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);

		int entitySize = entities.size();
		// set position of shoot to position of spider to cause a collision
		shoot.setPosition(spider.getPosition());
		runGame(100);

		// check if we lost one entity - the enemy entity created before
		return (entitySize - 1 == entities.size());
	}

	/*
	 * 
	 * 
	 * /* ***************************************************
	 * ********************** Life **********************
	 * ***************************************************
	 */

	/*
	 * @return true if there is a life entity, false if not
	 */
	public boolean gameHasLife() {
		try {
			Life life = (Life) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "life");
			return true;
		} catch (Exception e) {
			System.err.println("The game does not have a class called Life");
			return false;
		}
	}

	/*
	 * @return true if there is a life entity false if not
	 */
	public boolean gameLosesLifeAfterEnemyThroughHomeTower() {
		Life life = (Life) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "life");

		EnemyFactory factory = new EnemyFactory("spider");
		Enemy spider = (Enemy) factory.createEntity();

		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, spider);

		runGame(1);
		int lifeCount = life.getLife();
		// set enemy position to the edge of the display (simulating that the
		// enemy went through the hometower and reached the end of the path)
		Vector2f outOfDisplay = new Vector2f(801, 600);
		spider.setPosition(outOfDisplay);

		runGame(1);

		// check if life has decreased by 1
		return lifeCount - 1 == life.getLife();
	}

	/*
	 * ***************************************************
	 * ********************** Input **********************
	 * ***************************************************
	 */

	/*
	 * @param updatetime: time until updateGame is called
	 * 
	 * @param input: z.B. Input.KEY_K, Input.KEY_L
	 */
	public void handleKeyPressed(int updatetime, Integer input) {
		if (Towerdefense != null && app != null) {
			app.getTestInput().setKeyPressed(input);
			try {
				app.updateGame(updatetime);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method should emulate the pressing of the n key. This should start
	 * the game.
	 * 
	 */
	public void handleKeyPressN() {
		handleKeyPressed(0, Input.KEY_N);
	}

	/**
	 * This method should emulate the pressing of the esc key. This should end
	 * the game and change into the main menu.
	 */
	public void handleKeyPressESC() {
		handleKeyPressed(0, Input.KEY_ESCAPE);
	}

	/**
	 * This method moves the mouse over a certain entity
	 * 
	 * @param e
	 *            Entity to move the mouse over
	 */
	public void handleMouseOverEntity(Entity e) {
		Float x = e.getPosition().x;
		Float y = e.getPosition().y;
		app.getTestInput().setMouseX(x.intValue());
		app.getTestInput().setMouseY(y.intValue());
	}
}
