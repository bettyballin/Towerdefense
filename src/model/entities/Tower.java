package model.entities;

import eea.engine.entity.Entity;
import model.interfaces.ISpeed;
import model.interfaces.IStrength;

public class Tower extends Entity implements IStrength, ISpeed {

	private int cost;
	private int strength;
	private float speed;
	private int slowdown;
	private int range;
	private boolean showingButtons;

	/**
	 * Constructs a tower entity
	 */
	public Tower(String entityID) {
		super(entityID);
		cost = 0;
		strength = 0;
		speed = 0;
		slowdown = 0;
		range = 0;
		showingButtons = false;
	}

	public void setCosts(int cost) {
		this.cost = cost;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setSlowdown(int slowdown) {
		this.slowdown = slowdown;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void setShowingButtons(boolean show) {
		this.showingButtons = show;
	}

	public int getCosts() {
		return cost;
	}

	public int getStrength() {
		return strength;
	}

	public float getSpeed() {
		return speed;
	}

	public float getSlowdown() {
		return slowdown;
	}

	public int getRange() {
		return range;
	}

	public boolean getShowingButtons() {
		return showingButtons;
	}

	public void changeCosts(int cost) {
		this.cost += cost;
	}

	public void changeStrength(int strength) {
		this.strength += strength;
	}

	public void changeSpeed(int speed) {
		this.speed += speed;
	}

	public void changeSlowdown(int slowdown) {
		this.slowdown += slowdown;
	}

	public void changeRange(int range) {
		this.range += range;
	}

}
