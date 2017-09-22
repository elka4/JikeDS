package J_2_Binary_Search.Related_8;

/**
617
Maximum Average Subarray

 * Created by tianhuizhu on 6/28/17.
 */

//Maximum Average Subarray
public class _617_Maximum_Average_Subarray {
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


//////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////


}
/*
给出一个整数数组，有正有负。找到这样一个子数组，他的长度大于等于 k，且平均值最大。

 注意事项

保证数组的大小 >= k

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 nums = [1, 12, -5, -6, 50, 3], k = 3

返回 15.667 // (-6 + 50 + 3) / 3 = 15.667
 */