package model.events;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import model.entities.TowerTile;
import ui.Towerdefense;

public class MouseOverTowerTileEvent  extends Event{

	private Vector2f position;
	private TowerTile currentTile;

	public MouseOverTowerTileEvent(String name) {
		super(name);
		this.currentTile = new TowerTile("towertile");
		this.position = new Vector2f(0,0);		
	}

	private boolean isValidTowerTilePosition(TowerTile tile){
		return (position.x >= tile.getPosition().x - 30 && position.x <= tile.getPosition().x + 30
				&& position.y >= tile.getPosition().y - 30 && position.y <= tile.getPosition().y + 30);
	}
	
	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		List<Entity> it = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		position = new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY());	
		boolean isValidPosition = false;
		for(Entity e : it){
			if(e.getID() == "towertile"){
				if(isValidTowerTilePosition((TowerTile) e) && !((TowerTile)e).hasTower()){
					isValidPosition = true;
					currentTile = (TowerTile) e;
				}
			}
		}
		return isValidPosition;
	}
}
