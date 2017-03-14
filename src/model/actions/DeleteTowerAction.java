package model.actions;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.Enemy;
import model.entities.Money;
import model.entities.Tower;
import model.entities.TowerTile;
import ui.GameplayState;
import ui.Towerdefense;

public class DeleteTowerAction implements Action{
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		int state = Towerdefense.GAMEPLAYSTATE;
		Tower tower = (Tower) event.getOwnerEntity();
		List<Entity> l = StateBasedEntityManager.getInstance().getEntitiesByState(state);
		for(Entity e : l){
			if(e.getID() == "towertile"){
				if(tower == ((TowerTile) e).getTower()){
					((TowerTile) e).setHasTower(false);
					((TowerTile) e).setTower(null);
				}
			}
		}
		Money m = (Money) StateBasedEntityManager.getInstance().getEntity(state, "money");
		Float costs = (float) (tower.getCosts()/2);
		m.changeAmount(costs.intValue());	
		StateBasedEntityManager.getInstance().removeEntity(state, tower);
	}		
}
