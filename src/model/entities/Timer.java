package model.entities;

import eea.engine.entity.Entity;

public class Timer extends Entity {

	private int timer;
	private int waveCount;

	/**
	 * Constructs a timer which is set up to count 5000 seconds back and saves
	 * the amount of enemies of a wave
	 */
	public Timer(String entityID) {
		super(entityID);
		this.timer = 5000;
		this.waveCount = 0;
	}

	public void setTimer(int time) {
		this.timer = time;
	}

	public int getWaveCount() {
		return waveCount;
	}

	public void incWaveCount() {
		waveCount++;
	}

	public int getCurrentTimer() {
		return timer;
	}

	/**
	 * counts back one second
	 */
	public void tick() {
		if (this.timer >= 1000)
			this.timer -= 1000;
	}

}
