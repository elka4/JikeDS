package ss;

import org.junit.Test;

public class Course07_206ReverseLinkedList {
	/**
	 *题目中的单链表节点
	 */
	public class ListNode {
		public int val;
		public ListNode next;
		public ListNode(int val) {
			super();
			this.val = val;
		}
		public ListNode(int val, ListNode next) {
			super();
			this.val = val;
			this.next = next;
		}
		@Override
		public String toString() {
			return "ListNode [val=" + val + "]";
		}
	}

	
	public void printList(ListNode head){
		ListNode p=head;
		while(p!=null){
			System.out.print(p.val+" ");
			p=p.next;
		}
		System.out.println();
	}

	public ListNode arrayToList(int[] array){
		ListNode head=new ListNode(0);
		ListNode p=head;
		for(int value:array){
			p.next=new ListNode(value);
			p=p.next;
		}
		return head.next;
	}

	/**
	 * 链表反转，非递归算法
	 */
	public ListNode reverseList(ListNode head) {
	    if(head==null||head.next==null){
	    	return head;
	    }else{
	    	ListNode pre=head;
	    	ListNode p=head.next;
	    	ListNode next=null;
	    	while(p!=null){
	    		next=p.next;
	    		p.next=pre;
	    		pre=p;
	    		p=next;
	    	}
	    	head.next=null;
	    	return pre;
	    }
	}

	/**
	 * 链表反转，递归算法
	 */
	public ListNode reverseListRecursive(ListNode head){
		if(head==null||head.next==null){
		    return head;
		}else{
		    ListNode tail=recursive(head);
		    head.next=null;
		    return tail;
		}
	}

	public ListNode recursive(ListNode p){
		if(p.next==null){
			return p;
		}else{
			ListNode next=p.next;
			ListNode tail=recursive(next);
			next.next=p;
			return tail;
		}
	}

	@Test
	public void test01(){
		int[] array={1,2,3,4,5,6};
		ListNode head=arrayToList(array);
		printList(head);
		head=reverseList(head);
		printList(head);
	}

	@Test
	public void test02(){
		int[] array={1,2,3,4,5,6};
		ListNode head=arrayToList(array);
		printList(head);
		head=reverseListRecursive(head);
		printList(head);
	}
}
