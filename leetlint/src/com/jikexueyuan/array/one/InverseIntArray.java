package com.jikexueyuan.array.one;

import org.junit.Test;

/**
 * Created by Student on 12/27/16.
 */
public class InverseIntArray {

    public void swap(int[] array, int a, int b) {
        int t = array[a];
        array[a] = array[b];
        array[b] = t;
    }

    public void printArray(int[] array) {
        for (int i : array) {
            System.out.println(i + " ");
        }
    }

    public void invert01(int[] array){
        if (array == null | array.length <= 1){
            return;
        }
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }

    public void invert02(int[] array){
        if (array == null | array.length <= 1){
            return;
        }
        int n = array.length;
        int half = n / 2;
        for(int i = 0; i < half; i++){
            swap(array, i, n - i - 1);
        }

    }

    @Test
    public void test01(){
        int[] array1={1,2,3,4,5,6,7};
        printArray(array1);
        invert01(array1);
        printArray(array1);

        int[] array2={1,2,3,4,5,6};
        printArray(array2);
        invert01(array2);
        printArray(array2);
    }

    @Test
    public void test02(){
        int[] array2 = {1, 2, 3, 4, 5, 6, 7};
        printArray(array2);
        invert02(array2);
        printArray(array2);



    }
}







