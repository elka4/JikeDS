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
            return ((Integer) val).toString();
        }
    }
}
