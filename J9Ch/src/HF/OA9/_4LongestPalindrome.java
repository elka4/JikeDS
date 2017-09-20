package HF.OA9;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

//模拟
//Longest Palindrome
public class _4LongestPalindrome {

    // version 1
    /**
     * @param s a string which consists of lowercase or uppercase letters
     * @return the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        // Write your code here
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
            }
            else {
                set.add(c);
            }
        }

        int remove = set.size();
        if (remove > 0){
            remove -= 1;
        }

        return s.length() - remove;
    }

    @Test
    public void test01(){
        System.out.println(longestPalindrome("abccccdd"));
    }

//////////////////////////////////////////////////////////////////

    // version 2
    public int longestPalindrome2(String s) {
        int[] charStatArray = new int[52];
        int oneTimeOddCount = 0;
        int evenCount = 0;

        // zero clearing of the array
        //memset(charStatArray, 0, sizeof(charStatArray));

        // keep the times of appearance of each character in the array
        for (char ch: s.toCharArray()) {
            if (ch >= 97) {
                charStatArray[26 + ch - 'a']++;
            }
            else {
                charStatArray[ch - 'A']++;
            }
        }

        // the answer is the count of characters that has even number of appereances.
        // for characters that has odd number of appereances,
        // their appereances minus 1 will make their apperances even.
        // And finally we can put an unused character in the middle of the palindrome
        // (if there is any).
        for (int cnt: charStatArray) {
            if (cnt != 0) {
                if (cnt % 2 == 0) {
                    evenCount += cnt;
                } else {
                    if (cnt == 1) {
                        oneTimeOddCount++;
                    }
                    else {
                        evenCount += cnt - 1;
                        oneTimeOddCount++;
                    }
                }
            }
        }

        return oneTimeOddCount > 0 ? 1 + evenCount : evenCount;
    }

    @Test
    public void test02(){
        System.out.println(longestPalindrome2("abccccdd"));
    }

//////////////////////////////////////////////////////////////////

}
/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

 Notice

Assume the length of given string will not exceed 1010.

Have you met this question in a real interview? Yes
Example
Given s = "abccccdd" return 7

One longest palindrome that can be built is "dccaccd", whose length is 7.
 */