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
	
	public MoveRightEvent(String id, Enemy enemy) {
		super(id);
		this.enemy = enemy;
		
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		List<Entity> it = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		for (Entity e : it) {
			if(e.getID().startsWith("pathTile")){
				if((((PathTile) e).getDirection1()=="right" && ((PathTile) e).IsInFirstTilePart(enemy.getPosition())) ||
						((PathTile) e).getDirection2()=="right" && ((PathTile) e).IsInSecondTilePart(enemy.getPosition())){
					return true;
				}
			}
		}
		return false;
	}

}
