package ibeifeng1.ch05;
//import ibeifeng1.java.ch04.Node;
/*
 * ˫������
 */
public class FirstLastLinkList {
	//ͷ���
	private ibeifeng1.java.ch04.Node first;
	//β���
	private ibeifeng1.java.ch04.Node last;
	
	public FirstLastLinkList() {
		first = null;
		last = null;
	}
	
	/**
	 * ����һ����㣬��ͷ������в���
	 */
	public void insertFirst(long value) {
		ibeifeng1.java.ch04.Node node = new ibeifeng1.java.ch04.Node(value);
		if(isEmpty()) {
			last = node;
		}
		node.next = first;
		first = node;
	}
	
	/**
	 * ����һ����㣬��β�����в���
	 */
	public void insertLast(long value) {
		ibeifeng1.java.ch04.Node node = new ibeifeng1.java.ch04.Node(value);
		if(isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
	}
	
	/**
	 * ɾ��һ����㣬��ͷ�������ɾ��
	 */
	public ibeifeng1.java.ch04.Node deleteFirst() {
		ibeifeng1.java.ch04.Node tmp = first;
		if(first.next == null) {
			last = null;
		}
		first = tmp.next;
		return tmp;
	}
	
	/**
	 * ��ʾ����
	 */
	public void display() {
		ibeifeng1.java.ch04.Node current = first;
		while(current != null) {
			current.display();
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * ���ҷ���
	 */
	public ibeifeng1.java.ch04.Node find(long value) {
		ibeifeng1.java.ch04.Node current = first;
		while(current.data != value) {
			if(current.next == null) {
				return null;
			}
			current = current.next;
		}
		return current;
	}
	
	/**
	 * ɾ������������������������ɾ��
	 */
	public ibeifeng1.java.ch04.Node delete(long value) {
		ibeifeng1.java.ch04.Node current = first;
		ibeifeng1.java.ch04.Node previous = first;
		while(current.data != value) {
			if(current.next == null) {
				return null;
			}
			previous = current;
			current = current.next;
		}
		
		if(current == first) {
			first = first.next;
		} else {
			previous.next = current.next;
		}
		return current;
		
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return (first == null);
	}
}
