package src.ch01;

public class TestPersonArray {

	public static void main(String[] args) {
		PersonArray pa = new PersonArray();
		
		//��������
		Person p1 = new Person("����",20,"��");
		Person p2 = new Person("����",21,"��");
		Person p3 = new Person("����",22,"Ů");
		Person p4 = new Person("����",23,"��");
		Person p5 = new Person("��˹",24,"��");
		
		// �������
		pa.insert(p1);
		pa.insert(p2);
		pa.insert(p3);
		pa.insert(p4);
		pa.insert(p5);
		
		pa.display();
		
		// ɾ������
		pa.delete(p4);
		pa.display();
		
		pa.delete("��˹");
		pa.display();
		
		//�޸Ĳ���
		Person newPerson = new Person("������",40,"��");
		pa.change(p1, newPerson);
		pa.display();
	}

}
