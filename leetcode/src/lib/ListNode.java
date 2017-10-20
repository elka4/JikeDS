package lib;

/**
 * Created by tianhuizhu on 6/28/17.
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    @Override
    public String toString(){
        if (next != null) {
            return val + "->" + next.toString();
        } else {
            return ((Integer) val).toString() + "->null";
        }
    }

    public static ListNode create(int[] nums){
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        for (int i:nums
             ) {
            head.next = new ListNode(i);
            head = head.next;
        }
        return dummy.next;
    }

    public void print(){
        System.out.println(toString());
    }
}
