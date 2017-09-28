package J_7_2Pointers.all;

/** 143 Sort Colors II
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */

// sort k colors
public class _143_Sort_Colors_II {

    // version 1: O(nlogk), the best algorithm based on comparing
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }

    public void rainbowSort(int[] colors,
                            int left,
                            int right,
                            int colorFrom,
                            int colorTo) {
        if (colorFrom == colorTo) {
            return;
        }

        if (left >= right) {
            return;
        }

        int colorMid = (colorFrom + colorTo) / 2;
        int l = left, r = right;

        while (l <= r) {
            while (l <= r && colors[l] <= colorMid) {
                l++;
            }
            while (l <= r && colors[r] > colorMid) {
                r--;
            }
            if (l <= r) {
                swap(colors, l++, r--);
            }
        }

        rainbowSort(colors, left, r, colorFrom, colorMid);
        rainbowSort(colors, l, right, colorMid + 1, colorTo);
    }



//////////////////////////////////////////////////////////////////////////////
void swap(int[] colors, int left, int right) {
    int tmp = colors[left];
    colors[left] = colors[right];
    colors[right] = tmp;
}
//////////////////////////////////////////////////////////////////////////////

// version 2: O(nk), not efficient, will get Time Limit Exceeded error.
// But you should try to implement the following algorithm for practicing purpose.
    /**
     * @param colors: A list of integer
     * @param k:      An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        int count = 0;
        int left = 0;
        int right = colors.length - 1;

        while (count < k) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = left; i <= right; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }

            int cur = left;

            while (cur <= right) {
                if (colors[cur] == min) {
                    swap(colors, left, cur);
                    cur++;
                    left++;
                } else if (colors[cur] > min && colors[cur] < max) {
                    cur++;
                } else {
                    int tmp = colors[cur];
                    swap(colors, cur, right );
                    right--;
                }
            }
            count += 2;

        }

    }



/*
Given an array of n objects with k different colors (numbered from 1 to k),
 sort them so that objects of the same color are adjacent, with the colors
 in the order 1, 2, ... k.

 Notice

You are not suppose to use the library's sort function for this problem.

k <= n

Have you met this question in a real interview? Yes
Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to
 [1, 2, 2, 3, 4].
 */

}
