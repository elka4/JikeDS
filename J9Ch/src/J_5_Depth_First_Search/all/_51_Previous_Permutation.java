package J_5_Depth_First_Search.all;

import org.junit.Test;

import java.util.ArrayList;

/** 51 Previous Permutation
 * Created by tianhuizhu on 6/28/17.
 * The list may contains duplicate integers.
 Given a list of integers, which denote a permutation.
 Find the previous permutation in ascending order.

 Permutation
 */
public class _51_Previous_Permutation {
    /**
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public void swapItem(ArrayList<Integer> nums, int i, int j) {
        Integer tmp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, tmp);
    }
    public void swapList(ArrayList<Integer> nums, int i, int j) {
        while ( i < j) {
            swapItem(nums, i, j);
            i ++; j --;
        }
    }
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
        int len = nums.size();
        if ( len <= 1){
            return nums;
        }

        int i = len - 1;

        while ( i > 0 && nums.get(i) >= nums.get(i-1) ){
            i --;
        }
        System.out.println("i : " + i);

        swapList(nums, i, len - 1);

        System.out.println(nums);

        if ( i != 0) {
            int j = i;
            while ( nums.get(j) >= nums.get(i-1) ) j++;
            swapItem(nums, j, i-1);
        }

        return nums;
    }

    @Test
    public void test03(){
        int[] nums = {1,3,2,3};
        ArrayList<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(3);
        input.add(2);
        input.add(3);


        System.out.println(previousPermuation(input));
    }

///////////////////////////////////////////////

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

/*
For [1,3,2,3], the previous permutation is [1,2,3,3]

For [1,2,3,4], the previous permutation is [4,3,2,1]
 */

    @Test
    public void test01(){
        int[] nums = {1,3,2,3};
        ArrayList<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(3);
        input.add(2);
        input.add(3);


        System.out.println(previousPermuation(input));
    }

    @Test
    public void test02(){
        int[] nums = {1,2,3};
        ArrayList<Integer> input = new ArrayList<>();
        input.add(3);
        input.add(1);
        input.add(2);
        System.out.println(input);
        System.out.println(previousPermuation(input));
        //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
        //[1, 2, 3] [3, 2, 1]
        //[1, 3, 2] [1, 2, 3]
        //[2, 1, 3] [1, 3, 2]
        //[3, 1, 2] [2, 3, 1]
        //
        //




    }

}
