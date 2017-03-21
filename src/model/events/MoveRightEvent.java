package model.events;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import model.entities.Enemy;
import model.entities.PathTile;
import ui.Towerdefense;

public class MoveRightEvent extends Event {

	private final Enemy enemy;

	/**
	 * Constructs a move right event
	 * 
	 * @param id
	 * @param enemy
	 *            the enemy which asks if he has to right down or not
	 */
	public MoveRightEvent(String id, Enemy enemy) {
		super(id);
		this.enemy = enemy;
		
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		List<Entity> it = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		for (Entity e : it) {
			if(e.getID().startsWith("pathTile")){
				// return true if enemy is in first part of tile and first direction of tile is right
				// or he is in second part and second direction is right
				if((((PathTile) e).getDirection1()=="right" && ((PathTile) e).IsInFirstTilePart(enemy.getPosition())) ||
						((PathTile) e).getDirection2()=="right" && ((PathTile) e).IsInSecondTilePart(enemy.getPosition())){
					return true;
				}
			}
		}
		return false;
	}

}
