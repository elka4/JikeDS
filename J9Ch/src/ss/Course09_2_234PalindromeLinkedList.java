package ss;

import org.junit.Test;


/**
回文链表
 */
public class Course09_2_234PalindromeLinkedList {
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
	 * 链表反转
	 */
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

    public boolean isPalindrome(ListNode head) {
       if(head==null||head.next==null){
           return true;
       }
       int n=lengthOfList(head);
       int half=n/2;
       //1 2 3 4 3 2 1
       ListNode leftEnd=head;
       for(int i=0;i<half-1;i++){
           leftEnd=leftEnd.next;
       }
       ListNode rightStart=leftEnd.next;
       if(n%2!=0){
           rightStart=rightStart.next;
       }
       rightStart=reverseList(rightStart);
       ListNode leftStart=head;
       for(int i=1;i<=half;i++){
           if(leftStart.val!=rightStart.val){
               return false;
           }
           leftStart=leftStart.next;
           rightStart=rightStart.next;
       }
       return true;
    }


	  @Test
	  public void test(){
		  int[] array1={1,2,3,4,3,2,1};
		  ListNode head=ListNode.arrayToList(array1);
		  System.out.println(isPalindrome(head));
		  int[] array2={1,2,3,4,1,2,3};
		  head=ListNode.arrayToList(array2);
		  System.out.println(isPalindrome(head));
	  }
}
