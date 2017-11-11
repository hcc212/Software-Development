package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WhiteBag {
	// Stores all the pebbles of the white bag
	private List<Integer> pebbles = new ArrayList<Integer>();
	Random rand;
	
	public WhiteBag() {	
		rand = new Random();
	}
	
	// Empty the bag and return the list of pebble weights
	public List<Integer> EmptyBag() {
		List<Integer> bag = this.pebbles;
		this.pebbles = new ArrayList<Integer>();
		return bag;
	}
	
	// Add a pebble to the bag
	public void addPebble(int pebble) {
		this.pebbles.add(pebble);
	}
	
	// Returns number of pebbles in bag
	public int checkBag() {
		return this.pebbles.size();
	}
}
