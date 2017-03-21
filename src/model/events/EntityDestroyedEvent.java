package model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

public class EntityDestroyedEvent extends Event {

	private String entityID;

	/**
	 * Constructs an event which returns true when entity is not existing in the
	 * current StateBasedEntityManager
	 */
	public EntityDestroyedEvent(String entityID) {
		super("PlayerDestroyedEvent");
		this.entityID = entityID;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		return !StateBasedEntityManager.getInstance().hasEntity(sb.getCurrentStateID(), entityID);
	}

}
