package jike;

import java.util.Comparator;

public class Course14_1_BinarySearchUtil<T> {
	private Comparator<T> comp;
	@SuppressWarnings("unchecked")
	public int compare(T a, T b) {
		if (comp != null) {
			return comp.compare(a, b);
		} else {
			Comparable<T> c = (Comparable<T>) a;
			return c.compareTo(b);
		}
	}
	public Course14_1_BinarySearchUtil(Comparator<T> comp) {
		super();
		this.comp = comp;
	}
	public Course14_1_BinarySearchUtil() {
		super();
	}
	public int binarySearch(T[] array, int start, int end, T key) {
		end--;
		return recursion(array, start, end, key);
	}
	private int recursion(T[] array, int start, int end, T key) {
		if (start > end) {
			return -(start + 1);
		} else {
			int mid = (start + end) >> 1;
			if (compare(key, array[mid]) == 0) {
				return mid;
			} else if (compare(key, array[mid]) < 0) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
			return recursion(array, start, end, key);
		}
	}
}
