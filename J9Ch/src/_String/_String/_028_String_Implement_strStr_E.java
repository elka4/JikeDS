package _String._String;
import java.util.*;
import org.junit.Test;

//  28. Implement strStr()
//  https://leetcode.com/problems/implement-strstr/
//  http://www.lintcode.com/problem/implement-strstr/
//
//对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。


public class _028_String_Implement_strStr_E {
//------------------------------------------------------------------------------
    //Elegant Java solution
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }
//------------------------------------------------------------------------------
    //Share my accepted java solution
    public class Solution2 {
        public int strStr(String haystack, String needle) {
            int l1 = haystack.length(), l2 = needle.length();
            if (l1 < l2) {
                return -1;
            } else if (l2 == 0) {
                return 0;
            }
            int threshold = l1 - l2;
            for (int i = 0; i <= threshold; ++i) {
                if (haystack.substring(i,i+l2).equals(needle)) {
                    return i;
                }
            }
            return -1;
        }
    }


//------------------------------------------------------------------------------
    //9Ch
    class Jiuzhang {
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

            for (int i = 0; i < source.length() - target.length() + 1; i++) {
                int j = 0;
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
    }

//------------------------------------------------------------------------------
}
/*
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Seen this question in a real interview before?   Yes  No
Companies
Facebook Microsoft Apple Pocket Gems
Related Topics
Two Pointers String
Similar Questions
Shortest Palindrome Repeated Substring Pattern
 */