package com.jikexueyuan.one;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Student on 12/27/16.
 */
public class InverseIntArrayTest {
    @Test
    public void test01() {
        int[] array = {1, 2, 3, 4, 5};
        printArray(array);
        invertArray(array);
        printArray(array);

    }


}