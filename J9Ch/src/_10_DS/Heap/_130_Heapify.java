package _10_DS.Heap;

/** 130 Heapify
 * Created by tianhuizhu on 6/28/17.
 */

/*
Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, and for each A[i],
A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
Have you met this question in a real interview? Yes
Clarification
What is heap?

Heap is a data structure, which usually have three methods: push, pop and top.
where "push" add a new element the heap, "pop" delete the minimum/maximum element
in the heap, "top" return the minimum/maximum element.

What is heapify?
Convert an unordered integer array into a heap array. If it is min-heap,
for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].

What if there is a lot of solutions?
Return any of them.
Example
Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.

Challenge
O(n) time complexity
 */


public class _130_Heapify {

    // Version Linpz
    /**
     * @param A: Given an integer array
     * @return: void
     */
    private void siftdown(int[] A, int k) {
        while (k * 2 + 1 < A.length) {
            int son = k * 2 + 1;
            if (k * 2 + 2 < A.length && A[son] > A[k * 2 + 2])
                son = k * 2 + 2;
            if (A[son] >= A[k])
                break;

            int temp = A[son];
            A[son] = A[k];
            A[k] = temp;
            k = son;
        }
    }

    public void heapify(int[] A) {
        for (int i = (A.length - 1) / 2; i >= 0; i--) {
            siftdown(A, i);
        }
    }




//------------------------------------------------------------------------------


    // Version 1: this cost O(n)
    /**
     * @param A: Given an integer array
     * @return: void
     */
    private void siftdown2(int[] A, int k) {
        while (k < A.length) {
            int smallest = k;
            if (k * 2 + 1 < A.length && A[k * 2 + 1] < A[smallest]) {
                smallest = k * 2 + 1;
            }
            if (k * 2 + 2 < A.length && A[k * 2 + 2] < A[smallest]) {
                smallest = k * 2 + 2;
            }
            if (smallest == k) {
                break;
            }
            int temp = A[smallest];
            A[smallest] = A[k];
            A[k] = temp;

            k = smallest;
        }
    }

    public void heapify2(int[] A) {
        for (int i = A.length / 2; i >= 0; i--) {
            siftdown2(A, i);
        } // for
    }




//--------------------------------------------------------------------------------

    // Version 2: This cost O(nlogn)
    /**
     * @param A: Given an integer array
     * @return: void
     */
    private void siftup(int[] A, int k) {
        while (k != 0) {
            int father = (k - 1) / 2;
            if (A[k] > A[father]) {
                break;
            }
            int temp = A[k];
            A[k] = A[father];
            A[father] = temp;

            k = father;
        }
    }

    public void heapify3(int[] A) {
        for (int i = 0; i < A.length; i++) {
            siftup(A, i);
        }
    }

//------------------------------------------------------------------------------






//------------------------------------------------------------------------------






//------------------------------------------------------------------------------






//------------------------------------------------------------------------------







}
