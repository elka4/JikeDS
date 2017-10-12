package HF.HF0_OA9;

import org.junit.Test;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

//Sliding window类


// Window Sum
public class _3WindowSum {
    /**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length < k || k <= 0) return new int[0];


        int[] sums = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++){
            sums[0] += nums[i];
        }

        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] - nums[i - 1] + nums[i + k-1];
        }

        return sums;
    }

    @Test
    public void test01(){
        int[] input = {1,2,7,8,5};
        int k = 3;
        int[] result = winSum(input, 3);

        List<Integer> list =  Arrays.stream(result).boxed().collect(Collectors.toList());

        list.forEach(System.out::println);

    }


/*    public List<Integer> myWork(int[] array) {
        return Arrays.asList(ArrayUtils.toObject(array));
    }*/

//////////////////////////////////////////////////////////////
    public int[] winSum2(int[] nums, int k) {
        // write your code here
        if (k <= 0){
            return new int[]{};
        }

        Deque<Integer> deque = new LinkedList<>();
        int sum = 0;
        int[] result = new int[nums.length - k + 1];;


        for(int i = 0; i < k; i++){
            deque.offerFirst(nums[i]);
            sum += nums[i];
        }
        result[0] = sum;

        System.out.println("nums.length: " + nums.length);
        int j = 1;
//        int z = k;
//        while (z < nums.length){
//            deque.offerFirst(nums[z]);
//            sum -= deque.pollLast();
//            sum += nums[z];
//            result[j++] = sum;
//            z++;
//        }

        for(int z = k; z < nums.length; z++){

            System.out.println("i: " + z);
//            System.out.println("nums[z]: " + nums[z]);

//            System.out.println("sum: " + sum);
            deque.offerFirst(nums[z]);
            sum -= deque.pollLast();
//            System.out.println("sum: " + sum);

            sum += nums[z];

//            System.out.println("sum: " + sum);

            result[j++] = sum;

        }
        return result;

    }

    @Test
    public void test02(){
        int[] nums = {1,2,7,8,5};
        int k = 3;
        int[] result = winSum2(nums, k);
        for (int i : result
             ) {
            System.out.println(i);
        }
    }
//////////////////////////////////////////////////////////////

    public int[] winSumMy(int[] nums, int k) {
        // write your code here


        Deque<Integer> deque = new LinkedList<>();
        int sum = 0;
        int[] result = new int[nums.length - k + 1];

        if ( nums.length == 0 || k <= 0){
            return new int[]{};
        }

        for(int i = 0; i < k; i++){
            deque.offerFirst(nums[i]);
            sum += nums[i];
        }
        result[0] = sum;

        System.out.println("nums.length: " + nums.length);
        int j = 1;

        for(int z = k; k < nums.length; z++){

//            System.out.println("i: " + z);
//            System.out.println("nums[z]: " + nums[z]);

//            System.out.println("sum: " + sum);
            deque.offerFirst(nums[z]);
            sum -= deque.pollLast();
//            System.out.println("sum: " + sum);

            sum += nums[z];

//            System.out.println("sum: " + sum);

            result[j++] = sum;
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
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
}
/*
Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, find the sum of the element inside the window at each moving.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], moving window size k = 3.
1 + 2 + 7 = 10
2 + 7 + 8 = 17
7 + 8 + 5 = 20
return [10,17,20]
 */



/*
滑动窗口内数的和

 Description
 Notes
 Testcase
 Judge
给你一个大小为n的整型数组和一个大小为k的滑动窗口，将滑动窗口从头移到尾，
输出从开始到结束每一个时刻滑动窗口内的数的和。

Have you met this question in a real interview? Yes
Example
对于数组 [1,2,7,8,5] ，滑动窗口大小k= 3 。
1 + 2 + 7 = 10
2 + 7 + 8 = 17
7 + 8 + 5 = 20
返回 [10,17,20]
 */