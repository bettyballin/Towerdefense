package model.entities;

import eea.engine.entity.Entity;
import model.interfaces.ILife;
import model.interfaces.ISpeed;
import model.interfaces.IStrength;
import ui.Towerdefense;

public class Enemy extends Entity implements ILife, ISpeed {

	private int life;
	private final int maxLife;
	private float speed;
	
	public Enemy(String entityID) {
		super(entityID);

		int[] stats = {0,0,0};
		if(entityID == "spider"){
			stats = Towerdefense.spiderEnemy;
		}
		else stats = Towerdefense.wespEnemy;
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

	public int getLife() {
		return life;
	}

	public float getSpeed() {
		return speed;
	}

	public void changeLife(int life) {
		this.life += life;
		if (this.life < 0) this.life = 0;
	}

	public void changeSpeed(float speed) {
		this.speed += speed;
		if (this.speed < 0) this.speed = 0;
	}

}
