package ibeifeng1.ch04;
/*
 * ����㣬�൱���ǳ���
 */
public class Node {
	//������
	public long data;
	//ָ����
	public Node next;
	
	public Node(long value) {
		this.data = value;
	}
	
	/**
	 * ��ʾ����
	 */
	public void display() {
		System.out.print(data + " ");
	}
}
