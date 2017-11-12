package _05_DFS._Back_Subset_Permutation_Combination;
import org.junit.Test;

import java.util.*;

//  216. Combination Sum III
//  https://leetcode.com/problems/combination-sum-iii/description/
//
public class _216_BackTracking_Combination_Sum_III_M {

    //Combination Sum I, II and III Java solution (see the similarities yourself)
    //  https://discuss.leetcode.com/topic/44037/combination-sum-i-ii-and-iii-java-solution-see-the-similarities-yourself

    public List<List<Integer>> combinationSum(int k, int n) {
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
    public void test01(){
        System.out.println(combinationSum(3, 9));
    }//[[1, 2, 6], [1, 3, 5], [2, 3, 4]]


    ///////////////////////////////////////////////////////////////////////////////////////////
        //Simple and clean Java code, backtracking.
        public List<List<Integer>> combinationSum1(int k, int n) {
            List<List<Integer>> ans = new ArrayList<>();
            combination(ans, new ArrayList<Integer>(), k, 1, n);
            return ans;
        }

        private void combination(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
            if (comb.size() == k && n == 0) {
                List<Integer> li = new ArrayList<Integer>(comb);
                ans.add(li);
                return;
            }
            for (int i = start; i <= 9; i++) {
                comb.add(i);
                combination(ans, comb, k, i+1, n-i);
                comb.remove(comb.size() - 1);
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////
        // To make this faster, you can quit the for loop early and avoid unnecessary work.
        private void combination2(List<List<Integer>> ans, List<Integer> comb,
                                  int k,  int start, int n) {
            if (comb.size() > k) {
                return;
            }
            if (comb.size() == k && n == 0) {
                List<Integer> li = new ArrayList<Integer>(comb);
                ans.add(li);
                return;
            }
            for (int i = start; i <= n && i<=9; i++) {

                comb.add(i);
                combination2(ans, comb, k, i+1, n-i);
                comb.remove(comb.size() - 1);

            }
        }

///////////////////////////////////////////////////////////////////////////////////////////
        //if you think for loop with multiple conditions is complex,
        // you can add an if condition inside the for loop instead.
        private void combination3(List<List<Integer>> ans, List<Integer> comb, int k,
                                  int start, int n) {
            if (comb.size() > k) {
                return;
            }
            if (comb.size() == k && n == 0) {
                List<Integer> li = new ArrayList<Integer>(comb);
                ans.add(li);
                return;
            }
            for (int i = start; i<=9; i++) {
                if (n-i >= 0) {
                    comb.add(i);
                    combination3(ans, comb, k, i+1, n-i);
                    comb.remove(comb.size() - 1);
                }

            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////
//    Fast, easy Java code, with explanation!
//
//            19
//    S SmartFingers
//    Reputation:  58
//    Used backtracking to solve this.
//    Build an array to apply to "subset" template. Each time we add an element to the "list", for the next step, target= target - num[i]. Since we have already added one element, for the next step, we can only add k-1 elements. Since no duplicated elements accept, for the next loop, the "start" should point to the next index of current index. The list.remove(list.size() - 1) here means, we need to change the element here. I know it is hard to understand it, let me give you an example.
//    When k=3, n=9, my answer works like this:
//            [1]->[1,2]->[1,2,3]. Since now sum is not 9, no more backtracking, so after list.remove(list.size() - 1), it is [1,2]. Then next follows [1,2,4], sum is not 9, repeat process above untill [1,2,6]. When go to next backtracking, the list will be added to result, and for this list, no more backtracking.
//    Now we can go back to a previous backtracking, which is [1,3]->[1,3,4], fail. [1,4,]->[1,4,5], fail. And so one.
//    So the point of list.remove(list.size() - 1) is, after each "fail" or "success", since we don't need to do further attempts given such a condition, we delete the last element, and then end current backtracking. Next step is, add the next element to the deleted index, go on attempting.
//
//    If you have other questions, just reply me.

    public List<List<Integer>> combinationSum4(int k, int n) {
        int[] num = {1,2,3,4,5,6,7,8,9};
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, new ArrayList<Integer>(), num, k, n,0);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> list, int[] num, int k, int target, int start){
        if (k == 0 && target == 0){
            result.add(new ArrayList<Integer>(list));
        } else {
            for (int i = start; i < num.length && target > 0 && k >0; i++){
                list.add(num[i]);
                helper(result, list, num, k-1,target-num[i],i+1);
                list.remove(list.size()-1);
            }
        }
    }


///////////////////////////////////////////////////////////////////////////////////////////////

/*    Accepted recursive Java solution, easy to understand

    The idea is to choose proper number for 1,2..kth position in ascending order, and for each position, we only iterate through (prev_num, n/k]. Time comlexity O(k)*/

    public List<List<Integer>> combinationSum5(int k, int n) {
        List<List<Integer>> res = new  ArrayList<List<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        dfs(res, tmp ,k , n, 1);
        return res;
    }
    public void dfs( List<List<Integer>> res, List<Integer> tmp, int k, int n, int pos) {
        if (k == 0) {
            if (n == 0)
                res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = pos; i <= n / k && i < 10; i++) {
            tmp.add(i);
            dfs(res, tmp, k - 1, n - i, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> combinationSum6(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        rec(k, n, 1, new ArrayList<Integer>(), res);
        return res;
    }

    public void rec(int kLeft, int nLeft, int startNum, List<Integer> list, List<List<Integer>> res) {
        if(kLeft == 0) {
            if(nLeft == 0)
                res.add(list);
            return;
        }

        for(int i = startNum; i <= 9; i++) {
            List<Integer> newList = new ArrayList<Integer>(list);
            newList.add(i);
            rec(kLeft - 1, nLeft - i, i + 1, newList, res);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////
}
/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers
 from 1 to 9 can be used and each combination should be a unique set of numbers.

Example 1:
Input: k = 3, n = 7
Output:
[[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output:
[[1,2,6], [1,3,5], [2,3,4]]
 */