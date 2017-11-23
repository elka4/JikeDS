package HF.HF4_Search_DFS;

import org.junit.Test;

import java.util.*;

//Palindrome Partitioning
public class _5PalindromePartitioning {
    // version 1: shorter but slower
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return results;
        }

        List<String> partition = new ArrayList<String>();
        helper(s, 0, partition, results);

        return results;
    }

    private void helper(String s,
                        int startIndex,
                        List<String> partition,
                        List<List<String>> results) {
        if (startIndex == s.length()) {
            results.add(new ArrayList<String>(partition));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            String subString = s.substring(startIndex, i + 1);
            if (!isPalindrome(subString)) {
                continue;
            }
            partition.add(subString);
            helper(s, i + 1, partition, results);
            partition.remove(partition.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test01(){
        System.out.println(partition2("aab"));
    }
//-----------------------------------------------------------------------------------

    // version 2: longer but faster

    List<List<String>> results;
    boolean[][] isPalindrome;

    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition2(String s) {
        results = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return results;
        }

        getIsPalindrome(s);

        helper2(s, 0, new ArrayList<Integer>());

        return results;
    }

    private void getIsPalindrome(String s) {
        int n = s.length();
        isPalindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
    }

    private void helper2(String s,
                        int startIndex,
                        List<Integer> partition) {
        if (startIndex == s.length()) {
            addResult(s, partition);
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (!isPalindrome[startIndex][i]) {
                continue;
            }
            partition.add(i);
            helper2(s, i + 1, partition);
            partition.remove(partition.size() - 1);
        }
    }

    private void addResult(String s, List<Integer> partition) {
        List<String> result = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < partition.size(); i++) {
            result.add(s.substring(startIndex, partition.get(i) + 1));
            startIndex = partition.get(i) + 1;
        }
        results.add(result);
    }

    @Test
    public void test02(){
        System.out.println(partition2("aab"));
    }
//-----------------------------------------------------------------------------------

}
/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.
For example, given s = "aab", Return
[ ["aa","b"], ["a","a","b"] ]
 */

/*
给定一个字符串s，将s分割成一些子串，使每个子串都是回文串。

返回s所有可能的回文串分割方案。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 s = "aab"，返回

[
  ["aa", "b"],
  ["a", "a", "b"]
]
 */
