package _10_DS.Heap;
import java.util.*;

/** 545 Top k Largest Numbers II
 * Created by tianhuizhu on 6/28/17.
 */


/*
Implement a data structure, provide two interfaces:

add(number). Add a new number in the data structure.
topk(). Return the top k largest numbers in this data structure.
k is given when we create the data structure.
Have you met this question in a real interview? Yes
Example
s = new Solution(3);
>> create a new data structure.
s.add(3)
s.add(10)
s.topk()
>> return [10, 3]
s.add(1000)
s.add(-99)
s.topk()
>> return [1000, 10, 3]
s.add(4)
s.topk()
>> return [1000, 10, 4]
s.add(100)
s.topk()
>> return [1000, 100, 10]
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


//-----------------------------------------------------------------------------






//-----------------------------------------------------------------------------






//-----------------------------------------------------------------------------






//-----------------------------------------------------------------------------






}

