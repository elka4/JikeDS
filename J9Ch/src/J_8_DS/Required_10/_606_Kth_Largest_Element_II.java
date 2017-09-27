package J_8_DS.Required_10;

import java.util.PriorityQueue;
/** 606 Kth Largest Element II
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _606_Kth_Largest_Element_II {

    class Solution {
        /**
         * @param nums an integer unsorted array
         * @param k an integer from 1 to n
         * @return the kth largest element
         */
        public int kthLargestElement2(int[] nums, int k) {
            // Write your code here
            PriorityQueue<Integer> q = new PriorityQueue<Integer>(k);
            for (int num : nums) {
                q.offer(num);
                if (q.size() > k) {
                    q.poll();
                }
            }
            return q.peek();
        }
    };
}
