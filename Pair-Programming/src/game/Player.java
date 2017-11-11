package game;

import java.util.Random;

public class Player extends Bag{
	
	private Random rand;
	
	public Player() {
		super();
		rand = new Random();
	}
	
	// Initially fill the bag with 10 pebbles from a black bag
	public void fillBag(BlackBag bbag) {
		for (int x = 0; x < 10; x++) {
			int newPebble = bbag.removePebble();
			this.addPebble(newPebble);
		}
	}
	
	// Chuck random pebble into the white bag and then take a pebble from the black bag
	public void swapPebble(BlackBag bbag, WhiteBag wbag) {
		// Check black bag if empty
		if (bbag.bagSize() == 0) {
			// If empty then empty white bag and put in black bag
			bbag.AddPebbleList(wbag.EmptyBag());
		}
		
		// Remove pebble from players bag
		int oldPebble = this.removePebble();
		// Add pebble to white bag
		wbag.addPebble(oldPebble);
		// Get new pebble from black bag
		int newPebble = bbag.removePebble();
		// Add pebble to players bag
		this.addPebble(newPebble);
	}
	
	// Check players bag to see if they have won
	public boolean bagWins() {
		int total = 0;
		for (int x = 0; x < this.pebbles.size(); x++) {
			total += this.pebbles.get(x);
		}
		if (this.pebbles.size() == 10 && total == 100) {
			return true;
		} else {
			return false;
		}
	}
	
}
