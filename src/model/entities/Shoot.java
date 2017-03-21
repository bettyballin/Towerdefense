package model.entities;

import model.interfaces.ISpeed;
import model.interfaces.IStrength;
import eea.engine.entity.Entity;

public class Shoot extends Entity implements IStrength, ISpeed {

	protected int strength;
	protected float speed;

	/**
	 * Constructs a shoot entity with a given strength, sets shooting speed to
	 * 70
	 */
	public Shoot(String id, int strength) {
		super(id);
		this.strength = strength;
		this.speed = 70f;
	}

	@Override
	public void changeStrength(int value) {
		strength += value;
		if (strength < 0)
			strength = 0;
	}

	@Override
	public int getStrength() {
		return this.strength;
	}

	@Override
	public void setStrength(int strength) {
		this.strength = strength;
	}

	@Override
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public float getSpeed() {
		return speed;
	}

}
