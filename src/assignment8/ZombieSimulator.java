package assignment8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.NotYetImplementedException;
import support.cse131.Timing;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class ZombieSimulator {
        private static final String ZOMBIE_TOKEN_VALUE = "Zombie";
        private Entity[] entities;
        private int zomCount;


        /**
         * Constructs a ZombieSimulator with an empty array of Entities.
         */
        public ZombieSimulator(int n) {
                 entities = new Entity[n];


        }

        /**
         * @return the current array of entities
         */
        public Entity[] getEntities() {
                return entities;
        }

        /**
         * Reads an entire zombie simulation file from a specified ArgsProcessor, adding
         * each Entity to the array of entities.
         *
         * Assume that N (the integer indicating how many entities are in the simulation) has already been read in
         * and passed into the constructor.
         *
         * @param in Scanner to read the complete zombie simulation file format.
         */
        public void readEntities(Scanner in) {

                zomCount = 0;
                for(int i = 0; i < entities.length; i++) {

                        boolean isZom = in.next().equals(ZOMBIE_TOKEN_VALUE);
                        double x = in.nextDouble();
                        double y = in.nextDouble();

                        if(isZom) {
                                entities[i] = new Zombie(x,y);
                                zomCount++;
                        }
                        else {
                                entities[i] = new Nonzombie(x,y);
                        }
                        in.nextLine();
                }
        }

        /**
         * @return the number of zombies in entities.
         */
        public int getZombieCount() {
                return zomCount;

        }


        /**
         * @return the number of nonzombies in entities.
         */
        public int getNonzombieCount() {
                return entities.length - getZombieCount();
        }

        /**
         * Draws a frame of the simulation.
         */
        public void draw() {
                StdDraw.clear();

                // NOTE: feel free to edit this code to support additional features
                for (Entity entity : getEntities()) {
                        if(entity == null) continue;
                        entity.draw();
                }

                StdDraw.show(); // commit deferred drawing as a result of enabling double buffering
        }

        /**
         * Updates the entities for the current frame of the simulation given the amount
         * of time passed since the previous frame.
         *
         * Note: some entities may be consumed and will not remain for future frames of
         * the simulation.
         *
         * @param deltaTime the amount of time since the previous frame of the
         *                  simulation.
         */
        public void update() {
                for(int i = 0; i < entities.length; i++) {

                        if(entities[i] != null && entities[i].isAlive()) { //avoid processing entities that has been consumed

                                Entity updateEntity = entities[i].update(entities);

                                entities[i] = updateEntity;
                        }
                }
        } //ensures entity array remains accurate
		// iterates through the entities array, checks if it's null or alive to avoid processing entities
		// that have been consumed, updated by calling its update method, handles consumption, updates their state
		// accordingly, ensures entity array remains accurate in the current simulation
		// 
        /**
         * Runs the zombie simulation.
         *
         * @param args arguments from the command line
         * @throws FileNotFoundException
         */
        public static void main(String[] args) throws FileNotFoundException {
                StdDraw.enableDoubleBuffering(); // reduce unpleasant drawing artifacts, speed things up

                JFileChooser chooser = new JFileChooser("zombieSims");
                chooser.showOpenDialog(null);
                File f = new File(chooser.getSelectedFile().getPath());
                Scanner in = new Scanner(f); //making Scanner with a File

                ZombieSimulator zombieSimulator = new ZombieSimulator(in.nextInt());
                zombieSimulator.readEntities(in);

                double prevTime = Timing.getCurrentTimeInSeconds();
                while (zombieSimulator.getNonzombieCount() >= 0) {

                        zombieSimulator.update();
                        zombieSimulator.draw();

                        StdDraw.pause(20);

                }
        }
}