package J_6_List_Array.Water_Triangle;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class _02Largest_Rectangle_in_Histogram {
    public int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;

        for (int i = 0; i <= height.length; i++) {

            //curt 当前高度  //i == height.length 就是已经走出array
            int curt = (i == height.length) ? -1 : height[i];

            //curt <= height[stack.peek()]) 高度不再增长
            while (!stack.isEmpty() && curt <= height[stack.peek()]) {
                int h = height[stack.pop()];

                //right bound = i
                //left bound = stack.peek() + 1
                //回头看， 离得最近的，这儿用stack.peek()
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }

        return max;
    }


//-------------------------------------------------------------------------//


    public int largestRectangleArea1(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        //store the index
        Deque<Integer> stack = new LinkedList<Integer>();
        int max = 0;

        //Each elem will be push once and poll once
        for(int i = 0; i  <= array.length; i++) {

            //1. Check whether this elem can be pushed into the stack
            int curVal = i == array.length ? 0 : array[i];

            while(!stack.isEmpty() && array[stack.peekLast()]  >= curVal) {

                int height = array[stack.pollLast()];

                int leftBound = stack.isEmpty() ? 0 : stack.peekLast() + 1;
                int rightBound = i;

                max = Math.max(max, height * (rightBound - leftBound));
            }

            //2. Push the elem into the stack
            stack.addLast(i);
        }
        return max;
    }



}
/*Given n non-negative integers representing the histogram's bar 
 * height where the width of each bar is 1, find the area of largest 
 * rectangle in the histogram.
 



Above is a histogram where width of each bar is 1, given height = 
[2,1,5,6,2,3].



The largest rectangle is shown in the shaded area,
 which has area = 10 unit.

Have you met this question in a real interview? Yes
Example
Given height = [2,1,5,6,2,3],
return 10.

Tags 
Array Stack
Related Problems 
Hard Maximal Rectangle 24 %
Hard Max Tree 29 %*/