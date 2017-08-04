package _1_Array.HashMap_Tracking;
import java.util.*;

/*
LeetCode – Substring with Concatenation of All Words (Java)

You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given: s="barfoothefoobarman" & words=["foo", "bar"], return [0,9].

Analysis

This problem is similar (almost the same) to Longest Substring Which Contains 2 Unique Characters.

Since each word in the dictionary has the same length, each of them can be treated as a single character.
 */


public class Substring_with_Concatenation_of_All_Words {

    public List<Integer> findSubstring(String s, String[] words) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(s==null||s.length()==0||words==null||words.length==0){
            return result;
        }

        //frequency of words
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String w: words){
            if(map.containsKey(w)){
                map.put(w, map.get(w)+1);
            }else{
                map.put(w, 1);
            }
        }

        int len = words[0].length();

        for(int j=0; j<len; j++){
            HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
            int start = j;//start index of start
            int count = 0;//count totoal qualified words so far

            for(int i=j; i<=s.length()-len; i=i+len){
                String sub = s.substring(i, i+len);
                if(map.containsKey(sub)){
                    //set frequency in current map
                    if(currentMap.containsKey(sub)){
                        currentMap.put(sub, currentMap.get(sub)+1);
                    }else{
                        currentMap.put(sub, 1);
                    }

                    count++;

                    while(currentMap.get(sub)>map.get(sub)){
                        String left = s.substring(start, start+len);
                        currentMap.put(left, currentMap.get(left)-1);

                        count--;
                        start = start + len;
                    }


                    if(count==words.length){
                        result.add(start); //add to result

                        //shift right and reset currentMap, count & start point
                        String left = s.substring(start, start+len);
                        currentMap.put(left, currentMap.get(left)-1);
                        count--;
                        start = start + len;
                    }
                }else{
                    currentMap.clear();
                    start = i+len;
                    count = 0;
                }
            }
        }

        return result;
    }


///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////




}
