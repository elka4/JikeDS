package HF.HF0;

import java.util.*;

public class _4L_StringsHomomorphism {
    //My 6 lines solution
    public boolean isIsomorphic(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
            m[s1.charAt(i)] = m[s2.charAt(i)+256] = i+1;
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
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

    public boolean isIsomorphic3(String s1, String s2) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();

        for(Integer i = 0; i < s1.length(); i++) {

            if(m1.put(s1.charAt(i), i) != m2.put(s2.charAt(i), i)) {
                return false;
            }
        }
        return true;
    }


    public boolean isIsomorphic4(String sString, String tString) {

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


    //http://www.cnblogs.com/lz87/p/6943163.html
    public class Solution {
        public boolean isIsomorphic(String s, String t) {
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


    public class Solution2 {
        public boolean isIsomorphic(String s, String t) {
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