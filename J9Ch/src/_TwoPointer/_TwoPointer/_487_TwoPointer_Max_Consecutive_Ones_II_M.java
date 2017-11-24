package _TwoPointer._TwoPointer;
import java.util.*;
import org.junit.Test;


//  487. Max Consecutive Ones II

//  https://leetcode.com/problems/max-consecutive-ones-ii/description/
public class _487_TwoPointer_Max_Consecutive_Ones_II_M {
//Java clean solution easily extensible to flipping k zero and follow-up handled
/*The idea is to keep a window [l, h] that contains at most k zero

    The following solution does not handle follow-up, because nums[l] will need to access previous input stream
    Time: O(n) Space: O(1)*/

    public int findMaxConsecutiveOnes1(int[] nums) {
        int max = 0, zero = 0, k = 1; // flip at most k zero
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zero++;
            while (zero > k)
                if (nums[l++] == 0)
                    zero--;
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
/*    Now let's deal with follow-up, we need to store up to k indexes of zero within the window [l, h] so that we know where to move l next when the window contains more than k zero. If the input stream is infinite, then the output could be extremely large because there could be super long consecutive ones. In that case we can use BigInteger for all indexes. For simplicity, here we will use int
    Time: O(n) Space: O(k)*/

    public int findMaxConsecutiveOnes2(int[] nums) {
        int max = 0, k = 1; // flip at most k zero
        Queue<Integer> zeroIndex = new LinkedList<>();
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zeroIndex.offer(h);
            if (zeroIndex.size() > k)
                l = zeroIndex.poll() + 1;
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
/*    Note that setting k = 0 will give a solution to the earlier version Max Consecutive Ones

    For k = 1 we can apply the same idea to simplify the solution. Here q stores the index of zero within the window [l, h] so its role is similar to Queue in the above solution*/

    public int findMaxConsecutiveOnes3(int[] nums) {
        int max = 0, q = -1;
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0) {
                l = q + 1;
                q = h;
            }
            max = Math.max(max, h - l + 1);
        }
        return max;
    }


//    Same solution as posted by OP. I have tried to simplify it for the noobs like me. Can anyone simplify the algorithm description?

    /**
     * Algorithm:
     * k is most bits you can flip.
     * Use two indexes/pointers say high and low and a counter 'zero'.
     * zero counter : counts number of zeros seen till then.
     * high index checks for zeros and increments zero counter.
     * low index is only active when zero counter is greater than k and decrements zero counter when it encounters zero.
     *
     *
     * @param nums
     * @return
     */
    public static int findMaxConsecutiveOnes4(int[] nums) {
        int max = 0, zero = 0, k = 2; // flip at most k zero
        int low = 0;
        for (int high = 0; high < nums.length; high++) {
            if (nums[high] == 0) {
                zero++;
            }
            while (zero > k) {
                if (nums[low] == 0) {
                    zero--;
                }
                low++;
            }
            max = Math.max(max, high - low + 1);
        }
        return max;
    }


/*    you can use 2 counters, one counter has already used it's zero flip and the other has not. When you see a zero the one that has already used it's flip must reset and the one that has not yet used it it's zero uses it now. The counter which is lesser is the the one that has not used it's zero. Also you can limit your check for max to when you see a zero and at the end.*/

    public int FindMaxConsecutiveOnes5(int[] nums)
    {
        int c1 = 0;
        int c2 = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++)
        {
            if (nums[i] == 1)
            {
                c1++;
                c2++;
            }
            else if (c1 < c2)
            {
                max = Math.max(max, c2);
                c1++;
                c2 = 0;
            }
            else
            {
                max = Math.max(max, c1);
                c1 = 0;
                c2++;
            }
        }

        max = Math.max(max, c1);
        max = Math.max(max, c2);
        return max;
    }


//    I came up with a similar idea. Use variable index to record the position of zero, and initiate the index to -1. Once we find a zero, refresh the count and store the new index.

    public class Solution6 {
        public int findMaxConsecutiveOnes(int[] nums) {
            int max = 0, count = 0, index = -1;
            for(int i = 0;i< nums.length; i++){
                if(nums[i] == 1){
                    count++;
                }else{
                    count = i - index;
                    index = i;
                }
                max = Math.max(max,count);
            }
            return max;
        }
    }

    //Java Concise O(n) Time O(1) Space
    public int findMaxConsecutiveOnes7(int[] nums) {
        int maxConsecutive = 0, zeroLeft = 0, zeroRight = 0;
        for (int i=0;i<nums.length;i++) {
            zeroRight++;
            if (nums[i] == 0) {
                zeroLeft = zeroRight;
                zeroRight = 0;
            }
            maxConsecutive = Math.max(maxConsecutive, zeroLeft+zeroRight);
        }
        return maxConsecutive;
    }

//    I came up with a different idea. Use variable index to record the position of zero, and initiate the index to -1. Once we find a zero, refresh the count and store the new index. But yours are very impressive. Never thought this way before!

    public class Solution8 {
        public int findMaxConsecutiveOnes(int[] nums) {
            int max = 0, count = 0, index = -1;
            for(int i = 0;i< nums.length; i++){
                if(nums[i] == 1){
                    count++;
                }else{
                    count = i - index;
                    index = i;
                }
                max = Math.max(max,count);
            }
            return max;
        }
    }

    //Java Concise O(n) solution (which can handle input with infinite count)

/*    To handle infinite input, we should not access index of the array.

    I used enhanced for loop, so didn't access index of the array at all.

    The idea is maintaining two buffer. (frontSum and backSum)

    frontSum contains consecutive (one's sum + 1) before meeting recent zero.
            backSum contains consecutive one's sum after meeting recent zero.
            When meet zero, backSum is moved to frontSum.*/

   public class Solution9 {
        public int findMaxConsecutiveOnes(int[] nums) {
            int frontSum = 0, backSum = 0, maxSum = 0;
            for (int n : nums) {
                if (n == 0) {
                    frontSum = backSum + 1;
                    backSum = 0;
                }
                backSum += n;
                maxSum = Math.max(maxSum, frontSum + backSum);
            }
            return maxSum;
        }
    }

}
/*
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:
Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
Follow up:
What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?


 */