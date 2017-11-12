package _05_DFS._Back_Subset_Permutation_Combination;
import org.junit.Test;

import java.util.*;

//I:   C中的数字可以无限制重复被选取
//II:  C中每个数字在每个组合中只能使用一次。
//III: k numbers that add up to a number n, given that only numbers from 1 to 9 can be used
public class Combination_Sum_I_II_III {
    //Combination Sum I, II and III Java solution (see the similarities yourself)
    //  https://discuss.leetcode.com/topic/44037/combination-sum-i-ii-and-iii-java-solution-see-the-similarities-yourself

    //Combination Sum I
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<Integer>(), candidates, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList,
                           int[] candidates, int remain, int start) {
        if (remain < 0) return; /** no solution */
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for (int i = start; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                backtrack(list, tempList, candidates, remain - candidates[i], i);
                tempList.remove(tempList.size()-1);
            }
        }
    }

    @Test
    public void test01(){
        int[] candidates = {2,3,6,7};
        int t = 7;
        System.out.println(combinationSum(candidates, t));
    }//[[2, 2, 3], [7]]


///////////////////////////////////////////////////////////////////////////////////////////
    //Combination Sum II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        Arrays.sort(candidates);
        backtrack2(list, new ArrayList<Integer>(), candidates, target, 0);
        return list;
    }

    private void backtrack2(List<List<Integer>> list, List<Integer> tempList,
                           int[] cand, int remain, int start) {

        if(remain < 0) return; /** no solution */
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for (int i = start; i < cand.length; i++) {
                if(i > start && cand[i] == cand[i-1]) continue; /** skip duplicates */
                tempList.add(cand[i]);
                backtrack2(list, tempList, cand, remain - cand[i], i+1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    @Test
    public void test02(){
        int[] candidates = {10,1,6,7,2,1,5};
        int t = 8;
        System.out.println(combinationSum2(candidates, t));
    }//[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]

///////////////////////////////////////////////////////////////////////////////////////////
    //Combination Sum III
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<Integer>(), k, n, 1);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList,
                           int k, int remain, int start) {
        if(tempList.size() > k) return; /** no solution */
        else if(tempList.size() == k && remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for (int i = start; i <= 9; i++) {
                tempList.add(i);
                backtrack(list, tempList, k, remain-i, i+1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    @Test
    public void test03(){
        System.out.println(combinationSum3(3, 9));
    }//[[1, 2, 6], [1, 3, 5], [2, 3, 4]]

///////////////////////////////////////////////////////////////////////////////////////////
}
