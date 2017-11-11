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
	public synchronized void addPebble(int pebble) {
		this.pebbles.add(pebble);
	}
	
	// Get a random pebble from the bag
	public synchronized int removePebble() {
		System.out.println(pebbles.size());
		// Get random pebble from the bag
		int pos = rand.nextInt(pebbles.size());
		int randomPebble = pebbles.get(pos);
		// Remove pebble from the bag
		pebbles.remove(pos);
		return randomPebble;
	}
	
	// Returns number of pebbles in the bag
	public int bagSize() {
		return this.pebbles.size();
	}
		
	// Returns list of all pebbles in the bag
	public List<Integer> getBag() {
		return this.pebbles;
	}

}
