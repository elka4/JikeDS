package _TwoPointer.TwoPointer_J;

import org.junit.Test;

/** 31 Partition Array
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */

/*
Given an array nums of integers and an int k, partition the array
(i.e move the elements in "nums") such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

 Notice

You should do really partition in array nums instead of just counting
 the numbers of integers smaller than k.

If all elements in nums are smaller than k, then return nums.length
 */

//If nums = [3,2,2,1] and k=2, a valid answer is 1.

// Partition Array
public class _31_Partition_Array {
    /**
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int left = 0, right = nums.length - 1;

        while (left <= right) {

            while (left <= right && nums[left] < k) {
                left++;
            }

            while (left <= right && nums[right] >= k) {
                right--;
            }
            if (left <= right) {
                swap(nums, left++, right--);
            }

        }

        return left;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

//    private int reverse(int[] nums, int i, int j){
//        while(i <= j){
//            swap(nums, i++, j--);
//        }
//        return i;
//    }

    @Test
    public void test01(){
        int[] nums = {3,2,2,1};
        System.out.println(partitionArray(nums, 2));    //1
        for (int i:nums ) {
            System.out.print(i + ", ");//1, 2, 2, 3,
        }
    }

    @Test
    public void test02(){
        int[] nums = {3,2,2,1};
        System.out.println(partitionArray(nums, 1));    //0
        for (int i:nums ) {
            System.out.print(i + ", ");//3, 2, 2, 1,
        }
    }
/////////////////////////////////////////////////////////////////
    //jiuzhang
public class Jiuzhang {
    /**
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {

            while (left <= right && nums[left] < k) {
                left++;
            }

            while (left <= right && nums[right] >= k) {
                right--;
            }

            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                left++;
                right--;
            }
        }
        return left;
    }
}



}
/*
给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：

所有小于k的元素移到左边
所有大于等于k的元素移到右边
返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k。

 注意事项

你应该真正的划分数组 nums，而不仅仅只是计算比 k 小的整数数，如果数组 nums 中的所有元素都比 k 小，则返回 nums.length。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出数组 nums = [3,2,2,1] 和 k = 2，返回 1.

挑战
使用 O(n) 的时间复杂度在数组上进行划分。
 */