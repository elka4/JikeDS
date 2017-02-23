package top100.Enhance._4DP_PreClass;


public class _8HouseRober {
    public int houseRobber1(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1) {
            return  nums[0];
        }
        int prev = 0;
        int cur = nums[0];

        for(int i = 1; i < nums.length; i++) {
            int next = Math.max(cur, nums[i] + prev);
            prev = cur;
            cur = next;
        }
        return cur;
    }

    /////////////////////////////////
    public int rob(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        return Math.max(subRob(nums, 0, nums.length - 1), subRob(nums, 1, nums.length - 1));
    }

    private int subRob(int[] n, int start, int end){
        int prev = 0;
        int cur = n[start];
        for(int i = start + 1; i <= end; i++){
            int next = Math.max(cur, prev + n[i]);
            prev = cur;
            cur = next;
        }
        return cur;
    }
}
