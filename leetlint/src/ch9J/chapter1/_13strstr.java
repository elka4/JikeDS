package ch9J.chapter1;

import org.junit.Test;

public class _13strstr {
	/**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }
        //be careful with the +1
        for (int i = 0; i < source.length() - target.length() ; i++) {
            int j = 0;//need to define the j here because will use it out of for
            
            //j = 0 !!!!
            for (j = 0; j < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    break;
                }
            }
            // finished loop, target found
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
    }
    
    @Test
    public void testStrstr() {
    		String source = "";
    		String target = "";
    		System.out.println(strStr(source, target));
    		
    } 
}

/*
 * For a given source string and a target string, 
 * you should output the first index(from 0) of target string in source string.

If target does not exist in source, just return -1.

Example:

If source = "source" and target = "target", return -1.

If source = "abcdabcdefg" and target = "bcd", return 1.

Challenge 
O(n2) is acceptable. Can you implement an O(n) algorithm? (hint: KMP)

Tags : Basic Implementation String Facebook

 */