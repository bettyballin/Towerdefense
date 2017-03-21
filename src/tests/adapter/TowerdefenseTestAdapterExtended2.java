package tests.adapter;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import model.entities.Enemy;
import model.entities.Life;
import model.entities.PathTile;
import model.entities.Shoot;
import model.entities.Tower;
import model.entities.TowerTile;
import model.factory.EnemyFactory;
import model.factory.ShootFactory;
import model.factory.TowerFactory;
import model.options.Options;
import ui.Towerdefense;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class TowerdefenseTestAdapterExtended2 extends TowerdefenseTestAdapterExtended1 {

	/**
	 * Use this constructor to set up everything you need.
	 */
	public TowerdefenseTestAdapterExtended2() {
		super();
	}
	/*
	 * ***************************************************
	 * ********************** Enemy **********************
	 * ***************************************************
	 */

	/*
	 * @return true if the second enemy type exists, false if not
	 */
	public boolean secondEnemyTypeExists() {
		EnemyFactory factory = new EnemyFactory("wasp");
		Enemy wasp = (Enemy) factory.createEntity();
		return (wasp != null && (wasp.getSize().x > 0 && wasp.getSize().y > 0));
	}

	/*
	 * @return true if the second enemy type is faster than first, false if not
	 */
	public boolean secondEnemyTypeIsFasterThanFirst() {
		EnemyFactory factory1 = new EnemyFactory("spider");
		EnemyFactory factory2 = new EnemyFactory("wasp");
		Enemy spider = (Enemy) factory1.createEntity();
		Enemy wasp = (Enemy) factory2.createEntity();
		if (spider == null || wasp == null)
			return false;

		// check if speed of first enemy is less than speed of second enemy
		return spider.getSpeed() < wasp.getSpeed();
	}

	/*
	 * ***************************************************
	 * ********************** Tower **********************
	 * ***************************************************
	 */

	/*
	 * @return true if a second tower type with a slowdown attribute exists,
	 * false if not
	 */
	public boolean secondTowerTypeExistsAndHasSlowdown() {
		TowerFactory towerFactory = new TowerFactory("iceTower", new Vector2f(500, 500));
		Tower iceTower = (Tower) towerFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, iceTower);
		try {
			iceTower.getSlowdown();
			return true;
		} catch (Exception e) {
			System.err.println("The second tower does not have the method 'getSlowdown()'");
			return false;
		}
	}

	/*
	 * @return true if enemy moves in the middle of the path, false if not
	 */
	public boolean secondTowerTypeSlowsDownEnemies() {
		EnemyFactory enemyFactory = new EnemyFactory("spider");
		Enemy spider = (Enemy) enemyFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, spider);

		// create an entity of the second tower type
		TowerFactory towerFactory = new TowerFactory("iceTower", new Vector2f(500, 500));
		Tower iceTower = (Tower) towerFactory.createEntity();

		ShootFactory shootFactory = new ShootFactory(iceTower, 0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);

		// let shoot collide with enemy
		float speed = spider.getSpeed();
		shoot.setPosition(spider.getPosition());
		runGame(10);
		runGame(10);

		// check if speed of enemy decreased
		return speed > spider.getSpeed();
	}

	/*
	 * @return true if buttons for tower selection appear when mouse is over
	 * tower tile, false if not
	 */
	public boolean towerSelectionButtonsAppear() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
		}

		// check if the visibility of selection buttons is
		// true if the mouse is over the tile for each towerTile
		boolean correctFunctionality = false;
		for (TowerTile tile : towerTiles) {
			handleMouseOverEntity(tile);
			runGame(10);
			correctFunctionality = tile.getButtonVisibility();
			if (!correctFunctionality)
				return false;
		}
		return correctFunctionality;
	}

	/*
	 * @return true if clicking on the left side of the tower tile spawns the
	 * first type of tower, false if not
	 */
	public boolean towerSelectionWorksForFirstTower() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		int tower = 0;
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// count occurences of the first type of tower
			if (e.getID().startsWith("bulletTower"))
				tower++;
		}

		// put mouse over tile and simulate clicking
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// check if the amount of first type towers has increased by one
		int towerUpdate = 0;
		for (Entity e : entities) {
			if (e.getID().startsWith("bulletTower"))
				towerUpdate++;
		}
		return towerUpdate == tower + 1;
	}

	/*
	 * @return true if clicking on the right side of the tower tile spawns the
	 * second type of tower, false if not
	 */
	public boolean towerSelectionWorksForSecondTower() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		int tower = 0;
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// count occurences of the second type of tower
			if (e.getID().startsWith("iceTower"))
				tower++;
		}

		// put mouse over tile and simulate clicking
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() + 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// check if the amount of second type towers has increased by one
		int towerUpdate = 0;
		for (Entity e : entities) {
			if (e.getID().startsWith("iceTower"))
				towerUpdate++;
		}
		return towerUpdate > tower;
	}

	/*
	 * ***************************************************
	 * ********************** Difficulty *****************
	 * ***************************************************
	 */

	/*
	 * @return true if buttons for tower selection appear when mouse is over
	 * tower tile, false if not
	 */
	public boolean classOptionsExists() {
		try {
			Options.getInstance();
			return true;
		} catch (Exception e) {
			System.err.println("The class Options does not exist or does not have a method called 'getInstance()'");
			return false;
		}
	}

	/*
	 * @return true if class Options has a method which returns the difficulty,
	 * false if not
	 */
	public boolean optionsHaveDifficulty() {
		try {
			Options.getInstance().getDifficulty();
			return true;
		} catch (Exception e) {
			System.err.println("The class Options does not have a method called 'getDifficulty()'");
			return false;
		}
	}

	/*
	 * @return true if buttons for tower selection appear when mouse is over
	 * tower tile, false if not
	 */
	public boolean difficultyChanges() {
		String diff = Options.getInstance().getDifficulty();
		handleKeyPressD();
		return diff.compareTo(Options.getInstance().getDifficulty()) != 0;
	}

	/*
	 * @return true if first enemy has more life in a higher difficulty, false
	 * if not
	 */
	public boolean firstEnemyHasMoreLifeInHigherDifficulty() {
		// change to EINFACH or NORMAL
		if (Options.getInstance().getDifficulty() == "SCHWER") {
			handleKeyPressD();
		}
		// change into GAMEPLAYSTATE
		handleKeyPressESC();
		handleKeyPressN();

		// create new enemy and save the amount of life it has
		EnemyFactory factory = new EnemyFactory("spider");
		Enemy spider = (Enemy) factory.createEntity();
		int life = spider.getLife();

		// leave game, go into OPTIONSTATE, change into a higher difficulty
		handleKeyPressESC();
		handleKeyPressE();
		handleKeyPressD();

		// change back to game
		handleKeyPressESC();
		handleKeyPressN();

		// create new enemy and save amount of life it has
		EnemyFactory factory2 = new EnemyFactory("spider");
		Enemy spider2 = (Enemy) factory2.createEntity();
		int life2 = spider2.getLife();

		// go back into OPTIONSSTATE for tests
		handleKeyPressESC();
		handleKeyPressE();

		// check if life of enemy is now higher
		return life2 > life;
	}

	/*
	 * @return true if second enemy has more life in a higher difficulty, false if not
	 */
	public boolean secondEnemyHasMoreLifeInHigherDifficulty() {
		// change to EINFACH or NORMAL
		if (Options.getInstance().getDifficulty() == "SCHWER") {
			handleKeyPressD();
		}
		// change into GAMEPLAYSTATE
		handleKeyPressESC();
		handleKeyPressN();

		// create new enemy and save the amount of life it has
		EnemyFactory factory = new EnemyFactory("wasp");
		Enemy wasp = (Enemy) factory.createEntity();
		int life = wasp.getLife();

		// leave game, go into OPTIONSTATE, change into a higher difficulty
		handleKeyPressESC();
		handleKeyPressE();
		handleKeyPressD();

		// change back to game
		handleKeyPressESC();
		handleKeyPressN();

		// create new enemy and save amount of life it has
		EnemyFactory factory2 = new EnemyFactory("wasp");
		Enemy wasp2 = (Enemy) factory2.createEntity();
		int life2 = wasp2.getLife();

		// go back into OPTIONSSTATE for tests
		handleKeyPressESC();
		handleKeyPressE();

		// check if life of enemy is now higher
		return life2 > life;
	}

	/*
	 * @return true if life entity has a lower life value in a higher
	 * difficulty, false if not
	 */
	public boolean lifeIsLessInHigherDifficulty() {
		// change to EINFACH or NORMAL
		if (Options.getInstance().getDifficulty() == "SCHWER") {
			handleKeyPressD();
		}
		// change into GAMEPLAYSTATE
		handleKeyPressESC();
		handleKeyPressN();

		// get Life entity and save the amount of life left
		Life life = (Life) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "life");
		int lifeCount = life.getLife();

		// leave game, go into OPTIONSTATE, change into a higher difficulty
		handleKeyPressESC();
		handleKeyPressE();
		handleKeyPressD();

		// change back to game
		handleKeyPressESC();
		handleKeyPressN();

		// get a second Life entity and save the amount of life left
		Life life2 = (Life) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "life");
		int lifeCount2 = life2.getLife();

		// go back into OPTIONSSTATE for tests
		handleKeyPressESC();
		handleKeyPressE();

		// check if amount of life is now higher
		return lifeCount2 < lifeCount;
	}

	/*
	 * @return true if enemy has more speed in a higher difficulty, false if not
	 */
	public boolean firstEnemyIsFasterInHigherDifficulty() {
		// change to EINFACH or NORMAL
		if (Options.getInstance().getDifficulty() == "SCHWER") {
			handleKeyPressD();
		}
		// change into GAMEPLAYSTATE
		handleKeyPressESC();
		handleKeyPressN();

		// create new enemy and save its speed amount
		EnemyFactory factory = new EnemyFactory("spider");
		Enemy spider = (Enemy) factory.createEntity();
		float speed = spider.getSpeed();

		// leave game, go into OPTIONSTATE, change into a higher difficulty
		handleKeyPressESC();
		handleKeyPressE();
		handleKeyPressD();

		// change back to game
		handleKeyPressESC();
		handleKeyPressN();

		// create new enemy and save its speed amount
		EnemyFactory factory2 = new EnemyFactory("spider");
		Enemy spider2 = (Enemy) factory2.createEntity();
		float speed2 = spider2.getSpeed();

		// go back into OPTIONSSTATE for tests
		handleKeyPressESC();
		handleKeyPressE();

		// check if amount of speed of the enemy is now higher
		return speed2 > speed;
	}

	/*
	 * @return true if second enemy has more speed in a higher difficulty, false
	 * if not
	 */
	public boolean secondEnemyIsFasterInHigherDifficulty() {
		// change to EINFACH or NORMAL
		if (Options.getInstance().getDifficulty() == "SCHWER") {
			handleKeyPressD();
		}
		// change into GAMEPLAYSTATE
		handleKeyPressESC();
		handleKeyPressN();

		// create new enemy and save its speed amount
		EnemyFactory factory = new EnemyFactory("wasp");
		Enemy wasp = (Enemy) factory.createEntity();
		float speed = wasp.getSpeed();

		// leave game, go into OPTIONSTATE, change into a higher difficulty
		handleKeyPressESC();
		handleKeyPressE();
		handleKeyPressD();

		// change back to game
		handleKeyPressESC();
		handleKeyPressN();

		// create new enemy and save its speed amount
		EnemyFactory factory2 = new EnemyFactory("wasp");
		Enemy wasp2 = (Enemy) factory2.createEntity();
		float speed2 = wasp2.getSpeed();

		// go back into OPTIONSSTATE for tests
		handleKeyPressESC();
		handleKeyPressE();

		// check if amount of speed of the enemy is now higher
		return speed2 > speed;
	}
	/*
	 * ***************************************************
	 * ********************** Input **********************
	 * ***************************************************
	 */

	public void handleKeyPressE() {
		handleKeyPressed(0, Input.KEY_E);
	}

	public void handleKeyPressD() {
		handleKeyPressed(0, Input.KEY_D);
	}
}
