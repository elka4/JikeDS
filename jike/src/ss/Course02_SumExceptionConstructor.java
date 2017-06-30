package ss;
/**
 * 递归调用构造函数，并捕获异常
 */
public class Course02_SumExceptionConstructor {
public static int n;
public static int[] array;

public Course02_SumExceptionConstructor(int i){
	try {
		array[i]=array[i-1]+i;
		new Course02_SumExceptionConstructor(i+1);
	} catch (ArrayIndexOutOfBoundsException e) {
		System.out.println(array[n]);
		return;
	}
}
}
