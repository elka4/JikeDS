package ss;

import org.junit.Test;

public class Course05_2_RefTest {
	@Test
	public void testObj(){
		Object obj=new Object();
		System.out.println(obj);
		obj=new Object();
		System.out.println(obj);
	}
	public void changeString(String str){
		str="World";
	}
	public void changePerson(Course05_2_Person p){
		p=new Course05_2_Person("BBB");
	}
	
	@Test
	public void testString(){
		String str="Hello";
		changeString(str);
		System.out.println(str);
	}
	@Test
	public void testPerson(){
		Course05_2_Person p=new Course05_2_Person("AAA");
		changePerson(p);
		System.out.println(p.getName());
	}

}
