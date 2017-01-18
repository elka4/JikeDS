package jike;

import org.junit.Test;

/**
 *�ж��ַ��Ƿ����
 */
public class Course04_1_PalindromeString {
public boolean isPalindrome(String str){
	if(str==null||str.length()<=1){
		return true;
	}
	for(int i=0,j=str.length()-1;i<j;i++,j--){
		if(str.charAt(i)!=str.charAt(j)){
			return false;
		}
	}
	return true;
}
@Test
public void test(){
	System.out.println(isPalindrome("abcdcba"));
	System.out.println(isPalindrome("abcdeba"));
	System.out.println(isPalindrome("abccba"));
	System.out.println(isPalindrome("abcd"));
	System.out.println(isPalindrome("�ｭ������ɳ�� ����ɳ��ǳˮ�� ��ˮǳ��ɳ���� ��ɳ���������"));
}
}
