package _TwoPointer.TwoPointer_J;

import org.junit.Test;

/** 625 Partition Array II
 * Created by tianhuizhu on 6/28/17.
 */
public class _625_Partition_Array_II {

    // 参考程序1

    public int partitionArray1(int[] nums, int k) {
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
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
    @Test
    public void test01(){
        int[] nums = {3,2,2,1};
        System.out.println(partitionArray1(nums, 2));    //1
        for (int i:nums ) {
            System.out.print(i + ", ");//1, 2, 2, 3,
        }
    }

    @Test
    public void test02(){
        int[] nums = {3,2,2,1};
        System.out.println(partitionArray1(nums, 1));    //0
        for (int i:nums ) {
            System.out.print(i + ", ");//3, 2, 2, 1,
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

////////////////////////////////////////////////////////////////////////

    public void sortColors(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int left = 0;
        int right = a.length - 1;
        int i = 0;
        while (i <= right) {
            if (a[i] == 0) {
                swap(a, left, i);
                left++;
                i++;
            } else if(a[i] == 1) {
                i++;
            } else if(a[i] == 2) {
                swap(a, right, i);
                right--;
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
/*
    给定一个包含红，白，蓝且长度为 n 的数组，将数组元素进行分类使相同颜色的元素相邻，并按照红、白、蓝的顺序进行排序。

    我们可以使用整数 0，1 和 2 分别代表红，白，蓝。

     注意事项

    不能使用代码库中的排序函数来解决这个问题。
    排序需要在原数组中进行。

    样例
    给你数组 [1, 0, 1, 2], 需要将该数组原地排序为 [0, 1, 1, 2]。

    挑战
    一个相当直接的解决方案是使用计数排序扫描2遍的算法。

    首先，迭代数组计算 0,1,2 出现的次数，然后依次用 0,1,2 出现的次数去覆盖数组。

    你否能想出一个仅使用常数级额外空间复杂度且只扫描遍历一遍数组的算法？
 */


////////////////////////////////////////////////////////////////////////

    // 参考程序2

    public void partition2(int[] nums, int low, int high) {
        // Write your code here
        int left = 0;
        int right = nums.length - 1;

        // 首先把区间分为 < low 和 >= low 的两个部分
        while(left <= right) {
            while(left <= right && nums[left] < low) {
                left ++;
            }
            while(left <= right && nums[right] >= low) {
                right --;
            }

            if(left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left ++;
                right --;
            }
        }

        // 然后从 >= low 的部分里分出 <= high 和 > high 的两个部分
        right = nums.length - 1;
        while(left <= right) {
            while(left <= right && nums[left] <= high) {
                left ++;
            }
            while(left <= right && nums[right] > high) {
                right --;
            }
            if(left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left ++;
                right --;
            }
        }
    }

////////////////////////////////////////////////////////////////////////


}

/*
[LintCode] 625 Partition Array II 解题报告
Description
Partition an unsorted integer array into three parts:

The front part < low
The middle part >= low & <= high
The tail part > high
Return any of the possible solutions.


Notice
low <= high in all testcases.



Example
Given [4,3,4,1,2,3,1,2], and low = 2 and high = 3.

Change to [1,1,2,3,2,3,4,4].

([1,1,2,2,3,3,4,4] is also a correct answer, but [1,2,1,2,3,3,4,4] is not)



Challenge
Do it in place.
Do it in one pass (one loop).



思路
标准的3-way-partition。判断的时候不是只有一个v，而是有low和high而已。
 */