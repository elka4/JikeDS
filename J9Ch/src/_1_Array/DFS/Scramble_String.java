package _1_Array.DFS;
import java.util.*;

/* 87. Scramble String
Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */

public class Scramble_String {

    public boolean isScramble(String s1, String s2) {

        if(s1.length()!=s2.length())
            return false;

        if(s1.length()==0 || s1.equals(s2))
            return true;

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        if(!new String(arr1).equals(new String(arr2))){
            return false;
        }

        for(int i=1; i<s1.length(); i++){
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, s1.length());
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, s2.length());
            String s23 = s2.substring(0, s2.length()-i);
            String s24 = s2.substring(s2.length()-i, s2.length());

            if(isScramble(s11, s21) && isScramble(s12, s22))
                return true;
            if(isScramble(s11, s24) && isScramble(s12, s23))
                return true;
        }

        return false;
    }


////////////////////////////////////////////////////////////////////

    //Accepted Java solution
    public class Solution2 {
        public boolean isScramble(String s1, String s2) {
            if (s1.equals(s2)) return true;

            int[] letters = new int[26];

            for (int i=0; i<s1.length(); i++) {
                letters[s1.charAt(i)-'a']++;
                letters[s2.charAt(i)-'a']--;
            }

            for (int i=0; i<26; i++) if (letters[i]!=0) return false;

            for (int i=1; i<s1.length(); i++) {

                if (isScramble(s1.substring(0,i), s2.substring(0,i))
                        && isScramble(s1.substring(i), s2.substring(i))) return true;

                if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i))
                        && isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) return true;
            }

            return false;
        }
    }



////////////////////////////////////////////////////////////////////

    //Simple iterative DP Java solution with explanation

/*    Explanation in code itself. The iterative version of the idea is considerably slower than the recursive simply because here we consider all possible states, while the recursive will only compute required states as it founds them. Time complexity of both is, in any case, the same.*/

    public class Solution3 {
        public boolean isScramble(String s1, String s2) {
            if (s1.length() != s2.length()) return false;
            int len = s1.length();
            /**
             * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
             * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
             * Let q be the length of a cut (hence, q < k), then we are in the following situation:
             *
             * S1 [   x1    |         x2         ]
             *    i         i + q                i + k - 1
             *
             * here we have two possibilities:
             *
             * S2 [   y1    |         y2         ]
             *    j         j + q                j + k - 1
             *
             * or
             *
             * S2 [       y1        |     y2     ]
             *    j                 j + k - q    j + k - 1
             *
             * which in terms of F means:
             *
             * F(i, j, k) = for some 1 <= q < k we have:
             *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
             *
             * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal
             * */
            boolean [][][] F = new boolean[len][len][len + 1];

            for (int k = 1; k <= len; ++k){

                for (int i = 0; i + k <= len; ++i){

                    for (int j = 0; j + k <= len; ++j){

                        if (k == 1){
                            F[i][j][k] = s1.charAt(i) == s2.charAt(j);
                        } else {
                            for (int q = 1; q < k && !F[i][j][k]; ++q) {
                                F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q])
                                        || (F[i][j + k - q][q] && F[i + q][j][k - q]);
                            }
                        }
                    }
                }
            }


            return F[0][0][len];
        }
    }



////////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////////






}
