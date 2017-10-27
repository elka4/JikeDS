package _10_DS._Heap;
import java.util.*;
import org.junit.Test;
public class _659_Heap_Split_Array_into_Consecutive_Subsequences_M {

/*    Java O(n) Time O(n) Space
    We iterate through the array once to get the frequency of all the elements in the array
    We iterate through the array once more and for each element we either see if it can be appended to a previously constructed consecutive sequence or if it can be the start of a new consecutive sequence. If neither are true, then we return false.*/
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();
        for (int i : nums) freq.put(i, freq.getOrDefault(i,0) + 1);
        for (int i : nums) {
            if (freq.get(i) == 0) continue;
            else if (appendfreq.getOrDefault(i,0) > 0) {
                appendfreq.put(i, appendfreq.get(i) - 1);
                appendfreq.put(i+1, appendfreq.getOrDefault(i+1,0) + 1);
            }
            else if (freq.getOrDefault(i+1,0) > 0 && freq.getOrDefault(i+2,0) > 0) {
                freq.put(i+1, freq.get(i+1) - 1);
                freq.put(i+2, freq.get(i+2) - 1);
                appendfreq.put(i+3, appendfreq.getOrDefault(i+3,0) + 1);
            }
            else return false;
            freq.put(i, freq.get(i) - 1);
        }
        return true;
    }

    //  https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/
    // 659. Split Array into Consecutive Subsequences
    class Solution2{
        public boolean isPossible(int[] nums) {
            int pre = Integer.MIN_VALUE, p1 = 0, p2 = 0, p3 = 0;
            int cur = 0, cnt = 0, c1 = 0, c2 = 0, c3 = 0;

            for (int i = 0; i < nums.length; pre = cur, p1 = c1, p2 = c2, p3 = c3) {
                for (cur = nums[i], cnt = 0; i < nums.length && cur == nums[i]; cnt++, i++);

                if (cur != pre + 1) {
                    if (p1 != 0 || p2 != 0) return false;
                    c1 = cnt; c2 = 0; c3 = 0;

                } else {
                    if (cnt < p1 + p2) return false;
                    c1 = Math.max(0, cnt - (p1 + p2 + p3));
                    c2 = p1;
                    c3 = p2 + Math.min(p3, cnt - (p1 + p2));
                }
            }

            return p1 == 0 && p2 == 0;
        }
    }

//////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////



}
/*
You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences :
1, 2, 3
3, 4, 5
Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences :
1, 2, 3, 4, 5
3, 4, 5
Example 3:
Input: [1,2,3,4,4,5]
Output: False
Note:
The length of the input is in range of [1, 10000]

 */