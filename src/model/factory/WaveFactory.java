package model.factory;

import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;
import model.actions.SpawnEnemyAction;
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
		Entity wave = new Entity("wave");
		TimeEvent waveTimer = new TimeEvent(timeBetweenSpawns, true);
		waveTimer.addAction(new SpawnEnemyAction(enemyType, waveSize));
		wave.addComponent(waveTimer);

		return wave;
	}
}
