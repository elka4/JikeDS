package ibeifeng2.ch02;

public class StudentArray {
	// ����
	private Student[] arr;

	// ��������Ч���ݵĴ�С
	private int elems;

	// Ĭ�Ϲ��캯��
	public StudentArray() {
		arr = new Student[50];
	}

	public StudentArray(int max) {
		arr = new Student[max];
	}

	// ��������
	public void insert(Student stu) {
		arr[elems] = stu;
		elems++;
	}

	// ��ʾ����
	public void display() {
		for (int i = 0; i < elems; i++) {
			arr[i].display();
		}
	}

	// ����ѧ�Ž�������
	public void sortByNo() {
		int min = 0;
		Student tmp = null;
		for (int i = 0; i < elems - 1; i++) {
			min = i;
			for (int j = i + 1; j < elems; j++) {
				if (arr[j].getStuNo() < arr[min].getStuNo()) {
					min = j;
				}
			}
			tmp = arr[i];
			arr[i] = arr[min];
			arr[min] = tmp;
		}
	}

	// ����ѧ��������������
	public void sortByName() {
		int min = 0;
		Student tmp = null;
		for (int i = 0; i < elems - 1; i++) {
			min = i;
			for (int j = i + 1; j < elems; j++) {
				if (arr[j].getName().compareTo(arr[min].getName()) < 0) {
					min = j;
				}
			}
			tmp = arr[i];
			arr[i] = arr[min];
			arr[min] = tmp;
		}
	}
}
