package tests.adapter;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import model.entities.Enemy;
import model.entities.PathTile;
import model.entities.Shoot;
import model.entities.Tower;
import model.entities.TowerTile;
import model.factory.EnemyFactory;
import model.factory.ShootFactory;
import model.factory.TowerFactory;
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
	 * @return true if enemy moves in the middle of the path, false if not
	 */
	public boolean secondEnemyTypeIsFasterThanFirst() {
		EnemyFactory factory1 = new EnemyFactory("spider");
		EnemyFactory factory2 = new EnemyFactory("wasp");
		Enemy spider = (Enemy) factory1.createEntity();
		Enemy wasp = (Enemy) factory2.createEntity();
		if (spider == null || wasp == null)
			return false;

		return spider.getSpeed() < wasp.getSpeed();
	}

	/*
	 * ***************************************************
	 * ********************** Tower **********************
	 * ***************************************************
	 */

	/*
	 * @return true if enemy moves in the middle of the path, false if not
	 */
	public boolean secondTowerTypeSlowsDownEnemies() {
		EnemyFactory enemyFactory = new EnemyFactory("spider");
		Enemy spider = (Enemy) enemyFactory.createEntity();
		if (spider == null)
			return false;
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, spider);

		TowerFactory towerFactory = new TowerFactory("iceTower", new Vector2f(500, 500));
		Tower iceTower = (Tower) towerFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, iceTower);

		ShootFactory shootFactory = new ShootFactory(iceTower, 0);
		Shoot shoot = (Shoot) shootFactory.createEntity();
		StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, shoot);

		float speed = spider.getSpeed();
		shoot.setPosition(spider.getPosition());
		runGame(10);
		runGame(10);

		return speed > spider.getSpeed();
	}

	/*
	 * @return true if buttons for tower selection appear when mouse is over
	 * tower tile, false if not
	 */
	public boolean towerSelectionButtonsAppear() {
		List<Entity> entities = new ArrayList<Entity>();
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		}
		if (entities.size() == 0)
			return false;

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
				towerTiles.add((TowerTile) e);
		}
		if (towerTiles.size() == 0)
			return false;

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
	 * @return true if buttons for tower selection appear when mouse is over
	 * tower tile, false if not
	 */
	public boolean towerSelectionWorksForFirstTower() {
		List<Entity> entities = new ArrayList<Entity>();
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		}
		if (entities.size() == 0)
			return false;

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		int tower = 0;
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
					towerTiles.add((TowerTile) e);
			if (e.getID().startsWith("bulletTower"))
				tower++;
		}
		if (towerTiles.size() == 0)
			return false;

		boolean correctFunctionality = false;
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() - 10);

		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		int towerUpdate = 0;
		for (Entity e : entities) {
			if (e.getID().startsWith("bulletTower"))
				towerUpdate++;
		}
		return towerUpdate > tower;
	}

	/*
	 * @return true if buttons for tower selection appear when mouse is over
	 * tower tile, false if not
	 */
	public boolean towerSelectionWorksForSecondTower() {
		List<Entity> entities = new ArrayList<Entity>();
		if (Towerdefense != null) {
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		}
		if (entities.size() == 0)
			return false;

		List<TowerTile> towerTiles = new ArrayList<TowerTile>();
		int tower = 0;
		for (Entity e : entities) {
			if (e.getID().startsWith("towerTile"))
					towerTiles.add((TowerTile) e);
			if (e.getID().startsWith("iceTower"))
				tower++;
		}
		if (towerTiles.size() == 0)
			return false;

		boolean correctFunctionality = false;
		handleMouseOverEntity(towerTiles.get(0));
		app.getTestInput().setMouseX(app.getTestInput().getMouseX() + 10);

		app.getTestInput().setMouseButtonPressed(0);
		handleKeyPressed(0, Input.MOUSE_LEFT_BUTTON);
		runGame(10);

		int towerUpdate = 0;
		for (Entity e : entities) {
			if (e.getID().startsWith("iceTower"))
				towerUpdate++;
		}
		return towerUpdate > tower;
	}

	/*
	 * ***************************************************
	 * ********************** Input **********************
	 * ***************************************************
	 */
	public void handleKeyPressE() {
		handleKeyPressed(0, Input.KEY_E);
	}
}
