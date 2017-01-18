package jike;

import org.junit.Test;

public class Course04_2_125ValidPalindrome {
/**
�жϵ����ַ��Ƿ��ǡ����ֻ���ĸ��
 */
public boolean isAlpha(char c){
	if((c>='0'&&c<='9')||(c>='a'&&c<='z')){
		return true;
	}else{
		return false;
	}
}
public boolean isPalindrome(String s) {
    if(s==null||s.length()<=1){
    	return true;
    }
    s=s.toLowerCase();
    for(int i=0,j=s.length()-1;i<j;i++,j--){
    	while(i<j&&!isAlpha(s.charAt(i))){
    		i++;
    	}
    	while(i<j&&!isAlpha(s.charAt(j))){
    		j--;
    	}
    	if(s.charAt(i)!=s.charAt(j)){
    		return false;
    	}
    }
    return true;
}
@Test
public void test(){
	System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
	System.out.println(isPalindrome("race a car"));
}
}
