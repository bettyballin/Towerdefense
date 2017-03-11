package model.actions;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.TowerTile;
import ui.Towerdefense;

public class MakeUnvisibleAction implements Action {
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		List<Entity> entitylist = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		for(Entity entity : entitylist){
			if(entity.getID()=="towertile"){
				if(((TowerTile) entity).isVisible()) {
					entity.removeComponent("ImageRenderComponent");
					entity.setVisible(false);
				}
			}
		}
	}
}
