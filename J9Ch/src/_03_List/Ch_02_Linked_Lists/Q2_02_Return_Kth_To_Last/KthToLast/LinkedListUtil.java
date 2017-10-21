package _03_List.Ch_02_Linked_Lists.Q2_02_Return_Kth_To_Last.KthToLast;

/**
 * Created by tianhuizhu on 7/6/17.
 */
public class LinkedListUtil {
    /**
     * Gets the kth last element of a linked list.
     *
     * @param head The head of the linked list.
     * @param k The number of elements to count backward.
     * @return The kth last element of the linked list, if it is not large
     * enough, return 0.
     */
    public static LinkedList getKthLastElement(LinkedList head, int k) {
        if (head == null || k < 1) {
            return null;
        }

        LinkedList current = head;
        LinkedList nBehindCurrent = head;

        for (int i = 0; i < k - 1; i++) {
            current = current.next;
            if (current == null) {
                return null;
            }
        }

        while (current.next != null) {
            nBehindCurrent = nBehindCurrent.next;
            current = current.next;
        }

        return nBehindCurrent;
    }
}
