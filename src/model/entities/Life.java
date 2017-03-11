package model.entities;

import ui.Towerdefense;
import eea.engine.entity.Entity;
import model.interfaces.ILife;
import model.options.Options;

public class Life extends Entity implements ILife {

	private int lifeLeft;

	public Life(String entityID) {
		super(entityID);
		if (Options.getInstance().getDifficulty() == "LEICHT") {
			this.lifeLeft = Towerdefense.lifeEinfach;
		}
		if (Options.getInstance().getDifficulty() == "NORMAL") {
			this.lifeLeft = Towerdefense.lifeNormal;
		} else {
			this.lifeLeft = Towerdefense.lifeSchwer;
		}
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
		if (lifeLeft < 0)
			lifeLeft = 0;
	}

}
