package model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.event.Event;
import model.entities.TowerTile;

public class MouseNotOverTowerEvent extends Event{

	public MouseNotOverTowerEvent(String id) {
		super(id);
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		return !((TowerTile) getOwnerEntity()).hasTower();
	}

}
