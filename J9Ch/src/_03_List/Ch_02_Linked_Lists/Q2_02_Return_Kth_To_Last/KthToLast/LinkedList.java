package _03_List.Ch_02_Linked_Lists.Q2_02_Return_Kth_To_Last.KthToLast;

/**
 * Created by tianhuizhu on 7/6/17.
 */
public class LinkedList {
    public int data;
    public LinkedList next;
    public LinkedList prev;

    public LinkedList(int data) {
        this.data = data;
    }

    /**
     * Constructs a linked list with the provided data array, return the head
     */
    public LinkedList(int[] data, Type type) {
        if (data.length == 0)
            throw new IllegalArgumentException("data");
        this.data = data[0];
        LinkedList prev = this;
        for (int i = 1; i < data.length; i++) {
            prev.next = new LinkedList(data[i]);
            if (type == Type.DOUBLY)
                prev.next.prev = prev;
            prev = prev.next;
        }
        assertEquals(data, type);
    }

    /**
     * Ensure that the linked list contains the data specified in the parameter,
     * in that order and no more.
     */
    public void assertEquals(int[] data, Type type) {
        LinkedList elem = this;
        if (type == Type.DOUBLY)
            assert elem.prev == null;
        for (int i = 0; i < data.length; i++) {
            assert elem.data == data[i];
            if (type == Type.DOUBLY && elem.next != null)
                assert elem.next.prev == elem;
            elem = elem.next;
        }
        assert elem == null;
    }

    public enum Type {
        SINGLY,
        DOUBLY
    }
}