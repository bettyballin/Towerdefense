package model.actions;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import model.entities.TowerTile;
import ui.Towerdefense;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class MakeTowerSelectionVisibleAction implements Action {

	private ImageRenderComponent img;
	
	public MakeTowerSelectionVisibleAction(){
		try {
			img = new ImageRenderComponent(new Image("assets/towerdot.png"));
		} catch (SlickException en) {
			System.out.println("/assets/towerdot.png not found in MakeTowerSelectionVisibleAction.java");
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		TowerTile tile = (TowerTile)event.getOwnerEntity();
		if(!tile.hasTower()){
			tile.removeComponent("ImageRenderComponent");
			tile.addComponent(img);
			tile.setVisible(true);
		}
	}

}
