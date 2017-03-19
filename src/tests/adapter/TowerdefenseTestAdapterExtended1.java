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
	 * @return true if enemy moves in the middle of the path, false if not
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

		boolean isValidPos = false;
		while (enemy.getPosition().x < 700 && enemy.getPosition().y < 500) {
			runGame(100);
			isValidPos = false;
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
	 * @return true if enemy explodes after being dead, false if not
	 */
	public boolean enemyExplodes() {
		List<Entity> entities = new ArrayList<Entity>();
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		}
		if (entities.size() == 0)
			return false;

		EnemyFactory enemyFactory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) enemyFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);
		TowerFactory factory = new TowerFactory("bulletTower", new Vector2f(500,500));
		Tower tower = (Tower) factory.createEntity();
		ShootFactory shootFactory = new ShootFactory(tower,0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);
	
		if (shoot == null)
			return false;
		
		while(enemy.getLife() > 0){
			shoot.setPosition(enemy.getPosition());
			runGame(10);
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);
		}
		runGame(10);
		return StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "explosion") != null;
	}

	/*
	 * @return true if enemys have a life attribute, false if not
	 */
	public boolean enemyHasLife() {
		EnemyFactory factory = new EnemyFactory("spider");
		Enemy enemy = (Enemy) factory.createEntity();
		if (enemy == null)
			return false;
		if (Towerdefense != null) {
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);
		}

		try {
			enemy.getLife();
			return true;
		} catch (Exception e) {
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
		List<Entity> entities = new ArrayList<Entity>();
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		}
		if (entities.size() == 0)
			return false;
		TowerTile tile = null;
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile")) {
				tile = (TowerTile) e;
				break;
			}
		}
		if (tile == null)
			return false;

		TowerFactory factory = new TowerFactory("bulletTower", tile.getPosition());
		Tower tower = (Tower) factory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, tower);
		if (tower == null)
			return false;

		try {
			tower.getStrength();
			return true;
		} catch (Exception e) {
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
		TowerFactory factory = new TowerFactory("bulletTower", new Vector2f(500,500));
		Tower tower = (Tower) factory.createEntity();
		ShootFactory shootFactory = new ShootFactory(tower,0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);
	
		if (shoot == null)
			return false;
		shoot.setPosition(enemy.getPosition());
		
		int life = enemy.getLife();
		while(enemy.getLife() == life){
			shoot.setPosition(enemy.getPosition());
			runGame(10);
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);
		}
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
		if (Towerdefense != null) {
			if (StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money") != null) {
				return true;
			}
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
		TowerFactory factory = new TowerFactory("bulletTower", new Vector2f(500,500));
		Tower tower = (Tower) factory.createEntity();
		ShootFactory shootFactory = new ShootFactory(tower,0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);
	
		if (shoot == null)
			return false;
		shoot.setPosition(enemy.getPosition());
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		int amount = money.getAmount();
		
		while(enemy.getLife() > 0){
			shoot.setPosition(enemy.getPosition());
			runGame(10);
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);
		}
		runGame(10);
		return money.getAmount() > amount;
	}


	/*
	 * @return true if money decreases after building a tower, false if not
	 */
	public boolean moneyDecreasesAfterBuildingTower() {
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		int amount = money.getAmount();
		
		List<Entity> entities = new ArrayList<Entity>();
		TowerTile towerTile = null;
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		}
		if (entities.size() == 0)
			return false;

		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile")) {
				towerTile = (TowerTile) e;
			}
		}
		if (towerTile == null)
			return false;

		handleMouseOverEntity(towerTile);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		
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
		Path path = new Path("path");
		int[][] pathArray = path.getPathArray();
		path.printArray();
		path = new Path("path");
		while(hasSameEntries(path.getPathArray(),pathArray)){
			path = new Path("path");
			path.printArray();
		}
		return !hasSameEntries(path.getPathArray(),pathArray);
	}
	private boolean hasSameEntries(int[][] a, int[][] b){
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				if(a[column][row]!=b[column][row]) return false;
			}
		}
		return true;
	}
}
