package top100.HighFreq._3Graph;

import java.util.LinkedList;

public class _70Combination {
	int result;
	class Traveler{
		int result;
		public Traveler() {
			result = 0;
		}
	}
	void travel(int[] nums, int pos, int sum, LinkedList<Integer> trip) {
		if(sum == 0) {
			trip.forEach((value) -> System.out.println(value + " "));
			System.out.println();
			++result;
		}
		if(pos == nums.length) return;
		for (int i = 0; i < nums.length; ++i) {
			if(i > pos && nums[i] == nums[i - 1]) continue;
			trip.add(nums[i]);
			travel(nums, i + 1, sum + nums[i], trip);
			trip.removeLast();
		}
	}
}
//WHAT if there is duplicate?