package CtCILibrary;

public class LinkedListNode {
	public CtCILibrary.LinkedListNode next;
	public CtCILibrary.LinkedListNode prev;
	public CtCILibrary.LinkedListNode last;
	public int data;
	public LinkedListNode(int d, CtCILibrary.LinkedListNode n, CtCILibrary.LinkedListNode p) {
		data = d;
		setNext(n);
		setPrevious(p);
	}
	
	public LinkedListNode(int d) {
		data = d;
	}	
	
	public LinkedListNode() { }

	public void setNext(CtCILibrary.LinkedListNode n) {
		next = n;
		if (this == last) {
			last = n;
		}
		if (n != null && n.prev != this) {
			n.setPrevious(this);
		}
	}
	
	public void setPrevious(CtCILibrary.LinkedListNode p) {
		prev = p;
		if (p != null && p.next != this) {
			p.setNext(this);
		}
	}	
	
	public String printForward() {
		if (next != null) {
			return data + "->" + next.printForward();
		} else {
			return ((Integer) data).toString();
		}
	}
	
	public CtCILibrary.LinkedListNode clone() {
		CtCILibrary.LinkedListNode next2 = null;
		if (next != null) {
			next2 = next.clone();
		}
		CtCILibrary.LinkedListNode head2 = new CtCILibrary.LinkedListNode(data, next2, null);
		return head2;
	}
}
