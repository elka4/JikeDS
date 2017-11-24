package _01_Array.Two_Pointers_4;

/**
 * Created by tianhuizhu on 6/21/17.
 */

/*
Problem

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Analysis

Initially we can assume the result is 0. Then we scan from both sides. If leftHeight < rightHeight, move right and find a value that is greater than leftHeight. Similarily, if leftHeight > rightHeight, move left and find a value that is greater than rightHeight. Additionally, keep tracking the max value.
 */


public class b_11_Container_With_Most_Water {

    //Java Solution

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int max = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return max;
    }

//---------------------------------

    //Approach #1 Brute Force [Time Limit Exceeded]
    //In this case, we will simply consider the area for every possible
    // pair of the lines and find out the maximum area out of those.

    public int maxArea2(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++)
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
        return maxarea;
    }


//---------------------------------

    //Approach #2 (Two Pointer Approach) [Accepted]

    public int maxArea3(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///

}
