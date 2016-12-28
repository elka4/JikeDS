package com.jikexueyuan.one;

import org.junit.Test;

import java.util.Random;

/**
 * Created by Student on 12/28/16.
 */
public class TestInverseArray {

    @Test
    public void test01(){
        Person[] array = {new Person(1, "Ada"),
                new Person(2, "Bruce"),
                new Person(3, "Cathy"),
                new Person(4, "David")};
        InverseArray<Person> ia = new InverseArray<Person>();
        ia.printArray(array);
        ia.invert01(array);
        ia.printArray(array);

    }

    @Test
    public void test02() {
        Integer[] array = new Integer[7];
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            array[i] = random.nextInt(10);
        }
        InverseArray<Integer> ia = new InverseArray<>();
        ia.printArray(array);
        ia.invert01(array);
        ia.printArray(array);

    }
}
