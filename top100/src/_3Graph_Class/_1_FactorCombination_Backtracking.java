package _3Graph_Class;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class _1_FactorCombination_Backtracking {

	public List<List<Integer>> getFactors (int input) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();

		helper(result, list, input, 2);
		return result;
	}
	
	private void helper (List<List<Integer>> result, List<Integer> list,
				int input, int startFactor) {

	    //退出条件
		if (input == 1) {
			if (list.size() > 1) {
                result.add(new ArrayList<Integer>(list));
			}
			return;
		}

		for (int i = startFactor; i <= input; i++) {
		    //如果input可以整除i
			if (input % i == 0) {

				list.add(i);

				//start from last startFactor
                //因为允许相同的factor重复出现，下一层recursion从i开始
				helper(result, list, input/i, i);

				list.remove(list.size() - 1);
			}
		}
		
	}

///////////////////////////////////////////////////////////////////////


    //pruning
    public List<List<Integer>> getFactors2 (int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();

        helper2(res, list, n, 2);
        return res;
    }
    private void helper2(List<List<Integer>> res, List<Integer> list,
                        int n, int startFactor) {
        if (n == 1) {
            if (list.size() > 1) {
                res.add(new ArrayList<Integer>(list));
            }
            return;
        }

        for (int i = startFactor; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                list.add(i);
                helper2(res, list, n/i, i);
                list.remove(list.size() - 1);
            }
        }
        //base case
        list.add(n);
        helper2(res, list, 1, n);
        list.remove(list.size() - 1);

    }


	@Test
	public void test01(){
        System.out.println(getFactors(18));
    }
}
