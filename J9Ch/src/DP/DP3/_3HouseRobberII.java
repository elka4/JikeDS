package DP.DP3;

//_3HouseRobberII 27:11
//• 有状态的序列型动态规划

//House RobberII
public class _3HouseRobberII {

////////////////////////////////////////////////////////////////////

    //jiuzhang
    public int houseRobber2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robber1(nums, 0, nums.length - 2),
                        robber1(nums, 1, nums.length - 1));
    }

    public int robber1(int[] nums, int st, int ed) {
        int []res = new int[2];

        if(st == ed)
            return nums[ed];
        if(st+1 == ed)
            return Math.max(nums[st], nums[ed]);

        res[st%2] = nums[st];
        res[(st+1)%2] = Math.max(nums[st], nums[st+1]);

        for(int i = st+2; i <= ed; i++) {
            res[i%2] = Math.max(res[(i-1)%2], res[(i-2)%2] + nums[i]);

        }
        return res[ed%2];
    }

////////////////////////////////////////////////////////////////////

    // leetcode
    // Simple AC solution in Java in O(n) with explanation
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2),
                    rob(nums, 1, nums.length - 1));
    }

    private int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }
////////////////////////////////////////////////////////////////////

    public int rob3(int[] nums) {

        return Math.max(rob3(nums, 0, nums.length-2),
                rob3(nums, 1, nums.length-1));
    }

    public int rob3(int[] nums, int lo, int hi) {
        int preRob = 0, preNotRob = 0, rob = 0, notRob = 0;
        for (int i = lo; i <= hi; i++) {
            rob = preNotRob + nums[i];
            notRob = Math.max(preRob, preNotRob);

            preNotRob = notRob;
            preRob = rob;
        }
        return Math.max(rob, notRob);
    }

////////////////////////////////////////////////////////////////////

    //Good performance DP solution using Java
    public int rob4(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length < 2)
            return nums[0];

        int[] startFromFirstHouse = new int[nums.length + 1];
        int[] startFromSecondHouse = new int[nums.length + 1];

        startFromFirstHouse[0]  = 0;
        startFromFirstHouse[1]  = nums[0];
        startFromSecondHouse[0] = 0;
        startFromSecondHouse[1] = 0;

        for (int i = 2; i <= nums.length; i++) {
            startFromFirstHouse[i] = Math.max(startFromFirstHouse[i - 1],
                    startFromFirstHouse[i - 2] + nums[i-1]);

            startFromSecondHouse[i] = Math.max(startFromSecondHouse[i - 1],
                    startFromSecondHouse[i - 2] + nums[i-1]);
        }

        return Math.max(startFromFirstHouse[nums.length - 1], startFromSecondHouse[nums.length]);
    }

////////////////////////////////////////////////////////////////////
    //Jave O(1) space, O(n) time optimal solution
    /*
    Helper method returns DP solution from 0 - n-2 and 1 - n-1. Final answer is the max between two.
     */

    public int rob5(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(robHelper(nums, 0, n - 2), robHelper(nums, 1, n - 1));
    }

    private int robHelper(int[] nums, int start, int end) {
        int curr, prev, prev2;
        curr = prev = prev2 = 0;
        for (int i = start; i <= end; i++) {
            curr = Math.max(prev2 + nums[i], prev);
            prev2 = prev;
            prev = curr;
        }
        return curr;
    }


////////////////////////////////////////////////////////////////////

}
/*
After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 Notice

This is an extension of House Robber.

Have you met this question in a real interview? Yes
Example
nums = [3,6,4], return 6
 */