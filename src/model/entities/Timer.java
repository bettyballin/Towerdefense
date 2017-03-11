package model.entities;

import eea.engine.action.Action;
import eea.engine.entity.Entity;

public class Timer extends Entity {

	private int timer;
	private int count;
	
	public Timer(String entityID) {
		super(entityID);
		this.timer = 5000;
		this.count = 5;
	}
	public void setTimer(int time){
		this.timer = time;
	}

	public int generateWaveCount(int min, int max){
		Double count = Math.random() * max;
		if (count < min)
			count += min;
		this.count = count.intValue();
		return count.intValue();
	}
	
	public int getCurrentTimer(){
		return timer;
	}

	public void tick() {
		if(this.timer >= 1000) this.timer -= 1000;
	}

}
