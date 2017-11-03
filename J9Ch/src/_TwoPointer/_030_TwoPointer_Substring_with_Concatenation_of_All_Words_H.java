package _TwoPointer;
import java.util.*;
import org.junit.Test;


//  30. Substring with Concatenation of All Words
//  https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
public class _030_TwoPointer_Substring_with_Concatenation_of_All_Words_H {
//    I think your code is O(kn) where k = |L[0]|. I made a real O(n) code using k maps and each time do maps.get(i%k) to get its corresponding map. But I use extra queue, while the queue could be simplified into one variable after seeing your code.

    public class Solution {
        public List<Integer> findSubstring(String S, String[] L) {
            List<Integer> rslt = new ArrayList<Integer>();

            // edge case
            if(L.length==0) return rslt;

            // initialize k queues and k maps
            int k = L[0].length();
            int queues[] = new int[k];
            List<Map<String, Integer>> maps = new ArrayList<Map<String, Integer>>();
            Map<String, Integer> map_temp = new HashMap<String, Integer>();
            for(int i = 0;i < L.length;i++) setMap(map_temp, L[i]);
            for(int i = 0;i < k;i++) queues[i] = 0;
            for(int i = 0;i < k;i++) maps.add(new HashMap<String, Integer>(map_temp));

            // go though S
            for(int i = 0;i <= S.length()-k;i++){
                Map<String, Integer> map = maps.get(i%k);
                String s = S.substring(i,i+k);
                if(map.containsKey(s)){
                    queues[i%k]++;
                    map.put(s,map.get(s)-1);
                    if(map.get(s)==0) map.remove(s);
                    // find a match
                    if(queues[i%k]==L.length){
                        rslt.add(i-(queues[i%k]-1)*k);
                        setMap(map, S.substring(i-k*(queues[i%k]-1),i-k*(queues[i%k]-1)+k));
                        queues[i%k]--;
                    }
                }else{
                    if(queues[i%k] > 0){
                        if(!s.equals(S.substring(i-k*queues[i%k],i-k*queues[i%k]+k)))
                            while(queues[i%k] > 0){
                                setMap(map, S.substring(i-k*queues[i%k],i-k*queues[i%k]+k));
                                queues[i%k]--;
                            }
                    }
                }
            }

            return rslt;
        }

        private void setMap(Map<String, Integer> map, String s){
            if(!map.containsKey(s))
                map.put(s,1);
            else
                map.put(s,map.get(s)+1);
            return;
        }
    }

    /*
    I think the complexity is O(KN), K = L[0].length(), N = S.length(). If you think I am wrong, please point it out, thanks!

This is my Java version of Sliding Window.
     */
    // time: O(KN)
    public class Solution2 {
        // Sliding Window    360ms
        // ask interviewer if words is empty, should I return empty list
        public List<Integer> findSubstring(String S, String[] L) {
            List<Integer> res = new LinkedList<>();
            if (L.length == 0 || S.length() < L.length * L[0].length())   return res;
            int N = S.length(), M = L.length, K = L[0].length();
            Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
            for (String s : L) {
                if (map.containsKey(s))   map.put(s, map.get(s) + 1);
                else                      map.put(s, 1);
            }
            String str = null, tmp = null;
            for (int i = 0; i < K; i++) {
                int count = 0;  // remark: reset count
                for (int l = i, r = i; r + K <= N; r += K) {
                    str = S.substring(r, r + K);
                    if (map.containsKey(str)) {
                        if (curMap.containsKey(str))   curMap.put(str, curMap.get(str) + 1);
                        else                           curMap.put(str, 1);

                        if (curMap.get(str) <= map.get(str))    count++;
                        while (curMap.get(str) > map.get(str)) {
                            tmp = S.substring(l, l + K);
                            curMap.put(tmp, curMap.get(tmp) - 1);
                            l += K;
                            if (curMap.get(tmp) < map.get(tmp)) count--;
                        }
                        if (count == M) {
                            res.add(l);
                            tmp = S.substring(l, l + K);
                            curMap.put(tmp, curMap.get(tmp) - 1);
                            l += K;
                            count--;
                        }
                    }else {
                        curMap.clear();
                        count = 0;
                        l = r + K;
                    }
                }
                curMap.clear();
            }
            return res;
        }
    }


    //Simple Java Solution with Two Pointers and Map
//    My idea is pretty simple. Just build a map for the words and their relative count in L.
// Then we traverse through S to check whether there is a match.

    public  List<Integer> findSubstring3(String S, String[] L) {
        List<Integer> res = new ArrayList<Integer>();
        if (S == null || L == null || L.length == 0) return res;
        int len = L[0].length(); // length of each word

        Map<String, Integer> map = new HashMap<String, Integer>(); // map for L
        for (String w : L) map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);

        for (int i = 0; i <= S.length() - len * L.length; i++) {
            Map<String, Integer> copy = new HashMap<String, Integer>(map);
            for (int j = 0; j < L.length; j++) { // checkc if match
                String str = S.substring(i + j*len, i + j*len + len); // next word
                if (copy.containsKey(str)) { // is in remaining words
                    int count = copy.get(str);
                    if (count == 1) copy.remove(str);
                    else copy.put(str, count - 1);
                    if (copy.isEmpty()) { // matches
                        res.add(i);
                        break;
                    }
                } else break; // not in L
            }
        }
        return res;
    }
//    At first I was gonna to use a set for words.
// Owing to the fact that duplicate is allowed in L, we need to use map instead.

///////////////////////////////////////////////////////////////////
    //jiuzhang
    public class Jiuzhang{
        public ArrayList<Integer> findSubstring(String S, String[] L) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            HashMap<String, Integer> toFind = new HashMap<String, Integer>();
            HashMap<String, Integer> found = new HashMap<String, Integer>();
            int m = L.length, n = L[0].length();
            for (int i = 0; i < m; i ++){
                if (!toFind.containsKey(L[i])){
                    toFind.put(L[i], 1);
                }
                else{
                    toFind.put(L[i], toFind.get(L[i]) + 1);
                }
            }
            for (int i = 0; i <= S.length() - n * m; i ++){
                found.clear();
                int j;
                for (j = 0; j < m; j ++){
                    int k = i + j * n;
                    String stub = S.substring(k, k + n);
                    if (!toFind.containsKey(stub)) break;
                    if(!found.containsKey(stub)){
                        found.put(stub, 1);
                    }
                    else{
                        found.put(stub, found.get(stub) + 1);
                    }
                    if (found.get(stub) > toFind.get(stub)) break;
                }
                if (j == m) result.add(i);
            }
            return result;
        }
    }


}
/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).


 */