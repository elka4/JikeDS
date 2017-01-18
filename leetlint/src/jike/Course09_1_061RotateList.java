package jike;

import org.junit.Test;

public class Course09_1_061RotateList {
	/**
	取得链表长度
	 */
	public int lengthOfList(ListNode head){
		ListNode p=head;
		int n=0;
		while(p!=null){
			p=p.next;
			n++;
		}
		return n;
	}
	/**
	旋转链表
	 */
	public ListNode rotateRight(ListNode head, int k) {
	    if(k==0||head==null||head.next==null){
	    	return head;
	    }else{
	    	int n=lengthOfList(head);
	    	if(k>=n){
	    		k=k%n;
	    	}
	    	if(k==0){
	    		return head;
	    	}
	    	//1 2 3 4 5 k=2
	    	ListNode pre=head;
	    	int index=1;
	    	while(index<n-k){
	    		pre=pre.next;
	    		index++;
	    	}
	    	ListNode newHead=pre.next;
	    	ListNode last=newHead;
	    	while(last.next!=null){
	    		last=last.next;
	    	}
	    	pre.next=null;
	    	last.next=head;
	    	return newHead;
	    }
	}
	@Test
	public void test(){
		int[] array1={1,2,3,4,5};
		ListNode head=ListNode.arrayToList(array1);
		head=rotateRight(head, 2);
		ListNode.printList(head);
		int[] array2={1,2,3,4,5,6};
		head=ListNode.arrayToList(array2);
		head=rotateRight(head, 10);
		ListNode.printList(head);
	}
}
