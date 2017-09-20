package J_2_Binary_Search.Related_8;

/**
457
Classical Binary Search
 * Created by tianhuizhu on 6/28/17.
 */
public class _457_Classical_Binary_Search {
    // version 1: with jiuzhang template
    public class Solution1 {
        /**
         * @param nums an integer array sorted in ascending order
         * @param target an integer
         * @return an integer
         */
        public int findPosition(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int start = 0, end = nums.length - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (nums[start] == target) {
                return start;
            }
            if (nums[end] == target) {
                return end;
            }
            return -1;
        }
    }

    // version 2: without jiuzhang template
    public class Solution2 {
        /**
         * @param nums an integer array sorted in ascending order
         * @param target an integer
         * @return an integer
         */
        public int findPosition(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int start = 0, end = nums.length - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            if (nums[start] == target) {
                return start;
            }
            return -1;
        }
    }
}
