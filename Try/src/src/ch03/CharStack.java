package src.ch03;

public class CharStack {
	private int maxSize;
	
	private char[] arr;
	
	private int top;
	
	// ���췽��
	public CharStack(int size) {
		maxSize = size;
		arr = new char[maxSize];
		top = -1;
	}
	
	// ѹ������
	public void push(char value){
		arr[++top]=value;
	}
	
	// ��������
	public char pop() {
		return arr[top--];
	}
	
	// ����ջ��Ԫ��
	public char peek() {
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
