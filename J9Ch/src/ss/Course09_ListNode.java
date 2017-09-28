package ss;

/**
 * 链表的节点类
 */
public class Course09_ListNode {
	public int val;
	public Course09_ListNode next;
	public Course09_ListNode(int val) {
		super();
		this.val = val;
	}
	public Course09_ListNode(int val, Course09_ListNode next) {
		super();
		this.val = val;
		this.next = next;
	}
	@Override
	public String toString() {
		return "ListNode [val=" + val + "]";
	}
	
	public static void printList(Course09_ListNode head){
		Course09_ListNode p=head;
		while(p!=null){
			System.out.print(p.val+" ");
			p=p.next;
		}
		System.out.println();
	}
	
	public static Course09_ListNode arrayToList(int[] array){
		Course09_ListNode head=new Course09_ListNode(0);
		Course09_ListNode p=head;
		for(int value:array){
			p.next=new Course09_ListNode(value);
			p=p.next;
		}
		return head.next;
	}
}
