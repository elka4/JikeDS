package _TwoPointer.TwoPointer_S;

//  11. Container With Most Water
//  https://leetcode.com/problems/container-with-most-water/description/
//  http://www.lintcode.com/en/problem/container-with-most-water/
public class _04_Container_With_Most_Water {
    //https://leetcode.com/problems/container-with-most-water/solution/
    //Approach #1 Brute Force [Time Limit Exceeded]
    public int maxArea11(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxArea;
    }

        //Approach #2 (Two Pointer Approach) [Accepted]
        public int maxArea22(int[] height) {
            int maxarea = 0;
            int left = 0;
            int right = height.length - 1;

            while (left < right) {
                maxarea = Math.max(maxarea, Math.min(height[left], height[right]) * (right - left));

                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }
            return maxarea;
        }



    //-----------------------------------------------------------------------------//
    // 9Ch
    int computeArea(int left, int right,  int[] heights) {
        return (right-left)*Math.min(heights[left], heights[right]);
    }
    
    public int maxArea1(int[] heights) {
        // write your code here
        int left = 0;
        int right = heights.length - 1;
        int ans = 0;

        while(left <= right) {
            ans = Math.max(ans,computeArea(left, right, heights));

            if(heights[left]<=heights[right])
                left++;
            else
                right--;
        }
        return ans;
    }



    // for any i, the maxium area will be the farthest j that has a[j] > a[i];
    public int maxArea2(int[] height) {
        if(height == null || height.length < 2) {
            return 0;
        }
        int max = 0;
        int left = 0;
        int right = height.length -1;

        while(left < right) {
            max = Math.max( max, (right - left) * Math.min(height[left], height[right]));
    /*if height[left] < height[right] 左边最低
     * 从left到right中间的都不需要考虑
     * ［left, right - 1］,［left, right - 2］,.....
     *
     * 中间这些不管多高，都会从最左边漏掉
     * 就是说，左边已经漏了，右边的不管是变成多少，都不会让面积更大（本题求最大）
     * 因为不需要考虑，所以可以left＋＋，看下一轮谁是漏点
     */
            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
/*
给定 n 个非负整数 a1, a2, ..., an, 每个数代表了坐标中的一个点 (i, ai)。画 n 条垂直线，使得 i 垂直线的两个端点分别为(i, ai)和(i, 0)。找到两条线，使得其与 x 轴共同构成一个容器，以容纳最多水。

 注意事项

容器不可倾斜。

样例
给出[1,3,2], 最大的储水面积是2.
 */

/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.
 */