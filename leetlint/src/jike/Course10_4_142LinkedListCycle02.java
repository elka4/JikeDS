package jike;

import org.junit.Test;


public class Course10_4_142LinkedListCycle02 {
	/**
	 * 求链表环的开始位置
	 */
	public ListNode detectCycle(ListNode head) {
	    if(head==null){
	   	 return null;
	    }else{
	   	 ListNode slow=head;
	   	 ListNode fast=head;
	   	 while(fast!=null&&fast.next!=null){
	   		 slow=slow.next;
	   		 fast=fast.next.next;
	   		 if(slow==fast){
	   			 break;
	   		 }
	   	 }
	   	 if(fast==null||fast.next==null){
	   		 return null;
	   	 }else{
	   		 slow=head;
	   		 while(fast!=slow){
	   			 fast=fast.next;
	   			 slow=slow.next;
	   		 }
	   		 return fast;
	   	 }
	    }
	    }
	@Test
	public void test(){
		 int[] array={1,2,3,4,5,6,7};
		 ListNode head=ListNode.arrayToList(array);
		 System.out.println(detectCycle(head));
		 head=ListNode.arrayToCircle(array);
		 System.out.println(detectCycle(head).val);
		 head=ListNode.createCycle(array, 3);
		 System.out.println(detectCycle(head).val);
	}
}
