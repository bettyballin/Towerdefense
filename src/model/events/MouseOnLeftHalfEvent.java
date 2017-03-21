package model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import model.entities.TowerTile;
import eea.engine.entity.Entity;
import eea.engine.event.basicevents.MouseClickedEvent;

public class MouseOnLeftHalfEvent extends MouseClickedEvent {

	/*
	 * returns true if mouse is on left half of a towertile
	 */
	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		Entity e = getOwnerEntity();
		float x = gc.getInput().getMouseX();
		float y = gc.getInput().getMouseY();
		boolean build = true;

		if (TowerTile.class.isInstance(e)) {
			if (((TowerTile) e).hasTower()) {
				build = false;
			}
		}
		return (build && x >= e.getPosition().x - 50 && x <= e.getPosition().x && y >= e.getPosition().y - 50
				&& y <= e.getPosition().y + 50);
	}
}
