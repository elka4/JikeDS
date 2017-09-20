package J_5_DP2;

import java.util.Set;

/*单序列动态规划
￼￼￼• state: f[i]表示前i个位置/数字/字符,第i个... 
• function: f[i] = f[j] ... j 是i之前的一个位置 
• initialize: f[0]..
• answer: f[n]..
• 一般answer是f(n)而不是f(n-1)
//最大的特点就是数组要开n + 1个，结果取第n个
 * 
 */

public class _01Word_Break {
	private int getMaxLength(Set<String> dict) {
	    int maxLength = 0;
	    for (String word : dict) {
	        maxLength = Math.max(maxLength, word.length());
	    }
	    return maxLength;
	}

public boolean wordBreak(String s, Set<String> dict) {
    if (s == null || s.length() == 0) {
        return true;
    }
    //这个确保了不会去找太长的string当作单词
    int maxLength = getMaxLength(dict);
    boolean[] canSegment = new boolean[s.length() + 1];

    canSegment[0] = true;
    //WHY <= here????
    for (int i = 1; i <= s.length(); i++) {
        canSegment[i] = false;
        for (int lastWordLength = 1;
                 lastWordLength <= maxLength && lastWordLength <= i;
                 lastWordLength++) {
            if (!canSegment[i - lastWordLength]) {
                continue;
            }
//0 means the 0 + 1 th char
//1 means the 1 + 1 th char
//index i - lastWordLength means the (i - lastWordLength + 1)th char
            
//i is excluded, so inclued is i-1+1 th char
            String word = s.substring(i - lastWordLength, i);
            if (dict.contains(word)) {
                canSegment[i] = true;
                break;
            }
        }
    }

    return canSegment[s.length()];
}
}

/*
 * Given a string s and a dictionary of words dict, determine if s can be 
 * break into a space-separated sequence of one or more dictionary words.

Have you met this question in a real interview? Yes
Example
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".

Tags 
String Dynamic Programming
 */