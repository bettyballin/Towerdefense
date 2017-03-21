package model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.event.basicevents.MouseClickedEvent;

public class MapClickEvent extends MouseClickedEvent {

	private Vector2f position;

	/**
	 * Constructs an event that turns true if the map is clicked on a position
	 * that contains a valid towertile
	 */
	public MapClickEvent() {
		super();
		this.position = new Vector2f(0, 0);
	}

	/*
	 * check if the position of the tower is at a valid towertile position
	 */
	private boolean isValidTowerTilePosition(Entity tile) {
		return (position.x >= tile.getPosition().x - 50 && position.x <= tile.getPosition().x + 50
				&& position.y >= tile.getPosition().y - 50 && position.y <= tile.getPosition().y + 50);
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		// save the position of the mouse
		position = new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY());
		boolean isValidPosition = false;
		if (isValidTowerTilePosition(getOwnerEntity())) {
			isValidPosition = true;
		}
		// return true only when mouse is clicked at a valid position
		return (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && isValidPosition);
	}

}
