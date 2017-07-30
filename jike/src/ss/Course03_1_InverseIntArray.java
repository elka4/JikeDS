package ss;

import org.junit.Test;

/**
 * 整型数组的逆置
 */
public class Course03_1_InverseIntArray {
    public void swap(int[] array,int a,int b){
        int t=array[a];
        array[a]=array[b];
        array[b]=t;
    }

    public void printArray(int[] array){
        for(int i:array){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public void inverse01(int[] array){
        if(array==null||array.length<=1){
            return;
        }
        for(int i=0,j=array.length-1;i<j;i++,j--){
            swap(array, i, j);
        }
    }

    public void inverse02(int[] array){
        if(array==null||array.length<=1){
            return;
        }
        int n=array.length;
        int half=n/2;
        for(int i=0;i<half;i++){
            swap(array, i, n-1-i);
        }
    }
    @Test
    public void test01(){
        int[] array1={1,2,3,4,5,6,7};
        printArray(array1);
        inverse01(array1);
        printArray(array1);
        int[] array2={1,2,3,4,5,6};
        printArray(array2);
        inverse01(array2);
        printArray(array2);
    }
    @Test
    public void test02(){
        int[] array1={1,2,3,4,5,6,7};
        printArray(array1);
        inverse02(array1);
        printArray(array1);
        int[] array2={1,2,3,4,5,6};
        printArray(array2);
        inverse02(array2);
        printArray(array2);
    }
}
