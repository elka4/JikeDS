package J_6_Linked_List_Array.other.Sort_Colors;

//https://leetcode.com/problems/sort-colors/

// two pointer, 从两侧向中央
public class _1_Arrays_2Medium_SortColor {


	public void sortColors(int[] arr) {
		if(arr == null || arr.length <= 1) {
			return;
		}

        // represents the right boundary of processed '0'
		int left = 0;

        //represents the left boundary of processed '2'
		int right = arr.length - 1;

        // scanner for unprocessed data
		int cur = 0;

		while (cur <= right){
            // == 0 就和left swap，然后cur 和left ++
			if (arr[cur] == 0) {
				swap(arr, cur++, left++);
			} else if(arr[cur] == 2) {
				swap(arr, cur, right--);
			} else {
				cur++;
			}
		}
	}
	private void swap(int[] arr, int a, int b) {
		if (arr[a] != arr[b]) {
			arr[a] ^= arr[b];
			arr[b] ^= arr[a];
			arr[a] ^= arr[b];
		}
	}
}
