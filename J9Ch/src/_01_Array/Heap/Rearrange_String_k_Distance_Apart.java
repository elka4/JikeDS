package _01_Array.Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.*;
/*
LeetCode – Rearrange String k Distance Apart (Java)

Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example:

Example 1:
s = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
s = "aaabc", k = 3

Answer: ""

It is not possible to rearrange the string.
Example 3:
s = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.

 */


// Rearrange String k Distance Apart
public class Rearrange_String_k_Distance_Apart {
    public String rearrangeString(String str, int k) {
        if(k==0)
            return str;

        //initialize the counter for each character
        final HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c, 1);
            }
        }

        //sort the chars by frequency
        PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>(){
            public int compare(Character c1, Character c2){
                if(map.get(c2).intValue()!=map.get(c1).intValue()){
                    return map.get(c2)-map.get(c1);
                }else{
                    return c1.compareTo(c2);
                }
            }
        });


        for(char c: map.keySet())
            queue.offer(c);

        StringBuilder sb = new StringBuilder();

        int len = str.length();

        while(!queue.isEmpty()){

            int cnt = Math.min(k, len);
            ArrayList<Character> temp = new ArrayList<Character>();

            for(int i=0; i<cnt; i++){
                if(queue.isEmpty())
                    return "";

                char c = queue.poll();
                sb.append(String.valueOf(c));

                map.put(c, map.get(c)-1);

                if(map.get(c)>0){
                    temp.add(c);
                }

                len--;
            }

            for(char c: temp)
                queue.offer(c);
        }

        return sb.toString();
    }
//--------------------------------------------------------------------------------

    //leetcode
    //Java 15ms Solution with Two auxiliary array. O(N) time.
    public String rearrangeString2(String str, int k) {
        int length = str.length();
        int[] count = new int[26];
        int[] valid = new int[26];
        for(int i=0;i<length;i++){
            count[str.charAt(i)-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int index = 0;index<length;index++){
            int candidatePos = findValidMax(count, valid, index);
            if( candidatePos == -1) return "";
            count[candidatePos]--;
            valid[candidatePos] = index+k;
            sb.append((char)('a'+candidatePos));
        }
        return sb.toString();
    }

    private int findValidMax(int[] count, int[] valid, int index){
        int max = Integer.MIN_VALUE;
        int candidatePos = -1;
        for(int i=0;i<count.length;i++){
            if(count[i]>0 && count[i]>max && index>=valid[i]){
                max = count[i];
                candidatePos = i;
            }
        }
        return candidatePos;
    }
//--------------------------------------------------------------------------------

    //leetcode
    //Java 7 version of PriorityQueue O(nlogn) with comments and explanations

    public String rearrangeString3(String str, int k) {

        StringBuilder rearranged = new StringBuilder();
        //count frequency of each char
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        //construct a max heap using self-defined comparator, which holds all Map entries, Java is quite verbose
        Queue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });

        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        maxHeap.addAll(map.entrySet());

        while (!maxHeap.isEmpty()) {

            Map.Entry<Character, Integer> current = maxHeap.poll();
            rearranged.append(current.getKey());
            current.setValue(current.getValue() - 1);
            waitQueue.offer(current);

            if (waitQueue.size() < k) { // intial k-1 chars, waitQueue not full yet
                continue;
            }
            // release from waitQueue if char is already k apart
            Map.Entry<Character, Integer> front = waitQueue.poll();
            //note that char with 0 count still needs to be placed in waitQueue as a place holder
            if (front.getValue() > 0) {
                maxHeap.offer(front);
            }
        }

        return rearranged.length() == str.length() ? rearranged.toString() : "";
    }
//--------------------------------------------------------------------------------

    public String rearrangeString4(String str, int k) {
        if (k < 2) return str;
        int[][] times = new int[26][2];
        for(int i = 0; i < 26; i++) times[i][1] = 'a'+i;
        for (int i = 0; i < str.length(); i++) {
            times[str.charAt(i) - 'a'][0]++;
        }
        Arrays.sort(times, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(b[0], a[0]);
            }
        });

        int len = str.length(), maxlen = (len + k - 1)/k, count = 0, i = 0;
        if(times[0][0] > maxlen) return "";

        char[] res = new char[len];
        if((count = (len % k)) != 0){
            for(i = 0; i < 26; i++){
                if(times[i][0] < maxlen) break;
                if(i >= count) return "";
                for(int j = i; j < len; j += k) res[j] = (char)times[i][1];
            }
        }

        count = i; maxlen = i;
        for(int j = 25; j >= maxlen; j--){
            for(int t = 0; t < times[j][0]; t++){
                res[count] = (char)times[j][1];
                count += k;
                if(count >= len) count = ++i;
            }
        }

        return new String(res);
    }
}
