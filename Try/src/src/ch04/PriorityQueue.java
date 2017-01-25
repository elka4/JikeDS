package src.ch04;

public class PriorityQueue {
	// ����
	private long[] arr;
	
	// ���ռ�
	private int maxSize;
	
	// ��ЧԪ�ش�С
	private int elems;
	
	
	public PriorityQueue(int maxSize) {
		this.maxSize = maxSize;
		arr = new long[maxSize];
		elems = 0;
	}
	
	// ��������
	public void insert(long value) {
		int i;
		for (i = 0;  i < elems; i++) {
			if(value < arr[i]) {
				break;
			}
		}
		
		for(int j = elems; j > i;j--){
			arr[j] = arr[j - 1];
		}
		arr[i] = value;
		elems++;
	}
	
	// �Ƴ�����
	public long remove() {
		long value = arr[elems - 1];
		elems--;
		return value;
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
