package _TwoPointer.All_TwoPointer;


//  42. Trapping Rain Water

//  https://leetcode.com/problems/trapping-rain-water/description/
public class _042_TwoPointer_Trapping_Rain_Water_H {
//    Share my short solution.
//    Keep track of the maximum height from both forward directions backward directions, call them leftmax and rightmax.

    public int trap(int[] A){
        int a=0;
        int b=A.length-1;
        int max=0;
        int leftmax=0;
        int rightmax=0;
        while(a<=b){
            leftmax=Math.max(leftmax,A[a]);
            rightmax=Math.max(rightmax,A[b]);
            if(leftmax<rightmax){
                // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
                max+=(leftmax-A[a]);
                a++;
            }
            else{
                max+=(rightmax-A[b]);
                b--;
            }
        }
        return max;
    }
/////////////////////////////////////////////////////////////////
    //jiuzhang
//Version 0: Two pointer
public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        // write your code here
        int left = 0, right = heights.length - 1;
        int res = 0;
        if(left >= right)
            return res;
        int leftheight = heights[left];
        int rightheight = heights[right];
        while(left < right) {
            if(leftheight < rightheight) {
                left ++;
                if(leftheight > heights[left]) {
                    res += (leftheight - heights[left]);
                } else {
                    leftheight = heights[left];
                }
            } else {
                right --;
                if(rightheight > heights[right]) {
                    res += (rightheight - heights[right]);
                } else {
                    rightheight = heights[right];
                }
            }
        }
        return res;
    }
}

    // Version 1
    public class Jiuzhang2 {
        /**
         * @param heights: an array of integers
         * @return: a integer
         */
        public int trapRainWater(int[] heights) {
            if (heights.length == 0) {
                return 0;
            }

            int[] maxHeights = new int[heights.length + 1];
            maxHeights[0] = 0;
            for (int i = 0; i < heights.length; i++) {
                maxHeights[i + 1] = Math.max(maxHeights[i], heights[i]);
            }

            int max = 0, area = 0;
            for (int i = heights.length - 1; i >= 0; i--) {
                area += Math.min(max, maxHeights[i]) > heights[i]
                        ? Math.min(max, maxHeights[i]) - heights[i]
                        : 0;
                max = Math.max(max, heights[i]);
            }

            return area;
        }
    }



}
/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */