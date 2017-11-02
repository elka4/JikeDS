package Ch_16_Moderate.Q16_08_English_Int;

import CtCILibrary.AssortedMethods;

import java.util.LinkedList;

public class Question {
	public static String[] smalls = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	public static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	public static String[] bigs = {"", "Thousand", "Million", "Billion"};
	public static String hundred = "Hundred";
	public static String negative = "Negative";
	
	public static String convert(int num) {
		if (num == 0) {
			return smalls[0];
		} else if (num < 0) {
			return negative + " " + convert(-1 * num);
		}
		
		LinkedList<String> parts = new LinkedList<String>();
		int chunkCount = 0;
		
		while (num > 0) {
			if (num % 1000 != 0) {
				String chunk = convertChunk(num % 1000) + " " + bigs[chunkCount];
				parts.addFirst(chunk);
			}
			num /= 1000; // shift chunk
			chunkCount++;
		}
		
		return listToString(parts);
	}
	
	/* Convert a linked list of strings to a string, dividing it up with spaces. */
	public static String listToString(LinkedList<String> parts) {
		StringBuilder sb = new StringBuilder();
		while (parts.size() > 1) {
			sb.append(parts.pop());
			sb.append(" ");
		}
		sb.append(parts.pop());
		return sb.toString();
	}
	
	public static String convertChunk(int number) {
		LinkedList<String> parts = new LinkedList<String>();
		
		/* Convert hundreds place */
		if (number >= 100) {
			parts.addLast(smalls[number / 100]);
			parts.addLast(hundred);
			number %= 100;
		}
		
		/* Convert tens place */
		if (number >= 10 && number <= 19) {
			parts.addLast(smalls[number]);
		} else if (number >= 20) {
			parts.addLast(tens[number / 10]);
			number %= 10;
		}
		
		/* Convert ones place */
		if (number >= 1 && number <= 9) {
			parts.addLast(smalls[number]);
		}
		
		return listToString(parts);
	}
	
	public static void main(String[] args) {		
		/* numbers between 100000 and 1000000 */
		for (int i = 0; i < 8; i++) {
			int value = (int) (-1 * Math.pow(10, i));
			String s = convert(value);
			System.out.println(value + ": " + s);
		}			
		
		/* numbers between 0 and 100 */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(0, 100);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}	
		
		/* numbers between 100 and 1000 */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(100, 1000);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}
		
		/* numbers between 1000 and 100000 */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(1000, 100000);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}		
		
		
		/* numbers between 100000 and 100000000 */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(100000, 100000000);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}	
		
		/* numbers between 100000000 and 1000000000 */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(100000000, 1000000000);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}			
                
		/* numbers between 1000000000 and Integer.MAX_VALUE */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(1000000000, Integer.MAX_VALUE);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}			
	}
	/*
            -1: Negative One
            -10: Negative Ten
            -100: Negative One Hundred
            -1000: Negative One Thousand
            -10000: Negative Ten Thousand
            -100000: Negative One Hundred Thousand
            -1000000: Negative One Million
            -10000000: Negative Ten Million
            92: Ninety Two
            75: Seventy Five
            70: Seventy
            31: Thirty One
            17: Seventeen
            91: Ninety One
            19: Nineteen
            52: Fifty Two
            31: Thirty One
            41: Forty One
            417: Four Hundred Seventeen
            665: Six Hundred Sixty Five
            233: Two Hundred Thirty Three
            296: Two Hundred Ninety Six
            698: Six Hundred Ninety Eight
            662: Six Hundred Sixty Two
            622: Six Hundred Twenty Two
            860: Eight Hundred Sixty
            479: Four Hundred Seventy Nine
            510: Five Hundred Ten
	 */
}
