package _05_DFS._Back_Subset_Permutation_Combination;

import java.util.*;

public class backtracking_bit {

    // subset
    // Simple Java Solution Using Bit Operations
    public List<List<Integer>> subsets3(int[] nums) {
        int n = nums.length;
        List<List<Integer>> subsets = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, n); i++)
        {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++)
            {
                if (((1 << j) & i) != 0)
                    subset.add(nums[j]);
            }
            Collections.sort(subset);
            subsets.add(subset);
        }
        return subsets;
    }

    public List<List<Integer>> subsets33(int[] S) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        Arrays.sort(S);
        for (int i=0;i<1<<S.length;i++)
        {
            List<Integer> result =  new ArrayList<Integer>();
            for(int j=0;j<S.length;j++)
            {
                if ((i&(1<<j))!=0) result.add(S[j]);
            }
            results.add(result);
        }
        return results;
    }
///////////////////////////////////////////////////////////////////////////////

    //_2_Subsets_II

    //My solution using bit masks
    public List<List<Integer>> subsetsWithDup6(int[] num) {
        //Sort the input
        Arrays.sort(num);
        int numberSets = 1 << num.length;
        List<List<Integer>> solution = new LinkedList<>();

        for(int i = 0; i<numberSets; i++){

            List<Integer> subset = new LinkedList<Integer>();

            for(int j = 0; j< num.length; j++){
                if((i & (1 << j)) > 0){
                    subset.add(num[j]);
                }
            }

            if(!solution.contains(subset))
                solution.add(subset);
        }

        return solution;
    }

///////////////////////////////////////////////////////////////////////////////
    //Java solution using bit manipulation
    public List<List<Integer>> subsetsWithDup7(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> lists = new ArrayList<>();
        int subsetNum = 1<<num.length;

        for(int i=0;i<subsetNum;i++){
            List<Integer> list = new ArrayList<>();
            boolean illegal=false;
            for(int j=0;j<num.length;j++){
                if((i>>j&1)==1){
                    if(j>0&&num[j]==num[j-1]&&(i>>(j-1)&1)==0){
                        illegal=true;
                        break;
                    }else{
                        list.add(num[j]);
                    }
                }
            }
            if(!illegal){
                lists.add(list);
            }

        }
        return lists;
    }

///////////////////////////////////////////////////////////////////////////////

    //_3_Permutations
    //暂无

///////////////////////////////////////////////////////////////////////////////

    //_4_Permutations_II

///////////////////////////////////////////////////////////////////////////////

    //_5_Combination_Sum

///////////////////////////////////////////////////////////////////////////////

    //_6_Combination_Sum_II

///////////////////////////////////////////////////////////////////////////////

    //_7_Palindrome_Partitioning

///////////////////////////////////////////////////////////////////////////////

}
