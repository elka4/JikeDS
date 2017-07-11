package S_4_Binary_Search_Sweep_Line.Optional_3;
import java.util.*;
/** 633 Find the Duplicate Number


 * Created by tianhuizhu on 6/28/17.
 */
public class _633_Find_the_Duplicate_Number {

    // 二分法
    public class Solution1 {
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
    }

    // 映射法
    public class Solution2 {
        /**
         * @param nums an array containing n + 1 integers which is between 1 and n
         * @return the duplicate one
         */
        public int findDuplicate(int[] nums) {
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
}
