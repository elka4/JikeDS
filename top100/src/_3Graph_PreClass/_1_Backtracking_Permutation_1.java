package _3Graph_PreClass;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _1_Backtracking_Permutation_1 {
    private List<List <Integer>> permute(int[] num) {
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
    @Test
    public void test01 (){
        int[] input = {1, 2 , 3};
        System.out.println(permute(input));
    }
}
