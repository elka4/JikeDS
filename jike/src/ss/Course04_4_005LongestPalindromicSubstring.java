package ss;

import org.junit.Test;

public class Course04_4_005LongestPalindromicSubstring {
	/**
	 判断字符串的某一部分是否回文
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
	 暴力求解法
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
	 中心扩展法
	 */
public String longestPalindrome02(String s){
	int maxLeft=0;
	int maxRight=0;
	int max=1;
	int n=s.length();
	for(int i=0;i<n;i++){
		//偶数长度的回文子串
		int start=i;
		int end=i+1;
		int len=0;
		//left、right是为了防止越界
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
		//奇数长度的回文子串
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
