package _08_Bit.HF_bit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//
//
//
public class _06Subsets {
//------------------------------------------------------------------

    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<ArrayList<Integer>> subsets(int[] nums) {
        List<ArrayList<Integer>> result = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);

        // 1 << n is 2^n
        // each subset equals to an binary integer between 0 .. 2^n - 1
        // 0 -> 000 -> []
        // 1 -> 001 -> [1]
        // 2 -> 010 -> [2]
        // ..
        // 7 -> 111 -> [1,2,3]
        for (int i = 0; i < (1 << n); i++) {
            ArrayList<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // check whether the jth digit in i's binary representation is 1
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }

        return result;
    }
//------------------------------------------------------------------
}

/*

 */
