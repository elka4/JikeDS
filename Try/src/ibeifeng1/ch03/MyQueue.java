package ibeifeng1.ch03;
/*
 * �ж���
 */
public class MyQueue {
	//�ײ�ʹ������
	private long[] arr;
	//��Ч���ݵĴ�С
	private int elements;
	//��ͷ
	private int front;
	//��β
	private int end;
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public MyQueue() {
		arr = new long[10];
		elements = 0;
		front = 0;
		end = -1;
	}
	
	/**
	 * �������Ĺ��췽��������Ϊ����Ĵ�С
	 */
	public MyQueue(int maxsize) {
		arr = new long[maxsize];
		elements = 0;
		front = 0;
		end = -1;
	}
	
	/**
	 * �������,�Ӷ�β����
	 */
	public void insert(long value) {
		arr[++end] = value;
		elements++;
	}
	
	/**
	 * ɾ�����ݣ��Ӷ�ͷɾ��
	 */
	public long remove() {
		elements--;
		return arr[front++];
	}
	
	/**
	 * �鿴���ݣ��Ӷ�ͷ�鿴
	 */
	public long peek() {
		return arr[front];
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return elements == 0;
	}
	
	/**
	 * �ж��Ƿ�����
	 */
	public boolean isFull() {
		return elements == arr.length;
	}
}
