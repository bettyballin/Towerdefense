package tests.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import ui.Towerdefense;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.Enemy;
import model.entities.Explosion;
import model.entities.Money;
import model.entities.PathTile;
import model.entities.Shoot;
import model.entities.Tower;
import model.entities.TowerTile;
import model.path.Path;
import model.factory.EnemyFactory;
import model.factory.ShootFactory;
import model.factory.TowerFactory;

public class TowerdefenseTestAdapterExtended1 extends TowerdefenseTestAdapterMinimal {

	/**
	 * Use this constructor to set up everything you need.
	 */
	public TowerdefenseTestAdapterExtended1() {
		super();
	}

	/*
	 * ***************************************************
	 * ********************** Enemy **********************
	 * ***************************************************
	 */
	/*
	 * @return true if enemy moves in the center of the path, false if not
	 */
	public boolean enemyMovesInCenterOfPath() {
		EnemyFactory factory = new EnemyFactory("spider");
		List<Entity> entities = new ArrayList<Entity>();
		Enemy enemy = (Enemy) factory.createEntity();
		if (enemy == null)
			return false;
		if (Towerdefense != null) {
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		} else {
			System.err.println("The game Towerdefense has not been initialized!");
			return false;
		}
		if (entities.size() == 0)
			return false;

		// create a list of pathtiles
		List<PathTile> pathTiles = new ArrayList<PathTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("pathTile"))
				pathTiles.add((PathTile) e);
		}
		if (pathTiles.size() == 0)
			return false;

		boolean isValidPos = false;
		// while enemy is in the game window
		while (enemy.getPosition().x < 700 && enemy.getPosition().y < 500) {
			runGame(100);
			isValidPos = false;
			// check if there is a pathtile of which enemy is in the center
			for (PathTile tile : pathTiles) {
				if ((enemy.getPosition().x >= tile.getPosition().x - 10
						&& enemy.getPosition().x <= tile.getPosition().x + 10
						&& enemy.getPosition().y >= tile.getPosition().y - 50
						&& enemy.getPosition().y <= tile.getPosition().y + 50)
						|| (enemy.getPosition().x >= tile.getPosition().x - 50
								&& enemy.getPosition().x <= tile.getPosition().x + 50
								&& enemy.getPosition().y >= tile.getPosition().y - 10
								&& enemy.getPosition().y <= tile.getPosition().y + 10)) {
					isValidPos = true;
				}
			}
			if (!isValidPos)
				return false;
		}
		return isValidPos;
	}

	/*
	 * @return true if there is an explosion entity after enemy dies, false if
	 * not
	 */
	public boolean explosionEntityExists() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// create spider Enemy
		EnemyFactory enemyFactory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) enemyFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);

		// create tower for shoot
		TowerFactory factory = new TowerFactory("bulletTower", new Vector2f(500, 500));
		Tower tower = (Tower) factory.createEntity();

		// create shoot entity
		ShootFactory shootFactory = new ShootFactory(tower, 0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);

		// run game until enemy is dead
		while (enemy.getLife() > 0) {
			shoot.setPosition(enemy.getPosition());
			runGame(10);
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);
		}
		runGame(10);
		// check if there is an explosion entity
		return StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "explosion") != null;
	}

	/*
	 * @return true if the explosion entity is rendered in game, false if not
	 */
	public boolean explosionHasImage() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// create spider Enemy
		EnemyFactory enemyFactory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) enemyFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);

		// create tower for shoot
		TowerFactory factory = new TowerFactory("bulletTower", new Vector2f(500, 500));
		Tower tower = (Tower) factory.createEntity();

		// create shoot entity
		ShootFactory shootFactory = new ShootFactory(tower, 0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);

		// run game until enemy is dead
		while (enemy.getLife() > 0) {
			shoot.setPosition(enemy.getPosition());
			runGame(10);
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);
		}
		runGame(10);

		// check if RenderComponent of explosion is greater than zero (if
		// getSize() was Vector2f(0,0), this would be an indicator for not
		// having a renderComponent)
		Explosion explosion = (Explosion) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE,
				"explosion");
		return (explosion.getSize().x > 0 && explosion.getSize().y > 0);
	}

	/*
	 * @return true if enemys have a get method for the life attribute, false if
	 * not
	 */
	public boolean enemyHasLife() {
		EnemyFactory factory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) factory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);

		try {
			enemy.getLife();
			return true;
		} catch (Exception e) {
			System.err.println("Enemy does not have a method called getLife()");
			return false;
		}
	}

	/*
	 * ***************************************************
	 * ********************** Tower **********************
	 * ***************************************************
	 */

	/*
	 * @return true if tower has a strength attribute, false if not
	 */
	public boolean towerHasStrength() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		TowerTile tile = null;
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile")) {
				tile = (TowerTile) e;
				break;
			}
		}
		if (tile == null) {
			System.err.println("No entity starting with'towerTile' found in Towerdefense entities");
			return false;
		}

		TowerFactory factory = new TowerFactory("bulletTower", tile.getPosition());
		Tower tower = (Tower) factory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, tower);

		if (tower == null) {
			System.err.println("TowerFactory does not create a valid tower entity");
			return false;
		}

		// try to get the strength attribute of tower
		try {
			tower.getStrength();
			return true;
		} catch (Exception e) {
			System.err.println("The method 'getStrength()' does not exist in the class Tower");
			return false;
		}
	}

	/*
	 * @return true if shoot decreases life of enemy and does not kill him
	 * immediately, false if not
	 */
	public boolean shootDecreasesLifeOfEnemy() {
		EnemyFactory enemyFactory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) enemyFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);

		TowerFactory factory = new TowerFactory("bulletTower", new Vector2f(500, 500));
		Tower tower = (Tower) factory.createEntity();

		ShootFactory shootFactory = new ShootFactory(tower, 0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);

		if (shoot == null) {
			System.err.println("ShootFactory does not create a valid shoot entity");
			return false;
		}
		// create a collision of shoot and enemy
		shoot.setPosition(enemy.getPosition());

		// while enemy doesn't lose life
		int life = enemy.getLife();
		while (enemy.getLife() == life) {
			shoot.setPosition(enemy.getPosition());
			runGame(10);
			// let the entities collide and add the removed shoot again
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);
		}

		// check if enemy's life is decreased by the strength of the tower
		return enemy.getLife() == life - tower.getStrength();
	}

	/*
	 * ***************************************************
	 * ********************** Money **********************
	 * ***************************************************
	 */

	/*
	 * @return true if there is a money entity, false if not
	 */
	public boolean moneyEntityExists() {
		if (StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money") != null) {
			return true;
		}
		return false;
	}

	/*
	 * @return true if money increases after enemy dies, false if not
	 */
	public boolean moneyIncreasesAfterEnemyDies() {
		EnemyFactory enemyFactory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) enemyFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);

		TowerFactory factory = new TowerFactory("bulletTower", new Vector2f(500, 500));
		Tower tower = (Tower) factory.createEntity();

		ShootFactory shootFactory = new ShootFactory(tower, 0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);

		shoot.setPosition(enemy.getPosition());

		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		int amount = -1;
		try {
			amount = money.getAmount();
		} catch (Exception e) {
			System.err.println("The method 'getAmount()' does not exist in the class Money");
			return false;
		}

		// while enemy is not dead, let enemy collide with shoots
		while (enemy.getLife() > 0) {
			shoot.setPosition(enemy.getPosition());
			runGame(10);
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);
		}
		runGame(10);

		// check if money is more than before
		return money.getAmount() > amount;
	}

	/*
	 * @return true if money decreases after building a tower, false if not
	 */
	public boolean moneyDecreasesAfterBuildingTower() {
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		int amount = money.getAmount();

		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		TowerTile towerTile = null;
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile")) {
				towerTile = (TowerTile) e;
			}
		}

		// put mouse over towerTile and check if tower is build after clicking
		// on the towerTile
		handleMouseOverEntity(towerTile);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);

		// check if money is less after building the tower
		return money.getAmount() < amount;
	}

	/*
	 * ***************************************************
	 * ********************** Path **********************
	 * ***************************************************
	 */

	/*
	 * @return true if path is generated randomly, false if not
	 */
	public boolean pathIsGeneratedRandomly() {
		// create first path
		Path path = new Path("path");
		int[][] pathArray = path.getPathArray();

		// create second path
		path = new Path("path");
		// compare the entries and check if they are the same
		int counter = 0;
		while (hasSameEntries(path.getPathArray(), pathArray) && counter < 5) {
			path = new Path("path");
			counter++;
		}
		return !hasSameEntries(path.getPathArray(), pathArray);
	}

	/*
	 * @param a: array to compare b with
	 * 
	 * @param b: second array
	 * 
	 * @return true if arrays have the same entries, false if not
	 */
	private boolean hasSameEntries(int[][] a, int[][] b) {
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				if (a[column][row] != b[column][row])
					return false;
			}
		}
		return true;
	}
}
