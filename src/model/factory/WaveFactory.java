package model.factory;

import java.util.List;

import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;
import model.actions.SpawnEnemyAction;
import model.entities.Enemy;
import model.entities.Wave;
import model.events.TimeEvent;

public class WaveFactory implements IEntityFactory {

	private int waveSize;
	private String enemyType;
	private int timeBetweenSpawns;
	
	public WaveFactory(String enemy, int waveSize, int timeBetweenSpawns) {
		this.waveSize = waveSize;
		this.enemyType = enemy;
		this.timeBetweenSpawns = timeBetweenSpawns;
	}
	@Override
	public Entity createEntity() {
		Wave wave = new Wave(enemyType,waveSize);
		
		TimeEvent waveTimer = new TimeEvent(timeBetweenSpawns, true);
		waveTimer.addAction(new SpawnEnemyAction(wave.getWave()));
		wave.addComponent(waveTimer);
		
		return wave;
	}
}
