package ss;

import org.junit.Test;


public class Course09_3_024SwapNodesInPairs {
	 /**
	 * 交换链表的相邻节点
	 */
	public ListNode swapPairs(ListNode head) {
	        if(head==null||head.next==null){
	        	return head;
	        }
	        ListNode newHead=new ListNode(0);
	        newHead.next=head;
	        ListNode zero=newHead;
	        ListNode pre=head;
	        ListNode p=pre.next;
	        ListNode next=null;
	        while(pre!=null&&p!=null){
	        	next=p.next;
	        	p.next=pre;
	        	pre.next=next;
	        	zero.next=p;
	        	if(next==null){
	        		break;
	        	}else{
	        		zero=pre;
	        		pre=next;
	        		p=pre.next;
	        	}
	        }
	        return newHead.next;
	    }
	 @Test
	 public void test(){
		 int[] array1={1,2,3,4,5,6};
		 ListNode head=ListNode.arrayToList(array1);
		 head=swapPairs(head);
		 ListNode.printList(head);
		 int[] array2={1,2,3,4,5};
		 head=ListNode.arrayToList(array2);
		 head=swapPairs(head);
		 ListNode.printList(head);
	 }
}
