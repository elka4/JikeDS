package _05_DFS._Back_Word;
import org.junit.Test;

import java.util.*;

//  291. Word Pattern II
//  https://leetcode.com/problems/word-pattern-ii/description/
//  Backtracking
public class _291_BackTracking_Word_Pattern_II_H {
    //Share my Java backtracking solution
    public boolean wordPatternMatch1(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        // base case
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;

        // get current pattern character
        char c = pat.charAt(j);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String s = map.get(c);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(s, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }

        // pattern character does not exist in the map
        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);

            if (set.contains(p)) {
                continue;
            }

            // create or update it
            map.put(c, p);
            set.add(p);

            // continue to match the rest
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }

            // backtracking
            map.remove(c);
            set.remove(p);
        }

        // we've tried our best but still no luck
        return false;
    }

//    pattern = "abab", str = "redblueredblue" should return true.
//    pattern = "aaaa", str = "asdasdasdasd" should return true.
//    pattern = "aabb", str = "xyzabcxzyabc" should return false.
    @Test
    public void test01(){
        System.out.println(wordPatternMatch1("abab", "redblueredblue"));
        System.out.println(wordPatternMatch1("aaaa", "asdasdasdasd"));
        System.out.println(wordPatternMatch1("aabb", "xyzabcxzyabc"));
    }


//------------------------------------------------------------------------------/////
    //20 lines JAVA clean solution, easy to understand
    Map<Character,String> map =new HashMap();
    Set<String> set =new HashSet();
    public boolean wordPatternMatch2(String pattern, String str) {
        if(pattern.isEmpty()) return str.isEmpty();
        if(map.containsKey(pattern.charAt(0))){
            String value= map.get(pattern.charAt(0));
            if(str.length()<value.length() || !str.substring(0,value.length()).equals(value)) return false;
            if(wordPatternMatch2(pattern.substring(1),str.substring(value.length()))) return true;
        }else{
            for(int i=1;i<=str.length();i++){
                if(set.contains(str.substring(0,i))) continue;
                map.put(pattern.charAt(0),str.substring(0,i));
                set.add(str.substring(0,i));
                if(wordPatternMatch2(pattern.substring(1),str.substring(i))) return true;
                set.remove(str.substring(0,i));
                map.remove(pattern.charAt(0));
            }
        }
        return false;
    }
    @Test
    public void test02(){
        System.out.println(wordPatternMatch2("abab", "redblueredblue"));
        System.out.println(wordPatternMatch2("aaaa", "asdasdasdasd"));
        System.out.println(wordPatternMatch2("aabb", "xyzabcxzyabc"));
    }

//------------------------------------------------------------------------------/////
    //*Java* HashSet + backtracking (2ms beats 100%)
    public boolean wordPatternMatch3(String pattern, String str) {
        String[] map = new String[26]; // mapping of characters 'a' - 'z'
        HashSet<String> set = new HashSet<>(); // mapped result of 'a' - 'z'
        return wordPatternMatch3(pattern, str, map, set, 0,
                str.length()-1, 0, pattern.length()-1);
    }
    private boolean wordPatternMatch3(String pattern, String str, String[] map,
                                      HashSet<String> set, int start, int end, int startP, int endP) {
        // both pattern and str are exhausted
        if(startP==endP+1 && start==end+1) return true;
        // either of pattern or str is exhausted
        if((startP>endP && start<=end) || (startP<endP && start>end)) return false;

        char ch = pattern.charAt(startP);
        String matched = map[ch-'a'];
        // ch is already mapped, then continue
        if(matched!=null) {
            int count = matched.length();
            // substring equals previously mapped string
            return start+count<=end+1 && matched.equals(str.substring(start, start+count))
                    && wordPatternMatch3(pattern, str, map, set,
                    start+matched.length(), end, startP+1, endP); // moving forward
        }
        else {
            int endPoint = end;
            for(int i=endP; i>startP; i--) {
                endPoint -= map[pattern.charAt(i)-'a']==null ? 1 : map[pattern.charAt(i)-'a'].length();
            }
            for(int i=start; i<=endPoint; i++) { // try every possible mapping, from 1 character to the end
                matched = str.substring(start, i+1);
                if(set.contains(matched)) continue; // different pattern cannot map to same substring

                map[ch-'a'] = matched; // move forward, add corresponding mapping and set content
                set.add(matched);

                if(wordPatternMatch3(pattern, str, map, set, i+1, end, startP+1, endP))
                    return true;

                else { // backtracking, remove corresponding mapping and set content
                    map[ch-'a'] = null;
                    set.remove(matched);
                }
            }
        }
        return false; // exhausted
    }

    @Test
    public void test03(){
        System.out.println(wordPatternMatch3("abab", "redblueredblue"));
        System.out.println(wordPatternMatch3("aaaa", "asdasdasdasd"));
        System.out.println(wordPatternMatch3("aabb", "xyzabcxzyabc"));
    }


//------------------------------------------------------------------------------/////
}
/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.


 */