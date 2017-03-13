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
import ui.GameplayState;
import ui.Towerdefense;

public class SpawnEnemyAction implements Action{

	private List<Enemy> waveList = new ArrayList<Enemy>();
	private int nextEnemy;
	
	public SpawnEnemyAction(List<Enemy> waveList){
		this.nextEnemy = 0;
		this.waveList = waveList;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		if(nextEnemy < waveList.size()){
			Entity enemy = waveList.get(nextEnemy++);
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE,enemy);	
			GameplayState.getInstance().addEnemy((Enemy) enemy);
		}
	}

}
