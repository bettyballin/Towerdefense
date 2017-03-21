package model.factory;

import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;
import model.entities.PathTile;
import model.path.Path;

public class PathTileFactory implements IEntityFactory {

	private final List<PathTile> pathTiles;
	private final int[][] pathArray;
	private int nextTile;

	/**
	 * Constructs a pathtile factory
	 * 
	 * @param path
	 *            the given path which is currently only filled with 1s
	 */
	public PathTileFactory(Path path) {
		this.pathArray = path.getPathArray();
		this.pathTiles = path.getPathTiles();
		this.nextTile = 0;
	}

	public Boolean hasEntitiesLeft() {
		return (this.nextTile < pathTiles.size());
	}

	public int[][] getPath() {
		return pathArray;
	}

	@Override
	public Entity createEntity() {
		PathTile tile = pathTiles.get(nextTile++);
		try {
			tile.addComponent(new ImageRenderComponent(new Image("assets/dirt2.png")));
		} catch (SlickException e) {
			System.out.println("/assets/dirt2.png not found in PathTileFactory.java");
		}
		return tile;
	}

}
