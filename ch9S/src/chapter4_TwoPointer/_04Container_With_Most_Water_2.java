package chapter4_TwoPointer;

public class _04Container_With_Most_Water_2 {
// for any i, the maxium area will be the farthest j that has a[j] > a[i];
public int maxArea(int[] height) {
    if(height == null || height.length < 2) {
        return 0;
    }
    int max = 0;
    int left = 0;
    int right = height.length -1;
    while(left < right) {
        max = Math.max( max, (right - left) 
        		* Math.min(height[left], height[right]));
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
