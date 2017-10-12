package HF.HF0_OA9;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class test {

    public int[] winSum2(int[] nums, int k) {
        // write your code here

        Deque<Integer> deque = new LinkedList<>();
        int sum = 0;
        int[] result = new int[nums.length - k + 1];;


        for(int i = 0; i < k; i++){
            deque.offerLast(nums[i]);
            sum += nums[i];
        }
        result[0] = sum;

        System.out.println("nums.length: " + nums.length);
        int n = nums.length;
        int j = 1;

        for(int z = k; k < n - 1; z++){

            System.out.println("i: " + z);
//            System.out.println("nums[z]: " + nums[z]);

            System.out.println("sum: " + sum);

            sum -= deque.pollFirst();

            deque.offerLast(nums[z]);


            System.out.println("sum: " + sum);

            sum += nums[z];

            System.out.println("sum: " + sum);
            result[j] = sum;

            if (z < nums.length){
                j++;
            }


            System.out.println("result: ");
            for (int i : result
                    ) {
                System.out.print(i + " ");
            }
            System.out.println();

            System.out.println("z: " + z);
            if (z == nums.length - 1){
                break;
            }


        }
        return result;

    }

    @Test
    public void test03(){
        int[] nums = {1,2,7,8,5};
        int k = 3;
        int[] result = winSum2(nums, k);
        for (int i : result
                ) {
            System.out.println(i);
        }
    }
}
