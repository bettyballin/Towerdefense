package model.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.Enemy;
import model.factory.EnemyFactory;
import ui.Towerdefense;

public class SpawnEnemyAction implements Action {

	private int waveSize;
	private int nextEnemy;
	private String enemyType;

	/**
	 * Constructs a spawn enemy action
	 * 
	 * @param enemyType
	 *            type of enemy to be spawned
	 * @param waveSize
	 *            amount of enemies to be spawned
	 */
	public SpawnEnemyAction(String enemyType, int waveSize) {
		this.nextEnemy = 0;
		this.enemyType = enemyType;
		this.waveSize = waveSize;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		// if enemy counter has not yet reached the given wave size
		if (nextEnemy < waveSize) {
			// create new enemy and add it to game
			EnemyFactory factory = new EnemyFactory(enemyType);
			Enemy enemy = (Enemy) factory.createEntity();
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);

			// increase enemy counter by 1
			nextEnemy++;
		} else if (nextEnemy == waveSize && event.getOwnerEntity().getID() == "wave")
			// remove wave entity from game
			StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, event.getOwnerEntity());
	}

}
