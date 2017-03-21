package model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import model.actions.HitAction;
import model.entities.Shoot;
import model.entities.Tower;
import model.interfaces.ISpeed;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.event.basicevents.LoopEvent;
import eea.engine.interfaces.IEntityFactory;

public class ShootFactory implements IEntityFactory {

	protected final float rotation;
	protected final Vector2f position;
	protected final Tower tower;

	/**
	 * Constructs a shoot factory
	 * 
	 * @param tower
	 *            the tower which is shooting
	 * @param rotation
	 *            the orientation in which the tower has to shoot to hit the
	 *            enemy
	 */
	public ShootFactory(Tower tower, float rotation) {
		this.tower = tower;
		this.rotation = rotation;
		this.position = new Vector2f(tower.getPosition().x, tower.getPosition().y);
	}

	@Override
	public Entity createEntity() {
		Entity shoot = new Shoot("shoot", tower.getStrength());
		shoot.setPosition(position);
		shoot.setRotation(rotation);
		shoot.setScale(0.3f);

		// get image for shoot
		String img = "shoot";
		if (tower.getID() == "iceTower")
			img = "icicle";
		try {
			Image image = new Image("assets/" + img + ".png");
			shoot.addComponent(new ImageRenderComponent(image));
		} catch (SlickException e) {
			System.err.println("/assets/" + img + ".png not found in ShootFactory.java");
		}

		// destroy shoot if it leaves the display
		Event mainEvent = new LeavingScreenEvent();
		mainEvent.addAction(new DestroyEntityAction());
		shoot.addComponent(mainEvent);

		// if the shoot collides with an enemy, call HitAction
		mainEvent = new CollisionEvent();
		mainEvent.addAction(new HitAction(tower));
		shoot.addComponent(mainEvent);

		// let shoot entity move forward with the given speed
		mainEvent = new LoopEvent();
		mainEvent.addAction(new MoveForwardAction((ISpeed) shoot));
		shoot.addComponent(mainEvent);

		return shoot;
	}

}
