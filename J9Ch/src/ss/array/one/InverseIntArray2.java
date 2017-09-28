package ss.array.one;

import org.junit.Test;

/**
 * Created by Student on 12/28/16.
 */
public class InverseIntArray2 {
    public void swap(int[] array, int a, int b) {
        int t = array[a];
        array[a] = array[b];
        array[b] = t;
    }

    public void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void invert01(int[] array){
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }

    public void invert02(int[] array){
        if (array == null || array.length <= 1) {
            return;
        }
        int n = array.length;
        int half = n / 2;
        for (int i = 0; i < half; i++) {
            swap(array, i, n - i - 1);
        }
    }

    @Test
    public void test01(){
        int[] array = {1, 2, 3, 4, 5, 6};
        printArray(array);
        invert01(array);
        printArray(array);
    }

    @Test
    public void test02(){
        int[] array = {1,2,3,4,5,6,7};
        printArray(array);
        invert01(array);
        printArray(array);
    }

    @Test
    public void test03() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        printArray(array);
        invert02(array);
        printArray(array);
    }

}
