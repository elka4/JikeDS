package HF.HF4_Search_DFS;

import org.junit.Test;

import java.util.*;

// DP

// Word Break
public class _12WordBreak {
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

        int maxLength = getMaxLength(dict);
        boolean[] canSegment = new boolean[s.length() + 1];

        canSegment[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            canSegment[i] = false;
            for (int lastWordLength = 1;
                 lastWordLength <= maxLength && lastWordLength <= i;
                 lastWordLength++) {
                if (!canSegment[i - lastWordLength]) {
                    continue;
                }
                String word = s.substring(i - lastWordLength, i);
                if (dict.contains(word)) {
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[s.length()];
    }

    @Test
    public void test01(){
        Set<String> dict = new HashSet<>();
        String s = "lintcode";
        dict.add("lint");
        dict.add("code");

        System.out.println(wordBreak(s, dict));
    }
}
/*
给出一个字符串s和一个词典，判断字符串s是否可以被空格切分成一个或多个出现在字典中的单词。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出

s = "lintcode"

dict = ["lint","code"]

返回 true 因为"lintcode"可以被空格切分成"lint code"
 */