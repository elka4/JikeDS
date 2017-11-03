package _TwoPointer.All_TwoPointer;

//  209. Minimum Size Subarray Sum
//  https://leetcode.com/problems/minimum-size-subarray-sum/description/

//  http://www.lintcode.com/zh-cn/problem/minimum-size-subarray-sum/
public class _209_TwoPointer_Minimum_Size_Subarray_Sum_M {
    //Accepted clean Java O(n) solution (two pointers)
    public int minSubArrayLen(int s, int[] a) {
        if (a == null || a.length == 0)
            return 0;

        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;

        while (j < a.length) {
            sum += a[j++];

            while (sum >= s) {
                min = Math.min(min, j - i);
                sum -= a[i++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }


//    Perhaps could make it more readable. Thanks for sharing!

    public int minSubArrayLen2(int s, int[] nums) {
        int sum = 0, from = 0, win = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                win = Math.min(win, i - from + 1);
                sum -= nums[from++];
            }
        }
        return (win == Integer.MAX_VALUE) ? 0 : win;
    }

    //Two AC solutions in Java with time complexity of N and NLogN with explanation
/*
Since the given array contains only positive integers, the subarray sum can only increase by including more elements. Therefore, you don't have to include more elements once the current subarray already has a sum large enough. This gives the linear time complexity solution by maintaining a minimum window with a two indices.

As to NLogN solution, logN immediately reminds you of binary search. In this case, you cannot sort as the current order actually matters. How does one get an ordered array then? Since all elements are positive, the cumulative sum must be strictly increasing. Then, a subarray sum can expressed as the difference between two cumulative sum. Hence, given a start index for the cumulative sum array, the other end index can be searched using binary search.
 */
    class Solution3{
        public int minSubArrayLen(int s, int[] nums) {
            return solveNLogN(s, nums);
        }

        private int solveN(int s, int[] nums) {
            int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
            while (end < nums.length) {
                while (end < nums.length && sum < s) sum += nums[end++];
                if (sum < s) break;
                while (start < end && sum >= s) sum -= nums[start++];
                if (end - start + 1 < minLen) minLen = end - start + 1;
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }

        private int solveNLogN(int s, int[] nums) {
            int[] sums = new int[nums.length + 1];
            for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
            int minLen = Integer.MAX_VALUE;
            for (int i = 0; i < sums.length; i++) {
                int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
                if (end == sums.length) break;
                if (end - i < minLen) minLen = end - i;
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }

        private int binarySearch(int lo, int hi, int key, int[] sums) {
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (sums[mid] >= key){
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            return lo;
        }
    }

    public class Solution4 {

        public int minSubArrayLen(int s, int[] nums) {

            if (nums == null) return 0;

            int first = 0;
            int last = 0;
            int min = Integer.MAX_VALUE;
            int sum = 0;

            //find the shortest length subarray from last[0] to nums[last]
            while (last < nums.length) {

                sum += nums[last++]; // this statement is processed only when sum is smaller then s.

                //first will never be greater than last here, since when first meet
                // last-1 (since last is increased in the last step), sum will reach zero.
                while (sum >= s) {
                    min = Math.min(min, last - first);
                    sum -= nums[first++];
                    if (sum == 0) return 1; //This means nums[first-1] = s, thus the shortest length is 1.
                }

            }
            return min == Integer.MAX_VALUE ? 0 : min;

        }
    }



    //O(N),O(NLogN) solutions, both O(1) space
//    O(N) - keep a moving window expand until sum>=s, then shrink util sum<s. Each time after shrinking, update length. (similar to other solutions, just removed unnecessary min value assignment)

    public class Solution5 {
        public int minSubArrayLen(int s, int[] nums) {
            int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
            while (j < nums.length) {
                while (sum < s && j < nums.length) sum += nums[j++];
                if(sum>=s){
                    while (sum >= s && i < j) sum -= nums[i++];
                    min = Math.min(min, j - i + 1);
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }
//    O(NLogN) - search if a window of size k exists that satisfy the condition

    public class Solution6 {
        public int minSubArrayLen(int s, int[] nums) {
            int i = 1, j = nums.length, min = 0;
            while (i <= j) {
                int mid = (i + j) / 2;
                if (windowExist(mid, nums, s)) {
                    j = mid - 1;
                    min = mid;
                } else i = mid + 1;
            }
            return min;
        }


        private boolean windowExist(int size, int[] nums, int s) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i >= size) sum -= nums[i - size];
                sum += nums[i];
                if (sum >= s) return true;
            }
            return false;
        }
    }
//    Another O(NLogN) solution that first calculate cumulative sum and then for each starting point binary search for end position. This uses O(N) space

    public class Solution7 {
        public int minSubArrayLen(int s, int[] nums) {
            int sum = 0, min = Integer.MAX_VALUE;

            int[] sums = new int[nums.length];
            for (int i = 0; i < nums.length; i++)
                sums[i] = nums[i] + (i == 0 ? 0 : sums[i - 1]);

            for (int i = 0; i < nums.length; i++) {
                int j = findWindowEnd(i, sums, s);
                if (j == nums.length) break;
                min = Math.min(j - i + 1, min);
            }

            return min == Integer.MAX_VALUE ? 0 : min;
        }

        private int findWindowEnd(int start, int[] sums, int s) {
            int i = start, j = sums.length - 1, offset = start == 0 ? 0 : sums[start - 1];
            while (i <= j) {
                int m = (i + j) / 2;
                int sum = sums[m] - offset;
                if (sum >= s) j = m - 1;
                else i = m + 1;
            }
            return i;
        }
    }


    ////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
    //jiuzhang
public class Jiuzhang {
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        // write your code here
        int j = 0, i = 0;
        int sum =0;
        int ans = Integer.MAX_VALUE;
        for(i = 0; i < nums.length; i++) {
            while(j < nums.length && sum < s) {
                sum += nums[j];
                j ++;
            }
            if(sum >=s) {
                ans = Math.min(ans, j - i);
            }
            sum -= nums[i];
        }
        if(ans == Integer.MAX_VALUE)
            ans = -1;
        return ans;
    }
}
}
/*
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */

/*
和大于S的最小子数组

 描述
 笔记
 数据
 评测
给定一个由 n 个正整数组成的数组和一个正整数 s ，请找出该数组中满足其和 ≥ s 的最小长度子数组。如果无解，则返回 -1。

样例
给定数组 [2,3,1,2,4,3] 和 s = 7, 子数组 [4,3] 是该条件下的最小长度子数组。
 */