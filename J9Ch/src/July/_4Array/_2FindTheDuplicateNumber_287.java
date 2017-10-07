package July._4Array;

import org.junit.Test;

public class _2FindTheDuplicateNumber_287 {
    // 二分法
    /**
     * @param nums an array containing n + 1 integers which is between 1 and n
     * @return the duplicate one
     */
    public int findDuplicate(int[] nums) {
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

    @Test
    public void test01(){
        int[] nums1 = new int[]{5,5,4,3,2,1};
        int[] nums2 = new int[]{5,4,4,3,2,1};
        System.out.println(findDuplicate(nums1));
        System.out.println(findDuplicate(nums2));

    }
///////////////////////////////////////////////////////////////////////////////////////////
    // 映射法
    /**
     * @param nums an array containing n + 1 integers which is between 1 and n
     * @return the duplicate one
     */
    public int findDuplicate2(int[] nums) {
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
///////////////////////////////////////////////////////////////////////////////////////////
}
/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 注意事项

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.
您在真实的面试中是否遇到过这个题？ Yes
样例
Given nums = [5,5,4,3,2,1] return 5
Given nums = [5,4,4,3,2,1] return 4
 */