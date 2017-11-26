package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;

//  131     Palindrome Partitioning

/*
132. Palindrome Partitioning II  is DP
 */

public class _131_BackTracking_Palindrome_Partitioning_M {
    public class Solution {
        public List<List<String>> partition(String s) {
            List<List<String>> list = new ArrayList<>();
            backtrack(list, new ArrayList<>(), s, 0);
            return list;
        }

        public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
            if(start == s.length())
                list.add(new ArrayList<>(tempList));
            else{
                for(int i = start; i < s.length(); i++){
                    if(isPalindrome(s, start, i)){
                        tempList.add(s.substring(start, i + 1));
                        backtrack(list, tempList, s, i + 1);
                        tempList.remove(tempList.size() - 1);
                    }
                }
            }
        }

        public boolean isPalindrome(String s, int low, int high){
            while(low < high)
                if(s.charAt(low++) != s.charAt(high--)) return false;
            return true;
        }
    }


    public class Solution2 {
        public List<List<String>> partition(String s) {
            int len = s.length();
            List<List<String>>[] result = new List[len + 1];
            result[0] = new ArrayList<List<String>>();
            result[0].add(new ArrayList<String>());

            boolean[][] pair = new boolean[len][len];
            for (int i = 0; i < s.length(); i++) {
                result[i + 1] = new ArrayList<List<String>>();
                for (int left = 0; left <= i; left++) {
                    if (s.charAt(left) == s.charAt(i) && (i-left <= 1 || pair[left + 1][i - 1])) {
                        pair[left][i] = true;
                        String str = s.substring(left, i + 1);
                        for (List<String> r : result[left]) {
                            List<String> ri = new ArrayList<String>(r);
                            ri.add(str);
                            result[i + 1].add(ri);
                        }
                    }
                }
            }
            return result[len];
        }
    }


    public class Solution3 {
        List<List<String>> resultLst;
        ArrayList<String> currLst;
        public List<List<String>> partition(String s) {
            resultLst = new ArrayList<List<String>>();
            currLst = new ArrayList<String>();
            backTrack(s,0);
            return resultLst;
        }
        public void backTrack(String s, int l){
            if(currLst.size()>0 //the initial str could be palindrome
                    && l>=s.length()){
                List<String> r = (ArrayList<String>) currLst.clone();
                resultLst.add(r);
            }
            for(int i=l;i<s.length();i++){
                if(isPalindrome(s,l,i)){
                    if(l==i)
                        currLst.add(Character.toString(s.charAt(i)));
                    else
                        currLst.add(s.substring(l,i+1));
                    backTrack(s,i+1);
                    currLst.remove(currLst.size()-1);
                }
            }
        }
        public boolean isPalindrome(String str, int l, int r){
            if(l==r) return true;
            while(l<r){
                if(str.charAt(l)!=str.charAt(r)) return false;
                l++;r--;
            }
            return true;
        }
    }
//-------------------------------------------------------------------------------
    // jiuzhang

    // version 1: shorter but slower
    public class Jiuzhang1 {
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
    }

    // version 2: longer but faster
    public class Jiuzhang2 {
        List<List<String>> results;
        boolean[][] isPalindrome;

        /**
         * @param s: A string
         * @return: A list of lists of string
         */
        public List<List<String>> partition(String s) {
            results = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return results;
            }

            getIsPalindrome(s);

            helper(s, 0, new ArrayList<Integer>());

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

        private void helper(String s,
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
                helper(s, i + 1, partition);
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
    }
//-------------------------------------------------------------------------------


//-------------------------------------------------------------------------------



}
/*
 Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]

 */

/*
lint

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




