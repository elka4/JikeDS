package _05_DFS._Back_String;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//  131     Palindrome Partitioning
//  132. Palindrome Partitioning II  is DP
//  https://leetcode.com/problems/palindrome-partitioning/description/
//  http://www.lintcode.com/zh-cn/problem/palindrome-partitioning/
//  Backtracking
//  10: 1
public class _131_BackTracking_Palindrome_Partitioning_M {
    //1
    //传到下一层的是string和新一轮的start
    //Backtracking, 用tempList来存储状态
    public List<List<String>> partition01(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
        if(start == s.length()){
            list.add(new ArrayList<>(tempList));
            return;
        }

        for(int i = start; i < s.length(); i++){
            if(isPalindrome01(s, start, i)){
                tempList.add(s.substring(start, i + 1));
                backtrack(list, tempList, s, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public boolean isPalindrome01(String s, int low, int high){
        while(low < high)
            if(s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    @Test
    public void test01(){
        System.out.println(partition01("aab"));
    }//[[a, a, b], [aa, b]]


//-------------------------------------------------------------------------///////////
    //2
    //Concise Java Solution
    //这个貌似和1类似，每次传下去的是新的String，一个substring。什么时候substring为空串，就是全都取完了可以加入results。
    public List<List<String>> partition02(String s) {
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
    @Test
    public void test02(){
        System.out.println(partition02("aab"));
    }//[[a, a, b], [aa, b]]

//-------------------------------------------------------------------------/
    //3
    //My Java DP only solution without recursion. O(n^2)
    public List<List<String>> partition03(String s) {
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
    @Test
    public void test03(){
        System.out.println(partition03("aab"));
    }//[[a, a, b], [aa, b]]

//-------------------------------------------------------------------------/
    //4
    //Java: Backtracking solution.
    //https://discuss.leetcode.com/topic/6186/java-backtracking-solution
    List<List<String>> resultLst;
    ArrayList<String> currLst;
    public List<List<String>> partition04(String s) {
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
    @Test
    public void test04(){
        System.out.println(partition04("aab"));
    }//[[a, a, b], [aa, b]]

//-------------------------------------------------------------------------/
    //5
    //Java DP + DFS solution
/*
The normal dfs backtracking will need to check each substring for palindrome, but a dp array can be used to record the possible break for palindrome before we start recursion.

            Edit:
    Sharing my thought process:
    first, I ask myself that how to check if a string is palindrome or not, usually a two point solution scanning from front and back. Here if you want to get all the possible palindrome partition, first a nested for loop to get every possible partitions for a string, then a scanning for all the partitions. That's a O(n^2) for partition and O(n^2) for the scanning of string, totaling at O(n^4) just for the partition. However, if we use a 2d array to keep track of any string we have scanned so far, with an addition pair, we can determine whether it's palindrome or not by justing looking at that pair, which is this line if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])). This way, the 2d array dp contains the possible palindrome partition among all.

            second, based on the prescanned palindrome partitions saved in dp array, a simple backtrack does the job.
*/

    public List<List<String>> partition05(String s) {
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

    private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
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
    @Test
    public void test05(){
        System.out.println(partition05("aab"));
    }//[[a, a, b], [aa, b]]

//-------------------------------------------------------------------------/
    //6
    //Concise Java solution
    //DFS to find every combinations of the string, if the substring is not Palindrome, ignore it then go to the next.

    List<List<String>> result = new ArrayList<List<String>>();
    public List<List<String>> partition06(String s) {
        helper(s, new ArrayList<String>());
        return result;
    }

    public void helper(String s, List<String> cur){                 //DFS every combinations
        if(s.length() == 0){result.add(cur); return;}
        for(int i = 1; i <= s.length(); i++){
            String sub = s.substring(0,i);
            if(isPal(sub)){
                List<String> newList = new ArrayList<String>(cur);
                newList.add(sub);
                helper(s.substring(i,s.length()), newList);
            }
            else continue;                                    //not palindrome, ignore it
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
//    note: I found some people using the same method of mine, but they like to call their methods "backtracking", it is actually DFS, note backtracking.
    @Test
    public void test06(){
        System.out.println(partition06("aab"));
    }//[[a, a, b], [aa, b]]


//-------------------------------------------------------------------------/
    //7

/*    Classic recursive solution in Java

5
    C compileme
    Reputation:  10
    The init method computes the isPal[][] array, where isPal[i][j] is true if s[i..j] is palindrome. The helper method is doing the actual recursion, where the cut[] array records the cut positions, and the construct method reconstructs the result from the cut[] array.

    Compared to the non-recursive implementation this one saves a lot of space as you do not have to store the substrings or the cut positions up to position i for all 0 <= i < s.length(). However it does perform repetitive work compared to the DP solution, e.g., if you have found two different ways of partitioning s[0..k], you still recursively search for partitioning of s[k+1,...]. So it's a typical trade-off between space and time.*/

        public List<List<String>> partition07(String s) {
            int len = s.length();
            boolean[][] isPal = new boolean[len][len];
            boolean[] cut = new boolean[len];

            init(isPal, s);
            List<List<String>> ans = new ArrayList<List<String>>();
            helper(s, 0, len-1, cut, ans, isPal);
            return ans;
        }

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

        private void helper(String s, int start, int end, boolean[] cut, List<List<String>> ans, boolean[][] isPal) {
            if (start > end) construct(s, cut, ans);
            for (int i=start; i<=end; i++) {
                if (isPal[start][i]) {
                    cut[i] = true;
                    helper(s, i+1, end, cut, ans, isPal);
                    cut[i] = false;
                }
            }
        }
    @Test
    public void test07(){
        System.out.println(partition07("aab"));
    }//[[a, a, b], [aa, b]]

//-------------------------------------------------------------------------/
    //8
    //Java Easy-follow Recursive Solution, can convert to DP easily, what is complexity? O(n^2)?

    public boolean isPalindrom(String s) {
        int start = 0;
        int end = s.length()-1;
        while(start<end) {
            if(s.charAt(start)!=s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

    public List<List<String>> partition08(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(s.length()==0) {
            res.add(new ArrayList<String>());
            return res;
        }
        if(s.length()==1) {
            List<String> subLs = new ArrayList<String>();
            subLs.add(s);
            res.add(subLs);
            return res;
        }
        for(int i=0; i<s.length(); i++) {
            String subS = s.substring(0,i+1);
            if(isPalindrom(subS)) {
                List<List<String>> subRes = partition08(s.substring(i+1));
                for(List<String> l : subRes) {
                    l.add(0,subS);
                    res.add(l);
                }
            }
        }
        return res;
    }
    @Test
    public void test08(){
        System.out.println(partition08("aab"));
    }//[[a, a, b], [aa, b]]


//-------------------------------------------------------------------------/
    //9
    // jiuzhang
    // version 1: shorter but slower
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition_J1(String s) {
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
    public void test09(){
        System.out.println(partition_J1("aab"));
    }//[[a, a, b], [aa, b]]

//-------------------------------------------------------------------------/
    //10
    // version 2: longer but faster
    List<List<String>> results;
    boolean[][] isPalindrome;

    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition_J2(String s) {
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

    @Test
    public void test10(){
        System.out.println(partition_J2("aab"));
    }//[[a, a, b], [aa, b]]

//-------------------------------------------------------------------------/
}

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

