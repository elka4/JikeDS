package jike;

/**
 * 链表的节点类
 */
public class Course08_ListNode {
	public int val;
	public Course08_ListNode next;
	public Course08_ListNode(int val) {
		super();
		this.val = val;
	}
	public Course08_ListNode(int val, Course08_ListNode next) {
		super();
		this.val = val;
		this.next = next;
	}
	@Override
	public String toString() {
		return "ListNode [val=" + val + "]";
	}
	
	public static void printList(Course08_ListNode head){
		Course08_ListNode p=head;
		while(p!=null){
			System.out.print(p.val+" ");
			p=p.next;
		}
		System.out.println();
	}
	
	public static Course08_ListNode arrayToList(int[] array){
		Course08_ListNode head=new Course08_ListNode(0);
		Course08_ListNode p=head;
		for(int value:array){
			p.next=new Course08_ListNode(value);
			p=p.next;
		}
		return head.next;
	}
}
