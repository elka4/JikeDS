package ibeifeng1.java.ch05;
//import ibeifeng1.java.ch04.Node;
/*
 * 双端链表
 */
public class FirstLastLinkList {
	//头结点
	private ibeifeng1.java.ch04.Node first;
	//尾结点
	private ibeifeng1.java.ch04.Node last;
	
	public FirstLastLinkList() {
		first = null;
		last = null;
	}
	
	/**
	 * 插入一个结点，在头结点后进行插入
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
	 * 插入一个结点，从尾结点进行插入
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
	 * 删除一个结点，在头结点后进行删除
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
	 * 显示方法
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
	 * 查找方法
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
	 * 删除方法，根据数据域来进行删除
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
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return (first == null);
	}
}
