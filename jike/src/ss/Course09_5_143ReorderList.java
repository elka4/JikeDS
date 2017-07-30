package ss;

import org.junit.Test;


/**
链表洗牌
 */
public class Course09_5_143ReorderList {
	public int lengthOfList(ListNode head){
		ListNode p=head;
		int n=0;
		while(p!=null){
			p=p.next;
			n++;
		}
		return n;
	}

	public ListNode reverseList(ListNode head){
		ListNode pre=head;
		ListNode p=pre.next;
		ListNode next;
		while(p!=null){
			next=p.next;
			p.next=pre;
			pre=p;
			p=next;
		}
		head.next=null;
		return pre;
	}

    public void reorderList(ListNode head) {
        if(head==null||head.next==null){
           return;
        }
        int n=lengthOfList(head);
        int half=n/2;

        if(n%2!=0){
           half++;
        }

        ListNode leftEnd=head;

        for(int i=1;i<half;i++){
           leftEnd=leftEnd.next;
        }

        ListNode rightStart=leftEnd.next;
        rightStart=reverseList(rightStart);
        leftEnd.next=null;
        ListNode left=head;
        ListNode right=rightStart;
        boolean flag=true;
        ListNode next=null;

        while(right!=null){
           if(flag){
               next=left.next;
               left.next=right;
               left=next;
         }else{
             next=right.next;
             right.next=left;
             right=next;
         }

           flag=!flag;
        }
    }


	  @Test
	  public void test(){
		  int[] array1={1,2,3,4,5,6};
		  ListNode head=ListNode.arrayToList(array1);
		  reorderList(head);
		  ListNode.printList(head);
		  int[] array2={1,2,3,4,5,6,7};
		  head=ListNode.arrayToList(array2);
		  reorderList(head);
		  ListNode.printList(head);
	  }
	  
}
