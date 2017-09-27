package HF.HF3_Algo_DS_II_1BinarySearch;

//Maximum Number in Mountain Sequence
public class _4MaximumNumberinMountainSequence {
    // version 1：二分法
    /**
     * @param nums a mountain sequence which increase firstly and then decrease
     * @return then mountain top
     */
    public int mountainSequence(int[] nums) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return Math.max(nums[start], nums[end]);
    }


////////////////////////////////////////////////////////////////////////////////

    // version 2: 一个比较啰嗦的版本，本质也是2分法，每次取两个点
    /**
     * @param nums a mountain sequence which increase firstly and then decrease
     * @return then mountain top
     */
    public int mountainSequence2(int[] nums) {
        // Write your code here
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int m1 = left + (right - left) / 2;
            int m2 = right - (right - m1) / 2;
            if (nums[m1] < nums[m2]) {
                left = m1 + 1;
            } else if (nums[m1] > nums[m2]) {
                right = m2 - 1;
            } else {
                left = m1;
                right = m2;
            }
        }
        return nums[left] > nums[right] ? nums[left] : nums[right];
    }


////////////////////////////////////////////////////////////////////////////////
}
/*
Given a mountain sequence of n integers which increase firstly and then decrease, find the mountain top.

 */