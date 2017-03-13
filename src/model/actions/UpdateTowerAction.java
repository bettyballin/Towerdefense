package model.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.Money;
import model.entities.Tower;
import ui.Towerdefense;

public class UpdateTowerAction implements Action{
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		Money m = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		Tower tower = (Tower) event.getOwnerEntity();
		if(m.getAmount() >= tower.getCosts()*2){
			m.changeAmount(-tower.getCosts()*2);
			tower.changeStrength(Towerdefense.strengthPerUpdate);
			tower.changeRange(Towerdefense.rangePerUpdate);
			tower.changeSpeed(Towerdefense.speedPerUpdate);
			tower.changeCosts(tower.getCosts()*2);
			if(tower.getID()=="iceTower") tower.changeSlowdown(Towerdefense.slowdownPerUpdate);
		}
	}	
}
