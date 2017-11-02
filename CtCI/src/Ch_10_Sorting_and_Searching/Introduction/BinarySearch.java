package Ch_10_Sorting_and_Searching.Introduction;

public class BinarySearch {

	public static int binarySearch(int[] a, int x) {
		int low = 0;
		int high = a.length - 1;
		int mid;
		
		while (low <= high) {
			mid = (low + high) / 2;
			if (a[mid] < x) {
				low = mid + 1;
			} else if (a[mid] > x) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	public static int binarySearchRecursive(int[] a, int x, int low, int high) {
		if (low > high) return -1; // Error
		
		int mid = (low + high) / 2;
		if (a[mid] < x) {
			return binarySearchRecursive(a, x, mid + 1, high);
		} else if (a[mid] > x) {
			return binarySearchRecursive(a, x, low, mid - 1);
		} else {
			return mid;
		}
	}
	
	// Recursive algorithm to return the closest element
	public static int binarySearchRecursiveClosest(int[] a, int x, int low, int high) {
		if (low > high) { // high is on the left side now
			if (high < 0) return low;
			if (low >= a.length) return high;
			if (x - a[high] < a[low] - x) {
				return high;
			} 
			return low;
		}
		
		int mid = (low + high) / 2;
		if (a[mid] < x) {
			return binarySearchRecursiveClosest(a, x, mid + 1, high);
		} else if (a[mid] > x) {
			return binarySearchRecursiveClosest(a, x, low, mid - 1);
		} else {
			return mid;
		}
	}	
	
	public static void main(String[] args) {
		int[] array = {3, 6, 9, 12, 15, 18};
		for (int i = 0; i < 20; i++) {
			int loc = binarySearch(array, i);
			int loc2 = binarySearchRecursive(array, i, 0, array.length - 1);
			int loc3 = binarySearchRecursiveClosest(array, i, 0, array.length - 1);
			System.out.println(i + ": " + loc + " " + loc2 + " " + loc3);
		}
	}
/*
                    0: -1 -1 0
                    1: -1 -1 0
                    2: -1 -1 0
                    3: 0 0 0
                    4: -1 -1 0
                    5: -1 -1 1
                    6: 1 1 1
                    7: -1 -1 1
                    8: -1 -1 2
                    9: 2 2 2
                    10: -1 -1 2
                    11: -1 -1 3
                    12: 3 3 3
                    13: -1 -1 3
                    14: -1 -1 4
                    15: 4 4 4
                    16: -1 -1 4
                    17: -1 -1 5
                    18: 5 5 5
                    19: -1 -1 5
 */
}
