package Ch_08_Recursion_and_Dynamic_Programming.Q8_03_Magic_Index;

import CtCILibrary.AssortedMethods;
import org.junit.Test;

import java.util.Arrays;

public class QuestionB {

	public static int magicSlow(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == i) {
				return i;
			}
		}
		return -1;
	}
    public static int magicFast_0(int[] array, int start, int end) {
        if (end < start) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (array[mid] == mid) {
            return mid;
        } else if (array[mid] > mid){
            return magicFast(array, start, mid - 1);
        } else {
            return magicFast(array, mid + 1, end);
        }
    }
	public static int magicFast(int[] array, int start, int end) {
		if (end < start) {
			return -1;
		}
		int midIndex = (start + end) / 2;
		int midValue = array[midIndex];
		if (midValue == midIndex) {
			return midIndex;
		}
		/* Search left */
		int leftIndex = Math.min(midIndex - 1, midValue);
		int left = magicFast(array, start, leftIndex);
		if (left >= 0) {
			return left;
		}
		
		/* Search right */
		int rightIndex = Math.max(midIndex + 1, midValue);
		int right = magicFast(array, rightIndex, end);
		
		return right;
	}
	
	public static int magicFast(int[] array) {
		return magicFast(array,
                0, array.length - 1);
	}
	
	/* Creates an array that is sorted */
	public static int[] getSortedArray(int size) {
		int[] array = AssortedMethods.randomArray(size, -1 * size, size);
		Arrays.sort(array);
		return array;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			int size = AssortedMethods.randomIntInRange(5, 20);
			int[] array = getSortedArray(size);
			int v2 = magicFast(array);
            for (int j = 0; j < array.length; j++) {
                //System.out.println(j + " ");
            }
            //System.out.println("v2: " + v2);
            if (v2 == -1 && magicSlow(array) != -1) {
				int v1 = magicSlow(array);
				System.out.println("Incorrect value: index = -1, actual = "
                        + v1 + " " + i);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			} else if (v2 > -1 && array[v2] != v2) {
				System.out.println("Incorrect values: index= "
                        + v2 + ", value " + array[v2]);
				System.out.println(AssortedMethods.arrayToString(array));
				break;
			}
		}
	}
	@Test
    public void test01(){
        int size = AssortedMethods.randomIntInRange(5, 20);
        int[] array = getSortedArray(size);
        int v2 = magicFast(array);
        for (int j = 0; j < array.length; j++) {
            System.out.println(j + " " + array[j]);
        }
        System.out.println("v2: " + v2);

    }
    /*
                        0 -6
                        1 -5
                        2 0
                        3 1
                        4 4
                        5 5
                        v2: 4
     */

}
