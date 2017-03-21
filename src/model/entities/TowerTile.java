package model.entities;

import org.newdawn.slick.geom.Vector2f;

import eea.engine.entity.Entity;

public class TowerTile extends Entity {

	private Vector2f position;
	private boolean buttonsVisible;
	private boolean hasTower;
	private Tower tower;

	/**
	 * Constructs a TowerTile entity
	 */
	public TowerTile(String entityID) {
		super(entityID);
		this.position = new Vector2f(0, 0);
		this.buttonsVisible = false;
		this.hasTower = false;
		this.tower = null;
	}

	public void setPosition(int x, int y) {
		position = new Vector2f(x + 50, y + 50);
	}

	public Vector2f getPosition() {
		return position;
	}

	public boolean getButtonVisibility() {
		return buttonsVisible;
	}

	public void setButtonVisibility(boolean visible) {
		this.buttonsVisible = visible;
	}

	public void setHasTower(boolean hasTower) {
		this.hasTower = hasTower;
	}

	public boolean hasTower() {
		return hasTower;
	}

	public void setTower(Tower t) {
		this.tower = t;
	}

	public Tower getTower() {
		return tower;
	}
}
