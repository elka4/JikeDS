package HF.HF0;

import org.junit.Test;

// Check Word Abbreviation
public class _6G_CheckWordsAbbreviation {
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


///////////////////////////////////////////////////////////////

    // version: 高频题班
    /**
     * @param word a non-empty string
     * @param abbr an abbreviation
     * @return true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation2(String word, String abbr) {
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

    @Test
    public void test01(){
        System.out.println(validWordAbbreviation2("wqwrtyasdfga", "w10b"));
    }


///////////////////////////////////////////////////////////////////////


}

/*
Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 注意事项

Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.


Example 1:

Given s = "internationalization", abbr = "i12iz4n":
Return true.
Example 2:

Given s = "apple", abbr = "a2e":
Return false.
 */
