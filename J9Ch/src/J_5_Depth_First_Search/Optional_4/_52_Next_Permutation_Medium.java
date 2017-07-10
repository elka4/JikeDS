package J_5_Depth_First_Search.Optional_4;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
/**52 Next Permutation
 * Created by tianhuizhu on 6/28/17.
 */
public class _52_Next_Permutation_Medium {
    /**
     * @param nums: A list of integers
     * @return: A list of integers that's next permuation
     */
    public void swapItem(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void swapList(int[] nums, int i, int j) {
        while (i < j) {
            swapItem(nums, i, j);
            i ++; j --;
        }
    }
    public int[] nextPermutation(int[] nums) {
        int len = nums.length;
        if ( len <= 1)
            return nums;
        int i = len - 1;
        while (i > 0 && nums[i] <= nums[i - 1])
            i --;
        swapList(nums, i, len - 1);
        if (i != 0) {
            int j = i;
            while (nums[j] <= nums[i - 1]) j++;
            swapItem(nums, j, i-1);
        }
        return nums;
    }

/*
For [1,3,2,3], the next permutation is [1,3,3,2]

For [4,3,2,1], the next permutation is [1,2,3,4]
 */

    @Test
    public void test01(){
        int[] nums = {1,3,2,3};
        int[] result = nextPermutation(nums);
        for (int i : result
             ) {
            System.out.print(i + " ");
        }
    }




    // version 2
    public class Solution2 {
        /**
         * @param num: an array of integers
         * @return: return nothing (void), do not return anything, modify num in-place instead
         */

        public void reverse(int[] num, int start, int end) {
            for (int i = start, j = end; i < j; i++, j--) {
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
            }
        }

        public int[] nextPermutation(int[] num) {
            // find the last increase index
            int index = -1;
            for (int i = num.length - 2; i >= 0; i--) {
                if (num[i] < num[i + 1]) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                reverse(num, 0, num.length - 1);
                return num;
            }

            // find the first bigger one
            int biggerIndex = index + 1;
            for (int i = num.length - 1; i > index; i--) {
                if (num[i] > num[index]) {
                    biggerIndex = i;
                    break;
                }
            }

            // swap them to make the permutation bigger
            int temp = num[index];
            num[index] = num[biggerIndex];
            num[biggerIndex] = temp;

            // reverse the last part
            reverse(num, index + 1, num.length - 1);
            return num;
        }
    }
}
