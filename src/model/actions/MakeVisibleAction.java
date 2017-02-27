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

public class MakeVisibleAction implements Action {


	private ImageRenderComponent image;
	
	public MakeVisibleAction(){
		try{
			image = new ImageRenderComponent(new Image("assets/towerdot2.png"));
		}catch (SlickException e) {
			System.out.println("/assets/towerdot2.png not found in MakeVisibleAction.java");
		}
	}
	
	private boolean isValidTowerTilePosition(TowerTile tile, Vector2f position){
		return (position.x >= tile.getPosition().x - 30 && position.x <= tile.getPosition().x + 30
				&& position.y >= tile.getPosition().y - 30 && position.y <= tile.getPosition().y + 30);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		Vector2f mousePosition = new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY());
		List<Entity> entitylist = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		for(Entity entity : entitylist){
			if(entity.getID()=="towertile"){
				if(isValidTowerTilePosition((TowerTile) entity, mousePosition)) {
					entity.removeComponent("ImageRenderComponent");
					entity.addComponent(image);
					entity.setVisible(true);
				}
			}
		}
	}

}
