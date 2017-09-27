package J_5_DFS.other.backtracking;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/permutations/#/discuss


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
public class _7_Palindrome_Partitioning {

//Palindrome Partitioning : https://leetcode.com/problems/palindrome-partitioning/

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


////////////////////////////////////////////////////////////////////////////
//Java: Backtracking solution.
//https://discuss.leetcode.com/topic/6186/java-backtracking-solution/2
    public class Solution {
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



////////////////////////////////////////////////////////////////////////////
//My Java DP only solution without recursion. O(n^2)


    public class Solution2 {
        public  List<List<String>> partition(String s) {
            int len = s.length();
            List<List<String>>[] result = new List[len + 1];
            result[0] = new ArrayList<List<String>>();
            result[0].add(new ArrayList<String>());

            boolean[][] pair = new boolean[len][len];
            for (int i = 0; i < s.length(); i++) {
                result[i + 1] = new ArrayList<List<String>>();
                for (int left = 0; left <= i; left++) {
                    if (s.charAt(left) == s.charAt(i) && (i-left <= 1
                            || pair[left + 1][i - 1])) {

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

/*
Here the pair is to mark a range for the substring is a Pal.
if pair[i][j] is true, that means sub string from i to j is pal.

The result[i], is to store from beginng until current index i (Non inclusive),
 all possible partitions. From the past result we can determine current result.
 */

////////////////////////////////////////////////////////////////////////////
//Java DP + DFS solution
//https://discuss.leetcode.com/topic/37756/java-dp-dfs-solution/2

    public class Solution3 {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            boolean[][] dp = new boolean[s.length()][s.length()];
            for(int i = 0; i < s.length(); i++) {
                for(int j = 0; j <= i; j++) {
                    if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])) {
                        dp[j][i] = true;
                    }
                }
            }
            helper(res, new ArrayList<>(), dp, s, 0);
            return res;
        }

        private void helper(List<List<String>> res, List<String> path,
                            boolean[][] dp, String s, int pos) {
            if(pos == s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }

            for(int i = pos; i < s.length(); i++) {
                if(dp[pos][i]) {
                    path.add(s.substring(pos,i+1));
                    helper(res, path, dp, s, i+1);
                    path.remove(path.size()-1);
                }
            }
        }
    }



////////////////////////////////////////////////////////////////////////////
//Concise Java Solution

    public class Solution4 {
        public List<List<String>> partition(String s) {
            List<List<String>> res=new ArrayList<List<String>>();
            if(s.length()==0)return res;
            recur(res,new ArrayList<String>(),s);
            return res;
        }

        public void recur(List<List<String>> res,List<String> temp, String s){
            if(s.length()==0){
                res.add(new ArrayList<String>(temp));
                return;
            }
            for(int i=0;i<s.length();i++){
                if(isPalin(s.substring(0,i+1))){
                    temp.add(s.substring(0,i+1));
                    recur(res,temp,s.substring(i+1));
                    temp.remove(temp.size()-1);
                }
            }
        }

        public boolean isPalin(String s){
            for(int i=0;i<s.length()/2;i++){
                if(s.charAt(i)!=s.charAt(s.length()-1-i))return false;
            }
            return true;
        }
    }



////////////////////////////////////////////////////////////////////////////
// In order to check whether a string is palindrome, simply compare to reversed itself.

    public class Solution5 {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<List<String>>();
            partition(res, new ArrayList<String>(), s);
            return res;
        }

        public void partition(List<List<String>> res, List<String> list, String s) {
            if(isPali(s)) {
                List<String> l = new ArrayList<String>(list);
                l.add(s);
                res.add(l);
            }

            for(int i = 1; i < s.length(); i++) {
                String s1 = s.substring(0, i), s2 = s.substring(i, s.length());
                if(isPali(s1)) {
                    List<String> ll = new ArrayList<String>(list);
                    ll.add(s1);
                    partition(res, ll, s2);
                }
            }
        }

        public boolean isPali(String s) {

            return s.equals(new StringBuilder(s).reverse().toString());
        }
    }




////////////////////////////////////////////////////////////////////////////

//Concise Java solution

//DFS to find every combinations of the string,
// if the substring is not Palindrome,
// ignore it then go to the next.

    public class Solution6 {
        List<List<String>> result = new ArrayList<List<String>>();
        public List<List<String>> partition(String s) {
            helper(s, new ArrayList<String>());
            return result;
        }

        //DFS every combinations
        public void helper(String s, List<String> cur){
            if(s.length() == 0){result.add(cur); return;}
            for(int i = 1; i <= s.length(); i++){
                String sub = s.substring(0,i);
                if(isPal(sub)){
                    List<String> newList = new ArrayList<String>(cur);
                    newList.add(sub);
                    helper(s.substring(i,s.length()), newList);
                }
                //not palindrome, ignore it
                else continue;
            }
        }

        public boolean isPal(String str){
            int l = 0;
            int r = str.length()-1;
            while(l <= r){
                if(str.charAt(l) != str.charAt(r))  return false;
                l++;r--;
            }
            return true;
        }
    }


////////////////////////////////////////////////////////////////////////////
//Simple backtracking Java solution with 95% performance
public class Solution7 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (s.equals("")) {
            res.add(new ArrayList<String>());
            return res;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s, i + 1)) {
                for (List<String> list : partition(s.substring(i+1))) {
                    list.add(0, s.substring(0, i + 1));
                    res.add(list);
                }
            }
        }
        return res;
    }

    public boolean isPalindrome(String s, int n) {
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1))
                return false;
        }
        return true;
    }
}

//////////////////////////////////////////////////////////////////////////
    //Classic recursive solution in Java

    public class Solution8 {

        private void init(boolean[][] isPal, String s) {
            int len = isPal.length;
            for (int i=0; i<len; i++) isPal[i][i] = true;
            for (int k=1; k<len; k++)
                for (int i=0; i+k<len; i++) {
                    if (s.charAt(i) != s.charAt(i+k)) continue;
                    isPal[i][i+k] = (i+1 <= i+k-1) ? isPal[i+1][i+k-1] : true;
                }

        }

        private void construct(String s, boolean[] cut, List<List<String>> ans) {
            List<String> tmp = new ArrayList<String>();
            int cur = 0;
            for (int i=0; i<cut.length; i++) {
                if (cut[i]) {
                    tmp.add(s.substring(cur,i+1));
                    cur = i+1;
                }
            }
            ans.add(tmp);
        }

        private void helper(String s, int start, int end, boolean[] cut,
                            List<List<String>> ans, boolean[][] isPal) {
            if (start > end) construct(s, cut, ans);
            for (int i=start; i<=end; i++) {
                if (isPal[start][i]) {
                    cut[i] = true;
                    helper(s, i+1, end, cut, ans, isPal);
                    cut[i] = false;
                }
            }
        }

        public List<List<String>> partition(String s) {
            int len = s.length();
            boolean[][] isPal = new boolean[len][len];
            boolean[] cut = new boolean[len];

            init(isPal, s);
            List<List<String>> ans = new ArrayList<List<String>>();
            helper(s, 0, len-1, cut, ans, isPal);
            return ans;
        }
    }




}
