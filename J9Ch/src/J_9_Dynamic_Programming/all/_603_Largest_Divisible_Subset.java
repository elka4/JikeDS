package J_9_Dynamic_Programming.all;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** 603 Largest Divisible Subset
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class    _603_Largest_Divisible_Subset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int[] f = new int[nums.length];
        int[] pre = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            pre[i] = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[i] < f[j] + 1) {
                    f[i] = f[j] + 1;
                    pre[i] = j;
                }
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        if (nums.length == 0) {
            return ans;
        }
        int max = 0;
        int max_i = 0;
        for (int i = 0; i < nums.length; i++) {
            if (f[i] > max) {
                max = f[i];
                max_i = i;
            }
        }
        ans.add(nums[max_i]);
        while (max_i != pre[max_i]) {
            max_i = pre[max_i];
            ans.add(nums[max_i]);
        }
        Collections.reverse(ans);
        return ans;
    }

    @Test
    public void test01(){
        int[] nums = {1,2,3};
        System.out.println(largestDivisibleSubset(nums));
    }

/*

Given a set of distinct positive integers, find the largest subset such that every
 pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

 Notice

If there are multiple solutions, return any subset is fine.

Have you met this question in a real interview? Yes
Example
Given nums = [1,2,3], return [1,2] or [1,3]

Given nums = [1,2,4,8], return [1,2,4,8]


 */

}
