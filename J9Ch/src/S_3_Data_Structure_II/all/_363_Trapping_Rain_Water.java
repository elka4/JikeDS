package S_3_Data_Structure_II.all;

/** 363 Trapping Rain Water


 * Created by tianhuizhu on 6/28/17.
 */
public class _363_Trapping_Rain_Water {

    //Version 0: Two pointer
    public class Solution1 {
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
    public class Solution2 {
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
