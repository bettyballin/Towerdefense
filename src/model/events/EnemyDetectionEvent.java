package model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import model.entities.Enemy;
import model.entities.Tower;

public class EnemyDetectionEvent extends Event {

	Tower tower;
	Entity collider;

	/**
	 * Constructs an enemy detection event
	 * 
	 * @param id
	 * @param tower
	 *            the tower which is currently detecting the collision with the
	 *            enemy
	 */
	public EnemyDetectionEvent(String id, Tower tower) {
		super(id);
		this.tower = tower;
		this.collider = null;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		boolean perform = false;
		for (Entity e : StateBasedEntityManager.getInstance().getEntitiesByState(sb.getCurrentStateID())) {
			if (Enemy.class.isInstance(e)) {
				// if enemy is in range of the tower, return true
				if (isInRange(e)) {
					perform = true;
					collider = e;
				}
			}
		}
		return perform;
	}

	private boolean isInRange(Entity enemy) {
		float absX = Math.abs(tower.getPosition().x - enemy.getPosition().x);
		float absY = Math.abs(tower.getPosition().y - enemy.getPosition().y);
		int range = tower.getRange();
		return (absX <= range / 2 && absY <= range / 2);
	}

	public Entity getCollidedEntity() {
		return collider;
	}

}
