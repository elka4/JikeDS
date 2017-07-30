package J_6_Linked_List_Array.other.Maximum_Average_Subarray;

/*
Given an array consisting of n integers, find the contiguous subarray of
 given length k that has the maximum average value.
 And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].
 */

//https://leetcode.com/problems/maximum-average-subarray-i/#/solution

//
public class _leet_643_Maximum_Average_Subarray_I {

//Approach #1 Brute Force [Time Limit Exceeded]
//The naive solution is to consider every possible subarray with length equal to kk,
// and to find out the maximum average possible from out of these subarrays chosen.
public double findMaxAverage(int[] nums, int k) {
    double res = Integer.MIN_VALUE;
    for (int s = 0; s < nums.length - k + 1; s++) {
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i + s];
        }
        res = Math.max(res, sum / k);
    }
    return res;
}
/*
Complexity Analysis

Time complexity : O(n*k)O(n∗k). We traverse over subarrays of length kk for a total of n-kn−k times.

Space complexity : O(1)O(1). Constant extra space is used.
 */

////////////////////////////////////////////////////////////////
//Approach #2 Cumulative Sum [Accepted]

/*
We know that in order to obtain the averages of subarrays with length kk,
we need to obtain the sum of these kk length subarrays.
One of the methods of obtaining this sum is to make use of a cumulative sum array,
 sumsum, which is populated only once. Here, sum[i]sum[i] is used to store the sum
  of the elements of the given numsnums array from the first element upto the element at the i^{th}i
​th
​​  index.

Once the sumsum array has been filled up, in order to find the sum of elements from
the index ii to i+ki+k, all we need to do is to use: sum[i] - sum[i-k]sum[i]−sum[i−k].
Thus, now, by doing one more iteration over the sumsum array,
we can determine the maximum average possible from the subarrays of length kk.

The following animation illustrates the process for a simple example.
 */

    public double findMaxAverage2(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];

        // presum array: sum[]
        for (int i = 1; i < nums.length; i++)
            sum[i] = sum[i - 1] + nums[i];

        double res = sum[k - 1] * 1.0 / k;

        for (int i = k; i < nums.length; i++) {
            res = Math.max(res, (sum[i] - sum[i - k]) * 1.0 / k);
        }

        return res;
    }

/*
Complexity Analysis

Time complexity : O(n)O(n). We iterate over the numsnums array of length
 nn once to fill the sumsum array. Then, we iterate over n-kn−k elements
  of sumsum to determine the required result.

Space complexity : O(n)O(n). We make use of a sumsum array of length nn
to store the cumulative sum.
 */
////////////////////////////////////////////////////////////////
//Approach #3 Sliding Window [Accepted]

/*
Instead of creating a cumulative sum array first, and then traversing over
it to determine the required sum, we can simply traverse over numsnums just
 once, and on the go keep on determining the sums possible for the subarrays
  of length kk. To understand the idea, assume that we already know the sum
  of elements from index ii to index i+ki+k, say it is xx.. Now, to determine
   the sum of elements from the index i+1i+1 to the index i+k+1i+k+1, all we
   need to do is to subtract the element nums[i]nums[i] from xx and to add the
   element nums[i+k+1]nums[i+k+1] to xx. We can carry out our process based on
   this idea and determine the maximum possible average.
 */

    public double findMaxAverage3(int[] nums, int k) {
        double res=Integer.MIN_VALUE,sum=0;

        for(int i=0;i<k;i++)
            sum+=nums[i];

        res=Math.max(res,sum/k);

        for(int i=k;i<nums.length;i++){
            sum+=nums[i]-nums[i-k];
            res=Math.max(res,sum/k);
        }

        return res;
    }

/*
Complexity Analysis

Time complexity : O(n)O(n). We iterate over the given numsnums array of length nn once only.

Space complexity : O(1)O(1). Constant extra space is used.


 */

////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////


}
