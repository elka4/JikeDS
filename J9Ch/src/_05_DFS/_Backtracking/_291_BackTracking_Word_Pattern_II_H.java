package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _291_BackTracking_Word_Pattern_II_H {
    public class Solution {

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



    public class Solution2 {
        Map<Character,String> map =new HashMap();
        Set<String> set =new HashSet();
        public boolean wordPatternMatch(String pattern, String str) {
            if(pattern.isEmpty()) return str.isEmpty();
            if(map.containsKey(pattern.charAt(0))){
                String value= map.get(pattern.charAt(0));
                if(str.length()<value.length() || !str.substring(0,value.length()).equals(value)) return false;
                if(wordPatternMatch(pattern.substring(1),str.substring(value.length()))) return true;
            }else{
                for(int i=1;i<=str.length();i++){
                    if(set.contains(str.substring(0,i))) continue;
                    map.put(pattern.charAt(0),str.substring(0,i));
                    set.add(str.substring(0,i));
                    if(wordPatternMatch(pattern.substring(1),str.substring(i))) return true;
                    set.remove(str.substring(0,i));
                    map.remove(pattern.charAt(0));
                }
            }
            return false;
        }
    }
}
