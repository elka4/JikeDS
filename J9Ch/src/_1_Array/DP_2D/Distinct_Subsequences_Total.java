package _1_Array.DP_2D;

import java.util.ArrayList;
import java.util.HashMap;
/*
LeetCode – Distinct Subsequences Total (Java)

Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.

Analysis

The problem itself is very difficult to understand. It can be stated like this:
Give a sequence S and T, how many distinct sub sequences from S equals to T?
How do you define "distinct" subsequence? Clearly, the 'distinct' here mean different operation combination, not the final string of subsequence. Otherwise, the result is always 0 or 1. -- from Jason's comment

When you see string problem that is about subsequence or matching, dynamic programming method should come to mind naturally. The key is to find the initial and changing condition.


 */
public class Distinct_Subsequences_Total {
   /* Java Solution 1

    Let W(i, j) stand for the number of subsequences of S(0, i) equals to T(0, j). If S.charAt(i) == T.charAt(j), W(i, j) = W(i-1, j-1) + W(i-1,j); Otherwise, W(i, j) = W(i-1,j).*/

    public int numDistincts(String S, String T) {
        int[][] table = new int[S.length() + 1][T.length() + 1];

        for (int i = 0; i < S.length(); i++)
            table[i][0] = 1;

        for (int i = 1; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    table[i][j] += table[i - 1][j] + table[i - 1][j - 1];
                } else {
                    table[i][j] += table[i - 1][j];
                }
            }
        }

        return table[S.length()][T.length()];
    }
    /*Java Solution 2

    Do NOT write something like this, even it can also pass the online judge.*/

    public int numDistinct2(String S, String T) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<Character, ArrayList<Integer>>();

        for (int i = 0; i < T.length(); i++) {
            if (map.containsKey(T.charAt(i))) {
                map.get(T.charAt(i)).add(i);
            } else {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                map.put(T.charAt(i), temp);
            }
        }

        int[] result = new int[T.length() + 1];
        result[0] = 1;

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (map.containsKey(c)) {
                ArrayList<Integer> temp = map.get(c);
                int[] old = new int[temp.size()];

                for (int j = 0; j < temp.size(); j++)
                    old[j] = result[temp.get(j)];

                // the relation
                for (int j = 0; j < temp.size(); j++)
                    result[temp.get(j) + 1] = result[temp.get(j) + 1] + old[j];
            }
        }

        return result[T.length()];
    }

}
