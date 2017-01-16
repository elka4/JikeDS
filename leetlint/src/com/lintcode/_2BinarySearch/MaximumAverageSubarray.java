package com.lintcode._2BinarySearch;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tzh on 1/15/17.
 */
public class MaximumAverageSubarray {
    public double maxAverage(int[] nums, int k) {
        // Write your code here
        double l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < l)
                l = nums[i];
            if (nums[i] > r)
                r = nums[i];
        }
        int whileCount = 0;
        int breakCount = 0;
        double[] sum = new double[n + 1];
        sum[0] = 0;
        while (r - l >= 1e-1) {
            whileCount++;
            double mid = (l + r) / 2.0;

            double min_pre = 0;
            boolean check = false;
            for (int i = 1; i <= n; ++i) {
                sum[i] = sum[i - 1] + nums[i - 1] - mid;
                if (i >= k && sum[i] - min_pre >= 0) {
                    check = true;
                    System.out.println("Break");
                    breakCount++;
                    break;
                }
                if (i >= k)
                    min_pre = Math.min(min_pre, sum[i - k + 1]);
                    System.out.println("update");

            }
            if (check)
                l = mid;
            else
                r = mid;
        }
        //System.out.println(l);
        //System.out.println(r);
        System.out.println(whileCount);
        System.out.println("Breakcount" + breakCount);
        return l;
    }

    @Test
    public void test01() {
        int[] nums = {1, 12, -5, -6, 50, 3};

        int k = 3;
        System.out.print(maxAverage(nums, k));
    }

    @Test
    public void test02 () {
        int[] nums = {    -2147483648,-2147483648,-2147483648,-2147483648};
        int k = 2;
        //System.out.print(maxAverage(nums, k));
        Assert.assertTrue(maxAverage(nums, k) == -2147483648);
    }
    @Test
    public void testMinInt() {
        System.out.print(1e-0);
    }




}
