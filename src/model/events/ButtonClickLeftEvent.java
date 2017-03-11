package model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import model.entities.TowerTile;
import eea.engine.entity.Entity;
import eea.engine.event.basicevents.MouseClickedEvent;

public class ButtonClickLeftEvent extends MouseClickedEvent {

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		Entity e = getOwnerEntity();
		float x = gc.getInput().getMouseX();
		float y = gc.getInput().getMouseY();
		return(x >= e.getPosition().x - 30 && x <= e.getPosition().x && y >= e.getPosition().y - 50
				&& y <= e.getPosition().y + 50);
	}
}
