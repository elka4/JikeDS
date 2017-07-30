package ss;

import org.junit.Test;

public class Course05_2_ChangeTest {

	public void changePerson(Course05_2_Person p){

	    p.setName("BBB");
	}

	public void changeString01(String str){

	    str.replaceAll("Hello", "World");
	}

	public String changeString02(String str){
		str=str.replaceAll("Hello", "World");
		return str;
	}

	@Test
	public void testPerson(){
		Course05_2_Person p=new Course05_2_Person("AAA");
		changePerson(p);
		System.out.println(p.getName());
	}

	@Test
	public void testStr01(){
		String str="Hello";
		changeString01(str);
		System.out.println(str);
	}

	@Test
	public void testStr02(){
		String str="Hello";
		str=changeString02(str);
		System.out.println(str);
	}
}
