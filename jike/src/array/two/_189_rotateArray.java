package src.array.two;

import org.junit.Test;

/**
 * Created by Student on 12/28/16.
 */
public class _189_rotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        invert(nums, 0, n - 1);
        invert(nums, 0, k - 1);
        invert(nums, k, n - 1);

    }

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        invert(nums, 0, n - k - 1);//rotate front
        invert(nums, n - k, n - 1);//rotate behind
        invert(nums, 0, n - 1);

    }
    public void swap(int[] array, int a, int b){
        int t = array[a];
        array[a] = array[b];
        array[b] = t;
    }
    public void invert(int[] array, int a, int b){
        for(int i = a, j = b; i < j; i++, j--) {
            swap(array, i, j);
        }
    }
    public void print(int[] array) {
        for(int i : array) {
            System.out.print(i + " ");

        }
        System.out.println();
    }

    @Test
    public void test01() {
        int[] array = {1,2,3,4,5,6,7};
        print(array);

        //invert(array, 0, array.length - 1);
        rotate2(array, 3);
        print(array);

    }
}
