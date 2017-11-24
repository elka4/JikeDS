package _TwoPointer._TwoPointer;
import java.util.*;
import org.junit.Test;

//16. 3Sum Closest

//https://leetcode.com/problems/3sum-closest/description/
public class _016_TwoPointer_3Sum_Closest_M {
    //Java solution with O(n2) for reference
/*    Similar to 3 Sum problem, use 3 pointers to point current element, next element and the last element. If the sum is less than target, it means we have to add a larger element so next element move to the next. If the sum is greater, it means we have to add a smaller element so last element move to the second last element. Keep doing this until the end. Each time compare the difference between sum and target, if it is less than minimum difference so far, then replace result with it, otherwise keep iterating.*/

    public class Solution {
        public int threeSumClosest(int[] num, int target) {
            int result = num[0] + num[1] + num[num.length - 1];
            Arrays.sort(num);
            for (int i = 0; i < num.length - 2; i++) {
                int start = i + 1, end = num.length - 1;
                while (start < end) {
                    int sum = num[i] + num[start] + num[end];
                    if (sum > target) {
                        end--;
                    } else {
                        start++;
                    }
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        result = sum;
                    }
                }
            }
            return result;
        }
    }

    //Self-Explanatory Java Solution using two pointers
    public int threeSumClosest(int[] nums, int target) {
        if(nums==null || nums.length<3)
            return -1;
        Arrays.sort(nums);
        int result = 0;
        int diff = Integer.MAX_VALUE;
        for(int i=0; i<nums.length-1; i++){
            if(i>0 && nums[i] == nums[i-1])
                continue;
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];
                if(diff> Math.abs(target-sum)){
                    result=sum;
                    diff = Math.abs(target-sum);
                }if(sum==target){
                    return sum;
                }else if(sum<target){
                    j++;
                }else
                    k--;
            }

        }
        return result;
    }


//-------------------------------------------------------------------------//////
    //jiuzhang
public class Jiuzhang {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target : An integer
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
}
//给一个包含 n 个整数的数组 S, 找到和与给定整数 target 最接近的三元组，返回这三个数的和。

/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */