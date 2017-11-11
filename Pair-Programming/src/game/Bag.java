package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {
	
	List<Integer> pebbles = new ArrayList<Integer>();
	private Random rand;
	
	public Bag() {
		rand = new Random();
	}
	
	// Add a pebble to the bag
	public void addPebble(int pebble) {
		this.pebbles.add(pebble);
	}
	
	// Get a random pebble from the bag
	public int removePebble() {
		// Get random pebble from the bag
		int pos = rand.nextInt(this.pebbles.size());
		int randomPebble = this.pebbles.get(pos);
		// Remove pebble from the bag
		this.pebbles.remove(pos);
		return randomPebble;
	}
	
	// Returns number of pebbles in bag
	public int bagSize() {
		return this.pebbles.size();
	}
		
	// Returns players bag
	public List<Integer> getBag() {
		return this.pebbles;
	}

}
