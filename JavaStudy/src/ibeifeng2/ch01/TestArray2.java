package ibeifeng2.ch01;

public class TestArray2 {
	public static void main(String[] args) {
		int[] intArray = new int[10];

		// ��������
		intArray[0] = 1;
		intArray[1] = 2;

		// ѭ����������
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = i;
		}

		// ��ʾ����
		for (int i = 0; i < intArray.length; i++) {
			System.out.print(intArray[i] + " ");
		}

		System.out.println();

		// ��������
		int searchKey = 5;
		int i;
		for (i = 0; i < intArray.length; i++) {
			if (intArray[i] == searchKey) {
				break;
			}
		}

		if (i == intArray.length) {
			System.out.println("���Ҳ���ָ������");
		} else {
			System.out.println("�ҵ�ָ�����ݣ��±�Ϊ" + i);
		}

		// ɾ������
		int deleteKey = 4;
		for (i = 0; i < intArray.length; i++) {
			if (intArray[i] == deleteKey) {
				break;
			}
		}

		if (i == intArray.length) {
			System.out.println("���Ҳ���ָ������");
		} else {
			for (int j = i; j < intArray.length - 1; j++) {
				intArray[j] = intArray[j + 1];
			}
			intArray[intArray.length - 1] = 0;
		}

		// ��ʾ����
		for (int a = 0; a < intArray.length; a++) {
			System.out.print(intArray[a] + " ");
		}

		// �޸�����
		int xiugaiKey = 5;
		for (i = 0; i < intArray.length; i++) {
			if (intArray[i] == xiugaiKey) {
				break;
			}
		}

		if (i == intArray.length) {
			System.out.println("���Ҳ���ָ������");
		} else {
			intArray[i] = 6;
		}

		
		System.out.println();
		// ��ʾ����
		for (int a = 0; a < intArray.length; a++) {
			System.out.print(intArray[a] + " ");
		}
	}

}
