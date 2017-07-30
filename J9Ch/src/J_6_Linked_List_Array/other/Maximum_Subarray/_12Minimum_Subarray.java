package J_6_Linked_List_Array.other.Maximum_Subarray;

import org.junit.Test;
import java.util.ArrayList;



public class _12Minimum_Subarray {
/**
 * @param nums: a list of integers
 * @return: A integer indicate the sum of minimum subarray
 */
    public int minSubArray(ArrayList<Integer> nums) {
        if (nums == null)
            return 0;
        int len = nums.size();
        int min = Integer.MAX_VALUE, currSum = 0;
        int []localmin  = new int[len];
        int []globalmin = new int[len];
        for (int i = 0; i < len; i++) {
            if( i ==0 )
                globalmin[i] = localmin[i] = nums.get(i);
            else {
                //localmin记录的是，当前值，和，前一位最小+当前值，里最小的
                localmin[i] = Math.min(localmin[i - 1] + nums.get(i), nums.get(i));
                System.out.println("localmin[i] " + localmin[i]);
                globalmin[i] = Math.min(globalmin[i - 1], localmin[i]);
                System.out.println("globalmin[i] " + globalmin[i]);
            }
            System.out.println("========================");

        }
        return globalmin[len-1];
    }

    @Test
    public void test(){
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(-1);
        nums.add(-2);
        nums.add(1);
        System.out.println(minSubArray(nums));
    }
}
/*Given an array of integers, find the subarray with smallest sum.

Return the sum of the subarray.

 Notice

The subarray should contain one integer at least.

Example
For [1, -1, -2, 1], return -3.

Tags 
Greedy LintCode Copyright Subarray Array
Related Problems 
Medium Maximum Product Subarray 28 %*/