package ibeifeng2.ch01;

public class Person {
	// �û�����
	private String name;

	// �û�����
	private int age;

	// �û��Ա�
	private String sex;

	public Person(String name, int age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void display() {
		System.out.print("����:" + this.name);
		System.out.print(".����:" + this.age);
		System.out.print(".�Ա�:" + this.sex);
		System.out.println();
	}

}
