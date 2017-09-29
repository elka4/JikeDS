package J_7_2Pointers.all;

import org.junit.Test;

/** 604 Window Sum
 * Created by tianhuizhu on 6/28/17.
 */
public class _604_Window_Sum {
    /**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length < k || k <= 0)
            return new int[0];

        int[] sums = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++)
            sums[0] += nums[i];

        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] - nums[i - 1] + nums[i + k-1];
        }

        return sums;
    }

    @Test
    public void test01(){
        int[] nums = {1,2,7,8,5};
        int k = 3;

        int[] result = winSum(nums, k);
        for (int i:result
             ) {
            System.out.print(i + " ");
        }
    }


}
/*
给你一个大小为n的整型数组和一个大小为k的滑动窗口，将滑动窗口从头移到尾，
输出从开始到结束每一个时刻滑动窗口内的数的和。

您在真实的面试中是否遇到过这个题？ Yes
样例
对于数组 [1,2,7,8,5] ，滑动窗口大小k= 3 。
1 + 2 + 7 = 10
2 + 7 + 8 = 17
7 + 8 + 5 = 20
返回 [10,17,20]
 */