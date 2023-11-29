package assignment8;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.NotYetImplementedException;

public class Zombie extends Entity {

	public static final double ZOMBIE_SPEED = 0.011;

	/**
	 * Create a new Zombie object
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Zombie(double x, double y){
		// FIXME
		throw new NotYetImplementedException();
	}

	/**
	 * Grow the Zombie after consuming a Nonzombie
	 */
	public void consumeNonzombie(){
		// FIXME
		throw new NotYetImplementedException();
	}

	/**
	 * Draw the Zombie
	 */
	public void draw() {
		// FIXME
		throw new NotYetImplementedException();
	}


	/**
	 * Update the Zombie
	 * @param entities the array of Entity objects in the simulation, consumed or not
	 * @param deltaTime the time since the last frame
	 * @return the new Entity object to take the place of the current one
	 */
	public Entity update(Entity[] entities) {
		// FIXME
		throw new NotYetImplementedException();
	}
}