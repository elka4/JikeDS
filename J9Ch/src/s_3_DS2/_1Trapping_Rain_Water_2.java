package s_3_DS2;
//Version2:
public class _1Trapping_Rain_Water_2 {
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
