package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _503_Stack_Next_Greater_Element_II_M {

    //  https://leetcode.com/problems/next-greater-element-ii/solution/

/*    Java 10 lines and C++ 12 lines linear time complexity O(n) with explanation
    The approach is same as Next Greater Element I
    See explanation in my solution to the previous problem
    The only difference here is that we use stack to keep the indexes of the decreasing subsequence

            Java*/

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                next[stack.pop()] = num;
            if (i < n) stack.push(i);
        }
        return next;
    }


//------------------------------------------------------------------------------

/*    Typical ways to solve circular array problems. Java solution.
    The first typical way to solve circular array problems is to extend the original array to twice length, 2nd half has the same element as first half. Then everything become simple.
    Naive by simple solution, just look for the next greater element directly. Time complexity: O(n^2).*/

    public class Solution1 {
        public int[] nextGreaterElements(int[] nums) {
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                max = Math.max(max, num);
            }

            int n = nums.length;
            int[] result = new int[n];
            int[] temp = new int[n * 2];

            for (int i = 0; i < n * 2; i++) {
                temp[i] = nums[i % n];
            }

            for (int i = 0; i < n; i++) {
                result[i] = -1;
                if (nums[i] == max) continue;

                for (int j = i + 1; j < n * 2; j++) {
                    if (temp[j] > nums[i]) {
                        result[i] = temp[j];
                        break;
                    }
                }
            }

            return result;
        }
    }
/*    The second way is to use a stack to facilitate the look up. First we put all indexes into the stack, smaller index on the top. Then we start from end of the array look for the first element (index) in the stack which is greater than the current one. That one is guaranteed to be the Next Greater Element. Then put the current element (index) into the stack.
    Time complexity: O(n).*/

    public class Solution2 {
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];

            Stack<Integer> stack = new Stack<>();
            for (int i = n - 1; i >= 0; i--) {
                stack.push(i);
            }

            for (int i = n - 1; i >= 0; i--) {
                result[i] = -1;
                while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty()){
                    result[i] = nums[stack.peek()];
                }
                stack.add(i);
            }

            return result;
        }
    }
//------------------------------------------------------------------------------
/*
NO STACK: O(n) time complexity and O(1) space complexity using DP
    The idea is to use the array to be returned to store information rather than an extra Stack. We use the array called result to store index of next larger element and replace with actual values before returning it. In first iteration, we move from right to left and find next greater element assuming array to be non-cyclical. Then we do another iteration from right to left. This time, we assume array is cyclical and find next larger element for the elements that do not have next larger element if array is not cyclical.
*/

    public class Solution3 {
        public int[] nextGreaterElements(int[] nums) {

            //Case when array is empty
            if(nums.length == 0) return nums;

            int[] result = new int[nums.length];

            //Assuming array to be non-cyclical, last element does not have next larger element
            result[nums.length - 1] = -1;

            //Case when only one element is there in array
            if(result.length == 1) return result;

            for (int i = nums.length - 2; i >= 0; i--){
                //Starting from next element
                int k = i + 1;

                //Keep tracking next larger element until you find it or you find element with no next larger element
                while(nums[i] >= nums[k] && result[k] != -1){
                    k = result[k];
                }

                //If next larger element is found, store index
                if(nums[k] > nums[i]) result[i] = k;
                    //If not found, it doesn't have next larger element
                else result[i] = -1;
            }

            //Second iteration assuming cyclical array, last element can also have next larger element
            for (int i = nums.length - 1; i >= 0; i--){

                //If next larger element assuming non-cyclical array already exists, continue
                if(result[i] != -1) continue;

                //Case when i + 1 is greater than length of array: when on last element
                int k = (i + 1) % nums.length;

                //Keep tracking next larger element until you find it or you find element with no next larger element
                while(nums[i] >= nums[k] && result[k] != -1){
                    k = result[k];
                }

                //If next larger element is found, store it's index
                if(nums[k] > nums[i]) result[i] = k;
                    //If not found, it doesn't have next larger element
                else result[i] = -1;
            }

            //Replace indices with actual values
            for(int i = 0; i < nums.length; i++){
                if(result[i] != -1) result[i] = nums[result[i]];
            }

            return result;
        }
    }


}

/*

 */
