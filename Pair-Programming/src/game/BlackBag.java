package game;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class BlackBag {
	
	// Stores all the pebbles of the black bag
	private List<Integer> pebbles = new ArrayList<Integer>();
	Random rand;
	
	// Initialise the bag with an array of pebble sizes to fill the bag
	public BlackBag(int[] pebbles) {
		for (int x = 0; x < pebbles.length; x++) {
			this.pebbles.add(pebbles[x]);
		}
		rand = new Random();
	}
	
	// Add a list of pebbles to the bag
	public void AddPebbleList(List<Integer> pebbles) {
		this.pebbles.addAll(pebbles);
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
	public int checkBag() {
		return this.pebbles.size();
	}
	
}
