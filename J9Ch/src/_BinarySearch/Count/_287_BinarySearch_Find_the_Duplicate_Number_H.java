package _BinarySearch.Count;

//  287. Find the Duplicate Number
//  https://leetcode.com/problems/find-the-duplicate-number/
//  http://lintcode.com/zh-cn/problem/find-the-duplicate-number/

//  二分法 两根指针 数组
public class _287_BinarySearch_Find_the_Duplicate_Number_H {
//    Java O(1)space using Binary-Search

    /*
    有些index和value是相等的。从重复元素开始不相等。
     */
    public int findDuplicate1(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low <= high) {
            int mid = (int) (low + (high - low) * 0.5);
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;
            }
            if (cnt <= mid) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
//-------------------------------------------------------------------------//////
    // 9Ch
    // 二分法
    public int findDuplicate_J1(int[] nums) {
        // Write your code here
        int start = 1;
        int end = nums.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (check_smaller_num(mid, nums) <= mid) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (check_smaller_num(start, nums) <= start) {
            return end;
        }
        return start;
    }

    public int check_smaller_num(int mid, int[] nums) {
        int cnt = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= mid){
                cnt++;
            }
        }
        return cnt;
    }

    // 映射法
    /**
     * @param nums an array containing n + 1 integers which is between 1 and n
     * @return the duplicate one
     */
    /*
    slow就是一个value， fast就是以这个value作为index所指向的value
    只要他们相等，就是index == value，
    一旦开始不想等，就是遇见了重复元素
     */
    public int findDuplicate_J2(int[] nums) {
        // Write your code here
        if (nums.length <= 1)
            return -1;

        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}

/*
给出一个数组 nums 包含 n + 1 个整数，每个整数是从 1 到 n (包括边界)，保证至少存在一个重复的整数。

假设只有一个重复的整数，找出这个重复的数。

 注意事项

1.不能修改数组(假设数组只能读)
2.只能用额外的O(1)的空间
3.时间复杂度小于O(n^2)
4.数组中只有一个重复的数，但可能重复超过一次

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 nums = [5,5,4,3,2,1]，返回 5.
给出 nums = [5,4,4,3,2,1]，返回 4.
 */


/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.

 */
