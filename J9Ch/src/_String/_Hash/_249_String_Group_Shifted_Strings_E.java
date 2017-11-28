package _String._Hash;
import java.util.*;
import java.util.stream.*;
import org.junit.Test;

//  249. Group Shifted Strings
//  https://leetcode.com/problems/group-shifted-strings/description/
//  Hash Table String
//  _049_String_Group_Anagrams_M - hash
//  7:6
//
public class _249_String_Group_Shifted_Strings_E {
//------------------------------------------------------------------------------
    //6
    //JAVA beat 96% :)
    //和_049_String_Group_Anagrams_M真的很像啊，用每个string的第一个字母找到gap
    //然后用gap对这个string的每一个字母进行位移，用新的string作为key，原string作为value放进hash
    //最后hash的value作为result
    public class Solution6 {
        public List<List<String>> groupStrings(String[] strings) {
            if(strings.length < 1 || strings == null) return new ArrayList<List<String>>();
            Map<String,List<String>> map = new HashMap<>();

            for(String s : strings){
                char[] chars = s.toCharArray();
                char start = chars[0];
                // - gap to every char in chars and make sure chars[0] is 'a'
                int gap = start - 'a';
                for(int i = 0; i < chars.length; i++){
                    chars[i] -= gap;
                    if(chars[i] < 'a'){
                        chars[i] += 26;
                    }
                }
                String key = String.valueOf(chars);
                if(!map.containsKey(key)) map.put(key, new ArrayList<String>());
                map.get(key).add(s);
            }
            return new ArrayList<List<String>>(map.values());
        }
    }
//------------------------------------------------------------------------------
    //1
    //My Concise JAVA Solution
    public class Solution1 {
        public List<List<String>> groupStrings(String[] strings) {
            List<List<String>> result = new ArrayList<List<String>>();
            Map<String, List<String>> map = new HashMap<String, List<String>>();
            for (String str : strings) {
                int offset = str.charAt(0) - 'a';
                String key = "";
                for (int i = 0; i < str.length(); i++) {
                    char c = (char) (str.charAt(i) - offset);
                    if (c < 'a') {
                        c += 26;
                    }
                    key += c;
                }
                if (!map.containsKey(key)) {
                    List<String> list = new ArrayList<String>();
                    map.put(key, list);
                }
                map.get(key).add(str);
            }
            for (String key : map.keySet()) {
                List<String> list = map.get(key);
                Collections.sort(list);
                result.add(list);
            }
            return result;
        }
    }
//------------------------------------------------------------------------------
    //2
/*    Concise 10-lines JAVA Solution with explanation
            Explanation

    The basic idea is to set a key for each group: the sum of the difference between the adjacent chars in one string. Then we can easily group the strings belonging to the same shifting sequence with the same key. The code is as the following:*/
    class Solution2{
        public List<List<String>> groupStrings(String[] strs) {
            HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
            Arrays.sort(strs);
            for (String s : strs) {
                String key = "";
                for (int i = 1; i < s.length(); i++)
                    key += String.format("%2d", (s.charAt(i) - s.charAt(i-1) + 26) % 26);//Difference from the previous char.
                if (!map.containsKey(key)) map.put(key, new ArrayList<String>());
                map.get(key).add(s);
            }
            return new ArrayList<List<String>>(map.values());
        }
    }


//------------------------------------------------------------------------------
    //3
/*1-4 lines in Java
    Not sure I did it as good as it can be, as I'm still a beginner at Java streaming. If you can improve this, I'll be happy to see how.*/
    class Solution3{
        public List<List<String>> groupStrings(String[] strings) {
            return new ArrayList(Stream.of(strings).collect(Collectors.groupingBy(
                    s -> s.chars().mapToObj(c -> (c - s.charAt(0) + 26) % 26)
                            .collect(Collectors.toList())
            )).values());
        }
    }
//------------------------------------------------------------------------------
    //4
    //Simple solution in JAVA with detailed explaination
    /*
    HashMap<List<Integer>, List<String>> map:
for "abc","bcd","xyz", the key would be
[3, 1, 1]
[how many numbers, 2nd - 1st, 3rd - 2nd]

for "az","ba", the key would be
[2, 25]
[how many numbers, 2nd - 1st]
(NOTICE: for "ba": a - b, since a < b, the result would be 26 + 'a' - 'b')

thus, we have one unique key as List<Integer> for each Group, the List<String> for each key would be each group's result

finally, iterate through the res and sort each List<String>
     */

    public class Solution4 {
        public List<List<String>> groupStrings(String[] strings) {
            List<List<String>> res = new ArrayList<List<String>>();
            HashMap<List<Integer>, List<String>> map = new HashMap<List<Integer>, List<String>>();
            for(int i=0; i<strings.length; i++) {
                List<Integer> curKey = new ArrayList<Integer>();
                String str = strings[i];
                int length = str.length();
                curKey.add(length);
                for(int j=1; j<length; j++) {
                    int offset = str.charAt(j) - str.charAt(j-1);
                    int val = offset > 0 ? offset : 26 + offset;
                    curKey.add(val);
                }
                if (map.containsKey(curKey)) {
                    List<String> tmp = map.get(curKey);
                    tmp.add(str);
                } else {
                    List<String> tmp = new ArrayList<String>();
                    tmp.add(str);
                    res.add(tmp);
                    map.put(curKey, tmp);
                }
            }
            for(int i=0; i<res.size(); i++) {
                List<String> tmp = res.get(i);
                Collections.sort(tmp);
            }
            return res;
        }
    }

//------------------------------------------------------------------------------
    //5
    //Java Solution with separate shiftStr() function
    public class Solution5 {
        public List<List<String>> groupStrings(String[] strings) {
            if (strings == null)
                throw new IllegalArgumentException("strings is null");
            List<List<String>> ret = new ArrayList<List<String>> ();
            if (strings.length == 0)
                return ret;
            HashMap<String, List<String>> map = new HashMap<String, List<String>> ();
            for (String str : strings) {
                String shifted_str = shiftStr(str);
                if (map.containsKey(shifted_str)) {
                    map.get(shifted_str).add(str);
                } else{
                    List<String> item = new ArrayList<String> ();
                    item.add(str);
                    map.put(shifted_str, item);
                    ret.add(item);
                }
            }
            for (List<String> list : ret)
                Collections.sort(list);
            return ret;
        }


        private String shiftStr(String str) {
            StringBuffer buffer = new StringBuffer();
            char[] char_array = str.toCharArray();
            int dist = str.charAt(0) - 'a';
            for (char c : char_array)
                buffer.append((c - 'a' - dist + 26) % 26 + 'a');
            return buffer.toString();
        }
    }




//------------------------------------------------------------------------------
    //7
    //Beats 99% 4ms JAVA solution
    public class Solution7 {
        public List<List<String>> groupStrings(String[] strings) {
            List<List<String>> ans = new ArrayList<>();

            //corner cases
            if (strings.length == 0) {
                return ans;
            }

            HashMap<String, List<String>> map = new HashMap<>();
            for (String str : strings) {
                String key = getKey(str);
                if (map.containsKey(key)) {
                    map.get(key).add(str);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(str);
                    map.put(key, list);
                }
            }
            for (List<String> list : map.values()) {
                Collections.sort(list);
                ans.add(list);
            }
            return ans;
        }
        private String getKey(String str) {
            if (str.length() == 0) {
                return "";
            }
            if (str.length() == 1) {
                return "z";
            }
            char[] chars = str.toCharArray();
            int offset = 'z' - chars[0];
            for(int i = 0; i < chars.length; i++) {
                chars[i] += offset;
                if (chars[i] > 'z') {
                    chars[i] -= 26;
                }
            }
            return new String(chars);
        }
    }


//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
}
/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

//------------------------------------------------------------------------------
For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
A solution is:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
//------------------------------------------------------------------------------
Companies
Google Uber

Related Topics
Hash Table String

Similar Questions
Group Anagrams
//------------------------------------------------------------------------------
 */