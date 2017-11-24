package _TwoPointer.Other;

//  28. Implement strStr()
//  https://leetcode.com/problems/implement-strstr/description/
//  3:
public class _028_TwoPointer_Implement_strStr_E {
//------------------------------------------------------------------------------
    //1
    //    Elegant Java solution
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
    //2
    //    Share my accepted java solution
    public class Solution {
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
    //3
    // 9Ch
    public int strStr3(String source, String target) {
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

//------------------------------------------------------------------------------
}
/*
对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出
target 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。


您在真实的面试中是否遇到过这个题？ Yes
说明
在面试中我是否需要实现KMP算法？

不需要，当这种问题出现在面试中时，面试官很可能只是想要测试一下你的基础应用能力。当然你需要先跟面试官确认清楚要怎么实现这个题。
样例
如果 source = "source" 和 target = "target"，返回 -1。

如果 source = "abcdabcdefg" 和 target = "bcd"，返回 1。

挑战
O(n2)的算法是可以接受的。如果你能用O(n)的算法做出来那更加好。（提示：KMP）
 */