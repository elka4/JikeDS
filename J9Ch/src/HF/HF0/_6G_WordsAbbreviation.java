package HF.HF0;

import java.util.*;

public class _6G_WordsAbbreviation {
    public class Solution {
        /**
         * @param word a non-empty string
         * @param abbr an abbreviation
         * @return true if string matches with the given abbr or false
         */
        public boolean validWordAbbreviation(String word, String abbr) {
            // Write your code here
            int number = 0;
            int i = 0, j = 0;
            while (i < word.length() && j < abbr.length()) {
                if (Character.isDigit(abbr.charAt(j))) {
                    number = number * 10 + abbr.charAt(j) - '0';
                    if (number == 0)
                        return false;
                    j ++;
                } else {
                    i += number;
                    if (i >= word.length() ||
                            word.charAt(i) != abbr.charAt(j))
                        return false;
                    number = 0;
                    i ++;
                    j ++;
                }
            }
            i += number;
            return i == word.length() && j == abbr.length();
        }
    }

// version: 高频题班

    public class Solution2 {
        /**
         * @param word a non-empty string
         * @param abbr an abbreviation
         * @return true if string matches with the given abbr or false
         */
        public boolean validWordAbbreviation(String word, String abbr) {
            // Write your code here
            int i = 0, j = 0;
            char[] s = word.toCharArray();
            char[] t = abbr.toCharArray();

            while (i < s.length && j < t.length) {
                if (Character.isDigit(t[j])) {
                    if (t[j] == '0') {
                        return false;
                    }
                    int val = 0;
                    while (j < t.length && Character.isDigit(t[j])) {
                        val = val * 10 + t[j] - '0';
                        j++;
                    }
                    i += val;
                } else {
                    if (s[i++] != t[j++]) {
                        return false;
                    }
                }
            }
            return i == s.length && j == t.length;
        }
    }
///////////////////////////////////////////////////////////////////////

    //Really simple and straightforward Java solution
    public List<String> wordsAbbreviation(List<String> dict) {
        int len=dict.size();
        String[] ans=new String[len];
        int[] prefix=new int[len];
        for (int i=0;i<len;i++) {
            prefix[i]=1;
            ans[i]=makeAbbr(dict.get(i), 1); // make abbreviation for each string
        }
        for (int i=0;i<len;i++) {
            while (true) {
                HashSet<Integer> set=new HashSet<>();
                for (int j=i+1;j<len;j++) {
                    if (ans[j].equals(ans[i])) set.add(j); // check all strings with the same abbreviation
                }
                if (set.isEmpty()) break;
                set.add(i);
                for (int k: set)
                    ans[k]=makeAbbr(dict.get(k), ++prefix[k]); // increase the prefix
            }
        }
        return Arrays.asList(ans);
    }

    private String makeAbbr(String s, int k) {
        if (k>=s.length()-2) return s;
        StringBuilder builder=new StringBuilder();
        builder.append(s.substring(0, k));
        builder.append(s.length()-1-k);
        builder.append(s.charAt(s.length()-1));
        return builder.toString();
    }

////////////////////////////////////////////////////////////////

    //Verbose Java Solution, HashMap(s)
    public List<String> wordsAbbreviation2(List<String> dict) {
        Map<String, String> wordToAbbr = new HashMap<>();
        Map<Integer, List<String>> groups = new HashMap<>();

        // Try to group words by their length. Because no point to compare words with different length.
        // Also no point to look at words with length < 4.
        for (String word : dict) {
            int len = word.length();
            if (len < 4) {
                wordToAbbr.put(word, word);
            }
            else {
                List<String> g = groups.getOrDefault(len, new ArrayList<String>());
                g.add(word);
                groups.put(len, g);
            }
        }

        // For each group of words with same length, generate a result HashMap.
        for (int len : groups.keySet()) {
            Map<String, String> res = getAbbr(groups.get(len));
            for (String word : res.keySet()) {
                wordToAbbr.put(word, res.get(word));
            }
        }

        // Generate the result list
        List<String> result = new ArrayList<>();
        for (String word : dict) {
            result.add(wordToAbbr.get(word));
        }

        return result;
    }

    private Map<String, String> getAbbr(List<String> words) {
        Map<String, String> res = new HashMap<>();
        int len = words.get(0).length();

        // Try to abbreviate a word from index 1 to len - 2
        for (int i = 1; i < len - 2; i++) {
            Map<String, String> abbrToWord = new HashMap<>();
            for (String s : words) {
                if (res.containsKey(s)) continue;
                // Generate the current abbreviation
                String abbr = s.substring(0, i) + (len - 1 - i) + s.charAt(len - 1);
                // Tick: use reversed abbreviation to word map to check if there is any duplicated abbreviation
                if (!abbrToWord.containsKey(abbr)) {
                    abbrToWord.put(abbr, s);
                }
                else {
                    abbrToWord.put(abbr, "");
                }
            }

            // Add unique abbreviations find during this round to result HashMap
            for (String abbr : abbrToWord.keySet()) {
                String s = abbrToWord.get(abbr);
                // Not a unique abbreviation
                if (s.length() == 0) continue;
                res.put(s, abbr);
            }
        }

        // Add all words that can't be shortened.
        for (String s : words) {
            if (!res.containsKey(s)) {
                res.put(s, s);
            }
        }

        return res;
    }
}
/*
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
 注意事项

Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
您在真实的面试中是否遇到过这个题？ Yes
样例
Given dict = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
return ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
标签
 */