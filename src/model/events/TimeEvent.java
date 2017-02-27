package model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import model.StopWatch;

import eea.engine.event.Event;

public class TimeEvent extends Event {
	
	private StopWatch stopWatch;
	private long frequency;
	private boolean loop;

	public TimeEvent(long frequency, boolean loop) {
		super("TimeEvent");
		this.frequency = frequency;
		this.loop = loop;
		stopWatch = new StopWatch();
		stopWatch.start();
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		if(stopWatch.getElapsedTime() >= frequency){
			stopWatch.reset();
			if(loop) stopWatch.start();
			return true;
		} else return false;
	}

}
