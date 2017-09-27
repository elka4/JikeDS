package J_8_DS.Optional_6;

import java.util.*;
/** 545 Top k Largest Numbers II
 * Created by tianhuizhu on 6/28/17.
 */
public class _545_Top_k_Largest_Numbers_II {

    public class Solution {
        private int maxSize;
        private Queue<Integer> minheap;
        public Solution(int k) {
            minheap = new PriorityQueue<>();
            maxSize = k;
        }

        public void add(int num) {
            if (minheap.size() < maxSize) {
                minheap.offer(num);
                return;
            }

            if (num > minheap.peek()) {
                minheap.poll();
                minheap.offer(num);
            }
        }

        public List<Integer> topk() {
            Iterator it = minheap.iterator();
            List<Integer> result = new ArrayList<Integer>();
            while (it.hasNext()) {
                result.add((Integer) it.next());
            }
            Collections.sort(result, Collections.reverseOrder());
            return result;
        }
    }
}
