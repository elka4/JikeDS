package _01_Array.Subarray_Sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given a binary array, find the maximum length of a contiguous subarray
 with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number
 of 0 and 1.

Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with
equal number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000.

 */


public class _525_Contiguous_Array {
    //https://leetcode.com/problems/contiguous-array/solution/

    //Approach #1 Brute Force [Time Limit Exceeded]

    public int findMaxLength(int[] nums) {
        int maxlen = 0;
        for (int start = 0; start < nums.length; start++) {
            int zeroes = 0, ones = 0;
            for (int end = start; end < nums.length; end++) {
                if (nums[end] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
                if (zeroes == ones) {
                    maxlen = Math.max(maxlen, end - start + 1);
                }
            }
        }
        return maxlen;
    }


///////////////////////////////////////////////////////////////////

    //Approach #2 Using Extra Array [Accepted]

    public int findMaxLength2(int[] nums) {
        int[] arr = new int[2 * nums.length + 1];
        Arrays.fill(arr, -2);
        arr[nums.length] = -1;
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if (arr[count + nums.length] >= -1) {
                maxlen = Math.max(maxlen, i - arr[count + nums.length]);
            } else {
                arr[count + nums.length] = i;
            }

        }
        return maxlen;
    }


///////////////////////////////////////////////////////////////////

    //Approach #3 Using HashMap [Accepted]

    public int findMaxLength3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }


///////////////////////////////////////////////////////////////////

    //Easy Java O(n) Solution, PreSum + HashMap

    public int findMaxLength4(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) nums[i] = -1;
        }

        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int sum = 0, max = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumToIndex.containsKey(sum)) {
                max = Math.max(max, i - sumToIndex.get(sum));
            }
            else {
                sumToIndex.put(sum, i);
            }
        }

        return max;
    }



///////////////////////////////////////////////////////////////////

    //One pass,use a HashMap to record 0-1 count difference

    public int findMaxLength5(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);

        int zero=0;
        int one=0;
        int len=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                zero++;
            }else{
                one++;
            }

            if(map.containsKey(zero-one)){
                len=Math.max(len,i-map.get(zero-one));
            }else{
                map.put(zero-one,i);
            }
        }

        return len;
    }



///////////////////////////////////////////////////////////////////

    //Share my DP&Map solution, one pass

    public int findMaxLength6(int[] nums) {
        int n = nums.length, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int[][] dp = new int[n+1][2];
        for (int i = 1; i < dp.length; i++) {
            if (nums[i-1] == 0) {
                dp[i][0] = dp[i-1][0]+1;
                dp[i][1] = dp[i-1][1];
            }else {
                dp[i][0] = dp[i-1][0];
                dp[i][1] = dp[i-1][1]+1;
            }
            if (dp[i][0] == dp[i][1]) res = Math.max(res, dp[i][0]*2);
            else {
                int dif = dp[i][1]-dp[i][0];
                if (map.containsKey(dif)) res = Math.max(res, 2*(dp[i][0]-dp[map.get(dif)][0]));
                else map.put(dif, i);
            }
        }
        return res;
    }



///////////////////////////////////////////////////////////////////

    //Java Divide-and-conquer
    //Though not optimal, a divide-and-conquer approach is still interesting to write.
    //Time = O(n log n), peak space = O(n).


    public int findMaxLength7(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private int dfs(int[] a, int l, int r) {
        if (l >= r) return 0;
        int mid = (l + r) / 2;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = mid, one = 0, zero = 0; i >= l; i--) {
            if (a[i] == 0) zero++;
            else one++;
            map.put(zero - one, zero + one);
        }
        int max = 0;
        for (int i = mid + 1, one = 0, zero = 0; i <= r; i++) {
            if (a[i] == 0) zero++;
            else one++;
            if (map.containsKey(one - zero)) max = Math.max(max, map.get(one - zero) + zero + one);
        }

        return Math.max(max, Math.max(dfs(a, l, mid), dfs(a, mid + 1, r)));
    }




///////////////////////////////////////////////////////////////////

    //Java one pass O(n) solution with explanation

    public int findMaxLength8(int[] nums) {
        int res = 0;
        int n = nums.length;

        int[] diff = new int[n + 1];

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        for (int i = 1; i <= n; i++) {
            diff[i] = diff[i - 1] + (nums[i - 1] == 0 ? -1 : 1);

            if (!map.containsKey(diff[i]))
                map.put(diff[i], i);
            else
                res = Math.max(res, i - map.get(diff[i]));
        }

        return res;
    }





///////////////////////////////////////////////////////////////////




}
