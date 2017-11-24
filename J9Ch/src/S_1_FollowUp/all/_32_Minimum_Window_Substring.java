package S_1_FollowUp.all;

import org.junit.Test;

/** 32 Minimum Window Substring
 * Created by tianhuizhu on 6/28/17.
 */

/*
Given a string source and a string target, find the minimum window in source
which will contain all the characters in target.

 Notice

If there is no such window in source that covers all characters in target,
 return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will
always be only one unique minimum window in source.

Have you met this question in a real interview? Yes
Clarification
Should the characters in minimum window has the same order in target?

Not necessary.
Example
For source = "ADOBECODEBANC", target = "ABC", the minimum window is "BANC"
 */


public class _32_Minimum_Window_Substring {


    //方法一:
    int initTargetHash(int[] targethash, String Target) {
        int targetnum = 0;
        for (char ch : Target.toCharArray()) {
            targetnum++;
            targethash[ch]++;
        }
        return targetnum;
    }

    boolean valid(int[] sourcehash, int[] targethash) {

        for (int i = 0; i < 256; i++) {
            if (targethash[i] > sourcehash[i])
                return false;
        }
        return true;
    }

    public String minWindow(String Source, String Target) {
        // queueing the position that matches the char in T
        int ans = Integer.MAX_VALUE;
        String minStr = "";

        int[] sourcehash = new int[256];
        int[] targethash = new int[256];

        initTargetHash(targethash, Target);

        int j = 0, i = 0;

        for (i = 0; i < Source.length(); i++) {

            while (!valid(sourcehash, targethash) && j < Source.length()) {
                sourcehash[Source.charAt(j)]++;
                if (j < Source.length())
                    j++;
                else
                    break;
            }

            if (valid(sourcehash, targethash)) {
                if (ans > j - i) {
                    ans = Math.min(ans, j - i);
                    minStr = Source.substring(i, j);
                }
            }
            sourcehash[Source.charAt(i)]--;
        }
        return minStr;
    }


    @Test
    public void test01(){

        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

//------------------------------------------------------------------------------/////

        //方法二:
    int initTargetHash2(int[] targethash, String Target) {
        int targetnum = 0;
        for (char ch : Target.toCharArray()) {
            targetnum++;
            targethash[ch]++;
        }
        return targetnum;
    }

    public String minWindow2(String Source, String Target) {
        // queueing the position that matches the char in T
        int ans = Integer.MAX_VALUE;
        String minStr = "";

        int[] targethash = new int[256];

        int targetnum = initTargetHash2(targethash, Target);
        int sourcenum = 0;
        int j = 0, i = 0;
        for (i = 0; i < Source.length(); i++) {
            if (targethash[Source.charAt(i)] > 0)
                sourcenum++;

            targethash[Source.charAt(i)]--;
            while (sourcenum >= targetnum) {
                if (ans > i - j + 1) {
                    ans = Math.min(ans, i - j + 1);
                    minStr = Source.substring(j, i + 1);
                }
                targethash[Source.charAt(j)]++;
                if (targethash[Source.charAt(j)] > 0)
                    sourcenum--;
                j++;
            }
        }
        return minStr;
    }

    @Test
    public void test02(){

        System.out.println(minWindow2("ADOBECODEBANC", "ABC"));
    }



//------------------------------------------------------------------------------/////


}
