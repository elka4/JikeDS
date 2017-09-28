package Bit106.L04;

/**
 * Implement a queue
 */
public class Queue {
    private static class Node {
        private int val;
        private Node next;
        private Node(int x) {
            this.val = x;
        }
    }

    private Node head; // remove from the head
    private Node tail; // add node here

    public boolean isEmpty() {
        return head == null;
    }

    public int peek() {
        return head.val;
    }

    public void add(int val){
        Node node = new Node(val);
        if (tail != null){
            tail.next = node;
        }
        tail = node;
        if (head == null) { // if the queue is initially empty, then both head and tail should point to this node
            head = node;
        }
    }

    public int remove() {
        int value = head.val;
        head = head.next; // if there is only ONE node, then head.next is null
        if (head == null) { // If there is only ONE node, after remove, it's empty, so both head and tail should be null.
            tail = null;
        }
        return value;
    }
}
