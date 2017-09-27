package Classification.Bit_Manipulation_26;
import java.util.*;

public class b_78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
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

    //Great solution! The Math.pow(2, n) part can run faster by using 1 << n, too.


}
