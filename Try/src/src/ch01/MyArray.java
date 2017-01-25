package src.ch01;

public class MyArray {
	// ����
	private long[] arr;

	// ��������Ч���ݵĴ�С
	private int elems;

	//Ĭ�Ϲ��캯��
	public MyArray() {
		arr = new long[50];
	}
	
	public MyArray(int max) {
		arr = new long[max];
	}
	
	// ��������
	public void insert(long value) {
		arr[elems] = value;
		elems++;
	}

	// ��ʾ����
	public void display() {
		for (int i = 0; i < elems; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	// ��������
	public int find(long searchKey) {
		int i;
		for (i = 0; i < elems; i++) {
			if (arr[i] == searchKey) {
				break;
			}
		}

		if (i == arr.length) {
			return -1;
		} else {
			return i;
		}
	}
	
	// ɾ������
	public void delete(long deleteKey) {
		
		if (find(deleteKey) == -1) {
			System.out.println("���Ҳ���ָ�����ݣ�ɾ��ʧ��");
		} else {
			for(int i = find(deleteKey);i < elems; i++) {
				arr[i] = arr[i + 1];
			}
		}
	}
	
	// �޸�����
	public void change(long oddValue, long newValue) {
		if(find(oddValue) == -1) {
			System.out.println("���Ҳ���ָ�����ݣ��޸�ʧ��");
		} else {
			arr[find(oddValue)] = newValue; 
		}
	}
}
