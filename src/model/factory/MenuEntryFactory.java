package model.factory;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.NOTEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import eea.engine.interfaces.IEntityFactory;

public class MenuEntryFactory implements IEntityFactory {

	private Entity entry;

	private String name;
	private Action action;
	private int entry_height = 150;
	private Boolean noIcon;
	private ImageRenderComponent img1;
	private ImageRenderComponent img2;
	private ImageRenderComponent img3;

	/**
	 * Constructs a MenuEntry Factory
	 * 
	 * @param name
	 *            name of menu entry
	 * @param c
	 *            current GameContainer
	 * @param action
	 *            Action to connect the menu entry with
	 * @param entry_height
	 *            height of entry
	 * @param noIcon
	 *            decide if we have an icon (tower) or not in the entry (needed
	 *            for options)
	 */
	public MenuEntryFactory(String name, GameContainer c, Action action, int entry_height, Boolean noIcon) {
		this.name = name;
		this.action = action;
		this.entry_height = entry_height;
		this.noIcon = noIcon;
		try {
			this.img1 = new ImageRenderComponent(new Image("assets/entry.png"));
			this.img2 = new ImageRenderComponent(new Image("assets/entrybackground.png"));
			this.img3 = new ImageRenderComponent(new Image("assets/entry2.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Entity createEntity() {
		entry = new Entity(name);
		entry.setPosition(new Vector2f(245, entry_height));
		entry.setScale(0.55f);
		if (noIcon)
			entry.addComponent(img1);
		else
			entry.addComponent(img2);

		// if mouse enters the menu entry, add a blue background to the entry
		MouseEnteredEvent entered = new MouseEnteredEvent();
		entered.addAction(new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				entry.removeComponent("ImageRenderComponent");
				if (noIcon)
					entry.addComponent(img3);
				else
					entry.addComponent(img2);
			}
		});
		entry.addComponent(entered);

		// remove blue background after leaving the entry
		NOTEvent notEntered = new NOTEvent(new MouseEnteredEvent());
		notEntered.addAction(new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				entry.removeComponent("ImageRenderComponent");
				if (noIcon)
					entry.addComponent(img1);
				else
					entry.addComponent(img2);
			}
		});
		entry.addComponent(notEntered);

		// start given action if the entry is clicked
		ANDEvent mainEvents = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
		mainEvents.addAction(action);
		entry.addComponent(mainEvents);

		return entry;
	}

}
