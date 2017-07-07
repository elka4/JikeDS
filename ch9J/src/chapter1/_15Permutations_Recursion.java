package chapter1;

import java.util.ArrayList;

public class _15Permutations_Recursion {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<>();
        //if the question expect [[]] when num is empty, 
        //only use if (num == null)
        if (num == null || num.length == 0) {
            return rst; 
        }

        ArrayList<Integer> list = new ArrayList<>();
        helper(rst, list, num);
        return rst;
   }
   
   public void helper(ArrayList<ArrayList<Integer>> rst, 
		   			 ArrayList<Integer> list, 
		   			 int[] num){
       if(list.size() == num.length) {
           rst.add(new ArrayList<>(list));
           return;
       }
       
       for(int i = 0; i<num.length; i++){
           if(list.contains(num[i])){
               continue;
           }
           list.add(num[i]);
           helper(rst, list, num);
           list.remove(list.size() - 1);
       }
   }
}

/*
 * Given a list of numbers, return all possible permutations.
Example：For nums = [1,2,3], the permutations are:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
Challenge：Do it without recursion.
Tags： LinkedIn Recursion
Related Problems： 
				Medium Print Numbers by Recursion 24 %
				Medium Permutation Sequence 27 %
				Medium Permutations II 24 %
*/
