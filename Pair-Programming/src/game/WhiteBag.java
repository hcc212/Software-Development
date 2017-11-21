package game;

import java.util.ArrayList;
import java.util.List;

public class WhiteBag extends Bag{
	
	public WhiteBag() {	}
	
	// Empty the bag and return the list of pebble weights
	public List<Integer> EmptyBag() {
		List<Integer> bag = this.pebbles;
		this.pebbles = new ArrayList<Integer>();
		return bag;
	}
}
