package game;
import java.util.Random;
import java.util.List;

public class BlackBag extends Bag {
	
	// Stores all the pebbles of the black bag
	private Random rand;
	private int index;
	private String name;
	private String partnerName;
	
	// Initialise the bag with an array of pebble sizes to fill the bag
	public BlackBag(int[] pebbles, int index) {
		super();
		this.index = index;
		switch (index) {
		case 0:
			this.name = "X";
			this.partnerName = "A";
			break;
		case 1:
			this.name = "Y";
			this.partnerName = "B";
			break;
		case 2:
			this.name = "Z";
			this.partnerName = "C";
			break;
		}
		for (int x = 0; x < pebbles.length; x++) {
			this.pebbles.add(pebbles[x]);
		}
		rand = new Random();
	}
	
	// Add a list of pebble sizes to the bag
	public synchronized void AddPebbleList(List<Integer> pebbles) {
		this.pebbles.addAll(pebbles);
	}
	
	@Override
	public synchronized int removePebble() {
		// Get random pebble from the bag
		int pos = rand.nextInt(this.pebbles.size());
		int randomPebble = this.pebbles.get(pos);
		// Remove pebble from the bag
		pebbles.remove(pos);
		// If bag now empty fill it
		if (this.bagSize() == 0) {
			this.AddPebbleList(PebbleGame.getWhiteBag(index).EmptyBag());
		}
		return randomPebble;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPartnerName() {
		return partnerName;
	}
	
	

}
