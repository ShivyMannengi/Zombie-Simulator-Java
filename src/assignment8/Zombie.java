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
                super(x,y,true,ZOMBIE_SPEED);
        }
        /**
         * Grow the Zombie after consuming a Nonzombie
         */
        public void consumeNonzombie(){
                double radius = this.getRadius();
                if(radius <= 0.02) {
                        radius = this.getRadius() * 1.2;
                        this.setRadius(radius);
                }
        }
        /**
         * Draw the Zombie, increases size of zombie when it consumes a 
         non-zombie entity, max of 0.02
         */
        public void draw() {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledCircle(this.getX(), this.getY(),this.getRadius());
        } //visual representation of zombie, with coordinates
        public boolean isZombie() {
                return true;
        } //distinguishes zombies from other entities
        /**
         * Update the Zombie
         * @param entities the array of Entity objects in the simulation, consumed or not
         * @param deltaTime the time since the last frame
         * @return the new Entity object to take the place of the current one
         */
        public Entity update(Entity[] entities) {
                Nonzombie closestNonzombie = findClosestNonzombie(entities); //intentionally seeks out, not random
                if (closestNonzombie != null) {
                       moveToward(closestNonzombie);
                       checkBounds();
                }
                return this; //remainds updated after each simulation frame
        } //identifies closest nonzombie entity.
        // iterating over "entities" array and measures distances
        //move toward method brings it closer to the target point or nonzombie
        //checkbounds method keeps it in the frames
        //return this, zombie remains updated after each simulation frame
}