package _01_Array.Ch_01_Arrays_and_Strings.Q1_01_Is_Unique;

public class QuestionA {
	public static boolean isUniqueChars(String str) {
		if (str.length() > 128) {
			return false;
		}
		boolean[] char_set = new boolean[128];

		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			if (char_set[val]) return false;
			char_set[val] = true;
		}
		return true;
	}

    /* Assumes only letters a through z. */
    public static boolean isUniqueChars2(String str) {
        if (str.length() > 26) { // Only 26 characters
            return false;
        }
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) return false;
            checker |= (1 << val);
        }
        return true;
    }
	
	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle"};
		for (String word : words) {
			System.out.println(word + ": " + isUniqueChars(word));
		}
	}

}