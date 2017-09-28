package Bit106.L04;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers
 * sum that they add up to a specific target.
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 *
 * E.g.
 * Given nums = [2, 7, 11, 15], target = 9,
 * return [0, 1].
 */
public class SolutionTwoSum {
    private static int[] twoSum(int[] numbers, int target) {
        // define return value
        int[] indexArray = new int[2];

        // handle corner cases
        if (numbers == null || numbers.length == 0) {
            return null;
        }

        // Value <-> index map
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            hashMap.put(numbers[i], i);
        }
        // At this point we get a hashmap like this:
        // 2, 0
        // 7, 1
        // 11, 2
        // 15, 3

        // For every element, check if (target-element) exists in array
        // If exists, we update indexArray and return
        // otherwise, go to the next loop and check the next element
        for (int i = 0; i < numbers.length; i++) {
            if (hashMap.containsKey(target - numbers[i])) {
                indexArray[0] = i;
                indexArray[1] = hashMap.get(target - numbers[i]);
                // Remember we can't use the same element twice!!
                if (indexArray[0] == indexArray[1])
                    continue;
                return indexArray;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] indexArray = twoSum(nums, target);
        System.out.printf("[%d, %d]", indexArray[0], indexArray[1]);
    }
}
