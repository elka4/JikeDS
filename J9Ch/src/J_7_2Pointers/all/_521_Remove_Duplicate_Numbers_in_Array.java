package J_7_2Pointers.all;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** 521 Remove Duplicate Numbers in Array
 * Easy

 * Created by tianhuizhu on 6/28/17.
 */

public class _521_Remove_Duplicate_Numbers_in_Array {

    // O(n) time, O(n) space
    /**
     * @param nums an array of integers
     * @return the number of unique integers
     */
    public int deduplication(int[] nums) {
        // Write your code here
        HashMap<Integer, Boolean> mp = new HashMap<Integer, Boolean>();
        for (int i = 0; i < nums.length; ++i)
            mp.put(nums[i], true);

        int result = 0;

        for (Map.Entry<Integer, Boolean> entry : mp.entrySet())
            nums[result++] = entry.getKey();

        return result;
    }




//-------------------------------------------------------------------------///////

    // O(nlogn) time, O(1) extra space
    /**
     * @param nums an array of integers
     * @return the number of unique integers
     */
    public int deduplication2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int len = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[len]) {
                nums[++len] = nums[i];
            }
        }
        return len + 1;
    }

//-------------------------------------------------------------------------///////

}
