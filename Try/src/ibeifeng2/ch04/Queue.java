package ibeifeng2.ch04;

public class Queue {
	// ����
	private long[] arr;
	
	// ���ռ�
	private int maxSize;
	
	// ��ЧԪ�ش�С
	private int elems;
	
	// ��ͷ
	private int font;
	
	// ��β
	private int end;
	
	public Queue(int maxSize) {
		this.maxSize = maxSize;
		arr = new long[maxSize];
		elems = 0;
		font = 0;
		end = -1;
	}
	
	// ��������
	public void insert(long value) {
		arr[++end] = value;
		elems++;
	}
	
	// �Ƴ�����
	public long remove() {
		elems--;
		return arr[font++]; 
	}
	
	// �Ƿ�Ϊ��
	public boolean isEmpty() {
		return (elems == 0);
	}
	
	// �Ƿ�����
	public boolean isFull() {
		return (end == maxSize - 1);
	}
	
	// ������ЧԪ�ش�С
	public int size() {
		return elems;
	}
}
