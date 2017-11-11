package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WhiteBag extends Bag{

	private Random rand;
	
	public WhiteBag() {	
		rand = new Random();
	}
	
	// Empty the bag and return the list of pebble weights
	public synchronized List<Integer> EmptyBag() {
		List<Integer> bag = this.pebbles;
		this.pebbles = new ArrayList<Integer>();
		return bag;
	}
}
