package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _084_Stack_Largest_Rectangle_in_Histogram_H {
//    O(n) stack based JAVA solution
//    For explanation, please see http://www.geeksforgeeks.org/largest-rectangle-under-histogram/

    public class Solution {
        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            Stack<Integer> s = new Stack<Integer>();
            int maxArea = 0;
            for(int i = 0; i <= len; i++){
                int h = (i == len ? 0 : heights[i]);
                if(s.isEmpty() || h >= heights[s.peek()]){
                    s.push(i);
                }else{
                    int tp = s.pop();
                    maxArea = Math.max(maxArea, heights[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                    i--;
                }
            }
            return maxArea;
        }
    }
/*    OP's Note: Two years later I need to interview again. I came to this problem and I couldn't understand this solution. After reading the explanation through the link above, I finally figured this out again.
    Two key points that I found helpful while understanding the solution:

    Do push all heights including 0 heights.
            i - 1 - s.peek() uses the starting index where heights[s.peek() + 1] >= heights[tp], because the index on top of the stack right now is the first index left of tp with heights smaller than tp's heights.*/





/*
5ms O(n) Java solution explained (beats 96%)
For any bar i the maximum rectangle is of width r - l - 1 where r - is the last coordinate of the bar to the right with heights h[r] >= h[i] and l - is the last coordinate of the bar to the left which heights h[l] >= h[i]

So if for any i coordinate we know his utmost higher (or of the same heights) neighbors to the right and to the left, we can easily find the largest rectangle:

int maxArea = 0;
for (int i = 0; i < heights.length; i++) {
    maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
}
The main trick is how to effectively calculate lessFromRight and lessFromLeft arrays. The trivial solution is to use O(n^2) solution and for each i element first find his left/right heighbour in the second inner loop just iterating back or forward:

for (int i = 1; i < heights.length; i++) {
    int p = i - 1;
    while (p >= 0 && heights[p] >= heights[i]) {
        p--;
    }
    lessFromLeft[i] = p;
}
The only line change shifts this algorithm from O(n^2) to O(n) complexity: we don't need to rescan each item to the left - we can reuse results of previous calculations and "jump" through indices in quick manner:

while (p >= 0 && heights[p] >= heights[i]) {
      p = lessFromLeft[p];
}
Here is the whole solution:
 */

class Solution2{
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[heights.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[heights.length]; // idx of the first bar the right that is lower than current
        lessFromRight[heights.length - 1] = heights.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;

            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < heights.length && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }
}

//AC clean Java solution using stack
class Solution3{
    public int largestRectangleArea(int[] h) {
        int n = h.length, i = 0, max = 0;

        Stack<Integer> s = new Stack<>();

        while (i < n) {
            // as long as the current bar is shorter than the last one in the stack
            // we keep popping out the stack and calculate the area based on
            // the popped bar
            while (!s.isEmpty() && h[i] < h[s.peek()]) {
                // tricky part is how to handle the index of the left bound
                max = Math.max(max, h[s.pop()] * (i - (s.isEmpty() ? 0 : s.peek() + 1)));
            }
            // put current bar's index to the stack
            s.push(i++);
        }

        // finally pop out any bar left in the stack and calculate the area based on it
        while (!s.isEmpty()) {
            max = Math.max(max, h[s.pop()] * (n - (s.isEmpty() ? 0 : s.peek() + 1)));
        }

        return max;
    }
}

//Share my 2ms Java solution. Beats 100% Java submissions
public class Solution4 {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        return getMax(heights, 0, heights.length);
    }
    int getMax(int[] heights, int s, int e) {
        if (s + 1 >= e) return heights[s];
        int min = s;
        boolean sorted = true;
        for (int i = s; i < e; i++) {
            if (i > s && heights[i] < heights[i - 1]) sorted = false;
            if (heights[min] > heights[i]) min = i;
        }
        if (sorted) {
            int max = 0;
            for (int i = s; i < e; i++) {
                max = Math.max(max, heights[i] * (e - i));
            }
            return max;
        }
        int left = (min > s) ? getMax(heights, s, min) : 0;
        int right = (min < e - 1) ? getMax(heights, min + 1, e) : 0;
        return Math.max(Math.max(left, right), (e - s) * heights[min]);
    }
}


/////////////////////////////////////////////////////////
    //  http://www.cnblogs.com/yuzhangcmu/p/4191981.html
    //jiuzhang
    public class Jiuzhang {
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }

            Stack<Integer> stack = new Stack<Integer>();
            int max = 0;
            for (int i = 0; i <= heights.length; i++) {
                int curt = (i == heights.length) ? -1 : heights[i];
                while (!stack.isEmpty() && curt <= heights[stack.peek()]) {
                    int h = heights[stack.pop()];
                    int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                    max = Math.max(max, h * w);
                }
                stack.push(i);
            }

            return max;
        }
    }

}
/*

 */