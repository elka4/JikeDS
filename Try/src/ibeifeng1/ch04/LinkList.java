package ibeifeng1.ch04;
/*
 * �����൱�ڻ�
 */
public class LinkList {
	//ͷ���
	private Node first;
	
	public LinkList() {
		first = null;
	}
	
	/**
	 * ����һ����㣬��ͷ������в���
	 */
	public void insertFirst(long value) {
		Node node = new Node(value);
		node.next = first;
		first = node;
	}
	
	/**
	 * ɾ��һ����㣬��ͷ�������ɾ��
	 */
	public Node deleteFirst() {
		Node tmp = first;
		first = tmp.next;
		return tmp;
	}
	
	/**
	 * ��ʾ����
	 */
	public void display() {
		Node current = first;
		while(current != null) {
			current.display();
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * ���ҷ���
	 */
	public Node find(long value) {
		Node current = first;
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
	public Node delete(long value) {
		Node current = first;
		Node previous = first;
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
}
