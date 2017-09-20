package J_1_strStr_Coding_Style.Optional_3;

import java.util.ArrayList;
import java.util.Arrays;
/**
 18
 Subsets II
 * Created by tianhuizhu on 6/28/17.
 */
public class _18_Subsets_II {

    class Solution {
        /**
         * @param nums: A set of numbers.
         * @return: A list of lists. All valid subsets.
         */
        public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
            // write your code here
            ArrayList<ArrayList<Integer>> results = new ArrayList<>();
            if (nums == null) return results;

            if (nums.length == 0) {
                results.add(new ArrayList<Integer>());
                return results;
            }
            Arrays.sort(nums);

            ArrayList<Integer> subset = new ArrayList<>();
            helper(nums, 0, subset, results);

            return results;


        }
        public void helper(int[] nums, int startIndex, ArrayList<Integer> subset, ArrayList<ArrayList<Integer>> results){
            results.add(new ArrayList<Integer>(subset));
            for(int i=startIndex; i<nums.length; i++){
                if (i != startIndex && nums[i]==nums[i-1]) {
                    continue;
                }
                subset.add(nums[i]);
                helper(nums, i+1, subset, results);
                subset.remove(subset.size()-1);
            }
        }
    }
}
