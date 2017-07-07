package chapter8_DataStructure;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _18Data_Stream_Median {
	/**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    private PriorityQueue<Integer> maxHeap, minHeap;
	private int numOfElements = 0;

	public int[] medianII(int[] nums) {
        // write your code here
		Comparator<Integer> revCmp = new Comparator<Integer>() {
			@Override
			public int compare(Integer left, Integer right) {
				return right.compareTo(left);
			}
		};
        int cnt = nums.length;
		maxHeap = new PriorityQueue<Integer>(cnt, revCmp);
		minHeap = new PriorityQueue<Integer>(cnt);
        int[] ans = new int[cnt];
        for (int i = 0; i < cnt; ++i) {
		    addNumber(nums[i]);   
		    ans[i] = getMedian();
		}
        return ans;
	}
	void addNumber(int value) {
		maxHeap.add(value);
		if (numOfElements%2 == 0) {
			if (minHeap.isEmpty()) {
				numOfElements++;
				return;
			}
			else if (maxHeap.peek() > minHeap.peek()) {
				Integer maxHeapRoot = maxHeap.poll();
				Integer minHeapRoot = minHeap.poll();
				maxHeap.add(minHeapRoot);
				minHeap.add(maxHeapRoot);
			} 
		} 
		else {
			minHeap.add(maxHeap.poll());
		}
		numOfElements++;
	}
	int getMedian() {
		return maxHeap.peek();
	}
}

/*Numbers keep coming, return the median of numbers at every 
 * time a new number added.
 

Have you met this question in a real interview? Yes
Clarification
What's the definition of Median?
- Median is the number that in the middle of a sorted array. 
If there are n numbers in a sorted array A, 
the median is A[(n - 1) / 2]. 
For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.

Example
For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].

For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].

For numbers coming list: [2, 20, 100], return [2, 2, 20].

Challenge 
Total run time in O(nlogn).

Tags 
LintCode Copyright Heap Priority Queue Google
Related Problems 
Easy Median 22 %
Hard Median of two Sorted Arrays 22 %*/