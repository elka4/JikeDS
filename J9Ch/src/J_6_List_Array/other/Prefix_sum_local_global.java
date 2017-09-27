package J_6_List_Array.other;


//Prefix sum 数组的 local / global 通用模板

//求 min / max 皆可，使用时需要注意初始条件以及顺序；


public class Prefix_sum_local_global {

    void Prefix_sum_local_global(int[] nums){
        //求 min / max 皆可，使用时需要注意初始条件以及顺序；
        int n = nums.length;
        int[] leftMax = new int[n];
        int prefixSum = 0;
        int localMin = 0;
        int globalMax = Integer.MIN_VALUE;

        int index = 0;
        for(int num : nums) {
            prefixSum += num;
            // globalMax在前保证起点不会遗漏
            globalMax = Math.max(globalMax, prefixSum - localMin);
            localMin = Math.min(localMin, prefixSum);

            leftMax[index++] = globalMax;
        }
    }

}
