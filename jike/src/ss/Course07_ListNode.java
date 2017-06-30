package ss;

/**
 *题目中的单链表节点
 */
public class Course07_ListNode<T> {
	public int val;
	public Course07_ListNode next;
	public Course07_ListNode(int val) {
		super();
		this.val = val;
	}
	public Course07_ListNode(int val, Course07_ListNode next) {
		super();
		this.val = val;
		this.next = next;
	}
	@Override
	public String toString() {
		return "ListNode [val=" + val + "]";
	}
}
