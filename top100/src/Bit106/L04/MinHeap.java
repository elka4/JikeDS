package Bit106.L04;

import java.util.Arrays;

/**
 * Implement a min heap
 */
public class MinHeap {
    private int capacity = 10;
    private int size = 0;

    int[] items = new int[capacity];

    // Supporting methods
    private int getParentIndex(int childIndex) {
        return (childIndex - 1)/2;
    }
    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }
    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }
    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }
    private int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }
    private int parent(int index) {
        return items[getParentIndex(index)];
    }

    private void swap(int index1, int index2){
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    public int peek() throws Exception {
        if (size == 0) throw new Exception();
        return items[0];
    }

    public int poll() throws Exception {
        if (size == 0) throw new Exception();
        int item = items[0];
        items[0] = items[size - 1]; // Swap the root with the last one
        size--; // remove
        bubbleDown();
        return item;
    }

    public void add(int item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        bubbleUp();
    }

    public void bubbleUp() {
        int index = size - 1; // Start from the last node
        while ( hasParent(index) && parent(index) > items[index]) { // out of order, swap!
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void bubbleDown() {
        int index = 0; // start from the root
        while (hasLeftChild(index)) { // don't need to check right child
            // as if there is no left child, there is no right child either
            // get the smaller one between left and right
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items[index] < items[smallerChildIndex]) { // everything is good!
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex; // go to the next level
        }
    }


}
