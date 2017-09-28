package ss.array.four;

import org.junit.Test;

/**
 * Created by Student on 12/28/16.
 */
public class SeriesSum {
    public int[] seriesSum (int[] nums, int target) {
        int start = 0, end = 1;
        int half = (nums.length + 1) / 2;
        int[] result = new int[2];
        while (start < half) {
            int sum = sum(nums, start, end);
            if (sum == target) {
                result[0] = start;
                result[1] = end;
                return result;
            } else if (sum < target){
                end++;
            } else {
                start++;
            }
        }
        return result;

    }
    public int sum(int[] nums, int a, int b){
        int sum = 0;
        for (int i = a; i <= b; i++) {
            sum += nums[i];
        }
        return sum;
    }

    @Test
    public void test01(){
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] result = seriesSum(array, 21);
        System.out.print("result: " + result[0] + " " + result[1]);

    }


}
