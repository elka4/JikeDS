package J_6_List_Array.Water_Triangle;

public class _8ContainerWithMostWater {
    //editorial
    //https://leetcode.com/articles/container-most-water/
    //Approach #1 Brute Force [Time Limit Exceeded]
    public int maxArea(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++)
                maxarea = Math.max(maxarea,
                        Math.min(height[i], height[j]) * (j - i));
        return maxarea;
    }

//-----------------------------------------------------------------------------//

    //对撞型指针
    public int maxArea2(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            //对撞前计算当前面积，和之前最大面积比较
            maxarea = Math.max(maxarea,
                    Math.min(height[l], height[r]) * (r - l));
            //较小的那一侧漏水，所以要向中间移动
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }


}
