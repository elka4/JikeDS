package ibeifeng1.ch03;

public class MyStack {
	//�ײ�ʵ����һ������
	private long[] arr;
	private int top;
	
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public MyStack() {
		arr = new long[10];
		top = -1;
	}
	
	/**
	 * ���������췽��������Ϊ�����ʼ����С
	 */
	public MyStack(int maxsize) {
		arr = new long[maxsize];
		top = -1;
	}
	
	/**
	 * �������
	 */
	public void push(int value) {
		arr[++top] = value;
	}
	
	/**
	 * �Ƴ�����
	 */
	public long pop() {
		return arr[top--];
	}
	
	/**
	 * �鿴����
	 */
	public long peek() {
		return arr[top];
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * �ж��Ƿ�����
	 */
	public boolean isFull() {
		return top == arr.length - 1;
	}
}
