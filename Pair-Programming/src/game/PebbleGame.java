package game;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.io.PrintWriter;

public class PebbleGame {
	public static void main(String args[]) {
		//Asks for player input and stores said input in an int value called players

		System.out.println("Please enter the number of players: ");
		Scanner playerInput = new Scanner((System.in));
		int players;
		//Checks whether the input is an integer
		while (!playerInput.hasNextInt()||(players=playerInput.nextInt())<=0){
			playerInput.nextLine();
			System.out.println("Invalid input, please try again.");
		}
		try{
				//Loads the 3 BlackBags into the game
				playerInput.nextLine(); //needed to clear buffer
				for (int x = 0; x < 3; x++) {
					//Reads a file input specified by the player
					System.out.format("Enter the file name of bag %d to load:%n", x+1);
					Scanner input = new Scanner(new File(playerInput.nextLine())).useDelimiter("[$,|\\n]");
					//Creates an array to store elements in the file
					List<Integer> bag = new ArrayList<Integer>();
					//Adds all the elements to array *is not adding last value
				while (input.hasNextInt()) {
					bag.add(input.nextInt());
				}

				try{
					bag.add(Integer.parseInt(input.next().trim()));
				} catch (NumberFormatException e){
							System.out.println("Invalid value found in file " + (x+1));
							System.exit(0);
				}
				//Checks if number of players is 11x the number of elements in bagArray and then adds it to the PebbleGames BlackBag array blackBag[]
				if (bag.size()>=11*players){
					int[] array = bag.stream().mapToInt(i -> i).toArray();
					PebbleGame.blackBags[x] = new BlackBag(array,x);
				} else{
					System.out.println("Bagsize is insufficient");
					System.exit(0);
					}
			}
		PebbleGame game = new PebbleGame(players);
		game.startGame();

		}catch (FileNotFoundException e){
		 		System.out.println("Invalid File Option");
	 	}
	}

	final CyclicBarrier barrier= new CyclicBarrier(2);
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
			players[i].name = ("Player " + (i+1));
			players[i].fillBag(getRandomBlackBag());
			threads[i] = new Thread(players[i]);
			//Creates a custom text file for the player to write to
			try{
						players[i].file = new PrintWriter("player"+ (i+1) +"_output.txt");
				}catch(FileNotFoundException e){
						System.out.println("File not found");
				}
				players[i].file.println("Player "+ (i+1) + "'s hand is " + players[i].getBag().toString() + ".");
				System.out.println("Created player: " + (i+1));
		}
	}

	public int[] pebbleFile() {
			int[] testData = {10,22,3,14,5,6,7,17,9,10,11,12,13,1,15,16,17,18,19,20,10,22,3,14,5,6,7,17,9,10,11,12,13,1,15,16,17,18,19,20,10,22,3,14,5,6,7,17,9,10,11,12,13,1,15,16,17,18,19,20};
			return testData;
	}

	public void startGame() {
		// Start threads
		for (int j = 0; j < threads.length; j++) {
			threads[j].start();
		}
	}

	// Get Random Black Bag
	static synchronized public BlackBag getRandomBlackBag() {
		int pos = rand.nextInt(3);
		return blackBags[pos];
	}

	// Get Random White Bag
	static synchronized public WhiteBag getRandomWhiteBag() {
		int pos = rand.nextInt(3);
		return whiteBags[pos];
	}

	static synchronized public WhiteBag getWhiteBag(int pos) {
		return whiteBags[pos];
	}

	static synchronized public void announceWin(Player player) {
		// Stop all threads now winner is announced
		System.out.println(player.name + " Has won the game");
		System.out.println(player.name + " " + player.getBag());
		for (int x=0; x < players.length; x++){
			players[x].file.close();
		}
		System.exit(0);
	}

	class Player extends Bag implements Runnable{
		private Random rand = new Random();
		private CyclicBarrier barrier;
		String name;
		PrintWriter file;

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
			}//<--- should write to the file here of the whole array
		}

		// Chuck random pebble into the white bag and then take a pebble from the black bag
		public synchronized void swapPebble(BlackBag bbag, WhiteBag wbag) {
			// Remove pebble from players bag
			int oldPebble = this.removePebble();
			// Add pebble to white bag
			wbag.addPebble(oldPebble);
			this.file.println(this.name + " has discarded a pebble of weight " + oldPebble + " to bag ." );
			// Get new pebble from black bag
			int newPebble = bbag.removePebble();
			this.file.println(this.name + " has retrieved a pebble of weight " + newPebble + " from .");
			// Add pebble to players bag
			this.addPebble(newPebble);
			this.file.println(this.name + "'s hand is " + this.getBag().toString());

			}

			// Check players bag to see if they have won
		public synchronized boolean bagWins() {
			int total = 0;
			for (int x = 0; x < this.pebbles.size(); x++) {
				total += this.pebbles.get(x);
			}
			if (this.pebbles.size() == 10 && total == 100) {
				return true;
			}else {
				return false;
			}
		}

			@Override
			public void run() {
				try {
					barrier.await();
					while(!Thread.interrupted()) {
						swapPebble(getRandomBlackBag(), getRandomWhiteBag());
						if (bagWins() && !Thread.interrupted()) {
							announceWin(this);
						}
					}
					System.out.println(this.name + "Passed Barrier"); //** not printed before from what i can see
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}

			}

		}
}
