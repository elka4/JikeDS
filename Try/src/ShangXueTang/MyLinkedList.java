package ShangXueTang;

/**
 * Created by tzh on 1/19/17.
 */
public class MyLinkedList {
    private Node first;
    private Node last;

    private int size;

    public void add(Object obj) {
        Node n = new Node();
        if(first == null) {//if first is null, add it to the first

            n.setPrevious(null);
            n.setObj(obj);
            n.setNext(null);

            first = n;
            last = n;
        } else{//add obj to next of last

            n.setPrevious(last);
            n.setObj(obj);
            n.setNext(null);

            last.setNext(n);

            last = n;
        }
        size++;


    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add("aaa");
        list.add("bbb");
        System.out.println(list.size());
    }

}

class Node {
    Object previous;
    Object obj;
    Object next;

    public Node() {

    }


    public Node(Object previous, Object obj, Object next) {
        this.previous = previous;
        this.obj = obj;
        this.next = next;
    }


    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }
}
