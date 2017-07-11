package J_6_Linked_List_Array.Required_10;
import java.util.*;
/** 599 Insert into a Cyclic Sorted List
 * Easy

 * Created by tianhuizhu on 6/28/17.
 */
public class _599_Insert_into_a_Cyclic_Sorted_List {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public class Solution {
        /**
         * @param node a list node in the list
         * @param x an integer
         * @return the inserted new list node
         */
        public ListNode insert(ListNode node, int x) {
            // Write your code here
            if (node == null) {
                node = new ListNode(x);
                node.next = node;
                return node;
            }

            ListNode p = node;
            ListNode prev = null;
            do {
                prev = p;
                p = p.next;
                if (x <= p.val && x >= prev.val) {
                    break;
                }
                if ((prev.val > p.val) && (x < p.val || x > prev.val)) {
                    break;
                }
            } while (p != node);

            ListNode newNode = new ListNode(x);
            newNode.next = p;
            prev.next = newNode;
            return newNode;
        }
    }
}
