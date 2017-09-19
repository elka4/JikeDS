package S_3_DataStructure2;

import java.util.ArrayList;
import java.util.Comparator;

public class sliding_window_median {
	
class MaxComparator implements Comparator<Integer> {
	public MaxComparator() {}
	public int compare(Integer a, Integer b) {
		return b - a;
	}
}

class MinComparator implements Comparator<Integer> {
	public MinComparator() {}
	public int compare(Integer a, Integer b) {
		return a - b;
	}
}

public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
	// write your code here
	ArrayList<Integer> result = new ArrayList<>();
	HashHeap2 min_heap = new HashHeap2(new MinComparator());
	HashHeap2 max_heap = new HashHeap2(new MaxComparator());
	for(int i=0; i<nums.length; i++) {
		if(i >= k) {
			result.add(max_heap.peek());
			int obsolete = nums[i-k];
			if(max_heap.contains(obsolete)) max_heap.remove(obsolete);
			else min_heap.remove(obsolete);
		}
		if(max_heap.isEmpty() && min_heap.isEmpty()) max_heap.add(nums[i]);
		else if(!max_heap.isEmpty()) {
			if(nums[i] > max_heap.peek()) min_heap.add(nums[i]);
			else max_heap.add(nums[i]);
		} else {
			if(nums[i] < min_heap.peek()) max_heap.add(nums[i]);
			else min_heap.add(nums[i]);
		}
		if(max_heap.size() > min_heap.size() + 1) min_heap.add(max_heap.poll());
		if(min_heap.size() > max_heap.size()) max_heap.add(min_heap.poll());
	}
	if(!max_heap.isEmpty()) result.add(max_heap.peek());
	return result;
}
}