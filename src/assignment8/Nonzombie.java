package assignment8;
import edu.princeton.cs.introcs.StdDraw;
import support.cse131.NotYetImplementedException;
public class Nonzombie extends Entity {

        public static final double NONZOMBIE_SPEED = 0.01;
        /**
         *
         * @param x coordinate
         * @param y coordinate
         */
        public Nonzombie(double x, double y) {
                super(x,y,false,NONZOMBIE_SPEED);
        }
        /**
         * Create a Zombie object in place of the current Nonzombie
         * @return the new Zombie object
         */
        public Zombie convert() {
                return new Zombie(getX(), getY());
        }
        /**
         * Draw a Nonzombie
         */
        public void draw() {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledCircle(this.getX(), this.getY(),this.getRadius());
        }
        public boolean isZombie(){
                return false;
        }
        /**
         * Update the Nonzombie
         * @param entities the array of Entity objects in the simulation, consumed or not
         * @param deltaTime the time since the last frame
         * @return the new Entity object to take the place of the current one
         */
        public Entity update(Entity[] entities) {
        	Zombie closestZombie = findClosestZombie(entities);
        		if (closestZombie != null && !isTouching(closestZombie) ) {
        			moveAwayFrom(closestZombie);
                    checkBounds();
                    }
                if (closestZombie != null && isTouching(closestZombie)) {
                           double chance = Math.random();
                           if (chance < 0.8) {
                               return convert();
                           } else {
                               closestZombie.consumeNonzombie();
                           
                           }
                       }
                       return this; //if conditions aren't met, remains same state
        }
}
//finds closest zombie iterating through entity array, calcultates nearest zombie w/distance
//if there is nearby zombie that isn't touching nonzombie, moves away w/moveawayfrom method
//if non zombie is touching zombie, 80% it will convert to a zombie. if not, zombie consumes
//nonzombie and increases in size, removes from sim
//if conditions arement met, nonzombie remains same state