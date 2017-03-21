package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * This event represents cases where the mouse position matches the area
 * occupied by the entity owning this event
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class MouseEnteredEvent extends Event {

	/**
	 * creates a new MouseEntered event
	 */
	public MouseEnteredEvent() {
		super("MouseEnteredEvent");
	}

	/**
	 * A MouseEnteredEvent will cause the action(s) associated with this event
	 * to be performed if the mouse has entered the area containing the entity
	 * owning this event. (More simply: if the mouse cursor is on the owning
	 * entity.)
	 * 
	 * @param gc
	 *            the GameContainer object that handles the game loop, recording
	 *            of the frame rate, and managing the input system
	 * @param sb
	 *            the StateBasedGame that isolates different stages of the game
	 *            (e.g., menu, ingame, highscores etc.) into different states so
	 *            they can be easily managed and maintained.
	 * @param delta
	 *            the time passed in nanoseconds (ns) since the start of the
	 *            event, used to interpolate the current target position
	 * 
	 * @return true if the action(s) associated with this event shall be
	 *         performed, else false
	 */
	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
		// determine the current mouse (x, y) position
		Vector2f mousePosition = new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY());

		// return if the current mouse position and the area covered by the
		// shape overlap
		return (mousePosition.x > getOwnerEntity().getPosition().x - 50
				&& mousePosition.x < getOwnerEntity().getPosition().x + 50)
				&& (mousePosition.y > getOwnerEntity().getPosition().y - 50
						&& mousePosition.y < getOwnerEntity().getPosition().y + 50);
	}
}