package model.entities;

import model.interfaces.IStrength;
import eea.engine.entity.Entity;

public class Shoot extends Entity implements IStrength {

	protected int strength;

	public Shoot(String id, int strength) {
		super(id);
		this.strength = strength;
	}

	@Override
	public void changeStrength(int value) {
		strength += value;
		if(strength < 0) strength = 0;
	}

	@Override
	public int getStrength() {
		return this.strength;
	}

	@Override
	public void setStrength(int strength) {
		this.strength = strength;
	}

}
