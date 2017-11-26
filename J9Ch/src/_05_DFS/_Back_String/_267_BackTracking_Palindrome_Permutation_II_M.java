package _05_DFS._Back_String;
import org.junit.Test;

import java.util.*;

//  267. Palindrome Permutation II
//  https://leetcode.com/problems/palindrome-permutation-ii/description/
//  http://www.lintcode.com/zh-cn/problem/palindrome-partitioning-ii/
//  Backtracking
//  5: 3
public class _267_BackTracking_Palindrome_Permutation_II_M {
    //1
    //  https://leetcode.com/problems/palindrome-permutation-ii/solution/
    //Approach #1 Brute Force [Time Limit Exceeded]
    Set < String > set1 = new HashSet < > ();
    public List < String > generatePalindromes1(String s) {
        permute(s.toCharArray(), 0);
        return new ArrayList < String > (set1);
    }
    public boolean isPalindrome(char[] s) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] != s[s.length - 1 - i])
                return false;
        }
        return true;
    }
    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    void permute(char[] s, int l) {
        if (l == s.length) {
            if (isPalindrome(s))
                set1.add(new String(s));
        } else {
            for (int i = l; i < s.length; i++) {
                swap(s, l, i);
                permute(s, l + 1);
                swap(s, l, i);
            }
        }
    }
    @Test
    public void test01(){
        System.out.println(generatePalindromes1("abc"));//  []
        System.out.println(generatePalindromes1("aabb"));// [abba, baab]
    }

//------------------------------------------------------------------------------
    //2
    //Approach #2 Backtracking [Accepted]
    Set < String > set2 = new HashSet < > ();
    public List < String > generatePalindromes2(String s) {
        int[] map = new int[128];
        char[] st = new char[s.length() / 2];
        if (!canPermutePalindrome(s, map))
            return new ArrayList < > ();
        char ch = 0;
        int k = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 == 1)
                ch = (char) i;
            for (int j = 0; j < map[i] / 2; j++) {
                st[k++] = (char) i;
            }
        }
        permute(st, 0, ch);
        return new ArrayList < String > (set2);
    }

    public boolean canPermutePalindrome(String s, int[] map) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
    }

    void permute(char[] s, int l, char ch) {
        if (l == s.length) {
            set2.add(new String(s) + (ch == 0 ? "" : ch) + new StringBuffer(new String(s)).reverse());
        } else {
            for (int i = l; i < s.length; i++) {
                if (s[l] != s[i] || l == i) {
                    swap(s, l, i);
                    permute(s, l + 1, ch);
                    swap(s, l, i);
                }
            }
        }
    }
    @Test
    public void test02(){
        System.out.println(generatePalindromes2("abc"));//  []
        System.out.println(generatePalindromes2("aabb"));// [abba, baab]
    }

//--------------------------------------------------------------------------------
    //3

    public List<String> generatePalindromes3(String s) {
        int odd = 0;
        String mid = "";
        List<String> res = new ArrayList<>();
        List<Character> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();

        // step 1. build character count map and count odds
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
            odd += map.get(c) % 2 != 0 ? 1 : -1;
        }

        // cannot form any palindromic string
        if (odd > 1) return res;

        // step 2. add half count of each character to list
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();

            if (val % 2 != 0) mid += key;

            for (int i = 0; i < val / 2; i++) list.add(key);
        }

        // step 3. generate all the permutations
        getPerm(list, mid, new boolean[list.size()], new StringBuilder(), res);

        return res;
    }

    // generate all unique permutation from list
    void getPerm(List<Character> list, String mid, boolean[] used,
                 StringBuilder sb, List<String> res) {
        if (sb.length() == list.size()) {
            // form the palindromic string
            res.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            // avoid duplication
            if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) continue;

            if (!used[i]) {
                used[i] = true; sb.append(list.get(i));
                // recursion
                getPerm(list, mid, used, sb, res);
                // backtracking
                used[i] = false; sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    @Test
    public void test03(){
        System.out.println(generatePalindromes3("abc"));//  []
        System.out.println(generatePalindromes3("aabb"));// [abba, baab]
    }

//------------------------------------------------------------------------------
    //4
    public List<String> generatePalindromes4(String s) {
        int[] map = new int[256];
        for(int i=0;i<s.length();i++){
            map[s.charAt(i)]++;
        }
        int j=0,count=0;
        for(int i=0;i<256;i++){
            if(count== 0 && map[i] %2 == 1){
                j= i;
                count++;
            }else if(map[i] % 2==1){
                return new ArrayList<String>();
            }
        }
        String cur = "";
        if(j != 0){
            cur = ""+ (char)j;
            map[j]--;
        }
        List<String> res = new ArrayList<String>();
        DFS(res,cur,map,s.length());
        return res;
    }
    public void DFS(List<String> res,String cur,int[] map,int len){
        if(cur.length()== len){
            res.add(new String(cur));
        }else {
            for(int i=0;i<map.length;i++){
                if(map[i] <= 0) continue;
                map[i] = map[i] - 2;
                cur = (char)i + cur + (char)i;
                DFS(res,cur,map,len);
                cur = cur.substring(1,cur.length()-1);
                map[i] = map[i]+2;
            }
        }
    }

    @Test
    public void test04(){
        System.out.println(generatePalindromes4("abc"));//  []
        System.out.println(generatePalindromes4("aabb"));// [abba, baab]
    }


//------------------------------------------------------------------------------
    //5
    private List<String> list = new ArrayList<>();

    public List<String> generatePalindromes5(String s) {
        int numOdds = 0; // How many characters that have odd number of count
        int[] map = new int[256]; // Map from character to its frequency
        for (char c: s.toCharArray()) {
            map[c]++;
            numOdds = (map[c] & 1) == 1 ? numOdds+1 : numOdds-1;
        }
        if (numOdds > 1)   return list;

        String mid = "";
        int length = 0;
        for (int i = 0; i < 256; i++) {
            if (map[i] > 0) {
                if ((map[i] & 1) == 1) { // Char with odd count will be in the middle
                    mid = "" + (char)i;
                    map[i]--;
                }
                map[i] /= 2; // Cut in half since we only generate half string
                length += map[i]; // The length of half string
            }
        }
        generatePalindromesHelper(map, length, "", mid);
        return list;
    }
    private void generatePalindromesHelper(int[] map, int length, String s, String mid) {
        if (s.length() == length) {
            StringBuilder reverse = new StringBuilder(s).reverse(); // Second half
            list.add(s + mid + reverse);
            return;
        }
        for (int i = 0; i < 256; i++) { // backtracking just like permutation
            if (map[i] > 0) {
                map[i]--;
                generatePalindromesHelper(map, length, s + (char)i, mid);
                map[i]++;
            }
        }
    }
    @Test
    public void test05(){
        System.out.println(generatePalindromes5("abc"));//  []
        System.out.println(generatePalindromes5("aabb"));// [abba, baab]
    }


//-------------------------------------------------------------------------
//  lintcode的分割回文串II是DP，求个数或者切割次数
//-------------------------------------------------------------------------
}

/*
分割回文串 II

给定一个字符串s，将s分割成一些子串，使每个子串都是回文。

返回s符合要求的的最少分割次数。

样例
比如，给出字符串s = "aab"，

返回 1， 因为进行一次分割可以将字符串s分割成["aa","b"]这样两个回文子串

标签
动态规划
相关题目
中等 摆动排序 II 25 %
中等 分割回文串 28 %
中等 最长回文子串
 */



/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].


 */


