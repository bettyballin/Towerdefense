package model.actions;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import model.entities.TowerTile;
import ui.Towerdefense;

public class MakeUnvisibleAction implements Action {
	
	private ImageRenderComponent image;
	
	public MakeUnvisibleAction(){
		try{
			image = new ImageRenderComponent(new Image("assets/towerdot.png"));
		}catch (SlickException e) {
			System.out.println("/assets/towerdot.png not found in MakeUnVisibleAction.java");
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		Vector2f mousePosition = new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY());
		List<Entity> entitylist = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		for(Entity entity : entitylist){
			if(entity.getID()=="towertile"){
				if(((TowerTile) entity).isVisible()) {
					entity.removeComponent("ImageRenderComponent");
					entity.addComponent(image);
					entity.setVisible(false);
				}
			}
		}
	}
}
