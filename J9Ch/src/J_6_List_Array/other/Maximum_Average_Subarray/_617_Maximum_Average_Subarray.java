package J_6_List_Array.other.Maximum_Average_Subarray;

/** 617 Maximum Average Subarray
 * Created by tianhuizhu on 6/28/17.
 */

/* 和_leet_644_Maximum_Average_Subarray_II相同
Given an array with positive and negative numbers, find the maximum average
subarray which length should be greater or equal to given length k.

 Notice

It's guaranteed that the size of the array is greater or equal to k.

Have you met this question in a real interview? Yes
Example
Given nums = [1, 12, -5, -6, 50, 3], k = 3

Return 15.667 // (-6 + 50 + 3) / 3 = 15.667


 */

//subarray length greater or equal to given length k

public class _617_Maximum_Average_Subarray {
    // jiuzhang
    /**
     * @param nums an array with positive and negative numbers
     * @param k an integer
     * @return the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        // Write your code here
        double l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < l)
                l = nums[i];
            if (nums[i] > r)
                r = nums[i];
        }


        while (r - l >= 1e-6) {
            double mid = (l + r) / 2.0;

            if (check_valid(nums, mid, k)) {
                l = mid;
            }
            else {
                r = mid;
            }
        }

        return l;
    }

    private boolean check_valid(int nums[], double mid, int k) {
        int n = nums.length;
        double min_pre = 0;
        double[] sum = new double[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1] - mid;
            if (i >= k && sum[i] - min_pre >= 0) {
                return true;
            }
            if (i >= k)
                min_pre = Math.min(min_pre, sum[i - k + 1]);
        }
        return false;
    }

//////////////////////////////////////////////////////////////////////

    public double maxAverage2(int[] nums, int k) {
        // Write your code here
        double l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < l)
                l = nums[i];
            if (nums[i] > r)
                r = nums[i];
        }

        double[] sum = new double[n + 1];
        sum[0] = 0;
        while (r - l >= 1e-6) {
            double mid = (l + r) / 2.0;

            double min_pre = 0;
            boolean check = false;
            for (int i = 1; i <= n; ++i) {
                sum[i] = sum[i - 1] + nums[i - 1] - mid;
                if (i >= k && sum[i] - min_pre >= 0) {
                    check = true;
                    break;
                }
                if (i >= k)
                    min_pre = Math.min(min_pre, sum[i - k + 1]);
            }
            if (check)
                l = mid;
            else
                r = mid;
        }

        return l;
    }

//////////////////////////////////////////////////////////////////////



}
