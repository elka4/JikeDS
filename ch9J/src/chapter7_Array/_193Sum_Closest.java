package chapter7_Array;

import java.util.Arrays;

public class _193Sum_Closest {
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

/*Given an array S of n integers, find three integers in S such 
 * that the sum is closest to a given number, target. Return the 
 * sum of the three integers.
 

 Notice

You may assume that each input would have exactly one solution.

Have you met this question in a real interview? Yes
Example
For example, given array S = [-1 2 1 -4], and target = 1. 
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Challenge 
O(n^2) time, O(1) extra space

Tags 
Two Pointers Sort Array
Related Problems 
Medium Two Sum Closest 39 %
Medium 3Sum 19 %
Medium Two Sum 28 %*/