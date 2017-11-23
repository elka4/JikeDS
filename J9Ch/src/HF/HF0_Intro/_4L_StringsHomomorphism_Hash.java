package HF.HF0_Intro;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//  http://www.lintcode.com/en/problem/strings-homomorphism/
//  10
public class _4L_StringsHomomorphism_Hash {
//-----------------------------------------------------------
    //1
    public boolean isIsomorphic0(String s, String t) {
        // write your code here
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        if(s.length() != t.length()){
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            Integer sChar = (int)s.charAt(i);
            Integer tChar = (int)t.charAt(i);

            if(map.containsKey(sChar)){
                if (!tChar.equals(map.get(sChar))){
                    return false;
                }
            } else {
                map.put(sChar, tChar);
            }

        }


        HashMap<Integer, Integer> map2 = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            Integer sChar = (int)s.charAt(i);
            Integer tChar = (int)t.charAt(i);

            if(map2.containsKey(tChar)){
                if (!sChar.equals(map2.get(tChar))){
                    return false;
                }
            } else {
                map2.put(tChar, sChar);
            }

        }
        return true;
    }

    @Test
    public void test0(){
        String s = "\"a`%ii,VEZQc_BSU%ObO5<sX81B/bOw+CNUd#Uav*P!Ax!#>hh,k#b/|>4ixFQZl+l!?bJjakbQbGglEb<g>Hf81m@A9GIvbd]qh?y__t+E(Iyv7zUEfZF{81VaM-0u?]tG=_fFR/XJ=X{-,oRpxE9u*VNYlM\"";
        String t = "™\"b`%ii-WE[Qc_BSV%OcO5<sX82B/cOw+CNVd#Vbv*P!Bx!#?hh-k#c/|?4ixFQ[l+l!?cJkbkcQcGhlEc<h?Hf82m@B9GIvcd]rh?y__t+E(Iyv7{VEf[F{82WbN/0u?]tG=_fFR/XJ=X{/-oRpxE9u*WNYlN\"";

        System.out.println(isIsomorphic0(s,t));
    }

//-----------------------------------------------------------
    //2
    public boolean isIsomorphicMy(String s, String t) {
        // write your code here
        int[] m1 = new int[256];
        int[] m2 = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if(m1[sChar] == 0 && m2[tChar] == 0) {
                m1[sChar] = tChar;
                m2[tChar] = sChar;
            } else if (m1[sChar] != tChar){
                return false;
            }
        }
        return true;
    }

//-----------------------------------------------------------
    //3
    /**
     * @param s a string
     * @param t a string
     * @return true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        // Write your code here
        int[] m1 = new int[128];
        int[] m2 = new int[128];
        for (int i = 0; i < s.length(); ++i) {
            int cs = (int) s.charAt(i);
            int ts = (int) t.charAt(i);
            if (m1[cs] == 0 && m2[ts] == 0) {
                m1[cs] = ts;
                m2[ts] = 1;
            } else if (m1[cs] != ts) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test1(){
        System.out.println(isIsomorphic("abb", "xyy"));
    }

    @Test
    public void test11(){
        String s = "\"a`%ii,VEZQc_BSU%ObO5<sX81B/bOw+CNUd#Uav*P!Ax!#>hh,k#b/|>4ixFQZl+l!?bJjakbQbGglEb<g>Hf81m@A9GIvbd]qh?y__t+E(Iyv7zUEfZF{81VaM-0u?]tG=_fFR/XJ=X{-,oRpxE9u*VNYlM\"";
        String t = "\"b`%ii-WE[Qc_BSV%OcO5<sX82B/cOw+CNVd#Vbv*P!Bx!#?hh-k#c/|?4ixFQ[l+l!?cJkbkcQcGhlEc<h?Hf82m@B9GIvcd]rh?y__t+E(Iyv7{VEf[F{82WbN/0u?]tG=_fFR/XJ=X{/-oRpxE9u*WNYlN\"";

        System.out.println(isIsomorphic(s,t));
    }

//-------------------------------------------------------------------------
    //4
    // version: 高频题班
    /**
     * @param s a string
     * @param t a string
     * @return true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic2(String s, String t) {
        // Write your code here
        int[] map = new int[256];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            if (map[sc[i]] == 0) {
                map[sc[i]] = tc[i];
            } else {
                if (map[sc[i]] != tc[i]) {
                    return false;
                }
            }
        }

        /*
        ----------///////////假设t的取值只有'a' - 'z' 时做t->s 映射的一种写法   （仅做演示使用）
        int[] map2 = new int[26];
        for (int i = 0; i < t.length(); i++) {
            if (map2[tc[i] - 'a'] == 0) {
                map2[tc[i] - 'a'] = sc[i];
            } else {
                if (map2[tc[i] - 'a'] != sc[i]) {
                    return false;
                }
            }
        }
        */

        int[] map2 = new int[256];
        for (int i = 0; i < t.length(); i++) {
            if (map2[tc[i]] == 0) {
                map2[tc[i]] = sc[i];
            } else {
                if (map2[tc[i]] != sc[i]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void test2(){
        System.out.println(isIsomorphic2("abb", "xyy"));
    }

//-------------------------------------------------------------------------
    //5

    //My 6 lines solution
    public boolean isIsomorphic3(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
            m[s1.charAt(i)] = m[s2.charAt(i)+256] = i+1;
        }
        return true;
    }

    @Test
    public void test3(){
        System.out.println(isIsomorphic3("abb", "xyy"));
    }

//-------------------------------------------------------------------------
    //6
    public boolean isIsomorphic4(String s, String t) {
        if(s == null || s.length() <= 1) return true;
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for(int i = 0 ; i< s.length(); i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(map.containsKey(a)){
                if(map.get(a).equals(b))
                    continue;
                else
                    return false;
            }else{
                if(!map.containsValue(b))
                    map.put(a,b);
                else return false;

            }
        }
        return true;

    }

//-------------------------------------------------------------------------
    //7
    public boolean isIsomorphic5(String s1, String s2) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();

        for(Integer i = 0; i < s1.length(); i++) {

            if(m1.put(s1.charAt(i), i) != m2.put(s2.charAt(i), i)) {
                return false;
            }
        }
        return true;
    }

//-------------------------------------------------------------------------
    //8

    public boolean isIsomorphic6(String sString, String tString) {

        char[] s = sString.toCharArray();
        char[] t = tString.toCharArray();

        int length = s.length;
        if(length != t.length) return false;

        char[] sm = new char[256];
        char[] tm = new char[256];

        for(int i=0; i<length; i++){
            char sc = s[i];
            char tc = t[i];
            if(sm[sc] == 0 && tm[tc] == 0){
                sm[sc] = tc;
                tm[tc] = sc;
            }else{
                if(sm[sc] != tc || tm[tc] != sc){
                    return false;
                }
            }
        }
        return true;
    }

//-------------------------------------------------------------------------
    //9

    //http://www.cnblogs.com/lz87/p/6943163.html
    public class Solution3 {
        public boolean isIsomorphic7(String s, String t) {
            if(s == null || t == null){
                return false;
            }
            if(s.length() != t.length()){
                return false;
            }
            int n = s.length();
            HashMap<Character, Integer> map1 = new HashMap<Character, Integer>();
            HashMap<Character, Integer> map2 = new HashMap<Character, Integer>();
            int[] index1 = new int[n];
            int[] index2 = new int[n];
            for(int i = 0; i < n; i++){
                if(!map1.containsKey(s.charAt(i))){
                    map1.put(s.charAt(i), i);
                }
                if(!map2.containsKey(t.charAt(i))){
                    map2.put(t.charAt(i), i);
                }
            }
            for(int i = 0; i < n; i++){
                index1[i] = map1.get(s.charAt(i));
                index2[i] = map2.get(t.charAt(i));
            }
            for(int i = 0; i < n; i++){
                if(index1[i] != index2[i]){
                    return false;
                }
            }
            return true;
        }
    }


//-------------------------------------------------------------------------
    //10
    public class Solution4 {
        public boolean isIsomorphic8(String s, String t) {
            int[] m1 = new int[128];
            int[] m2 = new int[128];
            for(int i = 0; i < 128; i++){
                m1[i] = Integer.MAX_VALUE;
                m2[i] = Integer.MAX_VALUE;
            }
            for (int i = 0; i < s.length(); ++i) {
                int cs = (int) s.charAt(i);
                int ts = (int) t.charAt(i);
                if(m1[cs] == Integer.MAX_VALUE){
                    //neither s.charAt(i) nor t.charAt(i) has a mapping
                    if(m2[ts] == Integer.MAX_VALUE){
                        m1[cs] = ts;
                        m2[ts] = Integer.MIN_VALUE;
                    }
                    //s.charAt(i) has no mapping but t.charAt(i) already
                    //has a mapping to some other character that is not
                    //s.charAt(i)
                    else{
                        return false;
                    }
                }
                //s.charAt(i) already has a mapping, then it must maps to
                //t.charAt(i)
                else if(m1[cs] != ts){
                    return false;
                }
            }
            return true;
        }
    }

//-------------------------------------------------------------------------

}

/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

 注意事项

You may assume both s and t have the same length.

样例
Given s = "egg", t = "add", return true.

Given s = "foo", t = "bar", return false.

Given s = "paper", t = "title", return true.
 */

/*
638. 字符同构

 描述
 笔记
 数据
 评测
给定两个字符串 s 和 t ，确定它们是否是同构的。
两个字符串是同构的如果 s 中的字符可以被替换得到 t。
所有出现的字符必须用另一个字符代替，同时保留字符串的顺序。 没有两个字符可以映射到同一个字符，但一个字符可以映射到自己。

 注意事项

你可以假定两个字符串 s 和 t 是一样长度的.

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 s = "egg", t= "add", 返回 true。
给出 s = "foo", t= "bar", 返回 false。
给出 s = "paper", t= "title", 返回 true。

标签
领英 哈希表
相关题目
中等 乱序字符串
 */