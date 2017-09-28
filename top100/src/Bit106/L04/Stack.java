package Bit106.L04;

/**
 * Implement a stack
 */
public class Stack {
    private static class Node {
        private int val;
        private Node next;
        private Node(int x) {
            this.val = x;
        }
    }

    private Node top; // add AND remove here

    public boolean isEmpty() {
        return top == null;
    }

    public int peek() {
        // ignore corner case(null pointer check) here..
        return top.val;
    }

    public void push(int value) {
        Node node = new Node(value);
        node.next = top;
        top = node;
    }

    public int pop() {
        int value = top.val;
        top = top.next;
        return value;
    }

}
