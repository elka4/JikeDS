package _3Graph_Class;

public class _3_MergeKSortedLinkedList_2_MergeSort {
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		return split(lists, 0, lists.length - 1);
	}
	
	private ListNode split(ListNode[] lists, int start, int end) {
		if (start == end) {
			return lists[start];
		}

		int mid = start + (end - start) / 2;

		ListNode head1 = split(lists, start, mid);
		ListNode head2 = split(lists, mid + 1, end);

		return mergeSort(head1, head2);
	}

	private ListNode mergeSort(ListNode head1, ListNode head2) {
		if (head1 == null || head2 == null) {
			return head1 == null ? head2 : head1;
		}
		if (head1.val <= head2.val) {
			head1.next = mergeSort(head1.next, head2);
			return head1;
		} else {
			head2.next = mergeSort(head1, head2.next);
			return head2;
		}
	}


/////////////////////////////////////////////////////////


    public int[] mergeSort(int[] arr) {
        if(arr == null)
            return arr;

        int[] helper = new int[arr.length];

        doSort(arr, helper, 0, arr.length - 1);

        return arr;
    }

    private void doSort(int[] arr, int[] helper, int start, int end) {

        if(start >= end)
            return ;

        int mid = start + (end - start) / 2;

        doSort(arr, helper, start, mid);
        doSort(arr, helper, mid + 1, end);

        merge(arr, helper, start, mid, end);
    }

    private void merge(int[] arr, int[] helper, int aStart, int aEnd, int bEnd) {
        //Copy arr from aStart to bEnd
        for (int i = aStart; i <= bEnd; i++) {
            helper[i] = arr[i];
        }

        int aCur = aStart;
        int bCur = aEnd + 1;

        for (int i = aStart; i <= bEnd; i++) {
            //One Exausts
            if (aCur > aEnd) {
                arr[i] = helper[bCur++];
            } else if (bCur > bEnd) {
                arr[i] = helper[aCur++];
            } else if (helper[aCur] <= helper[bCur]) {
                arr[i] = helper[aCur++];
            } else {
                arr[i] = helper[bCur++];
            }
        }
    }

}
