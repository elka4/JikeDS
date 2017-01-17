package top100.Enhance._3Graph_Class;
import java.util.*;
public class _1_FactorCombination_1 {
	public List<List<Integer>> getFactors (int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		helper(res, list, n, 2);
		return res;
	}
	
	private void helper (List<List<Integer>> res, List<Integer> list,
				int n, int startFactor) {
		if (n == 1) {
			if (list.size() > 1) {
				res.add(new ArrayList<Integer>(list));
			}
			return;
		}
		for (int i = startFactor; i <= n; i++) {
			if (n % i == 0) {
				list.add(i);
				//start from last startFactor
				helper(res, list, n/i, i);
				list.remove(list.size() - 1);
			}
		}
		
	}
}
