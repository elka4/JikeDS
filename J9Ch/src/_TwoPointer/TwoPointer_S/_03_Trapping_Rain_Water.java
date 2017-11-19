package _TwoPointer.TwoPointer_S;
import java.util.*;

//Version 0: Two pointer
//  42. Trapping Rain Water
//  https://leetcode.com/problems/trapping-rain-water/
//  http://www.lintcode.com/zh-cn/problem/trapping-rain-water/
//   Two Pointers Stack
public class _03_Trapping_Rain_Water {
    //  https://leetcode.com/problems/trapping-rain-water/solution/
//-------------------------------------------------------------------------/////////////
//-------------------------------------------------------------------------/////////////

    //  https://segmentfault.com/a/1190000004594606

/*    Note
    有两种方法。一种是利用Stack去找同一层的两个边，不断累加寄存。
    如[2, 1, 0, 1, 2]，2入栈，1入栈，0入栈，下一个1大于栈顶元素0，则计算此处的雨水量加入res，
    此过程中0从栈中弹出，1入栈，到下一个2，先弹出1，由于之前还有一个1在栈中，
    所以计算时高度的因数相减为0，雨水量为0，res无变化，继续pop出栈中的元素，也就是之前的1，
    此时stack中仍有元素2，说明左边还有边，继续计算底层高度为1，两个值为2的边之间的水量，加入res。
    */

/*    Solution
1. Stack*/

    public class Solution3 {
        public int trapRainWater(int[] height) {
            Stack<Integer> stack = new Stack<Integer>();
            int res = 0;

            for (int i = 0; i < height.length; i++) {
                if (stack.isEmpty() || height[i] < height[stack.peek()])
                    stack.push(i);
                else {
                    while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                        int pre = stack.pop();
                        if (!stack.isEmpty()) {
                            res += (Math.min(height[i],
                                    height[stack.peek()]) - height[pre]) * (i - stack.peek() - 1);
                        }
                    }
                    stack.push(i);
                }
            }
            return res;
        }
    }

    //2. Two-Pointer
/*
    双指针法的思想：先找到左右两边的第一个峰值作为参照位，然后分别向后（向前）每一步增加该位与参照位在这一位的差值，
    加入sum，直到下一个峰值，再更新为新的参照位。

    这里有一个需要注意的地方，为什么要先计算左右两个峰值中较小的那个呢？
    因为在两个指针逼近中间组成最后一个积水区间时，要用较短边计算。
 */
    public class Solution4 {
        public int trap(int[] height) {
            int left = 0;
            int right = height.length-1;
            int res = 0;

            while (left < right && height[left] < height[left+1])
                left++;
            while (left < right && height[right] < height[right-1])
                right--;

            while (left < right) {
                int leftmost = height[left];
                int rightmost = height[right];
                
                if (leftmost < rightmost) {
                    while (left < right && height[++left] < leftmost)
                        res += leftmost - height[left];
                }
                else {
                    while (left < right && height[--right] < rightmost)
                        res += rightmost - height[right];
                }
            }
            return res;
        }
    }
//-------------------------------------------------------------------------/////////////
    // jiuzhang
    //Version 0: Two pointer
    public int trapRainWater1(int[] heights) {
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

    // Version 1
    public int trapRainWater2(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }

        int[] maxHeights = new int[heights.length + 1];
        maxHeights[0] = 0;
        for (int i = 0; i < heights.length; i++) {
            maxHeights[i + 1] = Math.max(maxHeights[i], heights[i]);
        }

        int max = 0;
        int area = 0;
        for (int i = heights.length - 1; i >= 0; i--) {
            area += Math.min(max, maxHeights[i]) > heights[i]
                    ? Math.min(max, maxHeights[i]) - heights[i]
                    : 0;
            max = Math.max(max, heights[i]);
        }

        return area;
    }


//-------------------------------------------------------------------------/////////////
}
/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

Trapping Rain Water

样例
如上图所示，海拔分别为 [0,1,0,2,1,0,1,3,2,1,2,1], 返回 6.
 */