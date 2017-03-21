package tests.adapter;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;

import ui.Towerdefense;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.Enemy;
import model.entities.Money;
import model.entities.Tower;
import model.entities.TowerTile;
import model.factory.EnemyFactory;
import model.factory.TowerFactory;
import model.factory.WaveFactory;
import model.options.Options;

public class TowerdefenseTestAdapterExtended3 extends TowerdefenseTestAdapterExtended2 {

	/**
	 * Use this constructor to set up everything you need.
	 */
	public TowerdefenseTestAdapterExtended3() {
		super();
	}

	/*
	 * ***************************************************
	 * ********************** Waves **********************
	 * ***************************************************
	 */

	/*
	 * @return true if wave entity exists, false if not
	 */
	public boolean waveEntityExists() {
		if (Towerdefense != null) {
			try {
				StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "wave");
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		System.err.println("Towerdefense is null, the game has not been initialized!");
		return false;
	}

	/*
	 * @return true if enemies spawn in waves, false if not
	 */
	public int enemiesSpawnInWaves(int waveSize) {
		// create wave of 3 enemies
		WaveFactory waveFactory = new WaveFactory("spider", waveSize, 0);
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE,
				StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "wave"));
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, waveFactory.createEntity());
		runGame(10);
		runGame(10);
		runGame(10);
		runGame(10);
		runGame(10);
		runGame(10);
		runGame(10);
		int enemy = 0;
		for (Entity e : entities) {
			if (e.getID().startsWith("spider"))
				enemy++;
		}
		return enemy;
	}

	/*
	 * ***************************************************
	 * ********************** Buttons **********************
	 * ***************************************************
	 */

	/*
	 * @return true if update button increases tower strength of first tower
	 * type, false if not
	 */
	public boolean updateButtonIncreasesFirstTowerStrength() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// make sure we have enough money for the update
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		money.changeAmount(Towerdefense.bulletTower[0] * 2);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// remove every entity of bulletTower in the game
			if (e.getID().contains("bulletTower"))
				StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, e);
		}

		// put mouse over left side of towerTile and click button
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// tower should have been created - get strength of it
		Tower tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE,
				"bulletTower");
		int strength = tower.getStrength();

		// put mouse over expected position of update button and click
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "bulletTower");
		// check if strength of tower has increased
		return strength < tower.getStrength();
	}

	/*
	 * @return true if update button increases tower strength of second tower
	 * type, false if not
	 */
	public boolean updateButtonIncreasesSecondTowerStrength() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// make sure we have enough money for the update
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		money.changeAmount(Towerdefense.iceTower[0] * 2);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// remove every entity of iceTower in the game
			if (e.getID().contains("iceTower"))
				StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, e);
		}

		// put mouse over right side of towerTile and click button
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() + 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// tower should have been created - get strength of it
		Tower tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "iceTower");
		int strength = tower.getStrength();

		// put mouse over expected position of update button and click
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "iceTower");
		// check if strength of tower has increased
		return strength < tower.getStrength();
	}

	/*
	 * @return true if update button increases tower speed of first tower, false
	 * if not
	 */
	public boolean updateButtonIncreasesFirstTowerSpeed() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// make sure we have enough money for the update
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		money.changeAmount(Towerdefense.bulletTower[0] * 2);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// remove every entity of bulletTower in the game
			if (e.getID().contains("bulletTower"))
				StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, e);
		}

		// put mouse over left side of towerTile and click button
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// tower should have been created - get speed of it
		Tower tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE,
				"bulletTower");
		float speed = tower.getSpeed();

		// put mouse over expected position of update button and click
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "bulletTower");
		// check if speed of tower has increased
		return speed < tower.getSpeed();
	}

	/*
	 * @return true if update button increases tower speed of second tower,
	 * false if not
	 */
	public boolean updateButtonIncreasesSecondTowerSpeed() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// make sure we have enough money for the update
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		money.changeAmount(Towerdefense.iceTower[0] * 2);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// remove every entity of bulletTower in the game
			if (e.getID().contains("iceTower"))
				StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, e);
		}

		// put mouse over left side of towerTile and click button
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() + 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// tower should have been created - get speed of it
		Tower tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "iceTower");
		float speed = tower.getSpeed();

		// put mouse over expected position of update button and click
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "iceTower");
		// check if speed of tower has increased
		return speed < tower.getSpeed();
	}

	/*
	 * @return true if update button increases tower range of first tower, false
	 * if not
	 */
	public boolean updateButtonIncreasesFirstTowerRange() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// make sure we have enough money for the update
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		money.changeAmount(Towerdefense.bulletTower[0] * 2);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// remove every entity of bulletTower in the game
			if (e.getID().contains("bulletTower"))
				StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, e);
		}

		// put mouse over left side of towerTile and click button
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// tower should have been created - get range of it
		Tower tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE,
				"bulletTower");
		int range = tower.getRange();

		// put mouse over expected position of update button and click
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "bulletTower");
		// check if range of tower has increased
		return range < tower.getRange();
	}

	/*
	 * @return true if update button increases tower range of second tower,
	 * false if not
	 */
	public boolean updateButtonIncreasesSecondTowerRange() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// make sure we have enough money for the update
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		money.changeAmount(Towerdefense.iceTower[0] * 2);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// remove every entity of iceTower in the game
			if (e.getID().contains("iceTower"))
				StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, e);
		}

		// put mouse over right side of towerTile and click button
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() + 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// tower should have been created - get range of it
		Tower tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "iceTower");
		int range = tower.getRange();

		// put mouse over expected position of update button and click
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "iceTower");
		// check if range of tower has increased
		return range < tower.getRange();
	}

	/*
	 * @return true if update button increases second tower slowdown, false if
	 * not
	 */
	public boolean updateButtonIncreasesSecondTowerSlowdown() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// make sure we have enough money for the update
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		money.changeAmount(Towerdefense.iceTower[0] * 2);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// remove every entity of iceTower in the game
			if (e.getID().contains("iceTower"))
				StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, e);
		}

		// put mouse over right side of towerTile and click button
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() + 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// tower should have been created - get slowdown of it
		Tower tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "iceTower");
		float slowdown = tower.getSlowdown();

		// put mouse over expected position of update button and click
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "iceTower");
		// check if slowdown of tower has increased
		return slowdown < tower.getSlowdown();
	}

	/*
	 * @return true if update button increases second tower slowdown, false if
	 * not
	 */
	public boolean updateButtonIncreasesFirstTowerSlowdown() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		// make sure we have enough money for the update
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		money.changeAmount(Towerdefense.bulletTower[0] * 2);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// remove every entity of bulletTower in the game
			if (e.getID().contains("bulletTower"))
				StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, e);
		}

		// put mouse over left side of towerTile and click button
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// tower should have been created - get slowdown of it
		Tower tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE,
				"bulletTower");
		float slowdown = tower.getSlowdown();

		// put mouse over expected position of update button and click
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "bulletTower");
		// check if slowdown of tower has increased
		return slowdown < tower.getSlowdown();
	}

	/*
	 * @return true if delete button deletes tower entity, false if not
	 */
	public boolean deleteButtonDeletesTower() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// remove every entity of bulletTower in the game
			if (e.getID().contains("bulletTower"))
				StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, e);
		}

		// put mouse over left side of towerTile and click button
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);
		// tower should have been created now

		// put mouse over expected position of delete button and click
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() + 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		// tower should be gone now
		Tower tower = (Tower) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE,
				"bulletTower");
		return (tower == null);
	}

	/*
	 * @return true if delete button deletes tower entity and increases money,
	 * false if not
	 */
	public boolean deleteButtonIncreasesMoney() {
		List<Entity> entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		
		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
			// remove every entity of bulletTower in the game
			if (e.getID().contains("bulletTower"))
				StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, e);
		}

		// put mouse over left side of towerTile and click button
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);
		// tower should have been created now
		
		// save amount of money
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		int amount = money.getAmount();

		// put mouse over expected position of delete button and click
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() + 10);
		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);
		return money.getAmount() > amount;
	}
}
