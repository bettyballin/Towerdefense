package model.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.Money;
import model.entities.Tower;
import ui.Towerdefense;

public class UpdateTowerAction implements Action {

	/**
	 * Action that is called when update button of a tower is clicked
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		// hole den Wert des Geldes vom StateBasedEntityManager
		Money m = (Money) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "money");
		Tower tower = (Tower) event.getOwnerEntity();
		// wenn mehr Geld da ist, als das Update kostet und zwar das doppelte
		// der Kosten des Towers
		if (m.getAmount() >= tower.getCosts() * 2) {
			// ziehe die Kosten vom Geld ab
			// veraendere die Staerke, die Reichweite, die Schnelligkeit wie in
			// Towerdefense initialisiert
			m.changeAmount(-tower.getCosts() * 2);
			tower.changeStrength(Towerdefense.strengthPerUpdate);
			tower.changeRange(Towerdefense.rangePerUpdate);
			tower.changeSpeed(Towerdefense.speedPerUpdate);
			// double the costs of tower
			tower.changeCosts(tower.getCosts() * 2);

			// if tower is iceTower, increase the slowdown of it
			if (tower.getID() == "iceTower")
				tower.changeSlowdown(Towerdefense.slowdownPerUpdate);
		}
	}
}
