package _String._Palindrome;
import java.util.*;
import org.junit.Test;

//  5. Longest Palindromic Substring
//  https://leetcode.com/problems/longest-palindromic-substring/
//  http://www.lintcode.com/problem/longest-palindromic-substring/
//
//给出一个字符串（假设长度最长为1000），求出它的最长回文子串，你可以假定只有一个满足条件的最长回文串。
//
//    214. Shortest Palindrome - String
//    266. Palindrome Permutation - Hash
//    336. Palindrome Pairs - Hash, String, Trie
//    516. Longest Palindromic Subsequence - DP
//    647. Palindromic Substrings - String, DP
//  9:
//
public class _005_String_Longest_Palindromic_Substring_M {
//------------------------------------------------------------------------------
//https://leetcode.com/articles/longest-palindromic-substring/
//------------------------------------------------------------------------------
    //1
    //Approach #4 (Expand Around Center) [Accepted]
    class Solution4{
        public String longestPalindrome(String s) {
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int len1 = expandAroundCenter(s, i, i);
                int len2 = expandAroundCenter(s, i, i + 1);
                int len = Math.max(len1, len2);
                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expandAroundCenter(String s, int left, int right) {
            int L = left, R = right;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            }
            return R - L - 1;
        }
    }




//------------------------------------------------------------------------------
    //2
    //Very simple clean java solution
    //    The performance is pretty good, surprisingly.
    public class Solution5 {
        private int lo, maxLen;

        public String longestPalindrome(String s) {
            int len = s.length();
            if (len < 2)
                return s;

            for (int i = 0; i < len-1; i++) {
                //assume odd length, try to extend Palindrome as possible
                extendPalindrome(s, i, i);
                extendPalindrome(s, i, i+1); //assume even length.
            }
            return s.substring(lo, lo + maxLen);
        }

        private void extendPalindrome(String s, int j, int k) {
            while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                j--;
                k++;
            }
            if (maxLen < k - j - 1) {
                lo = j + 1;
                maxLen = k - j - 1;
            }
        }}
//------------------------------------------------------------------------------
    //3
    //(AC) relatively short and very clear Java solution
    //    Key idea, every time we move to right, we only need to consider whether using this new character as tail could produce new palindrome string of length (current length +1) or (current length +2)

    public class Solution6 {
        public String longestPalindrome(String s) {
            String res = "";
            int currLength = 0;
            for(int i=0;i<s.length();i++){
                if(isPalindrome(s,i-currLength-1,i)){
                    res = s.substring(i-currLength-1,i+1);
                    currLength = currLength+2;
                }
                else if(isPalindrome(s,i-currLength,i)){
                    res = s.substring(i-currLength,i+1);
                    currLength = currLength+1;
                }
            }
            return res;
        }

        public boolean isPalindrome(String s, int begin, int end){
            if(begin<0) return false;
            while(begin<end){
                if(s.charAt(begin++)!=s.charAt(end--)) return false;
            }
            return true;
        }
    }

//------------------------------------------------------------------------------
    //4
//Share my Java solution using dynamic programming
//    dp(i, j) represents whether s(i ... j) can form a palindromic substring, dp(i, j) is true when s(i) equals to s(j) and s(i+1 ... j-1) is a palindromic substring. When we found a palindrome, check if it's the longest one. Time complexity O(n^2).
    public class Solution7 {
        public String longestPalindrome(String s) {
            int n = s.length();
            String res = null;

            boolean[][] dp = new boolean[n][n];

            for (int i = n - 1; i >= 0; i--) {
                for (int j = i; j < n; j++) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                    if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
                        res = s.substring(i, j + 1);
                    }
                }
            }

            return res;
        }
    }

//------------------------------------------------------------------------------
    //5
    //Clean and simple JAVA solution with comments
    public class Solution8 {
        public String longestPalindrome(String s) {
            if (s.isEmpty()) {
                return null;
            }

            if (s.length() == 1) {
                return s;
            }

            String longest = s.substring(0, 1);
            for (int i = 0; i < s.length(); i++) {

                // get longest palindrome with center of i
                String tmp = helper(s, i, i);
                if (tmp.length() > longest.length()) {
                    longest = tmp;
                }

                // get longest palindrome with center of i, i+1
                tmp = helper(s, i, i + 1);
                if (tmp.length() > longest.length()) {
                    longest = tmp;
                }
            }
            return longest;}

        public String helper(String s, int begin, int end) {

            while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {

                begin--;

                end++;

            }

            return s.substring(begin + 1, end);
        }
    }



//------------------------------------------------------------------------------
    //6
    //Java easy-understanding solution. Beats 97%
    class Solution9{
        public String longestPalindrome(String s) {
            char[] ca = s.toCharArray();
            int rs = 0, re = 0;
            int max = 0;
            for(int i = 0; i < ca.length; i++) {
                if(isPalindrome(ca, i - max - 1, i)) {
                    rs = i - max - 1; re = i;
                    max += 2;
                } else if(isPalindrome(ca, i - max, i)) {
                    rs = i - max; re = i;
                    max += 1;
                }
            }
            return s.substring(rs, re + 1);
        }

        private boolean isPalindrome(char[] ca, int s, int e) {
            if(s < 0) return false;

            while(s < e) {
                if(ca[s++] != ca[e--]) return false;
            }
            return true;
        }
    }


//------------------------------------------------------------------------------
    //7
    //Easy java solution with O(1) space and O(n^2) time
    /*
    The basic idea is to traverse all the palindromes with its pivot range from the first char of string s to the last char of string s (consider both even length and odd length situation). Use StringBuilder to minimize the space complexity. Here is the code, feast yourself:
     */
    public class Solution10 {
        StringBuilder longest = new StringBuilder("");

        public String longestPalindrome(String s) {
            if (s.length() <= 1) return s;

            for (int i = 0; i < s.length(); i++) {
                expand(s, longest, i, i); //odd
                expand(s, longest, i, i + 1); //even
            }

            return longest.toString();
        }

        private void expand(String s, StringBuilder longest, int i, int j) {
            while (i >= 0 && j < s.length()) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i + 1 > longest.length()) {
                        longest.delete(0, longest.length());
                        longest.append(s.substring(i, j + 1));
                    }
                    i--;
                    j++;
                }
                else
                    break;
            }
        }
    }


//------------------------------------------------------------------------------
    //8
    //Beat 100% Java Solution, and easy to understand
    class Solution11{
        public String longestPalindrome(String s) {
            if(s==null){
                return "";
            }
            char[] arr = s.toCharArray();
            int max = 0;
            int maxi = 0;
            int maxj = 0;

            for(int i = 0; i< arr.length;){
                int i1 = getFarestSameElementIndex(arr,i);
                int dist = getDistance(arr,i,i1);
                int index1 = i-dist;
                int index2 = i1 + dist;
                int l = index2 - index1;
                if(l>max){
                    max = l;
                    maxi = index1;
                    maxj = index2;
                }
                i = i1+1;
            }

            return s.substring(maxi, maxj+1);
        }

        private int getDistance(char[] arr,int index1,int index2){
            int i1 = index1-1;
            int i2 = index2+1;
            int dist = 0;
            while(i1>=0&&i2<arr.length){
                if(arr[i1]==arr[i2]){
                    dist++;
                }else{
                    break;
                }
                i1--;i2++;
            }
            return dist;
        }

        private int getFarestSameElementIndex(char[] arr, int index){
            for(int i = index+1;i<arr.length;i++){
                if(arr[i]!=arr[index]){
                    return i-1;
                }
            }
            return arr.length-1;
        }
    }

//------------------------------------------------------------------------------
    //9
    //9Ch
    public class Jiuzhang {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }

            int length = s.length();
            int max = 0;
            String result = "";
            for(int i = 1; i <= 2 * length - 1; i++){
                int count = 1;
                while(i - count >= 0 && i + count <= 2 * length  &&
                        get(s, i - count) == get(s, i + count)){
                    count++;
                }
                count--; // there will be one extra count for the outbound #
                if(count > max) {
                    result = s.substring((i - count) / 2, (i + count) / 2);
                    max = count;
                }
            }

            return result;
        }

        private char get(String s, int i) {
            if(i % 2 == 0)
                return '#';
            else
                return s.charAt(i / 2);
        }
    }

//------------------------------------------------------------------------------
}
/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"
Seen this question in a real interview before?   Yes  No
Companies
Microsoft Amazon Bloomberg

Related Topics
String

Similar Questions
214. Shortest Palindrome - String
266. Palindrome Permutation - Hash
336. Palindrome Pairs - Hash, String, Trie
516. Longest Palindromic Subsequence - DP
647. Palindromic Substrings - String, DP
 */