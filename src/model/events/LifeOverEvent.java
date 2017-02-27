package model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import model.entities.Life;
import ui.Towerdefense;

public class LifeOverEvent extends Event{

	public LifeOverEvent(String id) {
		super(id);
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		return ((Life)StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "life")).getLife() == 0;
	}

}
