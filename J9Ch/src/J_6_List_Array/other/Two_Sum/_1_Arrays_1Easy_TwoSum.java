package J_6_List_Array.other.Two_Sum;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/two-sum/

public class _1_Arrays_1Easy_TwoSum {

    //从前往后找，给出结果index
	public int[] twosum(int[] nums, int target) {
		int[] res = new int[2];
		if (nums == null || nums.length < 2) {
			return res;
		}
		//<remain, index>
		Map<Integer, Integer> remainHashMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (remainHashMap.containsKey(nums[i])) {
				res[0] = remainHashMap.get(nums[i]);
				res[1] = i;
				return res;
			} else {
                remainHashMap.put(target - nums[i], i);
			}
		}
		return res;
	}

	@Test
    public void test01(){
	    int[] nums = {1,2,3,4,5,6};
	    int[] result = twosum(nums, 7);
        for (int i : result
             ) {
            System.out.println(i);
        }
    }

//---------------------------------//////////////////////

	//two pointers, O(nlogn)
    //要sort array，从两边向中间找
    //这个two pointer模版有点像binary search啊
	public boolean twoSum2(int[] nums, int target){
		if(nums == null || nums.length < 2){
			return false;
		}
		Arrays.sort(nums);//O(nlog(n))

		int start = 0;
		int end = nums.length - 1;

		while(start < end){
			    //case1: found
			if(nums[start] + nums[end] == target) {
                System.out.println("nums[start] " + nums[start]);
                System.out.println("nums[end] " + nums[end]);
                return true;
                //case2: larger than target: move end pointer
			} else if (nums[start] + nums[end] > target){
				end--;
                //case3: smaller than target: move start pointer
			} else{
				start++;
			}
		}
		return false;
	}

    @Test
    public void test02(){
        int[] nums = {1,2,3,4,5,6};
        boolean result = twoSum2(nums, 7);
        System.out.println(result);
    }
}

//follow up: 3 Sum

//写一个找出所有满足sum的pair。只需要在nums[start] + nums[end] == target 这步加入