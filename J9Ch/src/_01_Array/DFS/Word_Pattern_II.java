package _01_Array.DFS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
LeetCode – Word Pattern II (Java)

This is the extension problem of Word Pattern I.

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
 */


public class Word_Pattern_II {
    public boolean wordPatternMatch(String pattern, String str) {
        if(pattern.length()==0 && str.length()==0)
            return true;
        if(pattern.length()==0)
            return false;

        HashMap<Character, String> map = new HashMap<Character, String>();

        return helper(pattern, str, 0, 0, map);
    }

    public boolean helper(String pattern, String str, int i, int j, HashMap<Character, String> map){
        if(i==pattern.length() && j==str.length()){
            return true;
        }

        if(i>=pattern.length() || j>=str.length())
            return false;

        char c = pattern.charAt(i);

        for(int k=j+1; k<=str.length(); k++){

            String sub = str.substring(j, k);

            if(!map.containsKey(c) && !map.containsValue(sub)){
                map.put(c, sub);
                if(helper(pattern, str, i+1, k, map))
                    return true;
                map.remove(c);

            }else if(map.containsKey(c) && map.get(c).equals(sub)){
                if(helper(pattern, str, i+1, k, map))
                    return true;
            }
        }

        return false;
    }

//---------------------------------/////////

/*    Since containsValue() method is used here, the time complexity is O(n). We can use another set to track the value set which leads to time complexity of O(1):*/

    public boolean wordPatternMatch2(String pattern, String str) {
        if(pattern.length()==0 && str.length()==0)
            return true;
        if(pattern.length()==0)
            return false;

        HashMap<Character, String> map = new HashMap<Character, String>();
        HashSet<String> set = new HashSet<String>();
        return helper(pattern, str, 0, 0, map, set);
    }

    public boolean helper(String pattern, String str, int i, int j, HashMap<Character, String> map, HashSet<String> set){

        if(i==pattern.length() && j==str.length()){
            return true;
        }

        if(i>=pattern.length() || j>=str.length())
            return false;

        char c = pattern.charAt(i);

        for(int k=j+1; k<=str.length(); k++){

            String sub = str.substring(j, k);

            if(!map.containsKey(c) && !set.contains(sub)){
                map.put(c, sub);
                set.add(sub);
                if(helper(pattern, str, i+1, k, map, set))
                    return true;
                map.remove(c);
                set.remove(sub);
            }else if(map.containsKey(c) && map.get(c).equals(sub)){
                if(helper(pattern, str, i+1, k, map, set))
                    return true;
            }
        }

        return false;
    }



//-------------------------------------------------------------------------//////

    //Share my Java backtracking solution

    public class Solution3 {

        public boolean wordPatternMatch(String pattern, String str) {
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

    }


//-------------------------------------------------------------------------//////

    //20 lines JAVA clean solution, easy to understand
    public class Solution4 {

        Map<Character, String> map = new HashMap();
        Set<String> set = new HashSet();

        public boolean wordPatternMatch(String pattern, String str) {

            if (pattern.isEmpty()) return str.isEmpty();

            if (map.containsKey(pattern.charAt(0))) {
                String value = map.get(pattern.charAt(0));

                if (str.length() < value.length() ||
                        !str.substring(0, value.length()).equals(value)){
                    return false;
                }

                if (wordPatternMatch(pattern.substring(1), str.substring(value.length()))){
                    return true;
                }

            } else {


                for (int i = 1; i <= str.length(); i++) {
                    if (set.contains(str.substring(0, i))) continue;
                    map.put(pattern.charAt(0), str.substring(0, i));
                    set.add(str.substring(0, i));
                    if (wordPatternMatch(pattern.substring(1), str.substring(i))) return true;
                    set.remove(str.substring(0, i));
                    map.remove(pattern.charAt(0));
                }

            }
            return false;
        }
    }


//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////








}
