package com.jikexueyuan.array.one;

import org.junit.Test;


/**
 * Created by Student on 12/28/16.
 */
public class InverseArray<T> {
    public void swap (T[] array, int a, int b){
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void printArray (T[] array){
        for (T i : array) {
            System.out.println(i + " ");
        }
        System.out.println();
    }

    public void invert01 (T[] array){
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }



}
