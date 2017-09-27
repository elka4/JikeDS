package ibeifeng2.ch01;

public class MyOrderedArray {
	// ����
	private long[] arr;

	// ��������Ч���ݵĴ�С
	private int elems;

	//Ĭ�Ϲ��캯��
	public MyOrderedArray() {
		arr = new long[50];
	}
	
	public MyOrderedArray(int max) {
		arr = new long[max];
	}
	
	// ��������
	public void insert(long value) {
		int i;
		for (i = 0;  i < elems; i++) {
			if(arr[i] > value) {
				break;
			}
		}
		
		for(int j = elems; j > i;j--){
			arr[j] = arr[j - 1];
		}
		arr[i] = value;
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
	
	// ���ַ�����
	public int binaryFind(long searchKey) {
		int ins = 0;
		int low = 0;
		int pow = elems;
		
		while(true) {
			ins = (low + pow) / 2;
			if(arr[ins] == searchKey) {
				return ins;
			} else if(low > pow){
				return -1;
			} else {
				if (arr[ins] > searchKey) {
					pow = ins - 1;
				} else {
					low = ins + 1;
				}	
			}
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
