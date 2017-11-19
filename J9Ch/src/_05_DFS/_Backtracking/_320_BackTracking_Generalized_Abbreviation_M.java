package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _320_BackTracking_Generalized_Abbreviation_M {
    //Java backtracking solution
    class Solution{
        public List<String> generateAbbreviations(String word){
            List<String> ret = new ArrayList<String>();
            backtrack(ret, word, 0, "", 0);

            return ret;
        }

        private void backtrack(List<String> ret, String word, int pos, String cur, int count){
            if(pos==word.length()){
                if(count > 0) cur += count;
                ret.add(cur);
            }
            else{
                backtrack(ret, word, pos + 1, cur, count + 1);
                backtrack(ret, word, pos+1, cur + (count>0 ? count : "") + word.charAt(pos), 0);
            }
        }
    }

    public class Solution2 {
        public List<String> generateAbbreviations(String word) {
            List<String> res = new ArrayList<String>();
            int len = word.length();
            res.add(len==0 ? "" : String.valueOf(len));
            for(int i = 0 ; i < len ; i++)
                for(String right : generateAbbreviations(word.substring(i+1))){
                    String leftNum = i > 0 ? String.valueOf(i) : "";
                    res.add( leftNum + word.substring(i,i + 1) + right );
                }
            return res;
        }
    }

    //Java 14ms beats 100%
    /*
    For each char c[i], either abbreviate it or not.

    Abbreviate: count accumulate num of abbreviating chars, but don't append it yet.
    Not Abbreviate: append accumulated num as well as current char c[i].
    In the end append remaining num.
    Using StringBuilder can decrease 36.4% time.
    This comes to the pattern I find powerful:

    int len = sb.length(); // decision point
    ... backtracking logic ...
    sb.setLength(len);     // reset to decision point
     */
    class Solution3{
        public List<String> generateAbbreviations(String word) {
            List<String> res = new ArrayList<>();
            DFS(res, new StringBuilder(), word.toCharArray(), 0, 0);
            return res;
        }

        public void DFS(List<String> res, StringBuilder sb, char[] c, int i, int num) {
            int len = sb.length();
            if(i == c.length) {
                if(num != 0) sb.append(num);
                res.add(sb.toString());
            } else {
                DFS(res, sb, c, i + 1, num + 1);               // abbr c[i]

                if(num != 0) sb.append(num);                   // not abbr c[i]
                DFS(res, sb.append(c[i]), c, i + 1, 0);
            }
            sb.setLength(len);
        }
    }


    //https://leetcode.com/problems/generalized-abbreviation/solution/
    //Approach #1 (Backtracking) [Accepted]
    public class Solution4 {
        public List<String> generateAbbreviations(String word){
            List<String> ans = new ArrayList<String>();
            backtrack(ans, new StringBuilder(), word, 0, 0);
            return ans;
        }

        // i is the current position
        // k is the count of consecutive abbreviated characters
        private void backtrack(List<String> ans, StringBuilder builder, String word, int i, int k){
            int len = builder.length(); // keep the length of builder
            if(i == word.length()){
                if (k != 0) builder.append(k); // append the last k if non zero
                ans.add(builder.toString());
            } else {
                // the branch that word.charAt(i) is abbreviated
                backtrack(ans, builder, word, i + 1, k + 1);

                // the branch that word.charAt(i) is kept
                if (k != 0) builder.append(k);
                builder.append(word.charAt(i));
                backtrack(ans, builder, word, i + 1, 0);
            }
            builder.setLength(len); // reset builder to the original state
        }
    }


    //Approach #2 (Bit Manipulation) [Accepted]
    public class Solution5 {
        public List<String> generateAbbreviations(String word) {
            List<String> ans = new ArrayList<>();
            for (int x = 0; x < (1 << word.length()); ++x) // loop through all possible x
                ans.add(abbr(word, x));
            return ans;
        }

        // build the abbreviation for word from number x
        private String abbr(String word, int x) {
            StringBuilder builder = new StringBuilder();
            int k = 0, n = word.length(); // k is the count of consecutive ones in x
            for (int i = 0; i < n; ++i, x >>= 1) {
                if ((x & 1) == 0) { // bit is zero, we keep word.charAt(i)
                    if (k != 0) { // we have abbreviated k characters
                        builder.append(k);
                        k = 0; // reset the counter k
                    }
                    builder.append(word.charAt(i));
                }
                else // bit is one, increase k
                    ++k;
            }
            if (k != 0) builder.append(k); //don't forget to append the last k if non zero
            return builder.toString();
        }
    }

//-------------------------------------------------------------------------/


//-------------------------------------------------------------------------/
}
/*
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Seen this question in a real interview before?   Yes  No
Difficulty:Medium
Total Accepted:24.7K
Total Submissions:54.6K
Contributor: LeetCode

 */