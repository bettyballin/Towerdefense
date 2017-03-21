package model.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.Money;
import model.entities.Tower;
import model.entities.TowerTile;
import model.factory.TowerFactory;
import ui.Towerdefense;

public class SpawnTowerAction implements Action {

	private String towerType;
	private Vector2f position;

	/**
	 * Constructs a spawn tower action
	 * 
	 * @param towerType
	 *            type of tower to be spawned
	 */
	public SpawnTowerAction(String towerType) {
		this.towerType = towerType;
		this.position = new Vector2f(0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		position = new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY());
		float x = position.x % 100;
		float y = position.y % 100;
		position = new Vector2f(position.x - x + 50, position.y - y + 50);
		Entity e = event.getOwnerEntity();
		Money money = (Money) StateBasedEntityManager.getInstance().getEntity(sb.getCurrentStateID(), "money");
		int amount = 0;
		// hole Kosten des Towers je nach Towertyp
		if (towerType == "bulletTower")
			amount = Towerdefense.bulletTower[0];
		else
			amount = Towerdefense.iceTower[0];
		// wenn die Geldmenge hoeher ist als die Kosten des Towers
		if (money.getAmount() >= amount) {
			money.changeAmount(-amount); // ziehe den Kostenbetrag vom Geld ab
			// erstelle einen neuen Tower entsprechend des Towertypen und fuege
			// ihn dem StatebasedEntityManager hinzu
			Tower tower = (Tower) new TowerFactory(towerType, position).createEntity();
			StateBasedEntityManager.getInstance().addEntity(Towerdefense.GAMEPLAYSTATE, tower);
			// aktualisieren des Towertiles, wo der Tower gebaut wurde
			if (TowerTile.class.isInstance(e)) {
				((TowerTile) e).setHasTower(true);
				((TowerTile) e).setTower(tower);
			}
		}
	}
}
