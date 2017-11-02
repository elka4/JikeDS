package Ch_06_Math_and_Logic_Puzzles.Introduction;

public class PrimeNumbers {

	public static boolean primeNaive(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean primeSlightlyBetter(int n) {
		int sqrt = (int) Math.sqrt(n);
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}	
	
	public static void main(String[] args) {
		for (int i = 2; i < 100; i++) {
			if (primeSlightlyBetter(i)) {
				System.out.print(i + ", ");
			}
		}
	}
/*
2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
 */
}
