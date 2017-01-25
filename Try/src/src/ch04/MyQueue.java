package src.ch04;

public class MyQueue {
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
	
	public MyQueue(int maxSize) {
		this.maxSize = maxSize;
		arr = new long[maxSize];
		elems = 0;
		font = 0;
		end = -1;
	}
	
	// ��������
	public void insert(long value) {
		if(end == maxSize - 1) {
			end = -1;
		}
		arr[++end] = value;
		elems++;
	}
	
	// �Ƴ�����
	public long remove() {
		long tmp = arr[font++];
		
		if(font == maxSize) {
			font = 0;
		}
		elems--;
		return tmp; 
	}
	
	// �Ƿ�Ϊ��
	public boolean isEmpty() {
		return (elems == 0);
	}
	
	// �Ƿ�����
	public boolean isFull() {
		return (elems == maxSize);
	}
	
	// ������ЧԪ�ش�С
	public int size() {
		return elems;
	}
}
