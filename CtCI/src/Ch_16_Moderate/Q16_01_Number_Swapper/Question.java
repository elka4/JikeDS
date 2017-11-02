package Ch_16_Moderate.Q16_01_Number_Swapper;

public class Question {

	public static void swap(int a, int b) {
		// Example for a = 9, b = 4
		a = a - b; // a = 9 - 4 = 5
		b = a + b; // b = 5 + 4 = 9
		a = b - a; // a = 9 - 5
		
		System.out.println("a: " + a);
		System.out.println("b: " + b);
	}
	
	public static void swap_opt(int a, int b) {
		a = a^b; 
		b = a^b; 
		a = a^b; 
		
		System.out.println("a: " + a);
		System.out.println("b: " + b);
	}
	
	public static void main(String[] args) {
		int a = 1672;
		int b = 9332;
		
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		
		swap(a, b);
		swap_opt(a, b);
	}
/*
                    a: 1672
                    b: 9332
                    a: 9332
                    b: 1672
                    a: 9332
                    b: 1672
 */
}
/*
Number Swapper: Write a function to swap a number in place (that is, without temporary vari- ables) .
Hints:
#492 Try picturing the two numbers, a and b, on a number line.,
#716,
#737
 */