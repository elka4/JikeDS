package _3Graph_PreClass;
import org.junit.Test;

import java.util.*;
/**
 * Created by tianhuizhu on 6/25/17.
 */
public class _1_Backtracking_Permutation_0 {
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0){
            return res;
        }
        res.add(new ArrayList<Integer>());
        for(int i = 0; i < nums.length; i++){
            List<List<Integer>> nextRes = new ArrayList<List<Integer>>();
            for (List<Integer> list : res){ //for each list in res
                for (int j = 0; j < list.size() + 1; j++) {
                    //copy a list to nextList
                    List<Integer> nextList = new ArrayList<Integer>(list);
                    nextList.add(j, nums[i]);
                    nextRes.add(nextList);
                }
            }
            res = nextRes;// move to next level
        }
        return res;
    }

    @Test
    public void test01 (){
        int[] input = {1, 2 , 3};
        System.out.println(permute(input));
    }
}
