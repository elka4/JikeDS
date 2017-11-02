package Ch_16_Moderate.Q16_02_Word_Frequencies;

import CtCILibrary.AssortedMethods;

public class QuestionA {
	public static int getFrequency(String[] book, String word) {
		word = word.trim().toLowerCase();
		int count = 0;
		for (String w : book) {
			if (w.trim().toLowerCase().equals(word)) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		String[] wordlist = AssortedMethods.getLongTextBlobAsStringList();
		
		String[] words = {"the", "Lara", "and", "outcropping", "career", "it"};
		for (String word : words) {
			System.out.println(word + ": " + getFrequency(wordlist, word));
		}
	}
	/*
                    the: 18
                    Lara: 3
                    and: 4
                    outcropping: 1
                    career: 0
                    it: 0

	 */
}
/*
Word Frequencies: Design a method to find the frequency of occurrences of any given word in a book. What if we were running this algorithm multiple times?
Hints: #489, #536
 */