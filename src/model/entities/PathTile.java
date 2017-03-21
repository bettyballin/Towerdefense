package model.entities;

import org.newdawn.slick.geom.Vector2f;

import eea.engine.entity.Entity;

public class PathTile extends Entity {

	private Vector2f position;
	private String direction1; // down
	private String direction2; // right

	/**
	 * Constructs a PathTile entity with the directions down -> right
	 */
	public PathTile(String entityID) {
		super(entityID);
		this.position = new Vector2f(0, 0);
		this.direction1 = "down";
		this.direction2 = "right";
	}

	@Override
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		position = new Vector2f(x + 50, y + 50);
	}

	/**
	 * determines if a position is in the first part of the tile
	 * 
	 * @param pos
	 *            position vector which is tested to be in first tile part
	 * 
	 */
	public boolean IsInFirstTilePart(Vector2f pos) {
		if (direction1 == "down") {
			return (pos.x >= position.x - 50 && pos.x < position.x + 50 && pos.y >= position.y - 50
					&& pos.y <= position.y);

		}
		if (direction1 == "right") {
			return (pos.y >= position.y - 50 && pos.y < position.y + 50 && pos.x >= position.x - 50
					&& pos.x <= position.x);
		}
		return false;
	}

	/**
	 * determines if a position is in the second part of the tile
	 * 
	 * @param pos
	 *            position vector which is tested to be in second tile part
	 * 
	 */
	public boolean IsInSecondTilePart(Vector2f pos) {
		return (pos.x >= position.x && pos.x <= position.x + 50 && pos.y >= position.y && pos.y <= position.y + 50);
	}

	public void setDirection1(String dir) {
		if (dir == "up" || dir == "right" || dir == "down" || dir == "left") {
			this.direction1 = dir;
		}
	}

	public void setDirection2(String dir) {
		if (dir == "up" || dir == "right" || dir == "down" || dir == "left") {
			this.direction2 = dir;
		}
	}

	public String getDirection1() {
		return this.direction1;
	}

	public String getDirection2() {
		return this.direction2;
	}

}
