package _01_Array.Heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.*;

/*
Merge K Sorted Arrays in Java

This is a classic interview question. Another similar problem is "merge k sorted lists".

This problem can be solved by using a heap. The time is O(nlog(n)).

Given m arrays, the minimum elements of all arrays can form a heap. It takes O(log(m)) to insert an element to the heap and it takes O(1) to delete the minimum element.
 */

// Merge k sorted arrays
public class Merge_k_sorted_arrays {
    class ArrayContainer implements Comparable<ArrayContainer> {
        int[] arr;
        int index;

        public ArrayContainer(int[] arr, int index) {
            this.arr = arr;
            this.index = index;
        }

        @Override
        public int compareTo(ArrayContainer o) {

            return this.arr[this.index] - o.arr[o.index];
        }
    }

    public int[] mergeKSortedArray(int[][] arr) {
        //PriorityQueue is heap in Java
        PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>();
        int total=0;

        //add arrays to heap
        for (int i = 0; i < arr.length; i++) {
            queue.add(new ArrayContainer(arr[i], 0));
            total = total + arr[i].length;
        }

        int m=0;
        int result[] = new int[total];

        //while heap is not empty
        while(!queue.isEmpty()){
            ArrayContainer ac = queue.poll();
            result[m++]=ac.arr[ac.index];

            if(ac.index < ac.arr.length-1){
                queue.add(new ArrayContainer(ac.arr, ac.index+1));
            }
        }

        return result;
    }

    public  void main(String[] args) {
        int[] arr1 = { 1, 3, 5, 7 };
        int[] arr2 = { 2, 4, 6, 8 };
        int[] arr3 = { 0, 9, 10, 11 };

        int[] result = mergeKSortedArray(new int[][] { arr1, arr2, arr3 });
        System.out.println(Arrays.toString(result));
    }


    @Test
    public void test01(){
        int[][] arrays = {{ 1, 3, 5, 7 }, {2, 4, 6, 8}, {0, 9, 10, 11}};
        int[] result = mergeKSortedArray(arrays);
        for (int i : result ) {
            System.out.print(i + ", ");
        }
    }


/////////////////////////////////////////////////////////////////////////////////////

    //jiuzhang
    class Element {
        public int row, col, val;
        Element(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    private Comparator<Element> ElementComparator = new Comparator<Element>() {
        public int compare(Element left, Element right) {
            return left.val - right.val;
        }
    };

    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null) {
            return new int[0];
        }

        int total_size = 0;
        Queue<Element> Q = new PriorityQueue<Element>(arrays.length, ElementComparator);

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                Element elem = new Element(i, 0, arrays[i][0]);
                Q.add(elem);
                total_size += arrays[i].length;
            }
        }

        int[] result = new int[total_size];
        int index = 0;

        while (!Q.isEmpty()) {
            Element elem = Q.poll();
            result[index++] = elem.val;
            if (elem.col + 1 < arrays[elem.row].length) {
                elem.col += 1;
                elem.val = arrays[elem.row][elem.col];
                Q.add(elem);
            }
        }

        return result;
    }

    @Test
    public void test02(){
        int[][] arrays = {{1,5,9}, {2,4,7}, {3,6,8}};
        int[] result = mergekSortedArrays(arrays);
        for (int i : result ) {
            System.out.print(i + ", ");
        }
    }


/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////



}
