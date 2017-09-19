package chapter1;

import org.junit.Test;

public class _13strstr_my {
    public int strStr(String source, String target) {
        // write your code here

        if (source == null || target == null ||source.length() < target.length()){
            return -1;
        }
        //int j = 0;
        for (int i = 0; i < source.length() - target.length() + 1; i++){
            int j = 0;
            for (; j < target.length(); j++){
                if (source.charAt(i + j) != target.charAt(j)){
                    break;
                }
            }
            if(j == target.length()){
                return i;
            }
        }

        return -1;
    }
    
    @Test
    public void test01() {
    		String source = "";
    		String target = "";
    		System.out.println(strStr(source, target));
    		
    }
    @Test
    public void test02() {
    		String source = "abcdabcdefg";
    		String target = "bcd";
    		System.out.println(strStr(source, target));

    }
}