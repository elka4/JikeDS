package _String._String;
import java.util.*;
import org.junit.Test;

//  161. One Edit Distance
//  https://leetcode.com/problems/one-edit-distance/description/
//
//  5:
//
public class _161_String_One_Edit_Distance_M {
//------------------------------------------------------------------------------
    //1
    //My CLEAR JAVA solution with explanation
    class Solution1{
        /*
         * There're 3 possibilities to satisfy one edit distance apart:
         *
         * 1) Replace 1 char:
               s: a B c
               t: a D c
         * 2) Delete 1 char from s:
              s: a D  b c
              t: a    b c
         * 3) Delete 1 char from t
              s: a   b c
              t: a D b c
         */
        public boolean isOneEditDistance(String s, String t) {
            for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
                        return s.substring(i + 1).equals(t.substring(i + 1));
                    else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
                        return s.substring(i).equals(t.substring(i + 1));
                    else // s is longer than t, so the only possibility is deleting one char from s
                        return t.substring(i).equals(s.substring(i + 1));
                }
            }
            //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
            return Math.abs(s.length() - t.length()) == 1;
        }
    }
//------------------------------------------------------------------------------
    //2
    //Easy understood Java solution
    class Solution2{
        public boolean isOneEditDistance(String s, String t) {
            if(Math.abs(s.length()-t.length()) > 1) return false;
            if(s.length() == t.length()) return isOneModify(s,t);
            if(s.length() > t.length()) return isOneDel(s,t);
            return isOneDel(t,s);
        }
        public boolean isOneDel(String s,String t){
            for(int i=0,j=0;i<s.length() && j<t.length();i++,j++){
                if(s.charAt(i) != t.charAt(j)){
                    return s.substring(i+1).equals(t.substring(j));
                }
            }
            return true;
        }
        public boolean isOneModify(String s,String t){
            int diff =0;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i) != t.charAt(i)) diff++;
            }
            return diff==1;
        }
    }

//------------------------------------------------------------------------------
    //3
    //in class
    public boolean isOneEditDistance(String s, String t){
        if (s.length() > t.length()) {
            return isOneEditDistance(t,s);
        }
        int diff = t.length() - s.length();

        if (diff == 0) {
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    cnt++;
                }
            }
            return (cnt == 1);
        }
        if (diff == 1) {
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    return (s.substring(i).equals(t.substring(i + 1)));
                }
            }
        }
        return true;
    }

    @Test
    public void test01(){
        String s = "aDb", t = "adb";
        System.out.println(isOneEditDistance(s,t));
    }

//--------------------------------------------------------------------------------
    //4
    // 9Ch
    /**
     * @param s a string
     * @param t a string
     * @return true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance2(String s, String t) {
        // Write your code here
        int m = s.length();
        int n = t.length();
        if (Math.abs(m - n) > 1)
            return false;

        if (m > n)
            return isOneEditDistance(t, s);
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (m == n) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
                return s.substring(i).equals(t.substring(i + 1));
            }
        }
        return m != n;
    }

    @Test
    public void test02(){
        String s = "aDb", t = "adb";
        System.out.println(isOneEditDistance2(s,t));
    }

//--------------------------------------------------------------------------------
    //5
    // version: 高频题班
    /**
     * @param s a string
     * @param t a string
     * @return true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance3(String s, String t) {
        // Write your code here
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }
        int diff = t.length() - s.length();

        if (diff > 1) {
            return false;
        }
        if (diff == 0) {
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    cnt++;
                }
            }
            return (cnt == 1);
        }
        if (diff == 1) {
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    return (s.substring(i).equals(t.substring(i + 1)));
                }
            }
        }
        return true;
    }

    @Test
    public void test03(){
        String s = "aDb", t = "adb";
        System.out.println(isOneEditDistance3(s,t));
    }

//------------------------------------------------------------------------------
}
/*
Given two strings S and T, determine if they are both one edit distance apart.

Seen this question in a real interview before?   Yes  No
Companies
Facebook Uber Twitter Snapchat
Related Topics
String
Similar Questions
Edit Distance
 */


/*
One Edit Distance

Example:
• s=“abcd” t=“abce”
• s=“abcd” t=“abcef”
output: true output: false
 • 两种one edit:
    1.增加/减少一位
    2.更改一位
 */

/*
思路:
• 想一想特殊情况 柿子要找软的捏
• 两字符串长度相差太大时?两个字符串长度一样?
• 三种情况:
1. 两个字符串长度差>1 直接false
2. 两个字符串长度差=0 只能有一位不同
3. 两个字符串长度差=1 只能插入/删除一个字符
 • 边界情况，特殊输入?
时间复杂度:O(n)
 */

/*
Company Tags: Facebook
考点:
– 很懵逼的情况下怎样突破 ->找特殊情况
– 分类讨论问题的情况
 */

/*
能力维度:
1. 理解问题
4. 逻辑思维/算法优化能力
5. 细节处理(corner case)
 */
