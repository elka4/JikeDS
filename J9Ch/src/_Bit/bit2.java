package _Bit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Student on 12/27/16.
 */
public class bit2 {
    public static void main(String[] args) {
        int[] nums = new int[3];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;

        ArrayList<ArrayList<Integer>> result = subsets(nums);
        System.out.println(result);

//        for (int i = 0; i < result.size(); i++) {
//            ArrayList<Integer> subList = result.get(i);
//            for (int j = 0; j < subList.size(); i++) {
//                Integer output = subList.get(j);
//                System.out.println(output);
//            }
//
//        }


    }

    public static ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);

        // 1 << n is 2^n
        // each subset equals to an binary integer between 0 .. 2^n - 1
        // 0 -> 000 -> []
        // 1 -> 001 -> [1]
        // 2 -> 010 -> [2]
        // ..
        // 7 -> 111 -> [1,2,3]
        int outer = 0;
        for (int i = 0; i < (1 << n); i++) {
            ArrayList<Integer> subset = new ArrayList<>();
            System.out.println("outer: " + ++outer);

            int inner = 0;
            for (int j = 0; j < n; j++) {
                System.out.println("innner: " + ++inner);

                // check whether the jth digit in i's binary representation is 1
                if ((i & (1 << j)) != 0) {
                    System.out.println("the i is 1: " + i);
                    System.out.println("the j is 1: " + j);
                    System.out.println("the nums[j]) is : " + nums[j]);
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }

        return result;
    }



}
