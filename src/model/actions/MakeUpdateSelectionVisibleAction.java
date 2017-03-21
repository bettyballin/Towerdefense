package model.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import model.entities.Tower;

public class MakeUpdateSelectionVisibleAction implements Action{

	private ImageRenderComponent img;
	
	/*
	 * make update and delete buttons visible for certain type of tower
	 */
	public MakeUpdateSelectionVisibleAction(String tower){
		try {
			img = new ImageRenderComponent( new Image("assets/"+tower+"buttons.png"));
		} catch (SlickException en) {
			System.out.println("/assets/"+tower+"buttons.png not found in MakeTowerSelectionVisibleAction.java");
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		Entity tower = event.getOwnerEntity();
		tower.removeComponent("ImageRenderComponent");
		tower.addComponent(img);
		((Tower) tower).setShowingButtons(true);
	}

}
