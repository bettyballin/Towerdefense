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
	 * *************************************************** ********* initialize,
	 * run, stop the game **********
	 * ***************************************************
	 */

	public StateBasedGame getStateBasedGame() {
		return Towerdefense;
	}

	/**
	 * Diese Methode initialisiert das Spiel im Debug-Modus, d.h. es wird ein
	 * AppGameContainer gestartet, der keine Fenster erzeugt und aktualisiert.
	 * 
	 * Sie muessen diese Methode erweitern
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
	 * Stoppe das im Hintergrund laufende Spiel
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
	 * Run the game for a specified duration. Laesst das Spiel fuer eine
	 * angegebene Zeit laufen
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
	 * @return true if Background is shown, else false
	 */
	public boolean backgroundVisible() {

		if (Towerdefense == null)
			System.err.println("The game Towerdefense has not been initialized, Towerdefense is null!");

		List<Entity> entities = new ArrayList<Entity>();

		if (Towerdefense != null)
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		boolean backgroundVisible = false;

		for (Entity entity : entities) {
			if (entity.toString().contains("Background")) {
				backgroundVisible = true;
			}
		}

		return backgroundVisible;
	}

	/*
	 * *************************************************** ******************
	 * Tupel model.path ********************
	 * ***************************************************
	 */

	/**
	 * @return true if path array is of size 6x8, false if not
	 */
	public boolean pathArrayIs6x8() {
		Path path = null;
		if (Towerdefense != null)
			path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		if (path == null)
			return false;

		int[][] pathlist = path.getPathArray();

		if (pathlist == null)
			return false;
		else
			return (pathlist.length == 6 && pathlist[0].length == 8);
	}

	/**
	 * @return true if path array starts at position 0.0, false if not
	 */
	public boolean pathArrayStartsAtX0Y0() {
		Path path = null;
		if (Towerdefense != null)
			path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		if (path == null)
			return false;

		int[][] pathlist = path.getPathArray();

		if (pathlist == null)
			return false;
		else
			return (pathlist[0][0] == 1);
	}

	/**
	 * @return true if path array ends at position 7.5, false if not
	 */
	public boolean pathArrayEndsAtX7Y5() {
		Path path = null;
		if (Towerdefense != null)
			path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		if (path == null)
			return false;

		int[][] pathlist = path.getPathArray();

		if (pathlist == null)
			return false;
		else
			return (pathlist[5][7] == 1);
	}

	/**
	 * @return true if path array does contain a connected path of 1's, false if
	 *         not
	 */
	public boolean pathArrayContainsPath() {
		Path path = null;
		if (Towerdefense != null)
			path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		if (path == null)
			return false;

		int[][] pathlist = path.getPathArray();

		if (pathlist == null)
			return false;
		else {
			boolean hasPath = true;
			for (int column = 0; column < 5; column++) {
				for (int row = 0; row < 7; row++) {
					if (pathlist[column][row] == 1) {
						if (pathlist[column + 1][row] != 1 && pathlist[column][row + 1] != 1) {
							hasPath = false;
						}
					}
				}
			}
			return hasPath;
		}
	}

	/**
	 * @return true if there is a pathTile entity for each 1 in pathTile array
	 */
	public boolean pathArrayContainsRightAmountOfPathTiles() {
		List<Entity> entities = new ArrayList<Entity>();
		Path path = null;
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
			path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		}
		if (path == null)
			return false;

		List<Entity> pathTiles = new ArrayList<Entity>();

		int[][] pathlist = path.getPathArray();

		if (pathlist == null)
			return false;
		for (Entity entity : entities) {
			if (entity.getID().startsWith("pathTile")) {
				pathTiles.add(entity);
			}
		}
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
	public boolean pathArrayContainsOnly0and1() {
		Path path = null;
		if (Towerdefense != null)
			path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		if (path == null)
			return false;

		int[][] pathlist = path.getPathArray();

		if (pathlist == null)
			return false;
		else {
			boolean rightEntry = true;
			for (int column = 0; column < 6; column++) {
				for (int row = 0; row < 8; row++) {
					if (pathlist[column][row] < 0 || pathlist[column][row] > 2)
						rightEntry = false;
				}
			}
			return rightEntry;
		}
	}

	/**
	 * @return true if path array does contain the right elements, false if not
	 */
	public boolean towerTileArrayContainsOnly012() {
		Path path = null;
		if (Towerdefense != null)
			path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		if (path == null)
			return false;

		int[][] pathlist = path.getPathArray();

		if (pathlist == null)
			return false;
		else {
			path.createTowerTileArray();
			pathlist = path.getPathArray();
			boolean rightEntry = true;
			for (int column = 0; column < 6; column++) {
				for (int row = 0; row < 8; row++) {
					if (pathlist[column][row] < 0 || pathlist[column][row] > 2)
						rightEntry = false;
				}
			}
			return rightEntry;
		}
	}

	/**
	 * @return true if path array does contain the right elements, false if not
	 */
	public boolean towerTilesAreNextToPath() {
		Path path = null;
		if (Towerdefense != null)
			path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		if (path == null)
			return false;

		int[][] pathlist = path.getPathArray();

		if (pathlist == null)
			return false;
		else {
			path.createTowerTileArray();
			pathlist = path.getPathArray();
			if (pathlist == null || pathlist.length == 0)
				return false;
			for (int column = 0; column < 6; column++) {
				for (int row = 0; row < 8; row++) {
					if (row < 7) {
						if (pathlist[column][row + 1] == 1 && pathlist[column][row] == 1) {
							if (column > 0 && row < 7)
								if (pathlist[column - 1][row + 1] != 2)
									return false;
							if (column < 5)
								if (pathlist[column + 1][row] != 2)
									return false;
						}
					}
					if (column < 5) {
						if (pathlist[column + 1][row] == 1 && pathlist[column][row] == 1) {
							if (row < 7)
								pathlist[column][row + 1] = 2;
							if (column < 5 && row > 0)
								pathlist[column + 1][row - 1] = 2;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * @return true if there is a towerTile entity for each 2 in pathTile array,
	 *         false if not
	 */
	public boolean pathArrayContainsRightAmountOfTowerTiles() {
		List<Entity> entities = new ArrayList<Entity>();
		Path path = null;
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
			path = (Path) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "path");
		}
		if (path == null)
			return false;

		List<Entity> towerTiles = new ArrayList<Entity>();
		int[][] pathlist = path.getPathArray();

		if (pathlist == null)
			return false;
		path.createTowerTileArray();
		for (Entity entity : entities) {
			if (entity.getID().startsWith("towerTile")) {
				towerTiles.add(entity);
			}
		}
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
	 * @return true if there is a towerTile entity for each 2 in pathTile array,
	 * false if not
	 */
	public boolean homeTowerIsOnLastTile() {
		Tower homeTower = null;

		if (Towerdefense != null) {
			homeTower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE,
					"homeTower");
		}
		if (homeTower == null)
			return false;

		return (homeTower.getPosition().x >= 700 && homeTower.getPosition().x <= 800 && homeTower.getPosition().y >= 500
				&& homeTower.getPosition().y <= 600);
	}

	/*
	 * @return true if a tower spawns after clicking on a towertile, false if
	 * not
	 */
	public boolean towerSpawnsAfterClickingOnTowerTile() {
		List<Entity> entities = new ArrayList<Entity>();
		TowerTile towerTile = null;
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		}
		if (entities.size() == 0)
			return false;

		int tower = 0;
		for (Entity e : entities) {
			if (e.getID().endsWith("Tower")) {
				tower++;
			}
			if (e.getID().startsWith("towerTile")) {
				towerTile = (TowerTile) e;
			}
		}
		if (towerTile == null)
			return false;

		handleMouseOverEntity(towerTile);
		int tower2 = 0;
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		for (Entity e : entities) {
			if (e.getID().endsWith("Tower")) {
				tower2++;
			}
		}
		return tower + 1 == tower2;
	}

	/*
	 * @return true if there is a towerTile entity for each 2 in pathTile array,
	 * false if not
	 */
	public boolean towerSpawnsAfterNotClickingOnTowerTile() {
		List<Entity> entities = new ArrayList<Entity>();
		Entity notTowerTile = null;
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		}
		if (entities.size() == 0)
			return false;

		int tower = 0;
		for (Entity e : entities) {
			if (e.getID().endsWith("Tower")) {
				tower++;
			}
			if (e.getID().startsWith("pathTile")) {
				notTowerTile = e;
			}
		}
		if (notTowerTile == null)
			return false;

		handleMouseOverEntity(notTowerTile);
		int tower2 = 0;
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
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
	public boolean towerShootsEnemy() {
		List<Entity> entities = new ArrayList<Entity>();
		EnemyFactory enemyFactory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) enemyFactory.createEntity();
		if (enemy == null)
			return false;
		if (Towerdefense != null) {
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		} else
			return false;
		if (entities.size() == 0)
			return false;

		Tower tower = null;
		List<PathTile> pathTiles = new ArrayList<PathTile>();
		for (Entity e : entities) {
			if (tower == null && e.getID().startsWith("towerTile")) {
				TowerFactory towerFactory = new TowerFactory("bulletTower", e.getPosition());
				tower = (Tower) towerFactory.createEntity();
				StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, tower);
			}
			if (e.getID().startsWith("pathTile")) {
				pathTiles.add((PathTile) e);
			}
		}
		if (tower == null || pathTiles.size() == 0)
			return false;
		
		Vector2f nearTower = null;
		for(PathTile tile : pathTiles){
			if (tower.getPosition().x >= tile.getPosition().x - 100
					&& tower.getPosition().x <= tile.getPosition().x + 100
					&& tower.getPosition().y >= tile.getPosition().y - 100
					&& tower.getPosition().y <= tile.getPosition().y + 100) {
				nearTower = tower.getPosition();
				break;
			}
		}
		if(nearTower == null) return false;
		enemy.setPosition(nearTower);
		int time = 100000;
		while(StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "shoot") == null && time > 0){
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
	 * @return true if there is an enemy entity after calling enemyFactory false
	 * if not
	 */
	public boolean enemyDoesSpawn() {
		EnemyFactory factory = new EnemyFactory("spider");
		return factory.createEntity() != null;
	}

	/*
	 * @return true if there is an enemy entity after calling enemyFactory false
	 * if not
	 */
	public boolean enemyDoesMove() {
		EnemyFactory factory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) factory.createEntity();
		if (enemy == null)
			return false;
		if (Towerdefense != null) {
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);
		}
		float x = enemy.getPosition().x;
		float y = enemy.getPosition().y;
		runGame(1);
		return (x != enemy.getPosition().x || y != enemy.getPosition().y);
	}

	/*
	 * @return true if enemy moves on path false if not
	 */
	public boolean enemyMovesOnPath() {
		EnemyFactory factory = new EnemyFactory("spider");
		List<Entity> entities = new ArrayList<Entity>();
		Enemy enemy = (Enemy) factory.createEntity();
		if (enemy == null)
			return false;
		if (Towerdefense != null) {
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		}
		if (entities.size() == 0)
			return false;
		List<PathTile> pathTiles = new ArrayList<PathTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("pathTile"))
				pathTiles.add((PathTile) e);
		}
		if (pathTiles.size() == 0)
			return false;

		int time = 100;
		boolean isOnPath = false;

		// run game for 2.8 seconds
		while (time <= 700) {
			runGame(time);
			isOnPath = false;
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
	 * @return true if there is a life entity false if not
	 */
	public boolean enemyDiesAfterBeingShot() {
		List<Entity> entities = new ArrayList<Entity>();
		EnemyFactory factory = new EnemyFactory("spider");
		Enemy spider = (Enemy) factory.createEntity();
		spider.setPosition(new Vector2f(10,50));
		if (Towerdefense != null) {
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, spider);
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		} else
			return false;
		TowerFactory towerFactory = new TowerFactory("bulletTower", new Vector2f(100,100));
		Tower tower = (Tower) towerFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, tower);
		ShootFactory shootFactory = new ShootFactory(tower, 0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);

		int entitySize = entities.size();
		shoot.setPosition(spider.getPosition());
		runGame(100);
		return (entitySize -1 == entities.size());
	}
	/*
	 * 
	 * 
	 * /* ***************************************************
	 * ********************** Life **********************
	 * ***************************************************
	 */

	/*
	 * @return true if there is a life entity false if not
	 */
	public boolean gameHasLife() {
		Life life = null;
		if (Towerdefense != null) {
			life = (Life) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "life");
		}
		return life != null;
	}

	/*
	 * @return true if there is a life entity false if not
	 */
	public boolean gameLosesLifeAfterEnemyThroughHomeTower() {
		Life life = null;
		EnemyFactory factory = new EnemyFactory("spider");
		Enemy spider = (Enemy) factory.createEntity();
		if (Towerdefense != null) {
			life = (Life) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "life");
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, spider);
		} else
			return false;
		runGame(1);
		int lifeCount = life.getLife();
		Vector2f outOfDisplay = new Vector2f(801, 600);
		spider.setPosition(outOfDisplay);
		runGame(1);
		return lifeCount - 1 == life.getLife();
	}

	/*
	 * 
	 * 
	 * /* ***************************************************
	 * ********************** Input **********************
	 * ***************************************************
	 */

	/**
	 * This Method should emulate the key pressed event. This should make the
	 * playertank shoot.
	 * 
	 * Diese Methode emuliert das Druecken beliebiger Tasten. (Dies soll es
	 * ermoeglichen, das Schiessen des Spielerpanzers zu testen)
	 * 
	 * @param updatetime
	 *            : Zeitdauer bis update-Aufruf
	 * @param input
	 *            : z.B. Input.KEY_K, Input.KEY_L
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
	 * This Method should emulate the pressing of the n key. This should pause
	 * the game.
	 * 
	 * Diese Methode emuliert das Druecken der 'n'-Taste. (Dies soll es
	 * ermoeglichen, das Spiel zu pausieren)
	 */
	public void handleKeyPressN() {
		handleKeyPressed(0, Input.KEY_N);
	}

	/**
	 * This Method should emulate the pressing of the esc key. This should end
	 * the game and change into the main menu.
	 * 
	 * Diese Methode emuliert das Druecken der 'esc'-Taste. (Dies soll es
	 * ermoeglichen, das Spiel zu beenden und ins Hauptmenue zu wechseln)
	 */
	public void handleKeyPressESC() {
		handleKeyPressed(0, Input.KEY_ESCAPE);
	}

	public void handleMouseOverEntity(Entity e) {
		Float x = e.getPosition().x;
		Float y = e.getPosition().y;
		app.getTestInput().setMouseX(x.intValue());
		app.getTestInput().setMouseY(y.intValue());
	}
}
