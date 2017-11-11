package game;

public class start {
	public static void main(String args[]) {
		System.out.println("Game Started");
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		int[] testBag = {10,22,3,14,5,6,7,17,9,10,11,12,13,1,15,16,17,18,19,20};
		BlackBag blackBag1 = new BlackBag(testBag);
		BlackBag blackBag2 = new BlackBag(testBag);
		BlackBag blackBag3 = new BlackBag(testBag);
		WhiteBag whiteBag1 = new WhiteBag();
		WhiteBag whiteBag2 = new WhiteBag();
		WhiteBag whiteBag3 = new WhiteBag();
		
		boolean noWinner = true;
		
		// Fill Player Bags
		player1.fillBag(blackBag1);
		
		while (noWinner) {
			// Swap Pebble with random bag
			player1.swapPebble(blackBag1, whiteBag1);
			System.out.println(player1.getBag());
			noWinner = (player1.bagWins()) ? false:true;
		}
	}
}
