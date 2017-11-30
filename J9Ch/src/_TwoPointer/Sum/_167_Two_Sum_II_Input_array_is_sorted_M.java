package _TwoPointer.Sum;

//  167. Two Sum II - Input array is sorted
//  https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
//
public class _167_Two_Sum_II_Input_array_is_sorted_M {
//-------------------------------------------------------------------------
    //1
//    Share my java AC solution.
//    Without HashMap, just have two pointers, A points to index 0, B points to index len - 1, shrink the scope based on the value and target comparison.

    public int[] twoSum(int[] num, int target) {
        int[] indice = new int[2];
        if (num == null || num.length < 2) return indice;
        int left = 0, right = num.length - 1;
        while (left < right) {
            int v = num[left] + num[right];
            if (v == target) {
                indice[0] = left + 1;
                indice[1] = right + 1;
                break;
            } else if (v > target) {
                right --;
            } else {
                left ++;
            }
        }
        return indice;
    }
//-------------------------------------------------------------------------
    //2

//    Simple 8 line Java solution with explanation. O(n)
    public int[] twoSum2(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (numbers[l] + numbers[r] != target) {
            if (numbers[l] + numbers[r] > target) r--;
            else l++;
        }
        return new int[]{l + 1, r + 1};
    }
/*    we use l and r to denote the first index and second index respectively.

    When the sum is:

    smaller than the target:

    we move l to the right by 1. we can't make r smaller because that's gonna make the sum even smaller.
    bigger than target:

    move r to the left by 1. we can't make l bigger because that's gonna make the sum even bigger.
    equal to the target:

    we found the answer and return.
    Since the question said there is EXACTLY one solution and didn't provide any info about when there is no valid answer, so we can always assume there is one and only one answer, which means l and r never across each other.

    Another thing to notice is that this array is sorted.*/
//-------------------------------------------------------------------------
}
/*

 */