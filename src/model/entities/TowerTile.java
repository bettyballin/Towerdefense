package model.entities;

import org.newdawn.slick.geom.Vector2f;

import eea.engine.entity.Entity;
import model.interfaces.Tile;

public class TowerTile extends Entity implements Tile{

	private Vector2f position;
	private boolean isVisible;
	
	public TowerTile(String entityID){
		super(entityID);
		this.position = new Vector2f(0,0);
		this.isVisible = false;
	}
	
	@Override
	public void setPosition(int x, int y) {
		position = new Vector2f(x + 50, y + 50);
		
	}

	@Override
	public Vector2f getPosition() {
		return position;
	}
	public boolean getVisibility(){
		return isVisible;
	}
	public void setVisibility(boolean visible){
		this.isVisible = visible;
	}
}
