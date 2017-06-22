package jike;

import org.junit.Test;

import java.util.Random;


public class Course03_1_TestInverse {
/**
 * 测试对象数组的逆置
 */
@Test
public void testPerson(){
	Course03_1_Person[] array=new Course03_1_Person[6];
	for(int i=0;i<6;i++){
		array[i]=new Course03_1_Person(i, "name"+i);
	}
	Course03_1_InverseArray<Course03_1_Person> ia =
			new Course03_1_InverseArray<>();
	ia.printArray(array);
	ia.inverse(array);
	ia.printArray(array);
}
/**
 * 测试整型数组的逆置
 */
@Test
public void testInteger(){
	Integer[] array=new Integer[7];
	Random ra=new Random();
	for(int i=0;i<7;i++){
		array[i]=ra.nextInt(10);
	}
	Course03_1_InverseArray<Integer> ia=new Course03_1_InverseArray<>();
	ia.printArray(array);
	ia.inverse(array);
	ia.printArray(array);
}
}
