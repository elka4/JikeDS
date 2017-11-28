package _String._Palindrome;
import java.util.*;
import org.junit.Test;

//  647. Palindromic Substrings
//  https://leetcode.com/problems/palindromic-substrings/
//String Dynamic Programming
//
//  5 Longest Palindromic Substring - String
//  516 Longest Palindromic Subsequence - DP
//  647 Palindromic Substrings - String, DP
//  5:
//
public class _647_String_Palindromic_Substrings_M {
//------------------------------------------------------------------------------
    //1
//Java solution, 8 lines, extendPalindrome
//    Idea is start from each index and try to extend palindrome for both odd and even length.

    public class Solution1 {
        int count = 0;

        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) return 0;

            for (int i = 0; i < s.length(); i++) { // i is the mid point
                extendPalindrome(s, i, i); // odd length;
                extendPalindrome(s, i, i + 1); // even length
            }

            return count;
        }

        private void extendPalindrome(String s, int left, int right) {
            while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++; left--; right++;
            }
        }
    }
//------------------------------------------------------------------------------
    //2
    //Java DP solution based on longest palindromic substring
    //This solution is almost same as the DP solution for longest palindromic substring, instead of storing the longest, just get the count of palindromic substrings.
    public class Solution2 {
        public int countSubstrings(String s) {
            int n = s.length();
            int res = 0;
            boolean[][] dp = new boolean[n][n];
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i; j < n; j++) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                    if(dp[i][j]) ++res;
                }
            }
            return res;
        }
    }



//------------------------------------------------------------------------------
    //3
    //[Java/C++] 6 lines solution - NO DP
/*
The idea is count the number of different palindromic substrings from their respective middle.

In the following code, when we consider the substring s[i-j, ..., i+j], i is the middle index of the substring; When we consider the substring s[i-1-j, ..., i+j], (i-1, i) is the middle index of the substring.
 */
    class Solution3{
        public int countSubstrings(String s) {
            int res = 0, n = s.length();
            for(int i = 0; i<n ;i++ ){
                for(int j = 0; i-j >= 0 && i+j < n && s.charAt(i-j) == s.charAt(i+j); j++)res++; //substring s[i-j, ..., i+j]
                for(int j = 0; i-1-j >= 0 && i+j < n && s.charAt(i-1-j) == s.charAt(i+j); j++)res++; //substring s[i-1-j, ..., i+j]
            }
            return res;
        }
    }
//------------------------------------------------------------------------------
    //4
    //A very easy explanation with an example
    class Solution4{
        int count =1;
        public int countSubstrings(String s) {
            if(s.length()==0)
                return 0;
            for(int i=0; i<s.length()-1; i++){
                checkPalindrome(s,i,i);     //To check the palindrome of odd length palindromic sub-string
                checkPalindrome(s,i,i+1);   //To check the palindrome of even length palindromic sub-string
            }
            return count;
        }

        private void checkPalindrome(String s, int i, int j) {
            while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){    //Check for the palindrome string
                count++;    //Increment the count if palindromin substring found
                i--;    //To trace string in left direction
                j++;    //To trace string in right direction
            }
        }
    }
//------------------------------------------------------------------------------
    //5
    //  Java Concise O(n^2) Time O(1) Space DP Solution
    class Solution5{
        public int countSubstrings(String s) {
            int count = 0;
            for (int i=0;i<s.length();i++) count += getCount(s, i, 0) + getCount(s, s.length() - 1, i + 1);
            return count;
        }

        public int getCount(String s, int l, int r) {
            int count = 0;
            while (l >= 0 && r < s.length()) {
                if (l >= r || s.charAt(l) == s.charAt(r)) count += l-- <= r++ ? 1 : 0;
                else break;
            }
            return count;
        }
    }
//------------------------------------------------------------------------------
}
/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.
Seen this question in a real interview before?   Yes  No
Companies
Facebook LinkedIn

Related Topics
String Dynamic Programming

Similar Questions
Longest Palindromic Substring
Longest Palindromic Subsequence
Palindromic Substrings


 */