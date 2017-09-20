package DP_Classified._2Sequence;

//House RobberII
public class _3HouseRobberII {
    //jiuzhang
    public int houseRobber2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robber1(nums, 0, nums.length - 2), robber1(nums, 1, nums.length - 1));
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

    //Simple AC solution in Java in O(n) with explanation
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

    //Java clean short solution DP
    public int rob3(int[] nums) {
        return Math.max(rob3(nums, 0, nums.length-2), rob3(nums, 1, nums.length-1));
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