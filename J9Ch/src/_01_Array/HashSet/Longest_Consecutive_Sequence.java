package _01_Array.HashSet;

import java.util.HashSet;
import java.util.Set;
/*
LeetCode – Longest Consecutive Sequence (Java)

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example, given [100, 4, 200, 1, 3, 2], the longest consecutive elements sequence should be [1, 2, 3, 4]. Its length is 4.

Your algorithm should run in O(n) complexity.


 */


public class Longest_Consecutive_Sequence {
    // 9Ch
    /**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            int down = nums[i] - 1;
            while (set.contains(down)) {
                set.remove(down);
                down--;
            }

            int up = nums[i] + 1;
            while (set.contains(up)) {
                set.remove(up);
                up++;
            }

            longest = Math.max(longest, up - down - 1);
        }

        return longest;
    }

//-------------------------------------------------------------------------//////

    // version: 高频题班
    /**
     * @param num: A list of integers
     * @return an integer
     */
    public int longestConsecutive2(int[] num) {
        // write you code here
        Set<Integer> set = new HashSet<>();
        for (int item : num) {
            set.add(item);
        }

        int ans = 0;
        for (int item : num) {
            if (set.contains(item)) {
                set.remove(item);

                int pre = item - 1;
                int next = item + 1;
                while (set.contains(pre)) {
                    set.remove(pre);
                    pre--;
                }
                while (set.contains(next)) {
                    set.remove(next);
                    next++;
                }
                ans = Math.max(ans, next - pre - 1);
            }
        }
        return ans;
    }

//-------------------------------------------------------------------------//////

/*    Java Solution 1

    Because it requires O(n) complexity, we can not solve the problem by sorting the array first. Sorting takes at least O(nlogn) time.

    We can use a HashSet to add and remove elements. HashSet is implemented by using a hash table. Elements are not ordered. The add, remove and contains methods have constant time complexity O(1).*/

    public static int longestConsecutive3(int[] num) {
        // if array is empty, return 0
        if (num.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<Integer>();
        int max = 1;

        for (int e : num)
            set.add(e);

        for (int e : num) {
            int left = e - 1;
            int right = e + 1;
            int count = 1;

            while (set.contains(left)) {
                count++;
                set.remove(left);
                left--;
            }

            while (set.contains(right)) {
                count++;
                set.remove(right);
                right++;
            }

            max = Math.max(count, max);
        }

        return max;
    }
/*    Java Solution 2

    We can also project the arrays to a new array with length to be the largest element in the array. Then iterate over the array and get the longest consecutive sequence. If the largest number is too large, then the time would be bad. This is just another thought.*/
}
