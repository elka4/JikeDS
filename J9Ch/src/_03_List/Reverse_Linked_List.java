package _03_List;



public class Reverse_Linked_List {
    //Java Solution 1 - Iterative

    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null)
            return head;

        ListNode p1 = head;
        ListNode p2 = p1.next;

        head.next = null;
        while(p1!=null&& p2!=null){
            ListNode t = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = t;
        }

        return p1;
    }



    //Java Solution 2 - Recursive

    public ListNode reverseList2(ListNode head) {
        if(head==null || head.next == null)
            return head;

        //get second node
        ListNode second = head.next;
        //set first's next to be null
        head.next = null;

        ListNode rest = reverseList2(second);
        second.next = head;

        return rest;
    }

/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////
}
