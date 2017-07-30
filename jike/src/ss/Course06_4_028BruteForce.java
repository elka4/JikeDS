package ss;

import org.junit.Test;

/**
 *暴力匹配算法
 */
public class Course06_4_028BruteForce {

    public int strStr(String haystack, String needle) {
        if(needle.length()==0){
        	return 0;
        }
        int m=haystack.length();
        int n=needle.length();
        for(int i=0;i<m;i++){
        	int count=0;
        	for(int j=0;j<n&&i+j<m;j++){
        		if(haystack.charAt(i+j)!=needle.charAt(j)){
        			break;
        		}else{
        			count++;
        		}
        	}
        	if(count==n){
        		return i;
        	}
        }
        return -1;
    }

	@Test
	public void test(){
		String s="aabbccagdbbccdec";
		String p="bbccd";
		System.out.println(strStr(s, p));
		System.out.println(s.indexOf(p));
		p="bbccf";
		System.out.println(strStr(s, p));
		System.out.println(s.indexOf(p));
	}
}
