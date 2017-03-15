package model.actions;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.Enemy;
import model.factory.EnemyFactory;
import ui.Towerdefense;

public class SpawnEnemyAction implements Action {

	private int waveSize;
	private int nextEnemy;
	private String enemyType;

	public SpawnEnemyAction(String enemyType, int waveSize) {
		this.nextEnemy = 0;
		this.enemyType = enemyType;
		this.waveSize = waveSize;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		if (nextEnemy < waveSize) {
			EnemyFactory factory = new EnemyFactory(enemyType);
			Enemy enemy = (Enemy) factory.createEntity();
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, enemy);
			nextEnemy++;
		} else if (nextEnemy == waveSize && event.getOwnerEntity().getID() == "wave")
			StateBasedEntityManager.getInstance().removeEntity(Towerdefense.GAMEPLAYSTATE, event.getOwnerEntity());
	}

}
