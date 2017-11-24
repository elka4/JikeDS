package _BinarySearch._BinarySearch;
import java.util.*;
import org.junit.Test;
public class _410_BinarySearch_Split_Array_Largest_Sum_M {

/*    Clear Explanation: 8ms Binary Search Java
    The answer is between maximum value of input array numbers and sum of those numbers.
    Use binary search to approach the correct answer. We have l = max number of array; r = sum of all numbers in the array;Every time we do mid = (l + r) / 2;
    Use greedy to narrow down left and right boundaries in binary search.
            3.1 Cut the array from left.
3.2 Try our best to make sure that the sum of numbers between each two cuts (inclusive) is large enough but still less than mid.
            3.3 We'll end up with two results: either we can divide the array into more than m subarrays or we cannot.
    If we can, it means that the mid value we pick is too small because we've already tried our best to make sure each part holds as many non-negative numbers as we can but we still have numbers left. So, it is impossible to cut the array into m parts and make sure each parts is no larger than mid. We should increase m. This leads to l = mid + 1;
    If we can't, it is either we successfully divide the array into m parts and the sum of each part is less than mid, or we used up all numbers before we reach m. Both of them mean that we should lower mid because we need to find the minimum one. This leads to r = mid - 1;*/
    public class Solution {
        public int splitArray(int[] nums, int m) {
            int max = 0; long sum = 0;
            for (int num : nums) {
                max = Math.max(num, max);
                sum += num;
            }
            if (m == 1) return (int)sum;
            //binary search
            long l = max; long r = sum;
            while (l <= r) {
                long mid = (l + r)/ 2;
                if (valid(mid, nums, m)) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return (int)l;
        }
        public boolean valid(long target, int[] nums, int m) {
            int count = 1;
            long total = 0;
            for(int num : nums) {
                total += num;
                if (total > target) {
                    total = num;
                    count++;
                    if (count > m) {
                        return false;
                    }
                }
            }
            return true;
        }
    }



/*    Java easy binary search solution 8ms
    Given a result, it is easy to test whether it is valid or not.
    The max of the result is the sum of the input nums.
    The min of the result is the max num of the input nums.
    Given the 3 conditions above we can do a binary search. (need to deal with overflow)*/
    public class Solution2 {
        public int splitArray(int[] nums, int m) {
            long sum = 0;
            int max = 0;
            for(int num: nums){
                max = Math.max(max, num);
                sum += num;
            }
            return (int)binary(nums, m, sum, max);
        }

        private long binary(int[] nums, int m, long high, long low){
            long mid = 0;
            while(low < high){
                mid = (high + low)/2;
                if(valid(nums, m, mid)){
                    //System.out.println(mid);
                    high = mid;
                }else{
                    low = mid + 1;
                }
            }
            return high;
        }

        private boolean valid(int[] nums, int m, long max){
            int cur = 0;
            int count = 1;
            for(int num: nums){
                cur += num;
                if(cur > max){
                    cur = num;
                    count++;
                    if(count > m){
                        return false;
                    }
                }
            }
            return true;
        }
    }



/*    DP Java
    DP solution. This is obviously not as good as the binary search solutions; but it did pass OJ.

            dp[s,j] is the solution for splitting subarray n[j]...n[L-1] into s parts.

            dp[s+1,i] = min{ max(dp[s,j], n[i]+...+n[j-1]) }, i+1 <= j <= L-s

    This solution does not take advantage of the fact that the numbers are non-negative (except to break the inner loop early). That is a loss. (On the other hand, it can be used for the problem containing arbitrary numbers)*/

    public int splitArray(int[] nums, int m)
    {
        int L = nums.length;
        int[] S = new int[L+1];
        S[0]=0;
        for(int i=0; i<L; i++)
            S[i+1] = S[i]+nums[i];

        int[] dp = new int[L];
        for(int i=0; i<L; i++)
            dp[i] = S[L]-S[i];

        for(int s=1; s<m; s++)
        {
            for(int i=0; i<L-s; i++)
            {
                dp[i]=Integer.MAX_VALUE;
                for(int j=i+1; j<=L-s; j++)
                {
                    int t = Math.max(dp[j], S[j]-S[i]);
                    if(t<=dp[i])
                        dp[i]=t;
                    else
                        break;
                }
            }
        }

        return dp[0];
    }
}
/*
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */