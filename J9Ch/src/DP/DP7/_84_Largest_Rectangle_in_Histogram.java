package DP.DP7;
import java.util.*;

//  84. Largest Rectangle in Histogram
//  https://leetcode.com/problems/largest-rectangle-in-histogram/description/
//  4:
public class _84_Largest_Rectangle_in_Histogram {
//------------------------------------------------------------------------------
    //https://leetcode.com/problems/largest-rectangle-in-histogram/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution01 {
        public int largestRectangleArea(int[] heights) {
            int maxarea = 0;
            for (int i = 0; i < heights.length; i++) {
                for (int j = i; j < heights.length; j++) {
                    int minheight = Integer.MAX_VALUE;
                    for (int k = i; k <= j; k++)
                        minheight = Math.min(minheight, heights[k]);
                    maxarea = Math.max(maxarea, minheight * (j - i + 1));
                }
            }
            return maxarea;
        }
    }
//------------------------------------------------------------------------------
    //2
    //Approach #2 Better Brute Force[Time Limit Exceeded]
    public class Solution02 {
        public int largestRectangleArea(int[] heights) {
            int maxarea = 0;
            for (int i = 0; i < heights.length; i++) {
                int minheight = Integer.MAX_VALUE;
                for (int j = i; j < heights.length; j++) {
                    minheight = Math.min(minheight, heights[j]);
                    maxarea = Math.max(maxarea, minheight * (j - i + 1));
                }
            }
            return maxarea;
        }
    }
//------------------------------------------------------------------------------
    //3
    //Approach #3 (Divide and Conquer Approach) [Time Limit Exceeded]
    public class Solution03 {
        public int calculateArea(int[] heights, int start, int end) {
            if (start > end)
                return 0;
            int minindex = start;
            for (int i = start; i <= end; i++)
                if (heights[minindex] > heights[i])
                    minindex = i;
            return Math.max(heights[minindex] * (end - start + 1),
                    Math.max(calculateArea(heights, start, minindex - 1),
                            calculateArea(heights, minindex + 1, end)));
        }
        public int largestRectangleArea(int[] heights) {
            return calculateArea(heights, 0, heights.length - 1);
        }
    }
    //Approach #4 (Better Divide and Conquer) [Accepted]

    //Approach #5 (Using Stack) [Accepted]
    public class Solution05 {
        public int largestRectangleArea(int[] heights) {
            Stack < Integer > stack = new Stack < > ();
            stack.push(-1);
            int maxarea = 0;
            for (int i = 0; i < heights.length; ++i) {
                while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                    maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
                stack.push(i);
            }
            while (stack.peek() != -1)
                maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
            return maxarea;
        }
    }
//--------------------------------------------------------------------------------
    //4
    //Simple Divide and Conquer AC solution without Segment Tree
    class Solution6{
        public int largestRectangleAreaDQ(int[] A) {
            if (A == null || A.length == 0)
                return 0;
            return maxArea(A, 0, A.length - 1);
        }

        int maxArea(int[] A, int l, int r) {
            if (l == r)
                return A[l];
            int m = l + (r - l) / 2;
            int area = maxArea(A, l, m);
            area = Math.max(area, maxArea(A, m + 1, r));
            area = Math.max(area, maxCombineArea(A, l, m, r));
            return area;
        }


        int maxCombineArea(int[] A, int l, int m, int r) {
            int i = m, j = m + 1;
            int area = 0, h = Math.min(A[i], A[j]);
            while (i >= l && j <= r) {
                h = Math.min(h, Math.min(A[i], A[j]));
                area = Math.max(area, (j - i + 1) * h);
                if (i == l)
                    ++j;
                else if (j == r)
                    --i;
                else {
                    if (A[i - 1] > A[j + 1])
                        --i;
                    else
                        ++j;
                }
            }
            return area;
        }
    }

//--------------------------------------------------------------------------------
}
/*

 */