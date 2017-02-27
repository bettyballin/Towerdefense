package model.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import model.factory.ShootFactory;
import model.events.EnemyDetectionEvent;
import model.entities.Enemy;
import model.entities.Tower;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.event.ANDEvent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class ShootAction implements Action {

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		// crazy cast from ANDEvent to EnemyDetectionEvent to get collided entity (enemy)
		Enemy enemy = (Enemy) ((EnemyDetectionEvent) ((ANDEvent) event).getEvents()[0]).getCollidedEntity();
		Tower tower = (Tower) event.getOwnerEntity();
		
		// get rotation for shoot orientation
		float rotation = getRotation(enemy.getPosition(), tower.getPosition());
		
		Entity shoot = new ShootFactory(tower, rotation).createEntity();
		StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(), shoot);
	}
	
	private float getRotation(Vector2f enemy, Vector2f tower) {
		float enemyX = Math.abs(enemy.x-tower.x);
		if(enemy.x < tower.x) enemyX = -enemyX;								// enemy on the left of tower
		float enemyY = Math.abs(enemy.y-tower.y);
		if(enemy.y > tower.y) enemyY = -enemyY;								// enemy below tower
		double enemyNorm = Math.sqrt(enemyX*enemyX+enemyY*enemyY);			// length of enemy vector = 1
		double towerNorm = Math.sqrt(Math.abs(enemyY)*Math.abs(enemyY));	// length of tower vector = 1
		double dot_product = enemyY/enemyNorm*(Math.abs(enemyY)/towerNorm); 
		Double angle = Math.toDegrees(Math.acos(dot_product));
		if (enemy.x < tower.x) angle = -angle; 								// enemy on the left of tower
		return angle.floatValue();
	}

}
