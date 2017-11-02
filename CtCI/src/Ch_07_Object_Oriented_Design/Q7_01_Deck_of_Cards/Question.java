package Ch_07_Object_Oriented_Design.Q7_01_Deck_of_Cards;

import java.util.ArrayList;

public class Question {

	
	public static void main(String[] args) {	
		int numHands = 5;
		
		BlackJackGameAutomator automator = new BlackJackGameAutomator(numHands);
		automator.initializeDeck();
		boolean success = automator.dealInitial();
			if (!success) {
				System.out.println("Error. Out of cards.");
			} else {
			System.out.println("-- Initial --");
			automator.printHandsAndScore();
			ArrayList<Integer> blackjacks = automator.getBlackJacks();
			if (blackjacks.size() > 0) {
				System.out.print("Blackjack at ");
				for (int i : blackjacks) {
					System.out.print(i + ", ");
				}
				System.out.println("");
			} else {
				success = automator.playAllHands();
				if (!success) {
					System.out.println("Error. Out of cards.");
				} else {
					System.out.println("\n-- Completed Game --");
					automator.printHandsAndScore();
					ArrayList<Integer> winners = automator.getWinners();
					if (winners.size() > 0) {
						System.out.print("Winners: ");
						for (int i : winners) {
							System.out.print(i + ", ");
						}
						System.out.println("");
					} else {
						System.out.println("Draw. All players have busted.");
					}
				}
			}
		}
	}
/*
            -- Initial --
            Hand 0 (15): 6d 9c
            Hand 1 (11): 5h 6c
            Hand 2 (19): 9d Kc
            Hand 3 (7): 4c 3h
            Hand 4 (17): 7c Qs

            -- Completed Game --
            Hand 0 (20): 6d 9c 5s
            Hand 1 (23): 5h 6c 2d Ks
            Hand 2 (19): 9d Kc
            Hand 3 (25): 4c 3h 8h Jd
            Hand 4 (17): 7c Qs
            Winners: 0,
 */
}
