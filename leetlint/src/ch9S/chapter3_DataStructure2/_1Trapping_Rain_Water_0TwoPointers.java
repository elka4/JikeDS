package ch9S.chapter3_DataStructure2;
//Version 0: Two Pointers:
public class _1Trapping_Rain_Water_0TwoPointers {
	public int trapRainWater(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int start = 0;
        int end = height.length - 1;
        int smaller;
        int area = 0;
        while (start < end) {
            if (height[start] < height[end]) {
                smaller = height[start];
                while (start < end && height[start] <= smaller) {
                    area += smaller - height[start];
                    start++;
                }
            } else {
                smaller = height[end];
                while (start < end && height[end] <= smaller) {
                    area += smaller - height[end];
                    end--;
                }
            }
        }
        return area;
    }
}
