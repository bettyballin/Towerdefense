package model.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import model.entities.TowerTile;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;

public class MakeTowerSelectionVisibleAction implements Action {

	private ImageRenderComponent img;

	public MakeTowerSelectionVisibleAction() {
		try {
			img = new ImageRenderComponent(new Image("assets/towerdot.png"));
		} catch (SlickException en) {
			System.out.println("/assets/towerdot.png not found in MakeTowerSelectionVisibleAction.java");
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		TowerTile tile = (TowerTile) event.getOwnerEntity();
		tile.removeComponent("ImageRenderComponent");
		tile.addComponent(img);
		tile.setVisible(true);
	}
}
