package model.entities;

import eea.engine.entity.Entity;
import model.interfaces.ILife;
import model.interfaces.ISpeed;
import model.options.Options;
import ui.Towerdefense;

public class Enemy extends Entity implements ILife, ISpeed {

	private int life;
	private final int maxLife;
	private float speed;
	private boolean isBlue;
	
	/**
	 * Constructs an enemy with the stats of Towerdefense
	 */
	public Enemy(String entityID) {
		super(entityID);
		this.isBlue = false;
		int[] stats = { 0, 0, 0 };
		/*
		 * wenn das Enemy eine Spinne ist, setze je nach Schwierigkeitsstufe
		 * stats mit den initialisierten Arrays aus Towerdefense
		 */
		if (entityID == "spider") {
			if (Options.getInstance().getDifficulty() == "NORMAL") {
				stats = Towerdefense.spiderEnemyNormal;
			} else if (Options.getInstance().getDifficulty() == "SCHWER") {
				stats = Towerdefense.spiderEnemySchwer;
			} else {
				stats = Towerdefense.spiderEnemyEinfach;
			}
			// ansonsten handelt es sich um eine Wespe
		} else {
			if (Options.getInstance().getDifficulty() == "NORMAL") {
				stats = Towerdefense.waspEnemyNormal;
			} else if (Options.getInstance().getDifficulty() == "SCHWER") {
				stats = Towerdefense.waspEnemySchwer;
			} else {
				stats = Towerdefense.waspEnemyEinfach;
			}
		}
		this.life = stats[0];
		this.maxLife = stats[0];
		this.speed = stats[1];
	}

	public float getMaxLife() {
		return maxLife;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setIceHit(boolean isBlue) {
		this.isBlue = isBlue;
	}

	public boolean getIceHit() {
		return isBlue;
	}

	public int getLife() {
		return life;
	}

	public float getSpeed() {
		return speed;
	}

	public void changeLife(int life) {
		this.life += life;
		if (this.life < 0)
			this.life = 0;
	}

	public void changeSpeed(float speed) {
		this.speed += speed;
		if (this.speed < 0)
			this.speed = 0;
	}

}
