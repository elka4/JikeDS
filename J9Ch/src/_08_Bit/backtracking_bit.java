package _08_Bit;

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
    //11
    //bit
/*Clean Java, 3 ms using bit-manipulation with explanation

2
    A anton15
    Reputation:  120
    We know that the number of permutations is n!. Hence, the maximum number of elements we can handle without overflowing the integer is 12 (12! < Integer.MAX_VALUE). So instead of using a boolean array to store the information about the elements that we have already used, we can just set/unset bits of our integer.

    For those who are not too familiar with bit manipulation:

            (set & (1 << i)) == 0 --------------------- checks if the bit at index i is unset.
            alternatively (set & (1 << i)) > 0 -------- checks if the bit at index i is set.
            set |= (1 << i) --------------------------- sets the bit at index i to 1.
    set &= ~(1 << i) -------------------------- clears the bit at index i to 0.
    In this case set is a 32-bit integer. One might think that somehow a short (16-bit) can be more efficient than a 32-bit integer, since we only need 12 bits. This is not true, most CPUs are 32-bit based, so having a short of length 16 will simply mask the remaining 16 bits, which doesn't give us any extra value.*/

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        int set = 0;
        List<Integer> perm = new ArrayList<>(nums.length);
        permute(nums, set, perm);
        return res;
    }

    private void permute(int[] nums, int set, List<Integer> perm) {
        if (perm.size() == nums.length) {
            res.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if ((set & (1 << i)) == 0) {
                set |= (1 << i);
                perm.add(nums[i]);
                permute(nums, set, perm);
                set &= ~(1 << i);
                perm.remove(perm.size()-1);
            }
        }
    }
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
