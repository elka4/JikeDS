package _01_Array.Maximum_Average_Subarray;

// 644. Maximum Average Subarray II

/*
Given an array consisting of n integers, find the contiguous subarray whose
 length is greater than or equal to k that has the maximum average value.
 And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.
 */

//ontiguous subarray whose length is
// greater than or equal to k that has the maximum average value

public class _leet_644_Maximum_Average_Subarray_II {

    //https://leetcode.com/problems/maximum-average-subarray-ii/solution/

    //Approach #1 Iterative method [Time Limit Exceeded]
    public double findMaxAverage(int[] nums, int k) {
        double res = Integer.MIN_VALUE;
        for (int s = 0; s < nums.length - k + 1; s++) {
            long sum = 0;
            for (int i = s; i < nums.length; i++) {
                sum += nums[i];
                if (i - s + 1 >= k)
                    res = Math.max(res, sum * 1.0 / (i - s + 1));
            }
        }
        return res;
    }

//-------------------------------------------------------------------------//

    //Approach #2 Using Binary Search [Accepted]
    public double findMaxAverage2(int[] nums, int k) {
        double max_val = Integer.MIN_VALUE;
        double min_val = Integer.MAX_VALUE;
        for (int n: nums) {
            max_val = Math.max(max_val, n);
            min_val = Math.min(min_val, n);
        }
        double prev_mid = max_val, error = Integer.MAX_VALUE;
        while (error > 0.00001) {
            double mid = (max_val + min_val) * 0.5;
            if (check(nums, mid, k))
                min_val = mid;
            else
                max_val = mid;
            error = Math.abs(prev_mid - mid);
            prev_mid = mid;
        }
        return min_val;
    }


    public boolean check(int[] nums, double mid, int k) {
        double sum = 0, prev = 0, min_sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i] - mid;
        if (sum >= 0)
            return true;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - mid;
            prev += nums[i - k] - mid;
            min_sum = Math.min(prev, min_sum);
            if (sum >= min_sum)
                return true;
        }
        return false;
    }



//-------------------------------------------------------------------------//

    //Check whether we can find a subarray whose average is bigger than x
    boolean check(int[] nums,int k,double x) {
        int n=nums.length;
        double[] a=new double[n];
        //Transfer to a[i], find whether there is a subarray whose sum is bigger than 0
        for (int i=0;i<n;i++) a[i]=nums[i]-x;

        double now=0,last=0;

        for (int i=0;i<k;i++) now+=a[i];

        if (now>=0) return true;

        for (int i=k;i<n;i++) {
            now+=a[i];
            last+=a[i-k];
            if (last<0) {
                now-=last;
                last=0;
            }
            if (now>=0) return true;
        }
        return false;
    }

    public double findMaxAverage3(int[] nums, int k) {
        double l=Integer.MIN_VALUE,r=Integer.MAX_VALUE;

        //Binary search the answer
        while (r-l>0.000004) {
            double mid=(l+r)/2;
            if (check(nums,k,mid)) l=mid; else r=mid;
        }
        return r;
    }


//-------------------------------------------------------------------------//



//-------------------------------------------------------------------------//


}
