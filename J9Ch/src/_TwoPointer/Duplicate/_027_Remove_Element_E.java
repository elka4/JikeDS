package _TwoPointer.Duplicate;
import org.junit.Test;

//  27. Remove Element
//  https://leetcode.com/problems/remove-element/description/
//  http://www.lintcode.com/en/problem/remove-element/
//  Array, Two Pointers
//  _026_TwoPointer_Remove_Duplicates_from_Sorted_Array_E
//  _203_List_Remove_Linked_List_Elements_E
//  _283_TwoPointer_Move_Zeroes_E
//  4:1
//  双前向指针
//给定一个数组和一个值，在原地删除与值相同的数字，返回新数组的长度。
//元素的顺序可以改变，并且对新的数组不会有影响。
public class _027_Remove_Element_E {
//------------------------------------------------------------------------------

    //https://leetcode.com/problems/remove-element/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 (Two Pointers) [Accepted]
    //按顺序一个一个检查元素，如果不等于目标val，就把这个元素赋给nums[size]， size++
    public int removeElement(int[] nums, int val) {
        int size = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[size] = nums[i];
                size++;
            }
        }
        return size;
    }
    //按顺序一个一个检查元素，如果不等于nums[size]，就把这个元素赋给nums[++size]
    public class removeDuplicates_9CH {
        public int removeDuplicates(int[] nums) {
            int size = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != nums[size]) {
                    nums[++size] = nums[i];
                }
            }
            return size + 1;
        }
    }
//------------------------------------------------------------------------------

    //for each 重写上面
    public int removeElement0(int[] nums, int val) {
        int size = 0;
        for (int n:nums) {
            if (n != val) {
                nums[size++] = n;
            }
        }
        return size;
    }

    public class removeDuplicates_9CH2 {
        public int removeDuplicates(int[] nums) {
            int size = 0;
            for (int n:nums) {
                if (n != nums[size]) {
                    nums[++size] = n;
                }
            }
            return size + 1;
        }
    }


    @Test
    public void test01(){
        int[] nums = {0,4,4,0,0,2,4,4};
        System.out.println(removeElement(nums, 4));
        for (int i:nums) {
            System.out.print(i + " ");
        }
    }
    /*
    4
    0 0 0 2 0 2 4 4
     */
//------------------------------------------------------------------------------
    //2
    //Approach #2 (Two Pointers - when elements to remove are rare) [Accepted]
    public int removeElement2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }

    @Test
    public void test02(){
        int[] nums = {0,4,4,0,0,2,4,4};
        System.out.println(removeElement2(nums, 4));
        for (int i:nums) {
            System.out.print(i + " ");
        }
    }
    /*
    4
    0 2 0 0 0 2 4 4
     */
//------------------------------------------------------------------------------
    //3
    //My solution for your reference.
    int removeElement3(int A[], int n, int elem) {
        int begin=0;
        for(int i=0;i<n;i++)
            if(A[i]!=elem)
                A[begin++]=A[i];
        return begin;
    }
    @Test
    public void test03(){
        int[] nums = {0,4,4,0,0,2,4,4};
        System.out.println(removeElement3(nums, nums.length, 4));
        for (int i:nums
                ) {
            System.out.print(i + " ");
        }
    }
        /*
    4
    0 0 0 2 0 2 4 4
         */
//------------------------------------------------------------------------------
    //4
    // 9Ch
    public int removeElement4(int[] nums, int elem) {
        int i = 0;
        int pointer = nums.length - 1;
        while(i <= pointer){
            printArr(nums);
            if(nums[i] == elem){
                System.out.println("index: " + i + " | " + "pointer: " + pointer + " | " + "nums[i]: "+ nums[i]);
                nums[i] = nums[pointer];
                pointer--;
            } else {
                System.out.println("index: " + i + " | " + "pointer: " + pointer + " | " + "i++: " + i);
                i++;
            }
        }
        return pointer + 1;
    }

    private void printArr(int[] nums){
        System.out.print("nums[]: ");
        for (int i:nums
             ) {
            System.out.print(i + " ");
        }
        System.out.print(" | ");
    }

    @Test
    public void test04(){
        int[] nums = {0,4,4,0,0,2,4,4};
        System.out.println(removeElement4(nums, 4));
        for (int i:nums
             ) {
            System.out.print(i + " ");
        }
    }

/*                      0 1 2 3 4 5 6 7
                nums[]: 0 4 4 0 0 2 4 4  | index: 0 | pointer: 7 | i++: 0
                nums[]: 0 4 4 0 0 2 4 4  | index: 1 | pointer: 7 | nums[i]: 4
                nums[]: 0 4 4 0 0 2 4 4  | index: 1 | pointer: 6 | nums[i]: 4
                nums[]: 0 4 4 0 0 2 4 4  | index: 1 | pointer: 5 | nums[i]: 4
                nums[]: 0 2 4 0 0 2 4 4  | index: 1 | pointer: 4 | i++: 1
                nums[]: 0 2 4 0 0 2 4 4  | index: 2 | pointer: 4 | nums[i]: 4
                nums[]: 0 2 0 0 0 2 4 4  | index: 2 | pointer: 3 | i++: 2
                nums[]: 0 2 0 0 0 2 4 4  | index: 3 | pointer: 3 | i++: 3
                4
                0 2 0 0 0 2 4 4
 */
//------------------------------------------------------------------------------

}
/*
Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.
 */


/*
给定一个数组和一个值，在原地删除与值相同的数字，返回新数组的长度。
元素的顺序可以改变，并且对新的数组不会有影响。

样例
给出一个数组 [0,4,4,0,0,2,4,4]，和值 4

返回 4 并且4个元素的新数组为[0,0,0,2]
 */