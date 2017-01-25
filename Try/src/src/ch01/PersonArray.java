package src.ch01;

public class PersonArray {
	// ����
	private Person[] arr;

	// ��������Ч���ݵĴ�С
	private int elems;

	//Ĭ�Ϲ��캯��
	public PersonArray() {
		arr = new Person[50];
	}
	
	public PersonArray(int max) {
		arr = new Person[max];
	}
	
	// ��������
	public void insert(Person person) {
		arr[elems] = person;
		elems++;
	}

	// ��ʾ����
	public void display() {
		for (int i = 0; i < elems; i++) {
			arr[i].display();
		}
		System.out.println();
	}
	
	// ��������
	public int find(String name) {
		int i;
		for (i = 0; i < elems; i++) {
			if (name.equals(arr[i].getName())) {
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
	public void delete(Person person) {
		
		if (find(person.getName()) == -1) {
			System.out.println("���Ҳ���ָ�����ݣ�ɾ��ʧ��");
		} else {
			for(int i = find(person.getName());i < elems; i++) {
				arr[i] = arr[i + 1];
			}
		}
		elems--;
	}
	
	//	 ɾ������
	public void delete(String name) {
		
		if (find(name) == -1) {
			System.out.println("���Ҳ���ָ�����ݣ�ɾ��ʧ��");
		} else {
			for(int i = find(name);i < elems; i++) {
				arr[i] = arr[i + 1];
			}
		}
		elems--;
	}
	
	// �޸�����
	public void change(Person oldPerson, Person newPerson) {
		if(find(oldPerson.getName()) == -1) {
			System.out.println("���Ҳ���ָ�����ݣ��޸�ʧ��");
		} else {
			arr[find(oldPerson.getName())] = newPerson; 
		}
	}
}
