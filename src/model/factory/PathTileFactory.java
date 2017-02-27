package model.factory;

import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;
import model.entities.Path;
import model.entities.PathTile;

public class PathTileFactory implements IEntityFactory {

	private final List<PathTile> route;
	private final int[][] mapArray;
	private int nextTile;

	public PathTileFactory(String name) {
		Path route = new Path(name);
		this.route = route.getRoute();
		this.mapArray = route.getMapArray();
		this.nextTile = 0;
	}

	public Boolean hasEntitiesLeft(){
		return (this.nextTile < route.size());
	}
	public int[][] getMapArray(){
		return mapArray;
	}
	
	@Override
	public Entity createEntity() {
		PathTile tile = route.get(nextTile++);
		try{
			tile.addComponent(new ImageRenderComponent(new Image("assets/dirt2.png")));
		}catch (SlickException e) {
			System.out.println("/assets/dirt2.png not found in PathTileFactory.java");
		}
		return tile;
	}

}
