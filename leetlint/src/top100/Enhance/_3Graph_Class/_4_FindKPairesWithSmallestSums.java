package top100.Enhance._3Graph_Class;

import java.util.*;

public class _4_FindKPairesWithSmallestSums {
	class Sum {
		int sum;
		int a;
		int b;
		public Sum(int sum, int a, int b) {
			this.sum = sum;
			this.a = a;
			this.b = b;
		}
	}
	
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<int[]> list = new ArrayList<int[]>();
		if (nums1 == null || nums2 == null ||
				nums1.length == 0 || nums2.length == 0 || k <= 0) {
			return list;
		}
		PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(
				new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					int sum1 = nums1[o1[0]] + nums2[o1[1]];
					int sum2 = nums1[o2[0]] + nums2[o2[1]];
					if (sum1 == sum2) {
						return 0;
					} else {
					return sum1 < sum2 ? -1 : 1;
					}
				}});
		for (int i = 0; i < nums1.length; i++) {
			minHeap.add(new int[]{i, 0});
		}
		while (!minHeap.isEmpty() && k > 0) {
			int[] cur = minHeap.poll();
			list.add(new int[]{nums1[cur[0]], nums2[cur[1]]});
			if(++cur[1] < nums2.length) {
				minHeap.add(cur);
			}
			k--;
		}
		return list;
	}
}
