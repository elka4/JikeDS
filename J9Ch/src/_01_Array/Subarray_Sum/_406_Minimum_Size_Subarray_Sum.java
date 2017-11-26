package _01_Array.Subarray_Sum;

//209. Minimum Size Subarray Sum
// 406 Minimum Size Subarray Sum

//https://leetcode.com/problems/minimum-size-subarray-sum/#/description

/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of
 which the time complexity is O(n log n).

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
 */


//find the minimal length of a contiguous subarray of which the sum ≥ s.
// n positive integers and a positive integer s

//Given the array [2,3,1,2,4,3] and s = 7,
// the subarray [4,3] has the minimal length under the problem constraint.

//sum ≥ s的最小的subarray的长度


public class _406_Minimum_Size_Subarray_Sum {

    // 9Ch
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


//-------------------------------------------------------------------------------

    //Java Solution - two pointers
    //A simple sliding window solution.

    public int minSubArrayLen1(int s, int[] nums) {
        if(nums==null || nums.length==1)
            return 0;

        int result = nums.length;

        int start=0;
        int sum=0;
        int i=0;
        boolean exists = false;

        while(i<=nums.length){
            if(sum>=s){
                exists=true; //mark if there exists such a subarray
                if(start==i-1){
                    return 1;
                }

                result = Math.min(result, i-start);
                sum=sum-nums[start];
                start++;

            }else{
                if(i==nums.length)
                    break;
                sum = sum+nums[i];
                i++;
            }
        }

        if(exists)
            return result;
        else
            return 0;
    }

//-------------------------------------------------------------------------------

    public int minSubArrayLen2(int s, int[] nums) {
        if(nums==null||nums.length==0)
            return 0;

        int i=0;
        int j=0;
        int sum=0;

        int minLen = Integer.MAX_VALUE;

        while(j<nums.length){
            if(sum<s){
                sum += nums[j];
                j++;
            }else{
                minLen = Math.min(minLen, j-i);
                if(i==j-1)
                    return 1;

                sum -=nums[i];
                i++;
            }
        }

        while(sum>=s){
            minLen = Math.min(minLen, j-i);

            sum -=nums[i++];
        }

        return minLen==Integer.MAX_VALUE ? 0: minLen;
    }

//-------------------------------------------------------------------------------

    //Editorial C++



//-------------------------------------------------------------------------------

    //Accepted clean Java O(n) solution (two pointers)

    public int minSubArrayLen4(int s, int[] a) {
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
//-------------------------------------------------------------------------------

    //Two AC solutions in Java with time complexity of N and NLogN with explanation

    public class Solution {
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
//-------------------------------------------------------------------------------

//-------------------------------------------------------------------------------


}
