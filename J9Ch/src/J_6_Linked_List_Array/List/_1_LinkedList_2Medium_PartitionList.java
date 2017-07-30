package J_6_Linked_List_Array.List;


import org.junit.Test;

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
            this.val = i;
		}
		public ListNode next;
		public int val;
		public String toString(){
		    String str = "" + val;
		    while(next != null){
		        str += "->" + next.val;
                next = next.next;

            }
            return str;
        }

	}

	@Test
    public void test01(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node5.next = node3;
        node3.next = node2;
        node2.next = node4;
        node4.next = node1;
        node1.next = node6;
        System.out.println(node5);
        ListNode result = partition(node3, 2);
        System.out.println(result);

    }

    @Test
    public void test02(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node3.next = node2;
        node2.next = node4;
        node4.next = node1;
        System.out.println(node3);
        ListNode result = partition(node3, 1);
        System.out.println(result);
    }
}
//follow up: reorder list�