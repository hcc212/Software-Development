package game;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class PebbleGame {
	public static void main(String args[]) {
		PebbleGame game = new PebbleGame(7);
	}
	
		final CyclicBarrier barrier = new CyclicBarrier(7);
		private static Random rand = new Random();
		private static BlackBag[] blackBags = new BlackBag[3];
		private static WhiteBag[] whiteBags = new WhiteBag[3];
		private static Player[] players;
		private static Thread[] threads;
		
		public PebbleGame(int noPlayers) {
			// Initialise Black and White Bags
			for (int x = 0; x < 3; x++) {
				blackBags[x] = new BlackBag(pebbleFile(), x);
				whiteBags[x] = new WhiteBag();
			}
			// Initialise all Players / Threads and fill their bag
			players = new Player[noPlayers];
			threads = new Thread[noPlayers];
			for (int i = 0; i < noPlayers; i++) {
				players[i] = new Player("Player " + i, barrier);
				players[i].fillBag(getRandomBlackBag());
				threads[i] = new Thread(players[i]);
				System.out.println("Created player: " + i);
			}
			// Start threads
			for (int j = 0; j < threads.length; j++) {
				threads[j].start();
			}
			
		}
		
		public int[] pebbleFile() {
			int[] testData = {10,22,3,14,5,6,7,17,9,10,11,12,13,1,15,16,17,18,19,20,10,22,3,14,5,6,7,17,9,10,11,12,13,1,15,16,17,18,19,20,10,22,3,14,5,6,7,17,9,10,11,12,13,1,15,16,17,18,19,20};
			return testData;
		}
		
		// Get Random Black Bag
		static public BlackBag getRandomBlackBag() {
			int pos = rand.nextInt(3);
			return blackBags[pos];
		}
		
		// Get Random White Bag
		static public WhiteBag getRandomWhiteBag() {
			int pos = rand.nextInt(3);
			return whiteBags[pos];
		}
		
		static public WhiteBag getWhiteBag(int pos) {
			return whiteBags[pos];
		}
		
		static synchronized public void announceWin(Player player) {
			// Stop all threads now winner is announced
			for (int x = 0; x < threads.length; x++) {
				threads[x].interrupt();
			}
			System.out.println(player.name + " Has won the game");
			System.out.println(player.name + " " + player.getBag());
			
			
		}
		
		
		
		
		
		
		class Player extends Bag implements Runnable{
			
			private Random rand = new Random();
			String name;
			private CyclicBarrier barrier;
			
			public Player(String name, CyclicBarrier barrier) {
				super();
				this.name = name;
				this.barrier = barrier;
			}
			
			// Initially fill the bag with 10 pebbles from a black bag
			public void fillBag(BlackBag bbag) {
				for (int x = 0; x < 10; x++) {
					int newPebble = bbag.removePebble();
					this.addPebble(newPebble);
				}
			}
			
			// Chuck random pebble into the white bag and then take a pebble from the black bag
			public synchronized void swapPebble(BlackBag bbag, WhiteBag wbag) {		
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
			public synchronized boolean bagWins() {
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
			
			@Override
			public void run() {
				try {
					System.out.println(this.name + "waiting at barrier" );
					barrier.await();
					System.out.println(this.name + "Passed Barrier");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println(this.name + " has started");
				while(!Thread.interrupted()) {
					
					swapPebble(getRandomBlackBag(), getRandomWhiteBag());
					if (bagWins() && !Thread.interrupted()) {
						announceWin(this);
					}
				}
			}
			
		}
}

