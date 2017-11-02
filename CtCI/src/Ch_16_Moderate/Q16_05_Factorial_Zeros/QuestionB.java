package Ch_16_Moderate.Q16_05_Factorial_Zeros;

public class QuestionB {
	public static int countFactZeros(int num) {
		int count = 0;
		if (num < 0) {
			System.out.println("Factorial is not defined for negative numbers");
			return 0;
		}
		for (int i = 5; num / i > 0; i *= 5) {
			count += num / i;
		}
		return count;
	}
	
	public static int factorial(int num) {
		if (num == 1) {
			return 1;
		} else if (num > 1) {
			return num * factorial(num - 1);
		} else {
			return -1; // Error
		}
	}
	
	public static void main(String[] args) {
		for (int i = 1; i < 12; i++) {
			System.out.println(i + "! (or " + factorial(i) + ") has "
                    + countFactZeros(i) + " zeros");
		}
	}
	/*
                1! (or 1) has 0 zeros
                2! (or 2) has 0 zeros
                3! (or 6) has 0 zeros
                4! (or 24) has 0 zeros
                5! (or 120) has 1 zeros
                6! (or 720) has 1 zeros
                7! (or 5040) has 1 zeros
                8! (or 40320) has 1 zeros
                9! (or 362880) has 1 zeros
                10! (or 3628800) has 2 zeros
                11! (or 39916800) has 2 zeros
	 */
}
