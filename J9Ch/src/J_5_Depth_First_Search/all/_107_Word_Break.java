package J_5_Depth_First_Search.all;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** 107 Word Break
 * Created by tianhuizhu on 6/28/17.
 */
public class _107_Word_Break {
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


    /*
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".
     */

    @Test
    public void test01(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "lintcode";
        String[] arr = {"code", "lint"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        System.out.println(wordBreak(s, wordDict));
    }

}
