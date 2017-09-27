package J_5_DFS.Required_7;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
/** 18. Subsets II
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _18_Subsets_II {
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
    public void helper(int[] nums, int startIndex, ArrayList<Integer> subset,
                       ArrayList<ArrayList<Integer>> results){
        results.add(new ArrayList<Integer>(subset));
        for(int i=startIndex; i<nums.length; i++){
            if (i != startIndex && nums[i]==nums[i-1]) {
                continue;
            }
            System.out.println("nums[i] " + nums[i]);
            System.out.println("subset " + subset);
            System.out.println("results " + results);
            System.out.println("========================");
            subset.add(nums[i]);
            helper(nums, i+1, subset, results);
            subset.remove(subset.size()-1);
        }
    }
    @Test
    public void test01(){
        int[] nums = {1,2,2,3};
        System.out.println(subsetsWithDup(nums));
    }
    /*
    nums[i] 1
subset []
results [[]]
========================
nums[i] 2
subset [1]
results [[], [1]]
========================
nums[i] 2
subset [1, 2]
results [[], [1], [1, 2]]
========================
nums[i] 3
subset [1, 2, 2]
results [[], [1], [1, 2], [1, 2, 2]]
========================
nums[i] 3
subset [1, 2]
results [[], [1], [1, 2], [1, 2, 2], [1, 2, 2, 3]]
========================
nums[i] 3
subset [1]
results [[], [1], [1, 2], [1, 2, 2], [1, 2, 2, 3], [1, 2, 3]]
========================
nums[i] 2
subset []
results [[], [1], [1, 2], [1, 2, 2], [1, 2, 2, 3], [1, 2, 3], [1, 3]]
========================
nums[i] 2
subset [2]
results [[], [1], [1, 2], [1, 2, 2], [1, 2, 2, 3], [1, 2, 3], [1, 3], [2]]
========================
nums[i] 3
subset [2, 2]
results [[], [1], [1, 2], [1, 2, 2], [1, 2, 2, 3], [1, 2, 3], [1, 3], [2], [2, 2]]
========================
nums[i] 3
subset [2]
results [[], [1], [1, 2], [1, 2, 2], [1, 2, 2, 3], [1, 2, 3], [1, 3], [2], [2, 2], [2, 2, 3]]
========================
nums[i] 3
subset []
results [[], [1], [1, 2], [1, 2, 2], [1, 2, 2, 3], [1, 2, 3], [1, 3], [2], [2, 2], [2, 2, 3], [2, 3]]
========================
[[], [1], [1, 2], [1, 2, 2], [1, 2, 2, 3], [1, 2, 3], [1, 3], [2], [2, 2], [2, 2, 3], [2, 3], [3]]
     */
}
