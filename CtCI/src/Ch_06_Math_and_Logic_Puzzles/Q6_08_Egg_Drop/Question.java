package Ch_06_Math_and_Logic_Puzzles.Q6_05_Egg_Drop;

public class Question {
	public static int breakingPoint = 89;
	public static int countDrops = 0;


	public static boolean willBreak(int floor) {
		countDrops++;
		return floor >= breakingPoint;
	}


	public static int findBreakingPoint(int floors) {
		int interval = 14;
		int previousFloor = 0;
		int egg1 = interval;
		
		/* Drop egg1 at decreasing intervals. */
		while (!willBreak(egg1) && egg1 <= floors) {
			interval -= 1;
			previousFloor = egg1;
			egg1 += interval;
		}
		
		/* Drop egg2 at 1 unit increments. */
		int egg2 = previousFloor + 1;
		while (egg2 < egg1 && egg2 <= floors && !willBreak(egg2)) {
			egg2 += 1;
		}
		
		/* If it didn’t break, return -1. */
		return egg2 > floors ? -1 : egg2;
	}


	public static void main(String[] args) {
		int max = 0;
		for (int i = 1; i <= 100; i++) {
			countDrops = 0;
			breakingPoint = i;
			int bp = findBreakingPoint(100);
			
			if (bp == breakingPoint) {
				System.out.println("SUCCESS: " + i + " -> " + bp + " -> " + countDrops);
			} else {
				System.out.println("ERROR: " + i + " -> " + bp + " vs " + breakingPoint);
				break;
			}
			max = countDrops > max ? countDrops : max;
		}
		System.out.println(max);
	}
	/*
SUCCESS: 1 -> 1 -> 2
SUCCESS: 2 -> 2 -> 3
SUCCESS: 3 -> 3 -> 4
SUCCESS: 4 -> 4 -> 5
SUCCESS: 5 -> 5 -> 6
SUCCESS: 6 -> 6 -> 7
SUCCESS: 7 -> 7 -> 8
SUCCESS: 8 -> 8 -> 9
SUCCESS: 9 -> 9 -> 10
SUCCESS: 10 -> 10 -> 11
SUCCESS: 11 -> 11 -> 12
SUCCESS: 12 -> 12 -> 13
SUCCESS: 13 -> 13 -> 14
SUCCESS: 14 -> 14 -> 14
SUCCESS: 15 -> 15 -> 3
SUCCESS: 16 -> 16 -> 4
SUCCESS: 17 -> 17 -> 5
SUCCESS: 18 -> 18 -> 6
SUCCESS: 19 -> 19 -> 7
SUCCESS: 20 -> 20 -> 8
SUCCESS: 21 -> 21 -> 9
SUCCESS: 22 -> 22 -> 10
SUCCESS: 23 -> 23 -> 11
SUCCESS: 24 -> 24 -> 12
SUCCESS: 25 -> 25 -> 13
SUCCESS: 26 -> 26 -> 14
SUCCESS: 27 -> 27 -> 14
SUCCESS: 28 -> 28 -> 4
SUCCESS: 29 -> 29 -> 5
SUCCESS: 30 -> 30 -> 6
SUCCESS: 31 -> 31 -> 7
SUCCESS: 32 -> 32 -> 8
SUCCESS: 33 -> 33 -> 9
SUCCESS: 34 -> 34 -> 10
SUCCESS: 35 -> 35 -> 11
SUCCESS: 36 -> 36 -> 12
SUCCESS: 37 -> 37 -> 13
SUCCESS: 38 -> 38 -> 14
SUCCESS: 39 -> 39 -> 14
SUCCESS: 40 -> 40 -> 5
SUCCESS: 41 -> 41 -> 6
SUCCESS: 42 -> 42 -> 7
SUCCESS: 43 -> 43 -> 8
SUCCESS: 44 -> 44 -> 9
SUCCESS: 45 -> 45 -> 10
SUCCESS: 46 -> 46 -> 11
SUCCESS: 47 -> 47 -> 12
SUCCESS: 48 -> 48 -> 13
SUCCESS: 49 -> 49 -> 14
SUCCESS: 50 -> 50 -> 14
SUCCESS: 51 -> 51 -> 6
SUCCESS: 52 -> 52 -> 7
SUCCESS: 53 -> 53 -> 8
SUCCESS: 54 -> 54 -> 9
SUCCESS: 55 -> 55 -> 10
SUCCESS: 56 -> 56 -> 11
SUCCESS: 57 -> 57 -> 12
SUCCESS: 58 -> 58 -> 13
SUCCESS: 59 -> 59 -> 14
SUCCESS: 60 -> 60 -> 14
SUCCESS: 61 -> 61 -> 7
SUCCESS: 62 -> 62 -> 8
SUCCESS: 63 -> 63 -> 9
SUCCESS: 64 -> 64 -> 10
SUCCESS: 65 -> 65 -> 11
SUCCESS: 66 -> 66 -> 12
SUCCESS: 67 -> 67 -> 13
SUCCESS: 68 -> 68 -> 14
SUCCESS: 69 -> 69 -> 14
SUCCESS: 70 -> 70 -> 8
SUCCESS: 71 -> 71 -> 9
SUCCESS: 72 -> 72 -> 10
SUCCESS: 73 -> 73 -> 11
SUCCESS: 74 -> 74 -> 12
SUCCESS: 75 -> 75 -> 13
SUCCESS: 76 -> 76 -> 14
SUCCESS: 77 -> 77 -> 14
SUCCESS: 78 -> 78 -> 9
SUCCESS: 79 -> 79 -> 10
SUCCESS: 80 -> 80 -> 11
SUCCESS: 81 -> 81 -> 12
SUCCESS: 82 -> 82 -> 13
SUCCESS: 83 -> 83 -> 14
SUCCESS: 84 -> 84 -> 14
SUCCESS: 85 -> 85 -> 10
SUCCESS: 86 -> 86 -> 11
SUCCESS: 87 -> 87 -> 12
SUCCESS: 88 -> 88 -> 13
SUCCESS: 89 -> 89 -> 14
SUCCESS: 90 -> 90 -> 14
SUCCESS: 91 -> 91 -> 11
SUCCESS: 92 -> 92 -> 12
SUCCESS: 93 -> 93 -> 13
SUCCESS: 94 -> 94 -> 14
SUCCESS: 95 -> 95 -> 14
SUCCESS: 96 -> 96 -> 12
SUCCESS: 97 -> 97 -> 13
SUCCESS: 98 -> 98 -> 14
SUCCESS: 99 -> 99 -> 14
SUCCESS: 100 -> 100 -> 13
14
	 */
}

