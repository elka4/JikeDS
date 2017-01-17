package top100.Enhance._4DP_PreClass;

public class _5_EditDistance {
	//Edit Distance I
	private int match(String word1, String word2, int i, int j, int[][] count) {
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        if (count[i][j] != 0) {
               return count[i][j];
        }
        int res;
        if (word1.charAt(i) == word2.charAt(j)) {
            res = match(word1, word2, i + 1, j + 1, count);
        } else {
            //Case 1: insert
            int insert = match(word1, word2, i, j + 1, count);
            //Case 2: delete
            int delete = match(word1, word2, i + 1, j, count);
            //Case 3: replace
            int replace = match(word1, word2, i + 1, j + 1, count);
            res = Math.min(Math.min(insert, delete), replace) + 1;
        }
        count[i][j] = res;
        return res;
  }
//Edit Distance II
public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        if (word1.length() == 0 || word2.length() == 0) {
            return word1.length() == 0 ? word2.length() : word1.length();
        }

        int[][] match = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            match[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            match[0][j] = j;
        }

        for (int i =  0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j))
                    match[i + 1][j + 1] = match[i][j];
                else {
                    match[i + 1][j + 1] = Math.min(Math.min(match[i][j], match[i][j + 1]), match[i + 1][j]) + 1;
                }
            }
        }
        return match[word1.length()][word2.length()];
	 }
}