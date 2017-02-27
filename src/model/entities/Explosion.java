package model.entities;

import model.interfaces.ISpeed;
import eea.engine.entity.Entity;

public class Explosion extends Entity implements ISpeed {

	private float width;
	private float height;
	private float speed;
	
	public Explosion(String id, float width , float height, float speed) {
		super(id);
		this.width = width;
		this.height = height;
		this.speed = speed;
	}

	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	@Override
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public float getSpeed() {
		return this.speed;
	}

}
