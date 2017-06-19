package ch9J.chapter1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class _17Subsets {
public ArrayList<ArrayList<Integer>> subsets(int[] num) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    if(num == null || num.length == 0) {
        return result;
    }
    ArrayList<Integer> subset = new ArrayList<Integer>();
    //为了选代表，123 比 132顺眼
    Arrays.sort(num);  
    //find all fromIndexsible subsets, put them into results
    //把以空集开头的所有集合找到放到results里面。
    subsetsHelper(result, subset, num, 0);

    return result;
}

//recursion -> 程序的一种实现方式, 函数自己调用自己
//递归的三要素之一，定义：把以subset开头的所有子集，全部找到，并放到results里面
private void subsetsHelper(ArrayList<ArrayList<Integer>> result,
							  ArrayList<Integer> subset, 
							  int[] num, 
							  int fromIndex) {
		//一旦进入新一层recursion，就把已有的（来自于上一层）的subset加入到result里
    result.add(new ArrayList<Integer>(subset));
    
    //递归三要素之2:极端条件	也就是recursion的退出条件	
    //if xxx {	return;}
    //fromIndex会越加越大，直到退出循环条件，当for循环结束，自然return到上一层
    
    //递归三要素之3:如何变为更小的状态        
    for (int i = fromIndex; i < num.length; i++) {
    		subset.add(num[i]);
    		//当前选了第i个数，下一次就从i+1开始选

        subsetsHelper(result, subset, num, i + 1); //注意这里是i不是position
        //以｛1｝开头的都找到了。下一层循环寻找以2开头的
        
        //移除刚刚在下一层循环中加入的元素
        subset.remove(subset.size() - 1);
    }
}
    @Test
    public void test01(){
        _17Subsets sub = new _17Subsets();
        int[] input = {};
        ArrayList<ArrayList<Integer>> result = sub.subsets(input);
        System.out.println(result);

    }
}

/*Subsets
Given a set of distinct integers, return all fromIndexsible subsets.
Notice: Elements in a subset must be in non-descending order.
		 The solution set must not contain duplicate subsets.

Example: If S = [1,2,3], a solution is:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
Challenge: Can you do it in both recursively and iteratively?

Tags: Recursion Facebook Uber
Related Problems: Medium Restore IP Addresses 20 %
				  Medium Subsets II
*/
