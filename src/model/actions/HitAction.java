package model.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.CollisionEvent;
import model.entities.Enemy;
import model.entities.Money;
import model.entities.Tower;
import model.factory.ExplosionFactory;
import ui.Towerdefense;

public class HitAction implements Action {

	private final Tower tower;

	/**
	 * Constructs a hit action which is caused when enemy got hit by a shoot
	 * 
	 * @param tower
	 *            which has caused the shoot
	 */
	public HitAction(Tower tower) {
		this.tower = tower;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		// only do sth if collided entity is an enemy
		if (Enemy.class.isInstance(((CollisionEvent) event).getCollidedEntity())) {
			int state = Towerdefense.GAMEPLAYSTATE;
			Enemy enemy = (Enemy) ((CollisionEvent) event).getCollidedEntity();

			// remove shoot
			StateBasedEntityManager.getInstance().removeEntity(state, event.getOwnerEntity());

			// lower life of enemy
			enemy.changeLife(-tower.getStrength());
			
			// slow enemy down if it got hit by an iceTower
			if (tower.getID() == "iceTower" && enemy.getSpeed() > 0) {
				enemy.changeSpeed(-tower.getSlowdown());
				enemy.setIceHit(true);
			}
			// if no life left in enemy, remove him
			if (enemy.getLife() == 0) {
				StateBasedEntityManager.getInstance().removeEntity(state, enemy);
				
				// create two cool explosions at the place where enemy was shot
				Entity explosion = new Entity("Explosion");
				explosion = new ExplosionFactory(tower, enemy.getPosition().x, enemy.getPosition().y + 10, 30, 50)
						.createEntity();
				StateBasedEntityManager.getInstance().addEntity(state, explosion);

				explosion = new ExplosionFactory(tower, enemy.getPosition().x, enemy.getPosition().y, 100, 120)
						.createEntity();
				StateBasedEntityManager.getInstance().addEntity(state, explosion);
				
				// get money for dead enemy
				Money money = (Money) StateBasedEntityManager.getInstance().getEntity(state, "money");
				money.changeAmount(Towerdefense.moneyPerEnemy);
			}
		}
	}
}
