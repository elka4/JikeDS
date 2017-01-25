package ibeifeng2.ch03;

public class MyStack {
	private int maxSize;
	
	private long[] arr;
	
	private int top;
	
	// ���췽��
	public MyStack(int size) {
		maxSize = size;
		arr = new long[maxSize];
		top = -1;
	}
	
	// ѹ������
	public void push(long value){
		arr[++top]=value;
	}
	
	// ��������
	public long pop() {
		return arr[top--];
	}
	
	// ����ջ��Ԫ��
	public long peek() {
		return arr[top];
	}
	
	// ջ�Ƿ�Ϊ��
	public boolean isEmpty() {
		return (top == -1);
	}
	
	// ջ�Ƿ�����
	public boolean isFull() {
		return (top == maxSize - 1);
	}
	
}
