package J_8_Data_Structure.Related_7;
import java.util.*;
/** 606 Kth Largest Element II


 * Created by tianhuizhu on 6/28/17.
 */
public class _606_Kth_Largest_Element_II_Medium {

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
    }
}