package _08_Bit;
import java.util.*;
import org.junit.Test;

//  320. Generalized Abbreviation

//  https://leetcode.com/problems/generalized-abbreviation/description/
//
public class _320_Bit_Generalized_Abbreviation_M {

//  https://leetcode.com/problems/generalized-abbreviation/solution/

    //Approach #1 (Backtracking) [Accepted]
    public class Solution {
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

    //  Approach #2 (Bit Manipulation) [Accepted]
    public class Solution2 {
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
                        builder.append(k); k = 0; // reset the counter k
                    }
                    builder.append(word.charAt(i));
                }
                else// bit is one, increase k
                ++k;
            }
            if (k != 0)
                builder.append(k); //don't forget to append the last k if non zero
            return builder.toString();
        }
    }

/////////////////////////////////////////////////////////////////////////
//  Meet in Google Interview Solution with concise explanation.

/*    I meet this problem in Google Interview. However, I didn't solve it at that time because I was totally out of mind when I meet with this problem. The interviewer didn't say much about the output and he first ask me how many abbreviation are there with a given word length of n. It took me a long time to guess it was 2^n.
    The following solution is what based on the idea that each position has the chance to be abbreviated to 1 or not with a recursion function call. It is quite similar to the question of SubsetsII.*/

    public class Solution3 {
        public List<String> generateAbbreviations(String word) {
            List<String> res = new LinkedList<>();
            recurse(res, word, 0);
            return res;
        }
        private void recurse(List<String> res, String word, int pos){
            if(pos==word.length()){
                res.add(word);
                return;
            }
        /* The current position does not abbreviate to 1 and call the recursion with the next position */

            recurse(res, word, pos+1);
            String nstring = word.substring(0,pos)+"1"+word.substring(pos+1);

      /* Abbreviate the current position and we have to check the position prior to this position.
       If the position prior to this position is a number, we have to combine them together.
      But there is still a little tricky to deal with the output because if the combined output is
      those 9, 99, 999, then the next position should be pos+1 with recursion call. If not,
     the next position should remain the same pos. */

            if(pos>0 && Character.isDigit(word.charAt(pos-1))){
                int count = 0;

           /*count the prior characters which is digits and we should combine them with 1 */

                while((pos-count-1)>=0 && Character.isDigit(word.charAt(pos-count-1))){
                    count++;
                }
                int num = Integer.parseInt(word.substring(pos-count, pos));
                num = num+1;
                String nnum = num+"";
                if(nnum.length()>count){
                    nstring = word.substring(0, pos-count)+nnum+word.substring(pos+1);
                    recurse(res, nstring, pos+1);
                }else{
                    nstring = word.substring(0, pos-count)+nnum+word.substring(pos+1);
                    recurse(res, nstring, pos);
                }
            }else{
                recurse(res, nstring, pos+1);
            }
        }
    }

/////////////////////////////////////////////////////////////////////////
//Java backtracking solution
class Solution4{
/*    The idea is: for every character, we can keep it or abbreviate it. To keep it, we add it to the current solution and carry on backtracking. To abbreviate it, we omit it in the current solution, but increment the count, which indicates how many characters have we abbreviated. When we reach the end or need to put a character in the current solution, and count is bigger than zero, we add the number into the solution.*/

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


/////////////////////////////////////////////////////////////////////////
//Java 14ms beats 100%
class Solution5{
/*    For each char c[i], either abbreviate it or not.

            Abbreviate: count accumulate num of abbreviating chars, but don't append it yet.
    Not Abbreviate: append accumulated num as well as current char c[i].
    In the end append remaining num.
    Using StringBuilder can decrease 36.4% time.
    This comes to the pattern I find powerful:

    int len = sb.length(); // decision point
... backtracking logic ...
            sb.setLength(len);     // reset to decision point
    Similarly, check out remove parentheses and add operators.*/

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

//9 line easy JAVA solution
public class Solution6 {
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


/////////////////////////////////////////////////////////////////////////
}
/*
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1",
"w1r1", "1o2", "2r1", "3d", "w3", "4"]

 */

/*

 */