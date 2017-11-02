package j_2_BinarySearch; import org.junit.Test;

import org.junit.Test;

/**
 * Created by tianhuizhu on 6/18/17.
 */
public class _585Maximum_Number_in_Mountain_Sequence {

    // version 1：二分法
    public class _Maximum_Number_in_Mountain_Sequence1 {
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
    }

    // version 2: 一个比较啰嗦的版本，本质也是2分法，每次取两个点
    public class _Maximum_Number_in_Mountain_Sequence2 {
        /**
         * @param nums a mountain sequence which increase firstly and then decrease
         * @return then mountain top
         */
        public int mountainSequence(int[] nums) {
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
    }

    public int mountainSequence_mine1(int[] nums) {
        // Write your code here
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid - 1] < nums[mid]) {
                start = mid;
            } else if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                end =  mid;
            }
        }
        return Math.max(nums[start], nums[end]);
    }

    public int mountainSequence_mine2(int[] nums) {
        // Write your code here
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid - 1] < nums[mid]) {
                start = mid;
            }
            if (nums[mid + 1] < nums[mid]){
                end = mid;
            }
        }
        return Math.max(nums[start], nums[end]);
    }

    @Test
    public void test01(){
        int[] input1 = {1, 2, 4, 8, 6, 3};
        int[] input2 = {10, 9, 8, 7};

        System.out.println(mountainSequence_mine1(input1));
        System.out.println(mountainSequence_mine1(input2));
    }
}
