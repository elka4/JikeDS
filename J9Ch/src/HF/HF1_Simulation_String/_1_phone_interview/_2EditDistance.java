package HF.HF1_Simulation_String._1_phone_interview;

/*
Example:
• s=“abcd” t=“abce”
• s=“abcd” t=“abcef”
output: true output: false
 • 两种one edit:
    1.增加/减少一位
    2.更改一位
 */

/*
思路:
• 想一想特殊情况 柿子要找软的捏
• 两字符串长度相差太大时?两个字符串长度一样?
• 三种情况:
1. 两个字符串长度差>1 直接false
2. 两个字符串长度差=0 只能有一位不同
3. 两个字符串长度差=1 只能插入/删除一个字符
 • 边界情况，特殊输入?
时间复杂度:O(n)
 */

/*
Company Tags: Facebook
考点:
– 很懵逼的情况下怎样突破 ->找特殊情况
– 分类讨论问题的情况
 */

/*
能力维度:
1. 理解问题
4. 逻辑思维/算法优化能力
5. 细节处理(corner case)
 */

public class _2EditDistance {
    //in class
    public boolean isOneEditDistance(String s, String t){
        if (s.length() > t.length()) {
            return isOneEditDistance(t,s);
        }
        int diff = t.length() - s.length();

        if (diff == 0) {
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    cnt++;
                }
            }
            return (cnt == 1);
        }
        if (diff == 1) {
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    return (s.substring(i).equals(t.substring(i + 1)));
                }
            }
        }
        return true;
    }

///////////////////////////////////////////////////////////////////
    //jiuzhang
    public class Solution1 {
        /**
         * @param s a string
         * @param t a string
         * @return true if they are both one edit distance apart or false
         */
        public boolean isOneEditDistance(String s, String t) {
            // Write your code here
            int m = s.length();
            int n = t.length();
            if (Math.abs(m - n) > 1)
                return false;

            if (m > n)
                return isOneEditDistance(t, s);
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    if (m == n) {
                        return s.substring(i + 1).equals(t.substring(i + 1));
                    }
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
            return m != n;
        }
    }

    // version: 高频题班
    public class Solution2 {
        /**
         * @param s a string
         * @param t a string
         * @return true if they are both one edit distance apart or false
         */
        public boolean isOneEditDistance(String s, String t) {
            // Write your code here
            if (s.length() > t.length()) {
                return isOneEditDistance(t, s);
            }
            int diff = t.length() - s.length();

            if (diff > 1) {
                return false;
            }
            if (diff == 0) {
                int cnt = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (t.charAt(i) != s.charAt(i)) {
                        cnt++;
                    }
                }
                return (cnt == 1);
            }
            if (diff == 1) {
                for (int i = 0; i < s.length(); i++) {
                    if (t.charAt(i) != s.charAt(i)) {
                        return (s.substring(i).equals(t.substring(i + 1)));
                    }
                }
            }
            return true;
        }
    }




///////////////////////////////////////////////////////////////////


}

/*
Given two strings S and T, determine if they are both one edit distance apart.

Have you met this question in a real interview? Yes
Example
Given s = "aDb", t = "adb"
return true
 */
