package model.actions;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import model.entities.Enemy;

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
		}
	}

}
