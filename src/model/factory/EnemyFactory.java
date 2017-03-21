package model.factory;

import org.newdawn.slick.GameContainer;

import ui.Towerdefense;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveDownAction;
import eea.engine.action.basicactions.MoveRightAction;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.interfaces.IEntityFactory;
import model.entities.Enemy;
import model.entities.Life;
import model.events.MoveDownEvent;
import model.events.MoveRightEvent;

public class EnemyFactory implements IEntityFactory {

	private final String type;
	private Image img;

	/**
	 * Constructs an enemy factory with the given type of enemy (spider or wasp)
	 */
	public EnemyFactory(String type) {
		this.type = type;
		try {
			img = new Image("assets/" + type + ".png");
		} catch (SlickException e) {
			System.err.println("/assets/" + type + ".png not found in EnemyFactory.java");
		}
	}

	@Override
	public Entity createEntity() {
		// create new enemy and set it to the beginning of the path
		Enemy enemy = new Enemy(type);
		enemy.setScale(0.5f);
		enemy.setPosition(new Vector2f(10, 50));
		enemy.addComponent(new ImageRenderComponent(img));

		// enemy moves right on path if the direction of the current part of the
		// pathtile is "right"
		MoveRightEvent moveRightEvent = new MoveRightEvent("moveright", enemy);
		moveRightEvent.addAction(new MoveRightAction(enemy));
		enemy.addComponent(moveRightEvent);

		// enemy moves down on path if the direction of the current part of the
		// pathtile is "down"
		MoveDownEvent moveDownEvent = new MoveDownEvent("movedown", enemy);
		moveDownEvent.addAction(new MoveDownAction(enemy));
		enemy.addComponent(moveDownEvent);

		// enemy disappears after leaving the screen and decreases the life
		// amount of life entity
		Event decreaseLife = new LeavingScreenEvent();
		decreaseLife.addAction(new DestroyEntityAction());
		decreaseLife.addAction(new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				((Life) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "life"))
						.changeLife(-1);
			}
		});
		enemy.addComponent(decreaseLife);

		return enemy;
	}

}
