package top100.Enhance._4DP_Class;

public class _7_RangeSumQuery_Mutable_1_BinaryIndexTree {
	public class NumArray {
	    int[] nums;
	    int[] bit;//Binary Index Tree
	    public NumArray(int[] nums) {
	        this.nums = nums;
	        this.bit = new int[nums.length + 1];
	        for (int i = 0; i < nums.length; i++) {
	            init(i, nums[i]);
	        }
	    }
	    //   i      1   2   3   4   5   6   7   8
	    //i & (-i)  1   2   1   4   1   2   1   8
	    private void init(int i, int val) {
	        for (int j = i + 1; j <= nums.length; j += j & (-j)) {
	            bit[j] += val;
	        }
	    }
	    void update(int i, int val) {
	        int diff = val - nums[i];
	        nums[i] = val;
	        init(i, diff);
	    }
	    private int getSum(int i) {
	        int sum = 0;
	        for (int j = i + 1; j > 0; j -= j & (-j)) {
	            sum += bit[j];
	        }
	        return sum;
	    }
	    public int sumRange(int i, int j) {
	        return getSum(j) - getSum(i - 1);
	    }
	}
}
