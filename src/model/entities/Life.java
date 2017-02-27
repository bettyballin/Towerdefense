package model.entities;

import eea.engine.entity.Entity;
import ui.Towerdefense;
import model.interfaces.ILife;

public class Life extends Entity implements ILife{

	private int lifeLeft;
	
	public Life(String entityID) {
		super(entityID);
		this.lifeLeft = Towerdefense.life;
	}

	@Override
	public void setLife(int life) {
		lifeLeft = life;
	}

	@Override
	public int getLife() {
		return lifeLeft;
	}

	@Override
	public void changeLife(int life) {
		lifeLeft += life;
		if(lifeLeft < 0) lifeLeft = 0;
	}
	
	
		
}
