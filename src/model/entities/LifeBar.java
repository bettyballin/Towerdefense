package model.entities;

import eea.engine.entity.Entity;

public class LifeBar extends Entity{

	private Enemy enemy;
	private int maxHealth;
	private int currentHealth = 50;
	
	public LifeBar(String entityID, Enemy enemy) {
		super(entityID);
		this.enemy = enemy;
		this.maxHealth = 100;
		this.currentHealth = enemy.getLife();
	}

}
