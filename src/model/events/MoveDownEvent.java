package model.events;

import model.entities.Enemy;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import model.entities.PathTile;
import ui.Towerdefense;

public class MoveDownEvent extends Event{

	private final Enemy enemy;

	public MoveDownEvent(String id, Enemy enemy) {
		super(id);
		this.enemy = enemy;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		List<Entity> it = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		for(Entity e : it){
			if(e.getID().startsWith("pathTile")){
				System.out.println("moveDown");
				if((((PathTile) e).getDirection1()=="down" && ((PathTile) e).IsInFirstTilePart(enemy.getPosition())) ||
						((PathTile) e).getDirection2()=="down" && ((PathTile) e).IsInSecondTilePart(enemy.getPosition())){
					return true;
				}
			}
		}
		return false;
	}

}
