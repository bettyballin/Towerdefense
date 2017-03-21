package model.actions;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.Money;
import model.entities.Tower;
import model.entities.TowerTile;
import model.factory.TowerTileFactory;
import ui.Towerdefense;

public class DeleteTowerAction implements Action{
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		int state = Towerdefense.GAMEPLAYSTATE;
		Tower tower = (Tower) event.getOwnerEntity();
		Money m = (Money) StateBasedEntityManager.getInstance().getEntity(state, "money");
		Float costs = (float) (tower.getCosts()/2);
		m.changeAmount(costs.intValue());	
		StateBasedEntityManager.getInstance().removeEntity(state, tower);
		
		for(Entity e : StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE)){
			if(e.getID().startsWith("towerTile")){
				if(((TowerTile) e).hasTower() && ((TowerTile) e).getTower().equals(tower)){
					((TowerTile) e).setTower(null);
					((TowerTile) e).setHasTower(false);
				}
			}
		}
	}		
}
