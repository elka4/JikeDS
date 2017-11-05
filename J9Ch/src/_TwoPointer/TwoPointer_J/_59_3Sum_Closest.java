package _TwoPointer.TwoPointer_J;

import java.util.Arrays;

/** 59 3Sum Closest
 * Created by tianhuizhu on 6/28/17.
 */

//  http://www.lintcode.com/zh-cn/problem/3sum-closest/
public class _59_3Sum_Closest {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target   : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers, int target) {
        if (numbers == null || numbers.length < 3) {
            return -1;
        }

        Arrays.sort(numbers);

        int bestSum = numbers[0] + numbers[1] + numbers[2];

        for (int i = 0; i < numbers.length; i++) {
            int start = i + 1, end = numbers.length - 1;

            while (start < end) {
                int sum = numbers[i] + numbers[start] + numbers[end];

                if (Math.abs(target - sum) < Math.abs(target - bestSum)) {
                    bestSum = sum;
                }

                if (sum < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        return bestSum;
    }

}
/*
给一个包含 n 个整数的数组 S, 找到和与给定整数 target 最接近的三元组，返回这三个数的和。

 注意事项

只需要返回三元组之和，无需返回三元组本身

样例
例如 S = [-1, 2, 1, -4] and target = 1. 和最接近 1 的三元组是 -1 + 2 + 1 = 2.

挑战
O(n^2) 时间, O(1) 额外空间。
 */