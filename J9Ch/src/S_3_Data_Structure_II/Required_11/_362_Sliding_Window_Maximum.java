package S_3_Data_Structure_II.Required_11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
/** 362 Sliding Window Maximum


 * Created by tianhuizhu on 6/28/17.
 */
public class _362_Sliding_Window_Maximum {

    public class Solution {

        /**
         * @param nums: A list of integers.
         * @return: The maximum number inside the window at each moving.
         */
        void inQueue(Deque<Integer> deque, int num) {
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.pollLast();
            }
            deque.offer(num);
        }

        void outQueue(Deque<Integer> deque, int num) {
            if (deque.peekFirst() == num) {
                deque.pollFirst();
            }
        }

        public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
            // write your code here
            ArrayList<Integer> ans = new ArrayList<Integer>();
            Deque<Integer> deque = new ArrayDeque<Integer>();
            if (nums.length == 0) {
                return ans;
            }
            for (int i = 0; i < k - 1; i++) {
                inQueue(deque, nums[i]);
            }

            for(int i = k - 1; i < nums.length; i++) {
                inQueue(deque, nums[i]);
                ans.add(deque.peekFirst());
                outQueue(deque, nums[i - k + 1]);
            }
            return ans;

        }
    }
}
