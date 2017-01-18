package jike;

import org.junit.Test;

public class Course04_4_005LongestPalindromicSubstring {
/**
 �ж��ַ��ĳһ�����Ƿ����
 */
public boolean isPanlindrome(String s,int start,int end){
	for(int i=start,j=end;i<j;i++,j--){
		if(s.charAt(i)!=s.charAt(j)){
			return false;
		}
	}
	return true;
}
/**
������ⷨ
 */
public String longestPalindrome01(String s) {
    if(s==null||s.length()<=1){
    	return s;
    }else{
    	int n=s.length();
    	int max=0;
    	int from=0;
    	int to=1;
    	for(int i=0;i<n;i++){
    		for(int j=i;j<n;j++){
    			if(isPanlindrome(s, i, j)){
    				if(j-i+1>=max){
    					max=j-i+1;
    					from=i;
    					to=j;
    				}
    			}
    		}
    	}
    	return s.substring(from, to+1);
    }
}
/**
������չ��
 */
public String longestPalindrome02(String s){
	int maxLeft=0;
	int maxRight=0;
	int max=1;
	int n=s.length();
	for(int i=0;i<n;i++){
		//ż��ȵĻ����Ӵ�
		int start=i;
		int end=i+1;
		int len=0;
		//left��right��Ϊ�˷�ֹԽ��
		int left=start;
		int right=end;
		while(start>=0&&end<n){
			if(s.charAt(start)==s.charAt(end)){
				len=len+2;
				left=start;
				right=end;
				start--;
				end++;
			}else{
				break;
			}
		}
		if(len>max){
			maxLeft=left;
			maxRight=right;
			max=len;
		}
		//����ȵĻ����Ӵ�
		start=i-1;
		end=i+1;
		len=1;
		left=start;
		right=end;
		while(start>=0&&end<n){
			if(s.charAt(start)==s.charAt(end)){
				len=len+2;
				left=start;
				right=end;
				start--;
				end++;
			}else{
				break;
			}
		}
		if(len>max){
			maxLeft=left;
			maxRight=right;
			max=len;
		}
	}
	return s.substring(maxLeft, maxRight+1);
}
@Test
public void test01(){
	System.out.println(longestPalindrome01("ghlabcbatyi"));
	System.out.println(longestPalindrome01("ghlabccbatyi"));
}
@Test
public void test02(){
	System.out.println(longestPalindrome01("ghlabcbatyi"));
	System.out.println(longestPalindrome01("ghlabccbatyi"));
}
}
