package j_7_Array;

import java.util.ArrayList;
import java.util.Arrays;

public class _183Sum {
public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		
ArrayList<ArrayList<Integer>> rst = new ArrayList<>();
if(num == null || num.length < 3) {
	return rst;
}
Arrays.sort(num);
for (int i = 0; i < num.length - 2; i++) {
	if (i != 0 && num[i] == num[i - 1]) {
		continue; // to skip duplicate numbers; e.g [0,0,0,0]
	}

	int left = i + 1;
	int right = num.length - 1;
	while (left < right) {
		int sum = num[left] + num[right] + num[i];
		if (sum == 0) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(num[i]);
			tmp.add(num[left]);
			tmp.add(num[right]);
			rst.add(tmp);
			left++;
			right--;
			// to skip duplicates
			while (left < right && num[left] == num[left - 1]) { 
				left++;
			}
			// to skip duplicates
			while (left < right && num[right] == num[right + 1]) { 
				right--;
			}
		} else if (sum < 0) {
			left++;
		} else {
			right--;
		}
	}
}
return rst;
}
}

/*Given an array S of n integers, are there elements a, b, c in
 *  S such that a + b + c = 0? Find all unique triplets in the array 
 *  which gives the sum of zero.
 

 Notice

Elements in a triplet (a,b,c) must be in non-descending order. 
(ie, a ? b ? c)

The solution set must not contain duplicate triplets.

Have you met this question in a real interview? Yes
Example
For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:

(-1, 0, 1)
(-1, -1, 2)
Tags 
Two Pointers Sort Array Facebook
Related Problems 
Medium 3Sum Closest 30 %
Medium 4Sum 20 %
Medium Two Sum 28 %*/