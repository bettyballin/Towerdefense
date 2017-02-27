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

	public int generateWaveTimer() {
		Double timer = Math.random() * 15000;
		timer -= timer%1000;					// remove milliseconds
		if (timer < 5000)
			timer += 5000;
		this.timer = 5000;
		return 5000;
	}
	
	public int generateWaveCount(){
		Double count = Math.random() * 8;
		if (count < 2)
			count += 2;
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
