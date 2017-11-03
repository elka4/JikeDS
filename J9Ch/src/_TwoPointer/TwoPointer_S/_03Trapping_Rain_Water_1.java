package _TwoPointer.TwoPointer_S;
//Version 1
public class _03Trapping_Rain_Water_1 {
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
