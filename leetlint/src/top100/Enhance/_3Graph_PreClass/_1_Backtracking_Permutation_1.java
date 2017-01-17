package top100.Enhance._3Graph_PreClass;

import java.util.*;

public class _1_Backtracking_Permutation_1 {
public List<List <Integer>> permute(int[] num) {
    //Corner Case Checked
List <List<Integer>> result = new ArrayList <List<Integer>>();
    List <Integer> list = new ArrayList <Integer>();
    helper(num, list, result);
    return result;
}
public void helper(int num[], List<Integer> list, List<List<Integer>> result){
    if(list.size() == num.length){
        result.add(new ArrayList <Integer>(list));
        return;
    }

    for(int i = 0; i  < num.length; i++){//For each elem
        if(!list.contains(num[i])){//Position left == nums left(No duplicates)
            list.add(num[i]);
            helper(num, list, result);//Next Position
            list.remove(list.size()-1);//Empty last position for next iteration
        }
    }
}
}
