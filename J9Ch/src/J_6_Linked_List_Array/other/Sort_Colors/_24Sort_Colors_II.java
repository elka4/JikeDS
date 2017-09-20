package J_6_Linked_List_Array.other.Sort_Colors;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// k  colors



public class _24Sort_Colors_II {

    // version 1: O(nlogk), the best algorithm based on comparing
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */

    // Devide and Conquer

    public void sortColors2(int[] colors, int k) {
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
                int temp = colors[l];
                colors[l] = colors[r];
                colors[r] = temp;

                l++;
                r--;
            }
        }

        /*

        while (i <= pr) {
            if (a[i] == 0) {
                swap(a, pl, i);
                pl++;
                i++;
            } else if(a[i] == 1) {
                i++;
            } else {
                swap(a, pr, i);
                pr--;
            }
        }
         */

        rainbowSort(colors, left, r, colorFrom, colorMid);



        rainbowSort(colors, l, right, colorMid + 1, colorTo);


    }

//////////////////////////////////////////////////////////////////////
    //本方法超时

    // version 2: O(nk), not efficient, will get Time Limit Exceeded error.
    // But you should try to implement the following algorithm for practicing purpose.
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors22(int[] colors, int k) {
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

        }
    }

    void swap(int left, int right, int[] colors) {
        int tmp = colors[left];
        colors[left] = colors[right];
        colors[right] = tmp;
    }

//////////////////////////////////////////////////////////////////////


    @Test
    public void test01(){
        int[] colors = {3, 2, 2, 1, 4};
        sortColors2(colors,4);
        for (int i: colors
             ) {
            System.out.println(i);
        }
    }

    @Test
    public void test02() throws FileNotFoundException {


        Scanner in = new Scanner(new File(
                "/Users/tianhuizhu/Downloads/uber/code/JikeDS/J9Ch/src/J_6_Linked_List_Array/other/Sort_Colors/input2"));

        int n = in.nextInt();
        List<Integer> list = new LinkedList<>();
        //list.add(n);
        while (n != -1) {
            list.add(n);

            n = in.nextInt();
        }

        int[] colors = new int[list.size()];
        for (int i = 0; i < list.size(); i++ ) {
            colors[i] = list.get(i);
        }

        sortColors2(colors,10000);
        for (int i: colors
             ) {
            System.out.println(i);
        }
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