package Bit106.L06;

/**
 * Implement mergeSort
 */
public class MergeSort {
    public static void sort(int[] array) {
        // Remember we need a temp array to do merge
        mergesort(array, new int[array.length], 0, array.length - 1);
    }

    public static void mergesort(int[] array, int[] temp, int leftStart, int rightEnd) {
        if (leftStart >= rightEnd) {
            return;
        }
        int middle = (leftStart + rightEnd) / 2;

        mergesort(array, temp, leftStart, middle);

        mergesort(array, temp, middle + 1, rightEnd);

        mergeHalves(array, temp, leftStart, rightEnd);
    }

    public static void mergeHalves(int[] array, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (rightEnd + leftStart) / 2;
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        // Walking thru each half of the array
        // and copy the smaller element
        while(left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                temp[index] = array[left];
                left++;
            }
            else {
                temp[index] = array[right];
                right++;
            }
            index++;
        }

        // Jump out of the while loop when any half is out of boundary
        // Copy the remaining elements in left/right half into temp

        // arraycopy: copy from array starting from left to temp starting from index,
        // copy (leftEnd - left + 1) elements
        // only one of the two lines will be valid
        System.arraycopy(array, left, temp, index, leftEnd - left + 1);
        System.arraycopy(array, right, temp, index, rightEnd - right + 1);

        System.arraycopy(temp, leftStart, array, leftStart, size);
    }
}

