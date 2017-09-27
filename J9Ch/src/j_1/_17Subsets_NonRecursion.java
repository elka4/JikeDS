package j_1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by tianhuizhu on 6/18/17.
 */
public class _17Subsets_NonRecursion {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int n = nums.length;
        Arrays.sort(nums);

        // 1 << n is 2^n
        // each subset equals to an binary integer between 0 .. 2^n - 1
        // 0 -> 000 -> []
        // 1 -> 001 -> [1]
        // 2 -> 010 -> [2]
        // ..
        // 7 -> 111 -> [1,2,3]
        System.out.println(Integer.toBinaryString(1 << n));

        for (int i = 0; i < (1 << n); i++) {
            ArrayList<Integer> subset = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                // check whether the jth digit in i's binary representation is 1
                System.out.println("i :      " + i + " : " + Integer.toBinaryString(i));
                System.out.println("1 << j : " + j + " : "+ Integer.toBinaryString(1 << j));
                if ((i & (1 << j)) != 0) {
                    System.out.println("subset.add " + nums[j]);
                    subset.add(nums[j]);
                }
                System.out.println("-------------" + subset + "-----------");
            }
            result.add(subset);
            System.out.println("===========" + result +"============");
        }
        return result;
    }

    @Test
    public void test01(){
        System.out.println(subsets(new int[]{1,2,3}));
    }


}
