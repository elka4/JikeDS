package J_6_Linked_List_Array.List;

// find kth node from the end of linked list
public class _1FindKthNodeFromTheEndOfLinkedList {

    private Node head;

    private static class Node {
        private int value;
        private Node next;

        Node(int value) {
            this.value = value;

        }
    }

    public void addToTheLast(Node node) {

        if (head == null) {
            head = node;
        } else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;

            temp.next = node;
        }
    }


    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.format("%d ", temp.value);
            temp = temp.next;
        }
        System.out.println();
    }


    public Node nthFromLastNode(Node head,int n)
    {
        Node firstPtr=head;
        Node secondPtr=head;

        for (int i = 0; i < n; i++) {
            firstPtr=firstPtr.next;

        }

        while(firstPtr!=null)
        {
            firstPtr=firstPtr.next;
            secondPtr=secondPtr.next;
        }

        return secondPtr;
    }

    public static void main(String[] args) {
        _1FindKthNodeFromTheEndOfLinkedList list = new _1FindKthNodeFromTheEndOfLinkedList();
        // Creating a linked list
        Node head=new Node(5);
        list.addToTheLast(head);
        list.addToTheLast(new Node(6));
        list.addToTheLast(new Node(7));
        list.addToTheLast(new Node(1));
        list.addToTheLast(new Node(2));

        list.printList();
        // Finding 3rd node from end
        Node nthNodeFromLast= list.nthFromLastNode(head,3);
        System.out.println("3th node from end is : "+ nthNodeFromLast.value);

    }
}
