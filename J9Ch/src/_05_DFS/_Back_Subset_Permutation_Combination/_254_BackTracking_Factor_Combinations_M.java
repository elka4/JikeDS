package _05_DFS._Back_Subset_Permutation_Combination;
import org.junit.Test;

import java.util.*;

//  254. Factor Combinations
//  https://leetcode.com/problems/factor-combinations/description/
public class _254_BackTracking_Factor_Combinations_M {

//------------------------------------------------------------------------------
    //My Recursive DFS Java Solution
    public List<List<Integer>> getFactors1(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper1(result, new ArrayList<Integer>(), n, 2);
        return result;
    }

    public void helper1(List<List<Integer>> result, List<Integer> list, int n, int start){
        if (n <= 1) {
            if (list.size() > 1) {
                result.add(new ArrayList<Integer>(list));
            }
            return;
        }

        for (int i = start; i <= n; ++i) {
            if (n % i == 0) {
                list.add(i);
                helper1(result, list, n / i, i);
                list.remove(list.size() - 1);
            }
        }
    }
    @Test
    public void test01(){
        System.out.println(getFactors1(12));
    }//[[2, 2, 3], [2, 6], [3, 4]]


//------------------------------------------------------------------------------
    //A simple java solution
    public List<List<Integer>> getFactors2(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n <= 3) return result;
        helper(n, -1, result, new ArrayList<Integer>());
        return result;
    }

    public void helper(int n, int lower, List<List<Integer>> result, List<Integer> cur) {
        if (lower != -1) {
            cur.add(n);
            result.add(new ArrayList<Integer>(cur));
            cur.remove(cur.size() - 1);
        }
        int upper = (int) Math.sqrt(n);
        for (int i = Math.max(2, lower); i <= upper; ++i) {
            if (n % i == 0) {
                cur.add(i);
                helper(n / i, i, result, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
    @Test
    public void test02(){
        System.out.println(getFactors2(12));
    }//[[2, 2, 3], [2, 6], [3, 4]]

//------------------------------------------------------------------------------
   /* My short Java solution which Is EASY to understand
    Here is my JAVA solution which will print the result in the following way:
    e.g.
    given n = 24, output: [[2,12], [2,2,6], [2,2,2,3], [2,3,4], [3,8], [4,6]]*/

    public List<List<Integer>> getFactors3(int n) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(n <= 3)  return result;
        List<Integer> path = new LinkedList<Integer>();
        getFactors(2, n, path, result);
        return result;
    }

    private void getFactors(int start, int n, List<Integer> path, List<List<Integer>> result){
        for(int i = start; i <= Math.sqrt(n); i++){
            if(n % i == 0 && n/i >= i){  // The previous factor is no bigger than the next
                path.add(i);
                path.add(n/i);
                result.add(new LinkedList<Integer>(path));
                path.remove(path.size() - 1);
                getFactors(i, n/i, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
    @Test
    public void test03(){
        System.out.println(getFactors3(12));
    }//[[2, 6], [2, 2, 3], [3, 4]]

//------------------------------------------------------------------------------

/*    Very easy to understand 2ms java backtracking solution

8
    S stephaniehmx_java
    Reputation:  10
    The upper bound for the factors of n is (int)sqrt(n), and when you find one factor of n, then put the factor and its corresponding factor to a temp list, and add the temp list to the result list. Then we remove the larger factor from the temp list, and recursively do the larger factor from the smaller factor to upper bound for the same procedure.

            For example, n = 16. Let the variable i be from 2 to 4, when i = 2, then i is one factor of 16, and its corresponding factor is 8, so we add 2 and 8 to a temp list, then add the temp list to the result list. And remove 8 from the temp list, and recursively do 8 from 2 to 2 for the same procedure.

    The result should be:
            [2, 8]
            [2, 2, 4]
            [2, 2, 2, 2]
            [4, 4]*/

    public List<List<Integer>> getFactors4(int n) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(res, new ArrayList<Integer>(), 2, n);
        return res;
    }

    public void backTrack(List<List<Integer>> res, List<Integer> cur, int start, int n) {
        int upper = (int)Math.sqrt(n);
        for(int i = start; i <= upper; i++) {
            int factor = -1;
            if(n % i == 0) {
                factor = n/i;
            }
            if(factor != -1 && factor >= i) {
                cur.add(i);
                cur.add(factor);
                res.add(new ArrayList<Integer>(cur));

                cur.remove(cur.size()-1);
                backTrack(res, cur, i, factor);
                cur.remove(cur.size()-1);
            }
        }
    }
    @Test
    public void test04(){
        System.out.println(getFactors4(12));
    }//[[2, 6], [2, 2, 3], [3, 4]]

//------------------------------------------------------------------------------

    //Java 2ms easy to understand, short and sweet
    public List<List<Integer>> getFactors5(int n) {
        List<List<Integer>> ans  = new ArrayList<>();
        if (n < 4){
            return ans;
        }
        DFS(n, ans, 2, new ArrayList<Integer>());
        return ans;
    }

    private void DFS(int n, List<List<Integer>> ans, int factor, List<Integer> entry){
        int limit = (int)Math.sqrt(n);
        for(int x = factor; x<=limit; x++){
            if(n%x == 0){
                entry.add(x);
                DFS(n/x, ans, x, entry);
                entry.add(n/x);
                ans.add(new ArrayList<>(entry));
                entry.remove(entry.size()-1);
                entry.remove(entry.size()-1);
            }
        }
    }
    @Test
    public void test05(){
        System.out.println(getFactors5(12));
    }//[[2, 6], [2, 2, 3], [3, 4]]

//------------------------------------------------------------------------------
    //Simple Java Recursive Solution
    public List<List<Integer>> getFactors6(int n) {
        List<List<Integer>> result = new ArrayList<>();

        result = perform(n, result, new ArrayList<Integer>());
        return result;
    }

    private List<List<Integer>> perform(int n, List<List<Integer>> result, List<Integer> l){
        for(int i=2;i<n;i++){
            if(n % i == 0 && i <= n/i && (l.size() == 0 || l.get(l.size() - 1) <= i)){
                List<Integer> temp  = new ArrayList<Integer>(l);
                temp.add(i);
                result = perform(n/i, result, temp);
                temp.add(n/i);
                result.add(temp);
            }
        }
        return result;
    }
    @Test
    public void test06(){
        System.out.println(getFactors6(12));
    }//[[2, 6], [2, 2, 3], [3, 4]]

//------------------------------------------------------------------------------
}
/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples:
input: 1
output:
[]

input: 37
output:
[]

input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]

input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 */