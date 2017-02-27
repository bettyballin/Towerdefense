package model.entities;

import org.newdawn.slick.geom.Vector2f;

import eea.engine.entity.Entity;
import model.interfaces.Tile;

public class PathTile extends Entity implements Tile{

	private Vector2f position;
	private String direction1; // right, down
	private String direction2; // right, down
	
	public PathTile(String entityID) {
		super(entityID);
		this.position = new Vector2f(0, 0);
		this.direction1 = "down";  // ↓
		this.direction2 = "right"; // →
	}

	@Override
	public Vector2f getPosition() {
		return position;
	}
	@Override
	public void setPosition(int x, int y) {
		position = new Vector2f(x + 50, y + 50);
	}

	public boolean IsInFirstTilePart(Vector2f pos) {
		if (direction1 == "down") { // ↓?
			return (pos.x >= position.x - 50 && pos.x < position.x + 50 && pos.y >= position.y - 50
					&& pos.y <= position.y);

		}
		if (direction1 == "right") { // →?
			return (pos.y >= position.y - 50 && pos.y < position.y + 50 && pos.x >= position.x - 50
					&& pos.x <= position.x);
		}
		return false;
	}

	public boolean IsInSecondTilePart(Vector2f pos) {
		return (pos.x >= position.x && pos.x <= position.x + 50 && pos.y >= position.y && pos.y <= position.y + 50);
	}

	public void setDirection1(String dir) {
		if (dir == "up" || dir == "right" || dir == "down" || dir == "left" || dir == "stop") {
			this.direction1 = dir;
		}
	}

	public void setDirection2(String dir) {
		if (dir == "up" || dir == "right" || dir == "down" || dir == "left" || dir == "stop") {
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
