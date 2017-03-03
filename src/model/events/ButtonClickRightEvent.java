package model.events;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.MouseClickedEvent;
import ui.Towerdefense;

public class ButtonClickRightEvent extends MouseClickedEvent {

	private Vector2f position;

	public ButtonClickRightEvent() {
		super();
		this.position = new Vector2f(0, 0);

	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		List<Entity> it = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		position = new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY());
		boolean isValidPosition = false;
		float x = 0;
		float y = 0;
		for (Entity e : it) {
			if (e.getID() == "towerbutr") {
				if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
					x = gc.getInput().getMouseX();
					y = gc.getInput().getMouseY();
				
					//if right button is clicked, proof the position of Input Mouse and right button
					if (((e.getPosition().x - 10) < x) && (x <= e.getPosition().x + 10) && (e.getPosition().y - 10 < y)
							&& (y <= e.getPosition().y + 10))
						isValidPosition = true;
					

				}
			}

		}
		return isValidPosition;
	}

}