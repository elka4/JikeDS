package _01_Array.DFS;

import java.util.HashMap;
import java.util.Map;
/*
LeetCode – Word Pattern (Java)

Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, such that there is a bijection between
a letter in pattern and a non-empty word in str.
 */

/*
Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.

Notes:
You may assume pattern contains only lowercase letters,
and str contains lowercase letters separated by a single space.
 */

//Hashtable

/*
就是建立 <Character, String> 的HashMap

这个是最标准的做法

这个题其实不应该放在DFS
 */

public class Word_Pattern {

    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.split(" ");

        //prevent out of boundary problem
        if(arr.length != pattern.length())
            return false;

        HashMap<Character, String> map = new HashMap<Character, String>();

        for(int i=0; i<pattern.length(); i++){
            char c = pattern.charAt(i);

            if(map.containsKey(c)){
                String value = map.get(c);
                if(!value.equals(arr[i])){
                    return false;
                }
            }else if (map.containsValue(arr[i])){
                return false;
            }

            map.put(c, arr[i]);
        }

        return true;
    }



//-------------------------------------------------------------------------------

    //8 lines simple Java

    /*
    这个方法其实很好，因为每次放进去的都是最新的char和最新的word比较index

    关键是要知道HashMap的put方法返回value

    如果words是单个的char，那么有风险。
     */
    public boolean wordPattern2(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();

        for (Integer i=0; i<words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;

        return true;
    }
/*    I go through the pattern letters and words in parallel and compare the indexes where they last appeared.

    Edit 1: Originally I compared the first indexes where they appeared, using putIfAbsent instead of put. That was based on mathsam's solution for the old Isomorphic Strings problem. But then czonzhu's answer below made me realize that put works as well and why.

            Edit 2: Switched from

    for (int i=0; i<words.length; ++i)
            if (!Objects.equals(index.put(pattern.charAt(i), i),
            index.put(words[i], i)))
            return false;
    to the current version with i being an Integer object, which allows to compare with just != because there's no autoboxing-same-value-to-different-objects-problem anymore. Thanks to lap_218 for somewhat pointing that out in the comments.*/



//-------------------------------------------------------------------------------

    //Very fast (3ms) Java Solution using HashMap
    public class Solution3 {
        public boolean wordPattern(String pattern, String str) {
            String[] arr= str.split(" ");
            HashMap<Character, String> map = new HashMap<Character, String>();
            if(arr.length!= pattern.length())
                return false;
            for(int i=0; i<arr.length; i++){
                char c = pattern.charAt(i);
                if(map.containsKey(c)){
                    if(!map.get(c).equals(arr[i]))
                        return false;
                }else{
                    if(map.containsValue(arr[i]))
                        return false;
                    map.put(c, arr[i]);
                }
            }
            return true;
        }
    }




//-------------------------------------------------------------------------------

    //My 3ms java solution using only one hashmap

    public boolean wordPattern4(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        char[] patterns = pattern.toCharArray();
        String[] strs = str.split(" ");
        if (patterns.length != strs.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<Character, String>();
        for (int i=0; i<patterns.length; i++) {
            if (map.containsKey(patterns[i])) {
                if (!map.get(patterns[i]).equals(strs[i])) {
                    return false;
                }
            } else if (map.containsValue(strs[i])) {
                return false;
            }
            map.put(patterns[i], strs[i]);
        }
        return true;
    }



//-------------------------------------------------------------------------------





//-------------------------------------------------------------------------------





//-------------------------------------------------------------------------------





//-------------------------------------------------------------------------------






}
