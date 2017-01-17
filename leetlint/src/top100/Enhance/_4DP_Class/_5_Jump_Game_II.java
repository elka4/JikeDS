package top100.Enhance._4DP_Class;

public class _5_Jump_Game_II {
	public int jump(int[] nums) {
        if(nums == null || nums.length == 0)
            return -1;
        int[] min = new int[nums.length];
        min[0] = 0;
        int curIndex = 0;
        for(int i = 1; i < nums.length; i++){
            for(int j = curIndex; j < i; j++){
                if(nums[j] + j >= i){
                    min[i] = min[j] + 1;
                    curIndex = j;
                    break;//Stop at the leftmost one
                }
            }
        }
        return min[nums.length - 1];
    }
}
