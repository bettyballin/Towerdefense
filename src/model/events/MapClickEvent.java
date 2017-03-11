package model.events;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.event.basicevents.MouseClickedEvent;
import model.entities.TowerTile;

public class MapClickEvent extends MouseClickedEvent {

	private Vector2f position;

	public MapClickEvent() {
		super();
		this.position = new Vector2f(0, 0);
	}

	private boolean isValidTowerTilePosition(TowerTile tile) {
		return (position.x >= tile.getPosition().x - 50 && position.x <= tile.getPosition().x + 50
				&& position.y >= tile.getPosition().y - 50 && position.y <= tile.getPosition().y + 50);
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		position = new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY());
		boolean isValidPosition = false;
		Entity e = getOwnerEntity();
		if (isValidTowerTilePosition((TowerTile) e)) {
			isValidPosition = true;
		}
		return (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && isValidPosition);
	}

}
