package game;
import java.util.Random;

public class start {
	public static void main(String args[]) {
		PebbleGame game = new PebbleGame(2);
	}
	
	static class PebbleGame {
		
		private static Random rand = new Random();
		private static BlackBag[] blackBags = new BlackBag[3];
		private static WhiteBag[] whiteBags = new WhiteBag[3];
		private static Player[] players;
		private static Thread[] threads;
		
		public PebbleGame(int noPlayers) {
			// Initialise Black and White Bags
			for (int x = 0; x < 3; x++) {
				blackBags[x] = new BlackBag(pebbleFile());
				whiteBags[x] = new WhiteBag();
			}
			// Initialise all Players / Threads and fill their bag
			players = new Player[noPlayers];
			threads = new Thread[noPlayers];
			for (int i = 0; i < noPlayers; i++) {
				players[i] = new Player("Player " + i);
				players[i].fillBag(getRandomBlackBag());
				threads[i] = new Thread(players[i]);
			}
			// Start threads
			for (int j = 0; j < noPlayers; j++) {
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
		
		static public void announceWin(Player player) {
			// Stop all threads now winner is announced
			for (int x = 0; x < PebbleGame.threads.length; x++) {
				threads[x].interrupt();
			}
			System.out.println(player.name + " Has won the game");
			for (int i = 0; i < players.length; i++) {
				System.out.println(players[i].getBag());
				System.out.println(blackBags[i].getBag());
			}
			
		}
	}
}

