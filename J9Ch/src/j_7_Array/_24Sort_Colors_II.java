package j_7_Array;

public class _24Sort_Colors_II {
public void sortColors2(int[] colors, int k) {
    int count = 0;
    int start = 0;
    int end = colors.length-1;
    while (count < k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i = start; i <= end; i++) {
            min = Math.min(min, colors[i]);
            max = Math.max(max, colors[i]);
        }
        int left = start;
        int right = end;
        int cur = left;
        while(cur <= right) {
            if (colors[cur] == min) {
                swap(left, cur, colors);
                cur++;
                left++;
            } else if (colors[cur] > min && colors[cur] < max) {
                cur++;
            } else {
                int tmp = colors[cur];
                swap(cur, right, colors);
                right--;
            }
        }
        count += 2;
        start = left;
        end = right;
    }
}

void swap(int left, int right, int[] colors) {
    int tmp = colors[left];
    colors[left] = colors[right];
    colors[right] = tmp;
}
}

/*Given an array of n objects with k different colors 
 * (numbered from 1 to k), sort them so that objects of the same 
 * color are adjacent, with the colors in the order 1, 2, ... k.
 

 Notice

You are not suppose to use the library's sort function for this problem.

Have you met this question in a real interview? Yes
Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors
 in-place to [1, 2, 2, 3, 4].

Challenge 
A rather straight forward solution is a two-pass algorithm using 
counting sort. That will cost O(k) extra memory. 
Can you do it without using extra memory?

Tags 
Two Pointers Sort
Related Problems 
Medium Wiggle Sort 40 %
Medium Wiggle Sort II 24 %
Medium Sort Colors 34 %*/