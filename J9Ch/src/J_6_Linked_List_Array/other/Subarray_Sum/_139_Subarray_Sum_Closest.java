package J_6_Linked_List_Array.other.Subarray_Sum;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/** 139 Subarray Sum Closest
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */

/*
Given an integer array, find a subarray with sum closest to zero.
Return the indexes of the first number and last number.
Example
Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].
 */

//sum最接近0的subarray

public class _139_Subarray_Sum_Closest {

    class Pair {
        int sum;
        int index;
        public Pair(int s, int i) {
            sum = s;
            index = i;
        }
    }

    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }

        int len = nums.length;
        if(len == 1) {
            res[0] = res[1] = 0;
            return res;
        }

        Pair[] sums = new Pair[len+1];
        int prev = 0;

        sums[0] = new Pair(0, 0);
        for (int i = 1; i <= len; i++) {
            sums[i] = new Pair(prev + nums[i-1], i);
            prev = sums[i].sum;
        }

        Arrays.sort(sums, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.sum - b.sum;
            }
        });

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= len; i++) {

            if (ans > sums[i].sum - sums[i-1].sum) {
                ans = sums[i].sum - sums[i-1].sum;
                int[] temp = new int[]{sums[i].index - 1, sums[i - 1].index - 1};
                Arrays.sort(temp);
                res[0] = temp[0] + 1;
                res[1] = temp[1];
            }
        }

        return res;
    }

    @Test
    public void test01(){
        int[] nums = {-3, 1, 1, -3, 5};
        int[] result = subarraySumClosest(nums);
        for (int i:result
             ) {
            System.out.println(i);
        }
    }

}

/*
问：为什么需要一个 (0,0) 的初始 Pair?
答：
我们首先需要回顾一下，在 subarray 这节课里，我们讲过一个重要的知识点，叫做 Prefix Sum
比如对于数组 [1,2,3,4]，他的 Prefix Sum 是 [1,3,6,10]
分别表示 前1个数之和，前2个数之和，前3个数之和，前4个数之和
这个时候如果你想要知道 子数组 从下标  1 到下标 2 的这一段的和(2+3)，就用前 3个数之和 减去 前1个数之和
= PrefixSum[2] - PrefixSum[0] = 6 - 1 = 5
你可以看到这里的 前 x 个数，和具体对应的下标之间，存在 +-1 的问题
第 x 个数的下标是 x - 1，反之 下标 x 是第 x + 1 个数
那么问题来了，如果要计算 下标从 0~2 这一段呢？也就是第1个数到第3个数，因为那样会访问到 PrefixSum[-1]
所以我们把 PrefixSum 整体往后面移动一位，把第0位空出来表示前0个数之和，也就是0. => [0,1,3,6,10]
那么此时就用 PrefixSum[3] - PrefixSum[0] ，这样计算就更方便了。
此时，PrefixSum[i] 代表 前i个数之和，也就是 下标区间在 0 ~ i-1 这一段的和

那么回过头来看看，为什么我们需要一个 (0,0) 的 pair 呢？
因为 这个 0,0 代表的就是前0个数之和为0
一个 n 个数的数组， 变成了 prefix Sum 数组之后，会多一个数出来
*/


