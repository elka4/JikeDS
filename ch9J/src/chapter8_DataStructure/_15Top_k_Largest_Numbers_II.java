package ch9J.chapter8_DataStructure;

import java.util.*;

public class _15Top_k_Largest_Numbers_II {
	private int maxSize;
    private Queue<Integer> minheap;
    public _15Top_k_Largest_Numbers_II(int k) {
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
        Iterator<Integer> it = minheap.iterator();
        List<Integer> result = new ArrayList<Integer>();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
}

/*Implement a data structure, provide two interfaces:

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
Tags 
Heap Priority Queue
Related Problems 
Medium Top k Largest Numbers 28 %
Medium Top K Frequent Words 16 %*/