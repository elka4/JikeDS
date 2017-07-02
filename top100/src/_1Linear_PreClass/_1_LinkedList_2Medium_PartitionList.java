package _1Linear_PreClass;



public class _1_LinkedList_2Medium_PartitionList {
	public ListNode partition (ListNode head, int x) {
		if (head == null || head.next ==null) {
			return head;
		}
		ListNode smallPre = new ListNode(0);
		ListNode largePre = new ListNode(0);
		ListNode largeDummy = largePre;
		ListNode smallDummy = smallPre;
		while (head != null) {
			if (head.val < x) {
				smallPre.next = head;
				smallPre = smallPre.next;
			} else {
				largePre.next = head;
				largePre = largePre.next;
			}
			
			head = head.next;
		}
		smallPre.next = largeDummy.next;
		largePre.next = null;
		return smallDummy.next;
	}
	
	private class ListNode {

		public ListNode(int i) {
			// TODO Auto-generated constructor stub
		}
		public ListNode next;
		public int val;

	}
}
//follow up: reorder list�