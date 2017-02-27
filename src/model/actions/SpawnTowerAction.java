package model.actions;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.Money;
import model.entities.Tower;
import model.factory.TowerFactory;
import ui.Towerdefense;

public class SpawnTowerAction implements Action{

	private String towerType;
	private Vector2f position;
	
	public SpawnTowerAction(String towerType){
		this.towerType = towerType;
		this.position = new Vector2f(0,0);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		position = new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY());
		float x = position.x%100;
		float y = position.y%100;
		position = new Vector2f(position.x-x+50, position.y-y+50);
		boolean alreadyUsed = false;
		List<Entity> entitiesByState = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		for (Entity e : entitiesByState) {
			if(Tower.class.isInstance(e)){
				if(((Tower) e).getPosition().x == position.x && ((Tower) e).getPosition().y == position.y){
					alreadyUsed = true;
				}
			}
		}
		if(!alreadyUsed){
			Money money = (Money) StateBasedEntityManager.getInstance().getEntity(sb.getCurrentStateID(), "money");
			int amount = 0;
			if(towerType == "bulletTower") amount = Towerdefense.bulletTower[0];
			else amount = Towerdefense.iceTower[0];
			if(money.getAmount() >= amount) {
				money.changeAmount(-amount);
				Tower tower = (Tower) new TowerFactory(towerType, position).createEntity();
				StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE,tower);
			}
			
		}
	}

}
