package Ch_16_Moderate.Q16_02_Word_Frequencies;

import CtCILibrary.AssortedMethods;

import java.util.HashMap;

public class QuestionB {
	public static HashMap<String, Integer> setupDictionary(String[] book) {
		HashMap<String, Integer> table = new HashMap<String, Integer>();
		for (String word : book) {
			word = word.toLowerCase();
			if (word.trim() != "") {
				if (!table.containsKey(word)) {
					table.put(word, 0);
				}
				table.put(word, table.get(word) + 1);
			}
		}
		return table;
	}
	public static int getFrequency(HashMap<String, Integer> table, String word) {
		if (table == null || word == null) {
			return -1;
		}
		word = word.toLowerCase();
		if (table.containsKey(word)) {
			return table.get(word);
		}
		return 0;
	}
	
	public static void main(String[] args) {
		String[] wordlist = AssortedMethods.getLongTextBlobAsStringList();
		HashMap<String, Integer> dictionary = setupDictionary(wordlist);
		
		String[] words = {"the", "Lara", "and", "outcropping", "career", "it"};
		for (String word : words) {
			System.out.println(word + ": " + getFrequency(dictionary, word));
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