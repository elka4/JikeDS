package Bit_Manipulation_26;
import java.util.*;

public class b_320_Generalized_Abbreviation {

    /*
    Approach #1 (Backtracking) [Accepted]

Intuition

How many abbreviations are there for a word of length nn? The answer is 2^n2
​n
​​  because each character can either be abbreviated or not, resulting in different abbreviations.

Algorithm

The backtracking algorithm enumerates a set of partial candidates that, in principle,
could be completed in several choices to give all the possible solutions to the problem.
 The completion is done incrementally, by extending the candidate in many steps.
 Abstractly, the partial candidates can be seen as nodes of a tree, the potential search tree.
 Each partial candidate is the parent of the candidates that derives from it by an extension step;
  the leaves of the tree are the partial candidates that cannot be extended any further.

In our problem, the partial candidates are incomplete abbreviations that can be extended by
one of the two choices:

keep the next character;
abbreviate the next character.
We extend the potential candidate in a depth-first manner. We backtrack when we reach a leaf
 node in the search tree. All the leaves in the search tree are valid abbreviations and shall
  be put into a shared list which will be returned at the end.
     */
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
/*
Time complexity : O(n 2^n)O(n2
​n
​​ ). For each call to backtrack, it either returns without branching, or it branches
into two recursive calls. All these recursive calls form a complete binary recursion
tree with 2^n2
​n
​​  leaves and 2^n - 12
​n
​​ −1 inner nodes. For each leaf node, it needs O(n)O(n) time for converting builder
to String; for each internal node, it needs only constant time. Thus, the total time
 complexity is dominated by the leaves. In total that is O(n2^n)O(n2
​n
​​ ).

Space complexity : O(n)O(n). If the return list doesn't count, we only need O(n)O(n)
 auxiliary space to store the characters in StringBuilder and the O(n)O(n) space used
  by system stack. In a recursive program, the space of system stack is linear to the
   maximum recursion depth which is nn in our problem.


 */

////////////////////////////////////////////////////////////////////////////////////////////////////



/*
Approach #2 (Bit Manipulation) [Accepted]

Intuition

If we use 00 to represent a character that is not abbreviated and 11 to represent one that is.
 Then each abbreviation is mapped to an nn bit binary number and vice versa.

Algorithm

To generate all the 2^n2
​n
​​  abbreviation with non-repetition and non-omission, we need to follow rules. In approach #1,
the rules are coded in the backtracking process. Here we introduce another way.

From the intuition section, each abbreviation has a one to one relationship to a nn bit binary
 number xx. We can use these numbers as blueprints to build the corresponding abbreviations.

For example:

Given word = "word" and x = 0b0011

Which means 'w' and 'o' are kept, 'r' and 'd' are abbreviated. Therefore, the result is "wo2".

Thus, for a number xx, we just need to scan it bit by bit as if it is an array so that we know
 which character should be kept and which should be abbreviated.

To scan a number xx bit by bit, one could extract its last bit by b = x & 1 and shift xx one bit
 to the right, i.e. x >>= 1. Doing this repeatedly, one will get all the nn bits of xx from last
  bit to first bit.
 */


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

    /*
    Time complexity : O(n 2^n)O(n2
​n
​​ ). Building one abbreviation from the number xx, we need scan all the nn bits.
Besides the StringBuilder::toString function is also linear. Thus, to generate all the 2^n2
​n
​​ , it costs O(n 2^n)O(n2
​n
​​ ) time.

Space complexity : O(n)O(n). If the return list doesn't count, we only need O(n)O(n)
auxiliary space to store the characters in StringBuilder.
     */
////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////

}
/*
vWrite a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1",
 "1o2", "2r1", "3d", "w3", "4"]

 */