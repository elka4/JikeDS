package _01_Array.Other_dbf_bfs;

import java.util.ArrayList;
import java.util.List;
/*
LeetCode – Factor Combinations (Java)

Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
 */
public class Factor_Combinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        helper(2, 1, n, result, list);
        return result;
    }

    public void helper(int start, int product, int n, List<List<Integer>> result, List<Integer> curr){
        if(start>n || product > n )
            return ;

        if(product==n) {
            ArrayList<Integer> t = new ArrayList<Integer>(curr);
            result.add(t);
            return;
        }

        for(int i=start; i<n; i++){
            if(i*product>n)
                break;

            if(n%i==0){
                curr.add(i);
                helper(i, i*product, n, result, curr);
                curr.remove(curr.size()-1);
            }
        }
    }


////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






}
