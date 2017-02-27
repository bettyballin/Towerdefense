package model.factory;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import eea.engine.action.Action;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import eea.engine.interfaces.IEntityFactory;

public class MenuEntryFactory implements IEntityFactory {

	private Entity entry;
	
	private String name;
    private Action action;
    private int entry_height = 150;
    private Boolean noIcon;
    
	public MenuEntryFactory(String name, GameContainer c, Action action, int entry_height, Boolean noIcon) {
		this.name = name;
	    this.action = action;
	    this.entry_height = entry_height;
	    this.noIcon = noIcon;
	}

	@Override
	public Entity createEntity() {
		
		try {
			float scale = 0.55f;
			
			entry = new Entity(name);
			entry.setPosition(new Vector2f(245,entry_height));
			entry.setScale(scale);
			if(noIcon) entry.addComponent(new ImageRenderComponent(new Image("assets/entry.png")));
			else entry.addComponent(new ImageRenderComponent(new Image("assets/entrybackground.png")));
			ANDEvent mainEvents = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
			mainEvents.addAction(action);			
			entry.addComponent(mainEvents);

			return entry;
			
		} catch (SlickException e) {
			e.printStackTrace();
			return null;
		}		
	}

}
