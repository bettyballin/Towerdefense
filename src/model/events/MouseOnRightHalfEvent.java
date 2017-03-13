package model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.event.basicevents.MouseClickedEvent;
import model.entities.TowerTile;

public class MouseOnRightHalfEvent extends MouseClickedEvent {

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		Entity e = getOwnerEntity();
		float x = gc.getInput().getMouseX();
		float y = gc.getInput().getMouseY();
		boolean build = true;
		if(TowerTile.class.isInstance(e)){
			if(((TowerTile) e).hasTower()){
				build = false;
			}
		}
		return (build && (x >= e.getPosition().x && x <= e.getPosition().x + 30)
				&& (y >= e.getPosition().y - 50 && y <= e.getPosition().y + 50));
	}

}