package J_6_List_Array.Array;

//209. Minimum Size Subarray Sum

//https://leetcode.com/problems/minimum-size-subarray-sum/#/description

/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of
 which the time complexity is O(n log n).

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
 */


// 406 Minimum Size Subarray Sum

public class _406_Minimum_Size_Subarray_Sum {
    //Java Solution - two pointers
//A simple sliding window solution.


    public int minSubArrayLen1(int s, int[] nums) {
        if(nums==null || nums.length==1)
            return 0;

        int result = nums.length;

        int start=0;
        int sum=0;
        int i=0;
        boolean exists = false;

        while(i<=nums.length){
            if(sum>=s){
                exists=true; //mark if there exists such a subarray
                if(start==i-1){
                    return 1;
                }

                result = Math.min(result, i-start);
                sum=sum-nums[start];
                start++;

            }else{
                if(i==nums.length)
                    break;
                sum = sum+nums[i];
                i++;
            }
        }

        if(exists)
            return result;
        else
            return 0;
    }

//-------------------------------------------------------------------------------

    public int minSubArrayLen2(int s, int[] nums) {
        if(nums==null||nums.length==0)
            return 0;

        int i=0;
        int j=0;
        int sum=0;

        int minLen = Integer.MAX_VALUE;

        while(j<nums.length){
            if(sum<s){
                sum += nums[j];
                j++;
            }else{
                minLen = Math.min(minLen, j-i);
                if(i==j-1)
                    return 1;

                sum -=nums[i];
                i++;
            }
        }

        while(sum>=s){
            minLen = Math.min(minLen, j-i);

            sum -=nums[i++];
        }

        return minLen==Integer.MAX_VALUE? 0: minLen;
    }

//-------------------------------------------------------------------------------


}
