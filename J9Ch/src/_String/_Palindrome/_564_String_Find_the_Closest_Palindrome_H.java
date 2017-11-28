package _String._Palindrome;
import java.util.*;
import org.junit.Test;

//  564. Find the Closest Palindrome
//  https://leetcode.com/problems/find-the-closest-palindrome/description/
//
//  6:
//
public class _564_String_Find_the_Closest_Palindrome_H {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/find-the-closest-palindrome/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Brute Force[Time Limit Exceeded]
    public class Solution1 {
        public String nearestPalindromic(String n) {
            long num = Long.parseLong(n);
            for (long i = 1;; i++) {
                if (isPalindrome(num - i))
                    return "" + (num - i);
                if (isPalindrome(num + i))
                    return "" + (num + i);
            }
        }
        boolean isPalindrome(long x) {
            long t = x, rev = 0;
            while (t > 0) {
                rev = 10 * rev + t % 10;
                t /= 10;
            }
            return rev == x;
        }
    }



//------------------------------------------------------------------------------
    //2
    //Approach #2 Using Math[Accepted]
    public class Solution2 {
        public String mirroring(String s) {
            String x = s.substring(0, (s.length()) / 2);
            return x + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") + new StringBuilder(x).reverse().toString();
        }
        public String nearestPalindromic(String n) {
            if (n.equals("1"))
                return "0";

            String a = mirroring(n);
            long diff1 = Long.MAX_VALUE;
            diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));
            if (diff1 == 0)
                diff1 = Long.MAX_VALUE;

            StringBuilder s = new StringBuilder(n);
            int i = (s.length() - 1) / 2;
            while (i >= 0 && s.charAt(i) == '0') {
                s.replace(i, i + 1, "9");
                i--;
            }
            if (i == 0 && s.charAt(i) == '1') {
                s.delete(0, 1);
                int mid = (s.length() - 1) / 2;
                s.replace(mid, mid + 1, "9");
            } else
                s.replace(i, i + 1, "" + (char)(s.charAt(i) - 1));
            String b = mirroring(s.toString());
            long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));


            s = new StringBuilder(n);
            i = (s.length() - 1) / 2;
            while (i >= 0 && s.charAt(i) == '9') {
                s.replace(i, i + 1, "0");
                i--;
            }
            if (i < 0) {
                s.insert(0, "1");
            } else
                s.replace(i, i + 1, "" + (char)(s.charAt(i) + 1));
            String c = mirroring(s.toString());
            long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));

            if (diff2 <= diff1 && diff2 <= diff3)
                return b;
            if (diff1 <= diff3 && diff1 <= diff2)
                return a;
            else
                return c;
        }
    }



//------------------------------------------------------------------------------
    //3
    //Java solution with full explaination
    public class Solution3 {
        public String nearestPalindromic(String n) {
            Long number = Long.parseLong(n);
            Long big = findHigherPalindrome(number + 1);
            Long small = findLowerPalindrome(number - 1);
            return Math.abs(number - small) > Math.abs(big - number) ? String.valueOf(big) : String.valueOf(small);
        }
        public Long findHigherPalindrome(Long limit) {
            String n = Long.toString(limit);
            char[] s = n.toCharArray(); // limit
            int m = s.length;
            char[] t = Arrays.copyOf(s, m); // target
            for (int i = 0; i < m / 2; i++) {
                t[m - 1 - i] = t[i];
            }
            for (int i = 0; i < m; i++) {
                if (s[i] < t[i]) {
                    return Long.parseLong(String.valueOf(t));
                } else if (s[i] > t[i]) {
                    for (int j = (m - 1) / 2; j >= 0; j--) {
                        if (++t[j] > '9') {
                            t[j] = '0';
                        } else {
                            break;
                        }
                    }
                    // make it palindrome again
                    for (int k = 0; k < m / 2; k++) {
                        t[m - 1 - k] = t[k];
                    }
                    return Long.parseLong(String.valueOf(t));
                }
            }
            return Long.parseLong(String.valueOf(t));
        }
        public Long findLowerPalindrome(Long limit) {
            String n = Long.toString(limit);
            char[] s = n.toCharArray();
            int m = s.length;
            char[] t = Arrays.copyOf(s, m);
            for (int i = 0; i < m / 2; i++) {
                t[m - 1 - i] = t[i];
            }
            for (int i = 0; i < m; i++) {
                if (s[i] > t[i]) {
                    return Long.parseLong(String.valueOf(t));
                } else if (s[i] < t[i]) {
                    for (int j = (m - 1) / 2; j >= 0; j--) {
                        if (--t[j] < '0') {
                            t[j] = '9';
                        } else {
                            break;
                        }
                    }
                    if (t[0] == '0') {
                        char[] a = new char[m - 1];
                        Arrays.fill(a, '9');
                        return Long.parseLong(String.valueOf(a));
                    }
                    // make it palindrome again
                    for (int k = 0; k < m / 2; k++) {
                        t[m - 1 - k] = t[k];
                    }
                    return Long.parseLong(String.valueOf(t));
                }
            }
            return Long.parseLong(String.valueOf(t));
        }
    }

//------------------------------------------------------------------------------
    //4
    //Java solution with detailed proof
    class Solution4{
        public String nearestPalindromic(String n) {
            char[] arr = n.toCharArray();
            for (int i = 0, j = arr.length - 1; i < j; i++, j--) arr[j] = arr[i];

            String curP = String.valueOf(arr);
            String preP = nearestPalindrom(curP, false);
            String nextP = nearestPalindrom(curP, true);

            long num = Long.valueOf(n);
            long cur = Long.valueOf(curP);
            long pre = Long.valueOf(preP);
            long next = Long.valueOf(nextP);

            long d1 = Math.abs(num - pre);
            long d2 = Math.abs(num - cur);
            long d3 = Math.abs(num - next);

            if (num == cur) {
                return d1 <= d3 ? preP : nextP;
            } else if (num > cur) {
                return d2 <= d3 ? curP : nextP;
            } else {
                return d1 <= d2 ? preP : curP;
            }
        }

        private String nearestPalindrom(String curP, boolean dir) {
            int k = curP.length() >> 1, p = curP.length() - k;
            int l = Integer.valueOf(curP.substring(0, p));
            l += (dir ? 1 : -1);

            if (l == 0) return k == 0 ? "0" : "9";

            StringBuilder left = new StringBuilder(String.valueOf(l));
            StringBuilder right = new StringBuilder(left).reverse();
            if (k > left.length()) right.append("9");

            return left.append(right.substring(right.length() - k)).toString();
        }
    }

//------------------------------------------------------------------------------
    //5
    //Concise Java Solution
    class Solution5{
    //    The following code attempts to simplify the logic by covering more cases. It is fine for this problem as the runtime is anyway O(1).

        long ans = 0, diff = Long.MAX_VALUE, n = 0;

        private void update(String val) {
            try {
                long tmp = Long.parseLong(val);
                if (tmp == n) return;
                if (Math.abs(tmp - n) < diff || (Math.abs(tmp - n) == diff && tmp < ans)) {
                    ans = tmp;
                    diff = Math.abs(tmp - n);
                }
            } catch (Exception e) { }
        }

        private void concat(long leftHalf) {
            String s = "" + leftHalf, rs = new StringBuilder(s).reverse().toString();
            update(s + rs); // abc -> abccba
            update(s + rs.substring(1)); // abc -> abcba
        }

        public String nearestPalindromic(String n) {
            this.n = Long.parseLong(n);
            diff = Long.MAX_VALUE;
            long leftHalf = Long.parseLong(n.substring(0, (n.length() + 1) / 2));
            concat(leftHalf - 1);
            concat((leftHalf - 1) * 10 + 9); // Handle 1, 1000, 100000, etc.
            concat(leftHalf);
            concat(leftHalf + 1);
            concat((leftHalf + 1) / 10); // Handle 9, 999, 99999, etc.
            return "" + ans;
        }
    }


//------------------------------------------------------------------------------
    //6
    //java solution
    public class Solution6 {
        public String nearestPalindromic(String n) {
            if (n.length() >= 2 && allNine(n)) {
                String s = "1";
                for (int i = 0; i < n.length() - 1; i++) {
                    s += "0";
                }
                s += "1";
                return s;
            }
            boolean isOdd = (n.length() % 2 != 0);
            String left = n.substring(0, (n.length() + 1) / 2);
            long[] increment = {-1, 0, +1};
            String ret = n;
            long minDiff = Long.MAX_VALUE;
            for (long i : increment) {
                String s = getPalindrom(Long.toString(Long.parseLong(left) + i), isOdd);
                if (n.length() >= 2 && (s.length() != n.length() || Long.parseLong(s) == 0)) {
                    s = "";
                    for (int j = 0; j < n.length() - 1; j++) {
                        s += "9";
                    }
                }
                long diff = s.equals(n) ? Long.MAX_VALUE : Math.abs(Long.parseLong(s) - Long.parseLong(n));
                if (diff < minDiff) {
                    minDiff = diff;
                    ret = s;
                }
            }
            return ret;
        }
        private String getPalindrom(String s, boolean isOdd) {
            String right = new StringBuilder(s).reverse().toString();
            return isOdd ? s.substring(0, s.length() - 1) + right : s + right;
        }
        private boolean allNine(String s) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '9') {
                    return false;
                }
            }
            return true;
        }
    }
//------------------------------------------------------------------------------
}
/*
Given an integer n, find the closest integer (not including itself), which is a palindrome.

The 'closest' is defined as absolute difference minimized between two integers.

Example 1:
Input: "123"
Output: "121"
Note:
The input n is a positive integer represented by string, whose length will not exceed 18.
If there is a tie, return the smaller one as answer.
Seen this question in a real interview before?   Yes  No

Companies
Yelp

Related Topics
String
 */