package _10_DS._Heap;
import java.util.*;
import org.junit.Test;
public class _358_Heap_Rearrange_String_k_Distance_Apart_H {

/*    Java 15ms Solution with Two auxiliary array. O(N) time.
    This is a greedy problem.
    Every time we want to find the best candidate: which is the character with the largest remaining count. Thus we will be having two arrays.
    One count array to store the remaining count of every character. Another array to keep track of the most left position that one character can appear.
    We will iterated through these two array to find the best candidate for every position. Since the array is fixed size, it will take constant time to do this.
    After we find the candidate, we update two arrays.*/

    public class Solution {
        public String rearrangeString(String str, int k) {
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
    }


/*    Java 7 version of PriorityQueue O(nlogn) with comments and explanations
    The greedy algorithm is that in each step, select the char with highest remaining count if possible (if it is not in the waiting queue). PQ is used to achieve the greedy. A regular queue waitQueue is used to "freeze" previous appeared char in the period of k.

    In each iteration, we need to add current char to the waitQueue and also release the char at front of the queue, put back to maxHeap. The "impossible" case happens when the maxHeap is empty but there is still some char in the waitQueue.*/

    public class Solution2 {
        public String rearrangeString(String str, int k) {

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
            Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
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

    }


//    Java_solution_in_12_ms, O(N) time and space

    public class Solution3 {
        public String rearrangeString(String str, int k) {
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
}
/*
Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

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