package model.factory;

import org.newdawn.slick.GameContainer;
import model.interfaces.IStrength;
import ui.Towerdefense;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import model.actions.HitAction;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveBackwardAction;
import eea.engine.action.basicactions.MoveDownAction;
import eea.engine.action.basicactions.MoveRightAction;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.OREvent;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.LoopEvent;
import eea.engine.interfaces.IEntityFactory;
import model.actions.ShootAction;
import model.entities.Enemy;
import model.entities.Life;
import model.entities.PathTile;
import model.events.MoveDownEvent;
import model.events.MoveRightEvent;

public class EnemyFactory implements IEntityFactory {

	private final String type;

	public EnemyFactory(String type) {
		this.type = type;
	}

	@Override
	public Entity createEntity() {
		Enemy enemy = new Enemy(type);
		enemy.setScale(0.5f);
		enemy.setPosition(new Vector2f(10,50));

		try {
			enemy.addComponent(new ImageRenderComponent(new Image("assets/"+type+".png")));
		} catch (SlickException e) {
			System.out.println("/assets/"+type+".png not found in EnemyFactory.java");
		}
		
		MoveRightEvent moveRightEvent = new MoveRightEvent("moveright", enemy);
		moveRightEvent.addAction(new MoveRightAction(enemy.getSpeed()/100));
		enemy.addComponent(moveRightEvent);

		MoveDownEvent moveDownEvent = new MoveDownEvent("movedown", enemy);
		moveDownEvent.addAction(new MoveDownAction(enemy.getSpeed()/100));
		enemy.addComponent(moveDownEvent);

		Event decreaseLife = new LeavingScreenEvent();
		decreaseLife.addAction(new DestroyEntityAction());
		decreaseLife.addAction(new Action(){
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				((Life)StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "life")).changeLife(-1);
			}
			
		});
		enemy.addComponent(decreaseLife);
    	
		return enemy;
	}

}
