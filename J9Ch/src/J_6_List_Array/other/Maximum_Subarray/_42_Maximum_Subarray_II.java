package J_6_List_Array.other.Maximum_Subarray;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** 42 Maximum Subarray II

 * Created by tianhuizhu on 6/28/17.
 */

/*
Given an array of integers, find two non-overlapping subarrays
 which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

 Notice: The subarray should contain at least one number

Example
For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3]and [2, -1, 2]
or [1, 3, -1, 2] and [2], they both have the largest sum 7.
 */

//两个不overlap的subarray，sum最大

public class _42_Maximum_Subarray_II {

    /*
    就是做两轮Maximum Subarray, 一轮从左， 一轮从右
    left[i] 指的是从0到i这个范围内，以i为右边界的，maximum subarray
    right[i + 1] 指的是从i+1 到 right.length - 1 这个范围内，以i+1为起点到，maximum subarray

    left[i] + right[i + 1]
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        int size = nums.size();
        int[] left = new int[size];
        int[] right = new int[size];

        int sum = 0;
        int minSum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < size; i++){
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            left[i] = max;
        }

        //1, 3, -1, 2, -1, 2
        for (int i : left){
            System.out.print(i + " ");
        }//1 4 4 5 5 6
//---------------------------------//////////////

        sum = 0;
        minSum = 0;
        max = Integer.MIN_VALUE;
        for(int i = size - 1; i >= 0; i--){
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            right[i] = max;
        }

        //1, 3, -1, 2, -1, 2
        System.out.println();
        for (int i : right){
            System.out.print(i + " ");
        }//6 5 3 3 2 2
        System.out.println();
//---------------------------------//////////////

        max = Integer.MIN_VALUE;
        for(int i = 0; i < size - 1; i++){
            max = Math.max(max, left[i] + right[i + 1]);
        }

        return max;
    }

    @Test
    public void test01(){
        Integer[] num = {1, 3, -1, 2, -1, 2};

        List<Integer> nums = Arrays.asList(num);

       // System.out.println(maxTwoSubArrays(nums));
    }

    @Test
    public void test02(){
        Integer[] integers = new Integer[] {1,2,3,4,5};
        List<Integer> list21 =  Arrays.asList(integers); // Cannot modify returned list
        List<Integer> list22 = new ArrayList<>(Arrays.asList(integers)); // good
        List<Integer> list23 = Arrays.stream(integers).collect(Collectors.toList()); //Java 8
    }


    @Test
    public void test03(){
        Integer[] num = {1, 3, -1, 2, -1, 2};

        List<Integer>  listofOptions = (List<Integer>) Arrays.asList(num);
        //then you can user constructoru of an arraylist to instantiate with predefined values.

        ArrayList<Integer> nums = new ArrayList<Integer>(listofOptions);

        //ArrayList<Integer>  nums = (ArrayList<Integer>) Arrays.asList(num);

        System.out.println(maxTwoSubArrays(nums));

    }
}
