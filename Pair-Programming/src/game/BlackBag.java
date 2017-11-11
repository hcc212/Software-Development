package game;
import java.util.Random;
import java.util.List;

public class BlackBag extends Bag {
	
	// Stores all the pebbles of the black bag
	private Random rand;
	
	// Initialise the bag with an array of pebble sizes to fill the bag
	public BlackBag(int[] pebbles) {
		super();
		for (int x = 0; x < pebbles.length; x++) {
			this.pebbles.add(pebbles[x]);
		}
		rand = new Random();
	}
	
	// Add a list of pebble sizes to the bag
	public void AddPebbleList(List<Integer> pebbles) {
		this.pebbles.addAll(pebbles);
	}
}
