package ss;

/**
 * 递归调用普通函数，并捕获异常
 */
public class Course02_SumExceptionMethod {
	private int n;
	private int[] array;

	public Course02_SumExceptionMethod() {

		super();
	}

	public Course02_SumExceptionMethod(int n) {
		super();
		this.n = n;
		array=new int[n+1];
	}

	public int sumMethod(int i){
		try {
			array[i]=array[i-1]+i;
			int k=sumMethod(i+1);
			return k;
		} catch (ArrayIndexOutOfBoundsException e) {//捕获到异常直接返回结果
			return array[n];
		}
	}

	/*@Test
	public void testMethod(){
		int n=100;
		Course02_SumExceptionMethod sem=new Course02_SumExceptionMethod(n);
		int sum=sem.sumMethod(1);
		System.out.println(sum);
	}*/

}
