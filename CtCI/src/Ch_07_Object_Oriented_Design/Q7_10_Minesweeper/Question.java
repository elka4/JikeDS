package Ch_07_Object_Oriented_Design.Q7_10_Minesweeper;

public class Question {	
	public static void main(String[] args) {
		Game game = new Game(7, 7, 3);
		game.initialize();
		game.start();
	}
/*
                   0 1 2 3 4 5 6
                --------------
                0| * 2 1
                1| 2 * 1
                2| 1 1 1
                3|   1 1 1
                4|   1 * 1
                5|   1 1 1
                6|
                Number remaining: 46

                   0 1 2 3 4 5 6
                --------------
                0| ? ? ? ? ? ? ?
                1| ? ? ? ? ? ? ?
                2| ? ? ? ? ? ? ?
                3| ? ? ? ? ? ? ?
                4| ? ? ? ? ? ? ?
                5| ? ? ? ? ? ? ?
                6| ? ? ? ? ? ? ?
 */
}
