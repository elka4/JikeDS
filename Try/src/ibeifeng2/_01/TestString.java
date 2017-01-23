package ibeifeng2._01;

public class TestString {
	public static void main(String[] args) {
		String str = "ibeifeng";
		String subStr = str.substring(2,5);
		System.out.println(subStr);
		
		String str1 = "ibei";
		String str2 = "feng";
		System.out.println(str1 + str2);
		
		
		
		String testStr1 = new String("ibeifeng");
		String testStr2 = new String("ibeifeng");
		System.out.println(testStr1 == testStr2);
		System.out.println(testStr1.equals(testStr2));
		
	}
}