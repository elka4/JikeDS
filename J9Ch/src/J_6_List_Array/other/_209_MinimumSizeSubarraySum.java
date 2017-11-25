package J_6_List_Array.other;
import org.junit.Test;

// Array, Two Pointers, Binary Search
//leetcode

//find the minimal length of a contiguous subarray of which the sum ≥ s.

public class _209_MinimumSizeSubarraySum {

    //Accepted clean Java O(n) solution (two pointers)
    // moving window
    // j右指针， i左指针
    //每轮循环j++；sum >= s时就有缩小窗口的机会，update min，update sum，i++。
    public int minSubArrayLen(int s, int[] a) {
        if (a == null || a.length == 0)
            return 0;

        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;

        while (j < a.length) {
            sum += a[j++];

            while (sum >= s) {
                min = Math.min(min, j - i);
                sum -= a[i++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    @Test
    public void test01(){
        int[] a = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7,a));
    }


//------------------------------------------------------------------------------
//Two AC solutions in Java with time complexity of N and NLogN with explanation

    public class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            return solveNLogN(s, nums);
        }

        private int solveN(int s, int[] nums) {
            int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
            while (end < nums.length) {
                while (end < nums.length && sum < s) sum += nums[end++];
                if (sum < s) break;
                while (start < end && sum >= s) sum -= nums[start++];
                if (end - start + 1 < minLen) minLen = end - start + 1;
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }

        private int solveNLogN(int s, int[] nums) {
            int[] sums = new int[nums.length + 1];
            for (int i = 1; i < sums.length; i++)
                sums[i] = sums[i - 1] + nums[i - 1];

            int minLen = Integer.MAX_VALUE;
            for (int i = 0; i < sums.length; i++) {
                int end =
                binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);

                if (end == sums.length) break;
                if (end - i < minLen) minLen = end - i;
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }

        private int binarySearch(int lo, int hi, int key, int[] sums) {
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (sums[mid] >= key){
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            return lo;
        }
    }

//------------------------------------------------------------------------------
    //O(N),O(NLogN) solutions, both O(1) space

    /*    O(N) - keep a moving window expand until sum>=s,
    then shrink util sum<s. Each time after shrinking, update length.
    (similar to other solutions, just removed unnecessary min value assignment)*/

    public int minSubArrayLen3(int s, int[] nums) {
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;

        while (j < nums.length) {

            while (sum < s && j < nums.length) sum += nums[j++];
            
            if(sum>=s){
                while (sum >= s && i < j) sum -= nums[i++];
                min = Math.min(min, j - i + 1);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }



    //O(NLogN) - search if a window of size k exists that satisfy the condition
    public int minSubArrayLen4(int s, int[] nums) {
        int i = 1, j = nums.length, min = 0;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (windowExist(mid, nums, s)) {
                j = mid - 1;
                min = mid;
            } else i = mid + 1;
        }
        return min;
    }


    private boolean windowExist(int size, int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= size) sum -= nums[i - size];
            sum += nums[i];
            if (sum >= s) return true;
        }
        return false;
    }




/*    Another O(NLogN) solution that first calculate cumulative sum
 and then for each starting point binary search for end position.
 This uses O(N) space*/
    public int minSubArrayLen5(int s, int[] nums) {
        int sum = 0, min = Integer.MAX_VALUE;

        int[] sums = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            sums[i] = nums[i] + (i == 0 ? 0 : sums[i - 1]);

        for (int i = 0; i < nums.length; i++) {
            int j = findWindowEnd(i, sums, s);
            if (j == nums.length) break;
            min = Math.min(j - i + 1, min);
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private int findWindowEnd(int start, int[] sums, int s) {
        int i = start, j = sums.length - 1,
                offset = start == 0 ? 0 : sums[start - 1];
        while (i <= j) {
            int m = (i + j) / 2;
            int sum = sums[m] - offset;
            if (sum >= s) j = m - 1;
            else i = m + 1;
        }
        return i;
    }



//------------------------------------------------------------------------------

//------------------------------------------------------------------------------

//------------------------------------------------------------------------------

//------------------------------------------------------------------------------

//------------------------------------------------------------------------------

    //https://leetcode.com/articles/minimum-size-subarray-sum/

    //Approach #3 Using Binary search [Accepted]

/*
int minSubArrayLen(int s, vector<int>& nums)
{
    int n = nums.size();
    if (n == 0)
        return 0;
    int ans = INT_MAX;
    vector<int> sums(n + 1, 0); //size = n+1 for easier calculations
    //sums[0]=0 : Meaning that it is the sum of first 0 elements
    //sums[1]=A[0] : Sum of first 1 elements
    //ans so on...
    for (int i = 1; i <= n; i++)
        sums[i] = sums[i - 1] + nums[i - 1];
    for (int i = 1; i <= n; i++) {
        int to_find = s + sums[i - 1];
        auto bound = lower_bound(sums.begin(), sums.end(), to_find);
        if (bound != sums.end()) {
            ans = min(ans, static_cast<int>(bound - (sums.begin() + i - 1)));
        }
    }
    return (ans != INT_MAX) ? ans : 0;
}
 */


//Approach #4 Using 2 pointers [Accepted]

/*
int minSubArrayLen(int s, vector<int>& nums)
{
    int n = nums.size();
    int ans = INT_MAX;
    int left = 0;
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += nums[i];
        while (sum >= s) {
            ans = min(ans, i + 1 - left);
            sum -= nums[left++];
        }
    }
    return (ans != INT_MAX) ? ans : 0;
}
 */


}

/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.
 */

//Minimum Window Substring, Maximum Size Subarray Sum Equals k