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
import model.entities.Shoot;
import model.entities.Tower;
import model.factory.ExplosionFactory;
import ui.Towerdefense;

public class HitAction implements Action {

	private final Tower tower;

	public HitAction(Tower tower) {
		this.tower = tower;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		// only do sth if collided entity is an enemy
		if (Enemy.class.isInstance(((CollisionEvent) event).getCollidedEntity())) {
			int state = Towerdefense.GAMEPLAYSTATE;
			Enemy enemy = (Enemy) ((CollisionEvent) event).getCollidedEntity();
			Shoot shoot = (Shoot) event.getOwnerEntity();

			// remove shot
			StateBasedEntityManager.getInstance().removeEntity(state, event.getOwnerEntity());

			// lower life of enemy
			enemy.changeLife(-tower.getStrength());
			if (tower.getID() == "iceTower" && enemy.getSpeed() > 0) {
				enemy.changeSpeed(-tower.getSlowdown());
				enemy.setIceHit(true);
			}
			// if no life left in enemy, remove him
			if (enemy.getLife() == 0) {
				StateBasedEntityManager.getInstance().removeEntity(state, enemy);

				// create explosion at the place where enemy was shot
				Entity explosion = new Entity("Explosion");
				explosion = new ExplosionFactory(tower, shoot.getPosition().x + 40, shoot.getPosition().y + 50, 30, 50)
						.createEntity();
				StateBasedEntityManager.getInstance().addEntity(state, explosion);

				explosion = new ExplosionFactory(tower, shoot.getPosition().x + 30, shoot.getPosition().y + 30, 100,
						120).createEntity();
				StateBasedEntityManager.getInstance().addEntity(state, explosion);

				// get money for dead enemy
				Money money = (Money) StateBasedEntityManager.getInstance().getEntity(state, "money");
				money.changeAmount(Towerdefense.moneyPerEnemy);
			}
		}
	}
}
